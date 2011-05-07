package se.bikku.javabooli;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.core.util.MultivaluedMapImpl;

import javax.ws.rs.core.MultivaluedMap;
import java.util.List;

/**
 * Wrapper for the Booli.se's public API.
 * <p/>
 * Example Make a search for listings near Jönköping and print the first one's address to console.
 * <p/>
 * Booli booli = Booli.new("my_caller_id", "my_secret_key");
 * List<Listing> listings = booli.search("Jönköping");
 * <p/>
 * System.out.println(listings.get(0).getStreetAddress());
 */
public class Booli {
  private static final String BASE_URL = "http://api.booli.se/listing/";

  private final String callerId;
  private final String key;

  /**
   * @param callerId the username you choosed when regestering at http://www.booli.se/api/
   * @param key      the api key
   */
  public Booli(String callerId, String key) {
    this.callerId = callerId;
    this.key = key;
  }

  /**
   * Search Booli's listings near a specified location.
   *
   * @param location a city or other location (Uppsala, Stockholm/Vasastan)
   * @return listings matching the search criteria
   */
  public List<Listing> search(String location) {
    ClientConfig config = new DefaultClientConfig();
    Client client = Client.create(config);

    WebResource webResource = client.resource(BASE_URL);
    MultivaluedMap<String, String> parameters = new MultivaluedMapImpl();

    parameters.add("format", "json");
    parameters.add("callerId", callerId);
    parameters.add("time", AuthenticationUtils.dateTimeISO8601());
    parameters.add("unique", AuthenticationUtils.uniqueString());
    String data = callerId + parameters.get("time").get(0) + key + parameters.get("unique").get(0);

    parameters.add("hash", AuthenticationUtils.shaHex(data));

    String json = webResource.path(location).queryParams(parameters).get(String.class);
    ResponseData container = new Gson().fromJson(json, ResponseData.class);

    return container.getBooli().getContent().getListings();
  }
}

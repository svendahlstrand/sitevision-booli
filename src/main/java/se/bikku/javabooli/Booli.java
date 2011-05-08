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

    String json = webResource.path(location).queryParams(defaultParameters()).get(String.class);
    Response response = new Gson().fromJson(json, Response.class);

    return response.getListings();
  }

  /**
   * Search Booli's listings near a specified location and limit the results.
   *
   * @param location a city or other location (Uppsala, Stockholm/Vasastan)
   * @param maxResults max number of listings you want back
   * @return listings matching the search criteria
   */
  public List<Listing> search(String location, Integer maxResults) {
    List<Listing> listings = search(location);

    if (maxResults == null || maxResults < 1 || listings.size() < maxResults) {
      return listings;
    }

    /*
     * TODO: Use as example!
     *
    Iterator<Listing> iterator = listings.iterator();
    List<Listing> newListings = new ArrayList<Listing>();

    for (int i = 0; (iterator.hasNext() && i < maxResults); i++)
    {
      newListings.add(iterator.next());
    }*/

    return listings.subList(0, maxResults);
  }

  /**
   * Default parameters with format and authentication.
   *
   * @return a map of parameters
   */
  MultivaluedMap<String, String> defaultParameters() {
    MultivaluedMap<String, String> parameters = new MultivaluedMapImpl();
    String time = AuthenticationUtils.dateTimeISO8601();
    String unique = AuthenticationUtils.uniqueString();

    // Use json format so when can parse the response with gson.
    parameters.add("format", "json");

    /*
     * Booli's API requires authentication, read more: http://www.booli.se/api/docs/#autentisering.
     */
    parameters.add("callerId", callerId);
    parameters.add("time", time);
    parameters.add("unique", unique);
    parameters.add("hash", hash(time, unique));

    return parameters;
  }

  /**
   * Booli's special hash thats needed for authentication, read more: http://www.booli.se/api/docs/#autentisering.
   *
   * @param time
   * @param unique
   * @return hash
   */
  String hash(String time, String unique) {
    return AuthenticationUtils.shaHex(callerId + time + key + unique);
  }
}

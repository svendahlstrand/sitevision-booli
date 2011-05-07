package se.bikku.javabooli;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.core.util.MultivaluedMapImpl;

import javax.ws.rs.core.MultivaluedMap;
import java.util.List;

public class Booli {
  private final String callerId;
  private final String key;
  private final String baseUrl = "http://api.booli.se/listing/";

  public Booli(String callerId, String key) {
    this.callerId = callerId;
    this.key = key;
  }

  public List<Listing> search(String city) {
    ClientConfig config = new DefaultClientConfig();
    Client client = Client.create(config);

    WebResource webResource = client.resource(baseUrl);
    MultivaluedMap<String, String> parameters = new MultivaluedMapImpl();

    parameters.add("format", "json");
    parameters.add("callerId", callerId);
    parameters.add("time", AuthenticationUtils.dateTimeISO8601());
    parameters.add("unique", AuthenticationUtils.uniqueString());
    String data = callerId + parameters.get("time").get(0) + key + parameters.get("unique").get(0);

    parameters.add("hash", AuthenticationUtils.shaHex(data));

    String json = webResource.path(city).queryParams(parameters).get(String.class);
    ResponseData container = new Gson().fromJson(json, ResponseData.class);

    return container.getBooli().getContent().getListings();
  }
}

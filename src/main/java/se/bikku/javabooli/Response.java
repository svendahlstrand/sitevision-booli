package se.bikku.javabooli;

import java.util.List;

/**
 * Represents the response from Booli's REST-API. We are really just intressted in the getListings() method.
 * 
 * The inner classes of Response is needed for gson to convert json to Java objects correctly.
 */
public class Response {
  private BooliData booli;

  /**
   * @return all the listings
   */
  public List<Listing> getListings() {
    return booli.content.listings;
  }

  public static class BooliData {
    private Content content;
  }

  public static class Content {
    private List<Listing> listings;
  }
}

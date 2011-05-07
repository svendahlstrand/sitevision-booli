package se.bikku.javabooli;

import java.util.List;

/*
 * These classes exist for the purpose of matching the nestled Booli API.
 */
public class Response {
  private BooliData booli;

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

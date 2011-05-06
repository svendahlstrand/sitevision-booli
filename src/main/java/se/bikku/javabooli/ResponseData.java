package se.bikku.javabooli;

import java.util.List;

public class ResponseData {
  private BooliData booli;

  public BooliData getBooli() {
    return booli;
  }

  public static class BooliData {
    private Content content;

    public Content getContent() {
      return content;
    }

  }

  public static class Content {
    private List<Listing> listings;

    public List<Listing> getListings() {
      return listings;
    }
  }
}

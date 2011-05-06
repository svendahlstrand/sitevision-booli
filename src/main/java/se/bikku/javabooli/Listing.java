package se.bikku.javabooli;

public class Listing {
  private String created;
  private String streetAddress;
  private String city;
  private String nRooms;
  private String priceForSale;
  private String listingUrl;
  private Images images;

  public Listing() {
  }

  public Listing(String streetAddress, String city, String numberOfRooms, String priceForSale) {
    this.streetAddress = streetAddress;
    this.city = city;
    this.nRooms = numberOfRooms;
    this.priceForSale = priceForSale;
  }

  public String getPriceForSale() {
    return priceForSale;
  }

  public String getStreetAddress() {
    return streetAddress;
  }

  public String getCity() {
    return city;
  }

  public String getNumberOfRooms() {
    return nRooms;
  }

  public String getImageUrl() {
    return getImageUrl(0, 0);
  }

  public String getImageUrl(Integer longestSide) {

    return getImageUrl(longestSide, longestSide);
  }

  public String getImageUrl(Integer width, Integer height) {
    if (images == null)
    {
      return null;
    }
    return images.getImage().getUrl().replaceAll("0x0", width + "x" + height);
  }

  public String getListingUrl() {
    return listingUrl;
  }

  public static class Images {
    private Image image;

    public Image getImage() {
      return image;
    }

  }

  public static class Image {
    private String url;

    public String getUrl() {
      return url;
    }

  }
}

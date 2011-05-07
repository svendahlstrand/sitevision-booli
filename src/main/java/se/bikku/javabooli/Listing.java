package se.bikku.javabooli;

public class Listing {
  private String created;
  private String nRooms;
  private String priceForSale;
  private String listingUrl;
  private String areaLiving;
  private Images images;
  private Location location;

  public Listing() {
  }

  public Listing(String streetAddress, String city, String numberOfRooms, String priceForSale) {
    this.nRooms = numberOfRooms;
    this.priceForSale = priceForSale;
  }

  public String getPriceForSale() {
    return priceForSale;
  }

  public String getStreetAddress() {
    return location.getAddress().streetAddress;
  }

  public String getCity() {
    return location.getAddress().city;
  }

  public String getNumberOfRooms() {
    return nRooms;
  }

  public boolean hasImage() {
    return true;
  }

  public String getImageUrl() {
    return getImageUrl(0, 0);
  }

  public String getImageUrl(Integer longestSide) {

    return getImageUrl(longestSide, longestSide);
  }

  public String getImageUrl(Integer width, Integer height) {
    if (images == null) {
      return null;
    }
    return images.getImage().getUrl().replaceAll("0x0", width + "x" + height);
  }

  public String getUrl() {
    return listingUrl;
  }

  public String getLivingArea() {
    return areaLiving;
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

  public static class Location {
    private Address address;

    public Address getAddress() {
      return address;
    }

  }

  public static class Address {
    private String city;
    private String streetAddress;
  }
}

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
    return location.address.streetAddress;
  }

  public String getCity() {
    return location.address.city;
  }

  public String getNumberOfRooms() {
    return floor(nRooms);
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
    return images.image.url.replaceAll("0x0", width + "x" + height);
  }

  public String getUrl() {
    return listingUrl;
  }

  public String getLivingArea() {
    return floor(areaLiving);
  }

  private String floor(String number) {
    return number.endsWith(".0") ? number.substring(0, number.length() - 2) : number;
  }


  /*
   * The following classes exists to deal with the nestled Booli API.
   */
  public static class Images {
    private Image image;
  }

  public static class Image {
    private String url;
  }

  public static class Location {
    private Address address;
  }

  public static class Address {
    private String city;
    private String streetAddress;
  }
}

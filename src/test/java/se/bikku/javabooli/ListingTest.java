package se.bikku.javabooli;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ListingTest {
  private Listing listing;

  @Before public void setUp() {
    listing = new Listing("Storgatan", "Småstaden", "3.0", "1500000");
  }

  @Test
  public void shouldHaveStreetAddress() {
    assertEquals("Storgatan", listing.getStreetAddress());
  }

  @Test
  public void shouldHaveCity() {
    assertEquals("Småstaden", listing.getCity());
  }

  @Test
  public void shouldHaveNumberOfRooms() {
    assertEquals("3.0", listing.getNumberOfRooms());
  }

  @Test
  public void shouldHavePriceForSale() {
    assertEquals("1500000", listing.getPriceForSale());
  }
}

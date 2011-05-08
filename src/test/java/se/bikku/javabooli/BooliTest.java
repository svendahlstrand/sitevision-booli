package se.bikku.javabooli;

import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.MultivaluedMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BooliTest {
  private Booli booli;

  @Before
  public void setUp() throws Exception {
    booli = new Booli("me", "my_key");
  }

  @Test
  public void shouldHashCorrectly() throws Exception {
    String hash = booli.hash("2004-02-12T15:19:21", "abcdefghijklmnop");
    assertEquals("a085cf6781cdd103eea12d836388fce5c683acbf", hash);
  }

  @Test
  public void shouldHaveDefaultParameters() throws Exception {
    MultivaluedMap<String, String> parameters = booli.defaultParameters();

    assertEquals("me", parameters.getFirst("callerId"));
    assertEquals("json", parameters.getFirst("format"));

    assertTrue(parameters.containsKey("time"));
    assertTrue(parameters.containsKey("unique"));
    assertTrue(parameters.containsKey("hash"));
  }
}

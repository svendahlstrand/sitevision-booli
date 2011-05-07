package se.bikku.javabooli;

import org.junit.Test;
import java.util.regex.Pattern;
import static org.junit.Assert.*;

public class AuthenticationUtilsTest {
  @Test
  public void shouldGenerateValidShaHex()
  {
    assertEquals("a9993e364706816aba3e25717850c26c9cd0d89d", AuthenticationUtils.shaHex("abc"));

    assertEquals(
      "84983e441c3bd26ebaae4aa1f95129e5e54670f1",
      AuthenticationUtils.shaHex("abcdbcdecdefdefgefghfghighijhijkijkljklmklmnlmnomnopnopq"));
  }

  @Test
  public void shouldThrowRuntimeExceptionWhenBogusAlgorithmIsUsed()
  {
    try {
      AuthenticationUtils.getMessageDigest("Bogus Bogus");
      fail("A RuntimeException should have been thrown.");
    } catch (RuntimeException e) {
      // Expected exception.
    }
  }

  @Test
  public void shouldGenerateUniqueStrings() throws Exception {
    String one = AuthenticationUtils.uniqueString();
    String another = AuthenticationUtils.uniqueString();

    assertFalse("Two unique strings should not be equal: " + one + ".", one.equals(another));
  }

  @Test
  public void shouldGenerate16CharactersUniqueStrings() throws Exception {
    String uniqueString = AuthenticationUtils.uniqueString();

    assertEquals(16, uniqueString.length());
  }

  @Test
  public void shouldDeliverDateAndTimeInISO8601Format() throws Exception {
    String time = AuthenticationUtils.dateTimeISO8601();

    String regex = "([0-9]{4})(-([0-9]{2})(-([0-9]{2})(T([0-9]{2}):([0-9]{2})(:([0-9]{2})(\\.([0-9]+))?)?(Z|(([-+])([0-9]{2}):([0-9]{2})))?)?)?)?";
    Boolean isMatch = Pattern.matches(regex, time);

    assertTrue("This is not a valid date and time: " + time + ".", isMatch);
  }
}

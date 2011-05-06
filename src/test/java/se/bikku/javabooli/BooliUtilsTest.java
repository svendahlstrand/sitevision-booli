package se.bikku.javabooli;

import org.junit.Test;
import java.util.regex.Pattern;
import static org.junit.Assert.*;

public class BooliUtilsTest {
  @Test
  public void shouldGenerateValidShaHex()
  {
    String data = "myteststring";
    String validHash = "01839df24747e474228c147818bc6e9e667d6fb6";

    assertEquals(validHash, BooliUtils.shaHex(data));
  }

  @Test
  public void shouldGenerateUniqueStrings() throws Exception {
    String one = BooliUtils.uniqueString();
    String another = BooliUtils.uniqueString();

    assertFalse("Two unique strings should not be equal: " + one + ".", one.equals(another));
  }

  @Test
  public void shouldGenerate16CharactersUniqueStrings() throws Exception {
    String uniqueString = BooliUtils.uniqueString();

    assertEquals(16, uniqueString.length());
  }

  @Test
  public void shouldDeliverDateAndTimeInISO8601Format() throws Exception {
    String time = BooliUtils.dateTimeISO8601();

    String regex = "([0-9]{4})(-([0-9]{2})(-([0-9]{2})(T([0-9]{2}):([0-9]{2})(:([0-9]{2})(\\.([0-9]+))?)?(Z|(([-+])([0-9]{2}):([0-9]{2})))?)?)?)?";
    Boolean isMatch = Pattern.matches(regex, time);

    assertTrue("This is not a valid date and time: " + time + ".", isMatch);
  }
}

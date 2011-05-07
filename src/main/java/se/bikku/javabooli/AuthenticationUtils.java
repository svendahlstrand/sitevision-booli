package se.bikku.javabooli;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Operations to simplify the authentication to Booli.se's API.
 */
public class AuthenticationUtils {
  /**
   * Calculates the SHA-1 digest and returns the value as a hex string.
   *
   * @param data Data to digest
   * @return SHA-1 digest as a hex string
   */
  public static String shaHex(String data)
  {
    MessageDigest messageDigest = getMessageDigest("SHA-1");

    byte[] digest = messageDigest.digest(data.getBytes());

    StringBuffer hexString = new StringBuffer();

    for (byte aDigest : digest) {
      hexString.append(Integer.toHexString(0x100 | 0xFF & aDigest).substring(1));
    }

    return hexString.toString();
  }

  /**
   * An unique string of 16 characters.
   *
   * @return unique string
   */
  public static String uniqueString()
  {
    String random =  UUID.randomUUID().toString();
    random = random.replaceAll("-", "");
    random = random.substring(0, 16);

    return random;
  }

  /**
   * The current date and time formatted as ISO 8601.
   *
   * @return current date time as ISO 8601
   */
  public static String dateTimeISO8601() {
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    Date date = new Date();

    return dateFormat.format(date);
  }

  /**
  * Returns a MessageDigest for the given algorithm.
  *
  * @param algorithm the name of the algorithm requested.
  * @return A MessageDigest instance
  * @throws RuntimeException
  */
  static MessageDigest getMessageDigest(String algorithm) {
    try {
      return MessageDigest.getInstance(algorithm);
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException(e.getMessage());
    }
  }
}

package se.bikku.javabooli;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class BooliUtils {
  public static String shaHex(String data)
  {
    MessageDigest messageDigest;

    try
    {
      messageDigest = MessageDigest.getInstance("SHA-1");
    }
    catch (NoSuchAlgorithmException e)
    {
      return "";
    }

    byte[] digest = messageDigest.digest(data.getBytes());

    StringBuffer hexString = new StringBuffer();

    for (byte aDigest : digest) {
      hexString.append(Integer.toHexString(0x100 | 0xFF & aDigest).substring(1));
    }

    return hexString.toString();
  }

  public static String uniqueString()
  {
    String random =  UUID.randomUUID().toString();
    random = random.replaceAll("-", "");
    random = random.substring(0, 16);

    return random;
  }

  public static String dateTimeISO8601() {
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    Date date = new Date();

    return dateFormat.format(date);
  }
}

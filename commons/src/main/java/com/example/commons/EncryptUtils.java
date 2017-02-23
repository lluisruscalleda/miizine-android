package com.example.commons;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import timber.log.Timber;

public final class EncryptUtils {

  private EncryptUtils() {
  }

  public static String toMd5(final String s) {
    try {
      // Create MD5 Hash
      MessageDigest digest = MessageDigest.getInstance("MD5");
      digest.update(s.getBytes());
      byte[] messageDigest = digest.digest();

      // Create Hex String
      StringBuilder hexString = new StringBuilder();
      for (byte aMessageDigest : messageDigest) {
        String h = Integer.toHexString(0xFF & aMessageDigest);
        while (h.length() < 2) {
          h = "0" + h;
        }
        hexString.append(h);
      }
      return hexString.toString();
    } catch (NoSuchAlgorithmException e) {
      Timber.e(e, "NoSuchAlgorithmException");
    }
    return "";
  }
}

package com.example.commons;

/**
 * Utils class to handle log operations
 */
public final class LogUtils {

  private static final String LOG_PREFIX = "android_";
  private static final int LOG_PREFIX_LENGTH = LOG_PREFIX.length();
  private static final int MAX_LOG_TAG_LENGTH = 23;

  private LogUtils() {
  }

  public static String makeLogTag(String str) {
    if (str.length() > MAX_LOG_TAG_LENGTH - LOG_PREFIX_LENGTH) {
      return LOG_PREFIX + str.substring(0, MAX_LOG_TAG_LENGTH - LOG_PREFIX_LENGTH - 1);
    }

    return LOG_PREFIX + str;
  }

}

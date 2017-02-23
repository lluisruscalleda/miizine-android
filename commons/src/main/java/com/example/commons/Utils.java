package com.example.commons;

import java.util.Collection;

/**
 * Utility to handle general operations
 */
public final class Utils {

  private Utils() {
  }

  public static boolean isNotBlank(String input) {
    return !isBlank(input);
  }

  public static boolean isBlank(String input) {
    return input == null || input.trim().isEmpty();
  }

  public static <T> boolean isEmpty(T[] array) {
    return array == null || array.length == 0;
  }

  public static <T> boolean isEmpty(Collection<T> collection) {
    return collection == null || collection.isEmpty();
  }
}

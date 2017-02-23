package com.example.commons;

import android.text.Html;
import android.text.Spanned;
import java.util.regex.Pattern;

import static com.example.commons.Utils.isNotBlank;

/**
 * Utility to manage all string related operations
 */
public final class StringUtils {

  public static final String HTML_BR_TAG;
  public static final String HTML_LI_TAG_UNICODE_REPLACEMENT;
  public static final String HTML_LI_TAG_REGEX;
  public static final Pattern HTML_LI_TAG_PATTERN;
  public static final Pattern LINE_SEPARATOR_PATTERN;

  static {
    HTML_BR_TAG = "<br/>";
    HTML_LI_TAG_UNICODE_REPLACEMENT = "\\n&#149;";
    HTML_LI_TAG_REGEX = "<\\s*li\\s*>";
    HTML_LI_TAG_PATTERN = Pattern.compile(HTML_LI_TAG_REGEX, Pattern.CASE_INSENSITIVE);
    LINE_SEPARATOR_PATTERN = Pattern.compile(System.getProperty("line.separator"), Pattern.CASE_INSENSITIVE);
  }

  private StringUtils() {
  }

  private static String safeString(String source) {
    return isNotBlank(source) ? source : "";
  }

  public static Spanned fromHtml(String source) {
    return Html.fromHtml(replaceLineSeparatorWithBRtag(replaceLItagWithUnicode(safeString(source))));
  }

  public static String replaceLineSeparatorWithBRtag(String input) {
    return input == null ? "" : LINE_SEPARATOR_PATTERN.matcher(input).replaceAll(HTML_BR_TAG);
  }

  public static String replaceLItagWithUnicode(String input) {
    return input == null ? "" : HTML_LI_TAG_PATTERN.matcher(input).replaceAll(HTML_LI_TAG_UNICODE_REPLACEMENT);
  }
}

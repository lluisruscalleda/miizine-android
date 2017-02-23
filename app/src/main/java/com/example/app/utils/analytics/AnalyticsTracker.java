package com.example.app.utils.analytics;

/**
 * Interface that represents an Analytics tracker ie {@link GoogleAnalyticsController}
 */
public interface AnalyticsTracker {

  /**
   * Returns name of all controllers
   */
  String getName();

  /**
   * Process Analytics Event by all controllers
   *
   * @param event Event to be handled
   */
  void handleEvent(Event event);

  /**
   * Process Analytics Screen change by all controllers
   *
   * @param screenName Name of the screen to be changed
   */
  void handleScreenChange(String screenName);

  /**
   * Ignore internal events
   *
   * @param event Event to be ignored
   */
  boolean shouldSkipEvent(Event event);

  /**
   * Returns screen name
   */
  String getScreen();
}

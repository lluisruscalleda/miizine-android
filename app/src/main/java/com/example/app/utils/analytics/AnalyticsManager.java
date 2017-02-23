package com.example.app.utils.analytics;
/*

import android.app.Application;
import com.google.android.gms.analytics.ecommerce.Product;
import com.google.android.gms.analytics.ecommerce.ProductAction;
import javax.inject.Inject;
import javax.inject.Singleton;

*/
/**
 * Analytics facade to allow multiple analytics tools
 *//*

@Singleton
public final class AnalyticsManager {

  private GoogleAnalyticsController googleAnalytics;

  @Inject
  public AnalyticsManager(Application application) {

    googleAnalytics = new GoogleAnalyticsController(application);
  }

  public void setScreen(String screenName) {

    googleAnalytics.handleScreenChange(screenName);
  }

  public void track(Event event) {

    googleAnalytics.handleEvent(event);
  }

  public void addTransaction(Product product, ProductAction productAction) {

    googleAnalytics.handleTransaction(product, productAction);
  }
}
*/

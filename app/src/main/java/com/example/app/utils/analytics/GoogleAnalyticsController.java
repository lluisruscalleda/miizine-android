package com.example.app.utils.analytics;

/*
import android.app.Application;
import com.example.app.R;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.google.android.gms.analytics.ecommerce.Product;
import com.google.android.gms.analytics.ecommerce.ProductAction;
import java.util.Map;

*/
/**
 * Google Analytics implementation of Analytics Tracker
 *//*

public final class GoogleAnalyticsController implements AnalyticsTracker {

  static GoogleAnalytics analytics;
  static Tracker tracker;

  private String screenName;

  public GoogleAnalyticsController(Application application) {
    analytics = GoogleAnalytics.getInstance(application);
    tracker = analytics.newTracker(R.xml.app_tracker);
  }

  @Override
  public String getName() {
    return "GoogleAnalyticsController";
  }

  @Override
  public void handleEvent(Event event) {
    if (shouldSkipEvent(event)) {
      return;
    }
    mapEvent(event);
  }

  public void handleTransaction(Product product, ProductAction productAction) {
    if (product == null || productAction == null) {
      return;
    }
    HitBuilders.ScreenViewBuilder builder = new HitBuilders.ScreenViewBuilder().addProduct(product).setProductAction(
        productAction);
    tracker.setScreenName("transaction");
    tracker.send(builder.build());
  }

  @Override
  public void handleScreenChange(String screenName) {
    this.screenName = screenName;
    tracker.setScreenName(screenName);
  }

  @Override
  public boolean shouldSkipEvent(Event event) {
    return "internal".equalsIgnoreCase(event.getType());
  }

  @Override
  public String getScreen() {
    return this.screenName;
  }

  private void mapEvent(Event event) {
    HitBuilders.EventBuilder builder =
        new HitBuilders.EventBuilder().setCategory(event.getType()).setAction(event.getName());

    if (event.getValue() != null) {
      builder.setLabel(event.getValue());
    }

    builder = mapPayload(builder, event.getPayload());

    tracker.send(builder.build());
  }

  private static HitBuilders.EventBuilder mapPayload(
      HitBuilders.EventBuilder builder, Map<String, String> payload
  ) {
    if (payload.containsKey("subscription_duration")) {
      builder.setCustomDimension(0, payload.get("subscription_duration"));
    }
    return builder;
  }
}
*/

package com.example.app.utils;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import com.example.app.AndroidApp;
import timber.log.Timber;

/**
 * Utils class to handle App Rating operations
 */
public final class RateUtils {

  private RateUtils() {
  }

  public static void goToMarketPlaceForRating(
      @NonNull
      Activity activity
  ) {
    try {
      activity.startActivity(new Intent("android.intent.action.VIEW",
                                        Uri.parse("market://details?id=" + AndroidApp.get(activity).getPackageName())
      ));
    } catch (Exception e) {
      Timber.e(e, "Failed to start view intent to open up the market place for rating");
    }
  }
}

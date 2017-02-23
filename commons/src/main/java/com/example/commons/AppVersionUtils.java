package com.example.commons;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import timber.log.Timber;

public final class AppVersionUtils {

  private AppVersionUtils() {
  }

  /**
   * get the application version
   *
   * @return version String
   */

  public static String getAppVersion(Context context) {
    String version = "";
    try {
      PackageManager manager = context.getPackageManager();
      PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
      version = info.versionName;
    } catch (Exception e) {
      Timber.e(e, "Error getting version");
    }

    return version;
  }
}

package com.example.app;

import android.app.Application;
import android.os.Build;
import android.os.StrictMode;
import android.util.Log;
import cat.ereza.customactivityoncrash.CustomActivityOnCrash;
import com.frogermcs.androiddevmetrics.AndroidDevMetrics;
import com.jakewharton.threetenabp.AndroidThreeTen;
import com.squareup.leakcanary.LeakCanary;
import timber.log.Timber;

class AppManager {

  private static final String DEBUG = "debug";
  private static final String STAGE = "stage";
  private static final String RELEASE = "release";

  private final Application application;
  private final String buildType;

  AppManager(Application application, String buildType) {
    this.buildType = buildType;
    this.application = application;
  }

  void init() {

    initCrashActivity();
    initLeakCanary();
    initThreetenAbp();

    switch (buildType) {
      case DEBUG:
        initTimber(new Timber.DebugTree());
        initDevMetrics();
        enableStrictMode();
        initStetho();
        break;
      case STAGE:
        enableStrictMode();
        initCrashReports();
        initTimber(new CrashReportingTree());
        break;
      case RELEASE:
        initCrashReports();
        initTimber(new CrashReportingTree());
        break;
      default:
        break;
    }
  }

  private void initThreetenAbp() {
    AndroidThreeTen.init(application);
  }

  private void initCrashActivity() {
    CustomActivityOnCrash.setShowErrorDetails(false);
    CustomActivityOnCrash.install(application);
  }

  private void initDevMetrics() {
    AndroidDevMetrics.initWith(application);
  }

  private void enableStrictMode() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
      StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectAll().penaltyLog().build());
      StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectAll()
                                     .penaltyLog()
                                     .penaltyDeathOnNetwork()
                                     .build());
    }
  }

  private void initCrashReports() {
/*    final Fabric fabric = new Fabric.Builder(application)
        .kits(new Crashlytics())
        .debuggable(BuildConfig.DEBUG)
        .build();
    Fabric.with(fabric);*/
  }

  private void initLeakCanary() {
    LeakCanary.install(application);
  }

  private void initStetho() {
/*    Stetho.initialize(
        Stetho.newInitializerBuilder(application)
            .enableDumpapp(Stetho.defaultDumperPluginsProvider(application))
            .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(application))
            .build()
    );*/
  }

  private void initTimber(Timber.Tree tree) {
    Timber.plant(tree);
  }

  /** A tree which logs important information for crash reporting. */
  private static class CrashReportingTree extends Timber.Tree {
    @Override
    protected void log(int priority, String tag, String message, Throwable t) {
      if (priority == Log.VERBOSE || priority == Log.DEBUG) {
        return;
      }

      //FakeCrashLibrary.log(priority, tag, message);

      if (t != null) {
        if (priority == Log.ERROR) {
          //FakeCrashLibrary.logError(t);
        } else if (priority == Log.WARN) {
          //FakeCrashLibrary.logWarning(t);
        }
      }
    }
  }
}

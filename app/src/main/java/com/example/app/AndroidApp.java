package com.example.app;

import android.app.Application;
import android.content.Context;
import com.example.app.di.components.AppComponent;
import com.example.app.di.components.DaggerAppComponent;
import com.example.app.di.modules.AppModule;

/**
 * Android Main Application
 */
public class AndroidApp extends Application {

  private AppComponent appComponent;
  private AppManager appManager;

  @Override
  public void onCreate() {
    super.onCreate();
    getAppManager().init();
  }

  public static AndroidApp get(Context context) {
    return (AndroidApp) context.getApplicationContext();
  }

  public AppComponent getComponent() {
    if (appComponent == null) {
      appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
    }
    return appComponent;
  }

  public void setComponent(AppComponent appComponent) {
    this.appComponent = appComponent;
  }

  public AppManager getAppManager() {
    if (appManager == null) {
      appManager = new AppManager(this, BuildConfig.BUILD_TYPE);
    }
    return appManager;
  }

  public void setAppManager(AppManager appManager) {
    this.appManager = appManager;
  }
}
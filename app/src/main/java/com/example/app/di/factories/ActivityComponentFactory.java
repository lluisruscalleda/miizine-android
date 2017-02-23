package com.example.app.di.factories;

import android.app.Activity;
import com.example.app.AndroidApp;
import com.example.app.di.components.ActivityComponent;
import com.example.app.di.modules.ActivityModule;

public final class ActivityComponentFactory {

  private ActivityComponentFactory() {
    //no op
  }

  public static ActivityComponent create(Activity activity) {
    return AndroidApp.get(activity).getComponent().plus(new ActivityModule(activity));
  }
}
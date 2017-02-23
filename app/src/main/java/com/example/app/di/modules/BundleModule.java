package com.example.app.di.modules;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import dagger.Module;
import dagger.Provides;

@Module
public class BundleModule {

  @Provides
  Bundle provideBundle(Activity context) {
    return context.getIntent().getExtras() == null ? new Bundle() : context.getIntent().getExtras();
  }

  @Provides
  Intent provideIntent(Activity context) {
    return context.getIntent() == null ? new Intent() : context.getIntent();
  }
}
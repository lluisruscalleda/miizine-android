package com.example.app.di.modules;

import android.app.Application;
import android.content.Context;

import com.example.app.AndroidApp;
import com.example.app.UIThread;
import com.example.data.executor.JobExecutor;
import com.example.domain.executor.PostExecutionThread;
import com.example.domain.executor.ThreadExecutor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger module that provides objects which will live during the application lifecycle.
 */
@Module
public class AppModule {
  private final AndroidApp application;

  public AppModule(AndroidApp application) {
    this.application = application;
  }

  @Provides
  @Singleton
  Application provideApplication() {
    return this.application;
  }

  @Provides
  @Singleton
  Context provideApplicationContext() {
    return this.application.getApplicationContext();
  }

  @Provides
  @Singleton
  ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
    return jobExecutor;
  }

  @Provides
  @Singleton
  PostExecutionThread providePostExecutionThread(UIThread uiThread) {
    return uiThread;
  }

}

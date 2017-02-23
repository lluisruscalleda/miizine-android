package com.example.app.di.modules;

import android.os.Build;
import com.example.app.BuildConfig;
import com.example.data.entity.AutoValueTypeAdapterFactory;
import com.example.data.repository.MiiZineDataRepository;
import com.example.data.repository.UserDataDataRepository;
import com.example.data.storage.shared.Preferences;
import com.example.data.storage.shared.ProjectSharedPreferences;
import com.example.domain.repository.MiiZineRepository;
import com.example.domain.repository.UserDataRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dagger.Module;
import dagger.Provides;
import java.util.Locale;
import javax.inject.Named;
import javax.inject.Singleton;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * A dagger module that provides all other stuff to be used in {@link ApiModule} or {@link DataModule}
 */
@Module(includes = {
    ApiModule.class, DatabaseModule.class,
})
public class DataModule {

  @Provides
  @Singleton
  MiiZineRepository provideMiiZineRepository(MiiZineDataRepository miiZineDataRepository) {
    return miiZineDataRepository;
  }

  @Provides
  @Singleton
  UserDataRepository provideUserDataRepository(UserDataDataRepository userDataDataRepository) {
    return userDataDataRepository;
  }

  @Provides
  @Singleton
  Preferences providePreferences(ProjectSharedPreferences projectSharedPreferences) {
    return projectSharedPreferences;
  }

  @Provides
  @Singleton
  @Named("apiUrl")
  String provideApiUrl() {
    return BuildConfig.API_URL;
  }

  @Provides
  @Singleton
  @Named("userAgent")
  String provideUserAgentHeader() {
    return String.format(
        Locale.getDefault(),
        "Android;%s;%s;%d;%s;%s;%d;",
        Build.BRAND,
        Build.MODEL,
        Build.VERSION.SDK_INT,
        BuildConfig.APPLICATION_ID,
        BuildConfig.VERSION_NAME,
        BuildConfig.VERSION_CODE
    );
  }

  @Provides
  @Singleton
  HttpLoggingInterceptor provideApiLogLevel() {
    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
    if (BuildConfig.DEBUG) {
      interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
    } else {
      interceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
    }
    return interceptor;
  }

  @Provides
  @Singleton
  Gson provideGson() {
    return new GsonBuilder()
        .registerTypeAdapterFactory(AutoValueTypeAdapterFactory.create())
        .create();
  }
}

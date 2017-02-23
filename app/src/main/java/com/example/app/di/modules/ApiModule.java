package com.example.app.di.modules;

import android.content.Context;
import com.example.app.BuildConfig;
import com.example.app.di.qualifiers.ClientCache;
import com.example.data.net.miizineapi.MiiZineApiService;
import com.example.data.net.interceptor.CacheInterceptor;
import com.example.data.net.interceptor.UserAgentInterceptor;
import com.example.domain.executor.ThreadExecutor;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;
import dagger.Module;
import dagger.Provides;
import java.io.File;
import javax.inject.Named;
import javax.inject.Singleton;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.schedulers.Schedulers;

/**
 * A dagger module that provides retrofit services
 */
@Module
public class ApiModule {

  private static final int CACHE_SIZE_20MB = 20 * 1024 * 1024;

  @Provides
  @Singleton
  MiiZineApiService provideMiiZineApiService(
          Retrofit retrofit
  ) {
    return retrofit.create(MiiZineApiService.class);
  }

  @Provides
  @Singleton
  Retrofit provideRetrofit(
      OkHttpClient client,
      @Named("apiUrl")
      String endPoint,
      Gson gson,
      ThreadExecutor threadExecutor
  ) {
    return new Retrofit.Builder()
        .baseUrl(endPoint)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.from(threadExecutor)))
        .client(client)
        .validateEagerly(BuildConfig.DEBUG)
        .build();
  }

  @Provides
  @Singleton
  OkHttpClient provideOkHttpClient(
      @ClientCache
      Cache cache,
      CacheInterceptor cacheInterceptor,
      //HttpLoggingInterceptor loggingInterceptor,
      @Named("userAgent")
      String userAgentValue
  ) {
    return new OkHttpClient.Builder()
        .cache(cache)
        //.addInterceptor(loggingInterceptor)
        .addNetworkInterceptor(new UserAgentInterceptor(userAgentValue))
        .addNetworkInterceptor(new StethoInterceptor())
        .addInterceptor(cacheInterceptor)
        .addNetworkInterceptor(cacheInterceptor)
        .build();
  }

  @Provides
  @Singleton
  @ClientCache
  Cache provideCache(
      @ClientCache
      File path
  ) {
    return new Cache(path, CACHE_SIZE_20MB);
  }

  @Singleton
  @Provides
  @ClientCache
  File provideCacheFile(Context context) {
    return new File(context.getCacheDir(), "HttpResponseCache");
  }
}

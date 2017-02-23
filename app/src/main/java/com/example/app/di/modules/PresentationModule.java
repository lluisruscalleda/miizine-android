package com.example.app.di.modules;

import com.example.app.utils.imageloader.GlideImageLoader;
import com.example.app.utils.imageloader.ImageLoader;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

/**
 * Dagger module that provides all necessary at presentation level
 */
@Module
public class PresentationModule {

  @Provides
  @Singleton
  ImageLoader provideImageLoader(GlideImageLoader glideImageLoader) {
    return glideImageLoader;
  }
}

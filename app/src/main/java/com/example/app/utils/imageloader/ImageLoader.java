package com.example.app.utils.imageloader;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

/**
 * Interface to be implemented to load images into the app
 */
public interface ImageLoader {

  /**
   * Load an image
   *
   * @param url The web URL of an image.
   * @param imageView imageView The target ImageView to load the image into.
   */
  void loadImage(
      String url, ImageView imageView
  );

  /**
   * Load an image
   *
   * @param url The web URL of an image.
   * @param imageView imageView The target ImageView to load the image into.
   * @param placeholderOverride A placeholder to use in place of the default placholder.
   * @param crop crop True to apply a center crop to the image.
   */
  void loadImage(
      String url, ImageView imageView, Drawable placeholderOverride, boolean crop
  );

  /**
   * Load an image
   *
   * @param url The web URL of an image.
   * @param imageView imageView The target ImageView to load the image into.
   * @param placeholderOverride A placeholder to use in place of the default placholder.
   */
  void loadImage(
      String url, ImageView imageView, Drawable placeholderOverride
  );
}

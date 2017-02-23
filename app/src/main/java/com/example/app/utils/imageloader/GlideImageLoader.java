package com.example.app.utils.imageloader;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import com.bumptech.glide.BitmapRequestBuilder;
import com.bumptech.glide.BitmapTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.example.app.R;
import javax.inject.Inject;

/**
 * Glide implementation of Image Loader
 */
public class GlideImageLoader implements ImageLoader {

  private final BitmapTypeRequest<String> glideModelRequest;
  private final CenterCrop centerCrop;

  @Inject
  GlideImageLoader(Context context) {
    this.glideModelRequest = Glide.with(context).from(String.class).asBitmap();
    this.centerCrop = new CenterCrop(Glide.get(context).getBitmapPool());
  }

  @Override
  public void loadImage(
      String url, ImageView imageView, Drawable placeholderOverride
  ) {
    loadImage(url, imageView, placeholderOverride, false);
  }

  @Override
  public void loadImage(
      String url, ImageView imageView
  ) {
    loadImage(url, imageView, null, false);
  }

  @Override
  public void loadImage(
      String url, ImageView imageView, Drawable placeholderOverride, boolean crop
  ) {
    BitmapRequestBuilder request = beginImageLoad(url, crop).animate(R.anim.fade_in_fast);
    if (placeholderOverride != null) {
      request.placeholder(placeholderOverride);
    }
    request.into(imageView);
  }

  private BitmapRequestBuilder beginImageLoad(
      String url, boolean crop
  ) {
    if (crop) {
      return glideModelRequest.load(url).transform(centerCrop);
    } else {
      return glideModelRequest.load(url);
    }
  }
}

package com.example.app.model;

import android.os.Parcelable;
import com.google.auto.value.AutoValue;

@AutoValue
public abstract class Pill implements Parcelable {

  public abstract String getId();

  public abstract String getName();

  public abstract String getDescription();

  public abstract String getCategorie();

  public abstract String getImage();


  public static Pill create(String id, String name, String description, String categorie, String image) {
    return new AutoValue_Pill(id, name, description, categorie, image);
  }

}

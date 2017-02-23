package com.example.data.entity;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import javax.annotation.Nullable;

import static android.R.attr.id;
import static android.R.attr.name;

@AutoValue
public abstract class PillEntity {

  @SerializedName("id")
  public abstract String getId();

  @Nullable
  @SerializedName("title")
  public abstract String getName();

  @Nullable
  @SerializedName("description")
  public abstract String getDescription();

  @Nullable
  @SerializedName("categorie")
  public abstract String getCategorie();

  @Nullable
  @SerializedName("image")
  public abstract String getImage();

  public static TypeAdapter<PillEntity> typeAdapter(Gson gson) {
    return new AutoValue_PillEntity.GsonTypeAdapter(gson);
  }

  public static TypeAdapter<PillEntity> typeAdapter(Gson gson, TypeToken<PillEntity> type) {
    Class<? super PillEntity> rawType = type.getRawType();
    if (rawType.equals(PillEntity.class)) {
      return new AutoValue_PillEntity.GsonTypeAdapter(gson);
    }

    return null;
  }
  public static PillEntity create(String id, String name, String description, String categorie, String image) {
    return new AutoValue_PillEntity(null, name, description, categorie, image);
  }
}


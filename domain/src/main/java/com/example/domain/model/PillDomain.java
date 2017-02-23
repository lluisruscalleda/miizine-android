package com.example.domain.model;

import com.google.auto.value.AutoValue;

import java.util.List;

@AutoValue
public abstract class PillDomain {

  public abstract String getId();

  public abstract String getName();

  public abstract String getDescription();

  public abstract String getCategorie();

  public abstract String getImage();

  public static PillDomain create(String id, String name, String description, String categorie, String image) {
    return new AutoValue_PillDomain(id, name, description, categorie, image);
  }

}
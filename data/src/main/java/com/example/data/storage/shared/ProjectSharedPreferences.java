package com.example.data.storage.shared;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.data.entity.PillEntity;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ProjectSharedPreferences implements Preferences {

  private Gson gson;

  /** The m preferences. */
  protected SharedPreferences sharedPreferences;

  /** The m editor. */
  protected SharedPreferences.Editor editor;

  /** The Constant SHARED_PREFERENCES_FILE. */
  private static final String SHARED_PREFERENCES_FILE = "project_preferences";

  /** The Constant PROJECT_DATA. */
  private static final String PROJECT_DATA = "project_data";

  public static final String PILL_FAVORITES = "pill_favorites";

  /**
   * Instantiates a new shared preferences data file.
   *
   * @param context the context
   */
  @Inject
  public ProjectSharedPreferences(Context context,  Gson gson) {
    this.gson = gson;
    sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_FILE, 0);
  }

  public void apply() {
    if (editor != null) {
      editor.apply();
      editor = null;
    }
  }

  public SharedPreferences.Editor getEditor() {
    if (editor == null) {
      editor = sharedPreferences.edit();
    }

    return editor;
  }

  public void clearEditor() {
    getEditor().clear();
    apply();
  }

  /**
   * Gets the project data json string.
   *
   * @return the project data
   */
  public String getProjectData() {
    return sharedPreferences.getString(PROJECT_DATA, null);
  }

  /**
   * Sets the project data.
   *
   * @param projectData the new project data
   */
  public SharedPreferences.Editor setProjectData(String projectData) {
    return getEditor().putString(PROJECT_DATA, projectData);
  }

  public SharedPreferences.Editor saveFavorites(List<PillEntity> pills){

    String jsonFavorites = gson.toJson(pills);

    return getEditor().putString(PILL_FAVORITES, jsonFavorites);
  }

  public Boolean addFavorite(PillEntity pill){
    List<PillEntity> favorites = getFavorites();

    if(favorites == null)
      favorites = new ArrayList<PillEntity>();
    favorites.add(pill);
    saveFavorites(favorites).apply();

    return true;
  }

  public Boolean removeFavorite(PillEntity pill) {
    List<PillEntity> favorites = getFavorites();
    if (favorites != null) {
      favorites.remove(pill);
      saveFavorites(favorites).apply();
    }

    return true;
  }

  public List<PillEntity> getFavorites() {
    List<PillEntity> favorites;
    String jsonFavorites = sharedPreferences.getString(PILL_FAVORITES, null);

    if(jsonFavorites != null) {
      PillEntity[] favoriteItems = gson.fromJson(jsonFavorites,
          PillEntity[].class);

      favorites = Arrays.asList(favoriteItems);
      favorites = new ArrayList<PillEntity>(favorites);
    } else {
      return null;
    }

    return (ArrayList<PillEntity>) favorites;
  }
}

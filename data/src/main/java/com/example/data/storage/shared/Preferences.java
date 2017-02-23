package com.example.data.storage.shared;

import android.content.SharedPreferences;

public interface Preferences {


  /**
   * An asynchronous commit from the editor to the {@link SharedPreferences} object it is editing.
   */
  void apply();

  /**
   * Gets the editor.
   *
   * @return the editor
   */
  SharedPreferences.Editor getEditor();


  /**
   * Clears the editor.
   */
  void clearEditor();
}

package com.example.app.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import com.example.app.AndroidApp;

/**
 * Utils class to handle device operations
 */
public final class KeyboardUtils {

  private KeyboardUtils() {
  }

  public static void closeSoftKeyboardForActivity(Activity activity) {
    activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
  }

  public static void openSoftKeyboardForActivity(Activity activity) {
    activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
  }

  public static void closeSoftKeyboardForFragment(View view) {
    ((InputMethodManager) view.getContext()
        .getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
        view.getWindowToken(),
        InputMethodManager.HIDE_IMPLICIT_ONLY
    );
  }

  public static void showKeyboard(Context context, EditText focus) {
    if (focus != null) {
      InputMethodManager inputMethodManager =
          (InputMethodManager) AndroidApp.get(context).getSystemService(Context.INPUT_METHOD_SERVICE);
      focus.requestFocus();
      inputMethodManager.showSoftInput(focus, InputMethodManager.SHOW_FORCED);
    }
  }
}

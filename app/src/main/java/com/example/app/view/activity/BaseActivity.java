package com.example.app.view.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import com.example.app.di.components.ActivityComponent;
import com.example.app.di.factories.ActivityComponentFactory;

/**
 * Base {@link android.app.Activity} class for every Activity in this application.
 */
public abstract class BaseActivity extends AppCompatActivity {

  private ActivityComponent activityComponent;

  public ActivityComponent getActivityComponent() {
    if (activityComponent == null) {
      activityComponent = ActivityComponentFactory.create(this);
    }
    return activityComponent;
  }

  /**
   * Adds a {@link Fragment} to this activity's layout.
   *
   * @param containerViewId The container view to where add the fragment.
   * @param fragment The fragment to be added.
   */
  void addFragment(int containerViewId, Fragment fragment) {
    FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
    fragmentTransaction.add(containerViewId, fragment, fragment.getClass().getName());
    fragmentTransaction.commit();
  }
}

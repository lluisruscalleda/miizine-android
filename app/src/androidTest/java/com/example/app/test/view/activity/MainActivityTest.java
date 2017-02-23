package com.example.app.test.view.activity;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.app.Fragment;
import android.test.suitebuilder.annotation.MediumTest;
import com.example.app.view.activity.MainActivity;
import com.example.app.view.fragment.ZinesFragment;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(AndroidJUnit4.class)
@MediumTest
public class MainActivityTest {

  @Rule
  public ActivityTestRule<MainActivity> activityTestRule =
      new ActivityTestRule<>(MainActivity.class);

  @Test
  public void testContainsRepositoriesFragment() {
    Fragment repositoriesFragment = activityTestRule.getActivity()
        .getSupportFragmentManager()
        .findFragmentByTag(ZinesFragment.class.getName());
    assertThat(repositoriesFragment, is(notNullValue()));
  }

}

package com.example.app.view.viewpresenter;

import com.example.domain.exception.ErrorBundle;

/**
 * Interface representing a MvpView that will use to load data.
 */
interface LoadDataMvpView extends MvpView {

  /**
   * Show a view with a progress bar indicating a loading process.
   */
  void showLoading();

  /**
   * Hide a loading view.
   */
  void hideLoading();

  /**
   * Show a retry view in case of an error when retrieving data.
   */
  void showRetry();

  /**
   * Hide a retry view shown if there was an error when retrieving data.
   */
  void hideRetry();

  /**
   * Show an error message
   *
   * @param errorBundle An error bundle.
   */
  void showError(ErrorBundle errorBundle);
}

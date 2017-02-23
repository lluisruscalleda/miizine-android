package com.example.app.presenter;

import com.example.app.view.viewpresenter.MvpView;

/**
 * Interface representing a Presenter in a model view presenter (MVP) pattern.
 */
interface Presenter<T extends MvpView> {

  /**
   * Method that control the lifecycle of the view. It should be called in the view's
   * (Activity or Fragment) onResume() method.
   */
  void initialize();

  /**
   * Method that allows to attach the view of the mvp
   *
   * @param view to be attached
   */
  void attachView(T view);

  /**
   * Method that allows to dettach the view of the mvp
   */
  void detachView();

  /**
   * Method that control the lifecycle of the view. It should be called in the view's
   * (Activity or Fragment) onResume() method.
   */
  void resume();

  /**
   * Method that control the lifecycle of the view. It should be called in the view's
   * (Activity or Fragment) onPause() method.
   */
  void pause();

  /**
   * Method that control the lifecycle of the view. It should be called in the view's
   * (Activity or Fragment) onDestroy() method.
   */
  void destroy();
}

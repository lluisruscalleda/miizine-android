package com.example.app.presenter;

import com.example.app.view.viewpresenter.MvpView;

/**
 * Base class that implements the Presenter interface and provides a base implementation for
 * attachView() and detachView(). It also handles keeping a reference to the mvpView that
 * can be accessed from the children classes by calling getMvpView().
 */
class BasePresenter<T extends MvpView> implements Presenter<T> {

  private T mMvpView;

  @Override
  public void initialize() {
    //no-op
  }

  @Override
  public void attachView(T mvpView) {
    mMvpView = mvpView;
  }

  @Override
  public void detachView() {
    mMvpView = null;
  }

  @Override
  public void resume() {
    //no-op
  }

  @Override
  public void pause() {
    //no-op
  }

  @Override
  public void destroy() {
    //no-op
  }

  public boolean isViewAttached() {
    return mMvpView != null;
  }

  public T getMvpView() {
    return mMvpView;
  }

  public void checkViewAttached() {
    if (!isViewAttached()) {
      throw new MvpViewNotAttachedException();
    }
  }

  public static class MvpViewNotAttachedException extends RuntimeException {
    public MvpViewNotAttachedException() {
      super("Please save Presenter.attachView(MvpView) before" + " requesting data to the Presenter");
    }
  }
}

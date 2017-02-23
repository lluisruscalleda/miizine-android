package com.example.app.presenter;

import android.content.Context;
import com.example.app.model.Pill;
import com.example.app.model.mapper.PillDomainPresMapper;
import com.example.app.view.viewpresenter.ZinesMvpView;
import com.example.domain.exception.DefaultErrorBundle;
import com.example.domain.exception.ErrorBundle;
import com.example.domain.interactor.DefaultSubscriber;
import com.example.domain.interactor.UseCase;
import com.example.domain.model.PillDomain;
import com.fernandocejas.frodo.annotation.RxLogSubscriber;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;

public class ZinesPresenter extends BasePresenter<ZinesMvpView> {

    private final UseCase getPillListUseCase;
    private final PillDomainPresMapper pillDomainPresMapper;

    private String moment;

    @Inject
    public ZinesPresenter(
            @Named("pillList")
                    UseCase getPillListUseCase, PillDomainPresMapper pillDomainPresMapper
    ) {
       this.getPillListUseCase = getPillListUseCase;
        this.pillDomainPresMapper = pillDomainPresMapper;
    }

    public void setMoment(String moment) {
        this.moment = moment;
    }

    public String getMoment() {
        return this.moment;
    }

    public void favItem(Pill pill, boolean fav, Context context) {
        //FavoritePillUseCase addFavoritePill = favoritePillUseCase.create(pillDomainPresMapper.transformToDomain(pill), fav);
        //addFavoritePill.execute(new AddFavoritePillSubscriber());
    }

    @Override
    public void initialize() {
        hideRetry();
        showLoading();
        loadItems(getMoment());
    }

    @Override
    public void attachView(ZinesMvpView view) {
        super.attachView(view);
    }

    @Override
    public void resume() {
        //no op
    }

    @Override
    public void pause() {
        //no op
    }

    @Override
    public void destroy() {
        if (getPillListUseCase != null) {
            getPillListUseCase.unsubscribe();
        }
        super.detachView();
    }

    private void loadItems(String moment) {

      switch(moment) {
        case "pills":
          getPillListUseCase.execute(new ItemListSubscriber());
          break;
        case "favorites":
          //getFavoritePillListUseCase.execute(new FavoritePillListSubscriber());
          break;
      }
    }

    private void showLoading() {
        getMvpView().showLoading();
    }

    private void hideLoading() {
        getMvpView().hideLoading();
    }

    private void showRetry() {
        getMvpView().showRetry();
    }

    private void hideRetry() {
        getMvpView().hideRetry();
    }

    private void showError(ErrorBundle errorBundle) {
        getMvpView().showError(errorBundle);
    }

    private void showRepositories(List<PillDomain> pillDomainList) {
        getMvpView().showPills(pillDomainPresMapper.transform(pillDomainList));
    }

    @RxLogSubscriber
    private final class ItemListSubscriber extends DefaultSubscriber<List<PillDomain>> {

        @Override
        public void onCompleted() {
            ZinesPresenter.this.hideLoading();
        }

        @Override
        public void onError(Throwable e) {
            ZinesPresenter.this.hideLoading();
            ZinesPresenter.this.showRetry();
            ZinesPresenter.this.showError(new DefaultErrorBundle((Exception) e));
        }

        @Override
        public void onNext(List<PillDomain> pillDomainList) {
            ZinesPresenter.this.showRepositories(pillDomainList);
        }
    }

    @RxLogSubscriber
    private final class AddFavoritePillSubscriber extends DefaultSubscriber<Boolean> {

        @Override
        public void onCompleted() {
            ZinesPresenter.this.hideLoading();
        }

        @Override
        public void onError(Throwable e) {
            ZinesPresenter.this.hideLoading();
            ZinesPresenter.this.showError(new DefaultErrorBundle((Exception) e));
        }

        @Override
        public void onNext(Boolean response) {

        }
    }
}

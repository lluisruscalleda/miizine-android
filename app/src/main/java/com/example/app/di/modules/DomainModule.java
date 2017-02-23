package com.example.app.di.modules;

import com.example.domain.interactor.FavoritePillUseCase;
import com.example.domain.interactor.GetPillListUseCase;
import com.example.domain.interactor.UseCase;
import dagger.Module;
import dagger.Provides;
import javax.inject.Named;
import javax.inject.Singleton;

/**
 * Dagger module that provides implementations of {@link UseCase}
 */
@Module
public class DomainModule {

  @Provides
  @Singleton
  @Named("pillList")
  UseCase provideGetPillListUseCase(GetPillListUseCase getPillListUseCase) {
    return getPillListUseCase;
  }

  @Provides
  @Singleton
  @Named("addFavoritePill")
  UseCase provideAddFavoritePillUseCase(FavoritePillUseCase addFavoritePill) {
    return addFavoritePill;
  }

}

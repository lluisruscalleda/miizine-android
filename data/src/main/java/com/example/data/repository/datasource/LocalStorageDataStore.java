package com.example.data.repository.datasource;

import com.example.data.entity.PillEntity;
import com.example.data.entity.mapper.PillDataDomainMapper;
import com.example.data.storage.shared.ProjectSharedPreferences;

import com.example.domain.model.PillDomain;
import java.util.List;

import rx.Observable;

/**
 * {@link MiiZineDataStore} implementation based on connections to the api (Cloud).
 */
public class LocalStorageDataStore implements UserDataDataStore {

  private final PillDataDomainMapper pillDataDomainMapper;

  private final ProjectSharedPreferences preferences;

  public LocalStorageDataStore(ProjectSharedPreferences preferences, PillDataDomainMapper pillDataDomainMapper) {
    this.preferences = preferences;
    this.pillDataDomainMapper = pillDataDomainMapper;
  }

  @Override
  public Observable<List<PillDomain>> favoriteEntityList() {
    return Observable.just(pillDataDomainMapper.transform(preferences.getFavorites()));
  }

  @Override
  public Observable<Boolean> addFavoriteEntity(PillEntity pillEntity) {
    return Observable.just(preferences.addFavorite(pillEntity));
  }

  @Override
  public Observable<Boolean> removeFavoriteEntity(PillEntity pillEntity) {
    return Observable.just(preferences.removeFavorite(pillEntity));
  }
}

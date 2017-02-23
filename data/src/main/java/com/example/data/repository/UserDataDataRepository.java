package com.example.data.repository;

import com.example.data.entity.mapper.PillDataDomainMapper;
import com.example.data.repository.datasource.LocalStorageDataStore;
import com.example.data.storage.shared.ProjectSharedPreferences;
import com.example.domain.model.PillDomain;
import com.example.domain.repository.UserDataRepository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

@Singleton
public class UserDataDataRepository implements UserDataRepository {

  private final ProjectSharedPreferences preferences;
  private final PillDataDomainMapper pillDataDomainMapper;

  @Inject
  public UserDataDataRepository(
          ProjectSharedPreferences preferences,
      PillDataDomainMapper pillDataDomainMapper
  ) {
    this.preferences = preferences;
    this.pillDataDomainMapper = pillDataDomainMapper;
  }

  @SuppressWarnings("Convert2MethodRef")
  @Override
  public Observable<List<PillDomain>> favoritePills() {
    return new LocalStorageDataStore(preferences, pillDataDomainMapper).favoriteEntityList();
  }

  @SuppressWarnings("Convert2MethodRef")
  @Override
  public Observable<Boolean> addFavoritePill(PillDomain pillDomain) {
    return new LocalStorageDataStore(preferences, pillDataDomainMapper).addFavoriteEntity(this.pillDataDomainMapper.transformToData(pillDomain));
  }

  @SuppressWarnings("Convert2MethodRef")
  @Override
  public Observable<Boolean> removeFavoritePill(PillDomain pillDomain) {
    return new LocalStorageDataStore(preferences, pillDataDomainMapper).removeFavoriteEntity(this.pillDataDomainMapper.transformToData(pillDomain));
  }
}

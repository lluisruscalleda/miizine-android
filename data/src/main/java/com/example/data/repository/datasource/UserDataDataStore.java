package com.example.data.repository.datasource;

import com.example.data.entity.PillEntity;
import com.example.domain.model.PillDomain;
import java.util.List;

import rx.Observable;

public interface UserDataDataStore {

  Observable<List<PillDomain>> favoriteEntityList();
  Observable<Boolean> addFavoriteEntity(PillEntity timmer);
  Observable<Boolean> removeFavoriteEntity(PillEntity timmer);
}

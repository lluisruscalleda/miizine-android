package com.example.domain.repository;


import com.example.domain.model.PillDomain;
import java.util.List;

import rx.Observable;

public interface UserDataRepository {

  Observable<List<PillDomain>> favoritePills();
  Observable<Boolean> addFavoritePill(PillDomain pillDomain);
  Observable<Boolean> removeFavoritePill(PillDomain pillDomain);
}

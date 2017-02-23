package com.example.domain.repository;

import com.example.domain.model.PillDomain;
import java.util.List;
import rx.Observable;

public interface MiiZineRepository {

  /**
   * Get an {@link rx.Observable} which will emit a List of {@link PillDomain}.
   */
  Observable<List<PillDomain>> pills();
}

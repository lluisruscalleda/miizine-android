package com.example.data.repository;

import com.example.data.entity.mapper.PillDataDomainMapper;
import com.example.data.repository.datasource.MiiZineDataStore;
import com.example.data.repository.datasource.MiiZineDataStoreFactory;
import com.example.domain.model.PillDomain;
import com.example.domain.repository.MiiZineRepository;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import rx.Observable;

@Singleton
public class MiiZineDataRepository implements MiiZineRepository {

  private final MiiZineDataStoreFactory miiZineDataStoreFactory;
  private final PillDataDomainMapper pillDataDomainMapper;

  @Inject
  public MiiZineDataRepository(
      MiiZineDataStoreFactory miiZineDataStoreFactory, PillDataDomainMapper pillDataDomainMapper
  ) {
    this.miiZineDataStoreFactory = miiZineDataStoreFactory;
    this.pillDataDomainMapper = pillDataDomainMapper;
  }

  @SuppressWarnings("Convert2MethodRef")
  @Override
  public Observable<List<PillDomain>> pills() {

    final MiiZineDataStore miiZineDataStore = this.miiZineDataStoreFactory.create();

    return miiZineDataStore.pillEntityList().map(pillEntities -> this.pillDataDomainMapper.transform(pillEntities));
  }
}

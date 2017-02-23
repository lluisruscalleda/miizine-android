package com.example.data.repository.datasource;

import com.example.data.entity.PillEntity;
import com.example.data.net.miizineapi.MiiZineApi;
import java.util.List;
import rx.Observable;

/**
 * {@link MiiZineDataStore} implementation based on connections to the api (Cloud).
 */
public class CloudMiiZineDataStore implements MiiZineDataStore {

  private final MiiZineApi miiZineApi;

  public CloudMiiZineDataStore(MiiZineApi miiZineApi) {
    this.miiZineApi = miiZineApi;
  }

  @Override
  public Observable<List<PillEntity>> pillEntityList() {
    return this.miiZineApi.pillEntityList();
  }
}

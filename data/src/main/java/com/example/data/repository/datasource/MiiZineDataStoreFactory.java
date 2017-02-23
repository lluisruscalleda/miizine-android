package com.example.data.repository.datasource;

import com.example.data.net.miizineapi.MiiZineApiImpl;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Factory that creates different implementations of {@link MiiZineDataStore}.
 */
@Singleton
public class MiiZineDataStoreFactory {

  private final MiiZineApiImpl githubApi;

  @Inject
  public MiiZineDataStoreFactory(MiiZineApiImpl githubApi) {
    this.githubApi = githubApi;
  }

  public MiiZineDataStore create() {
    MiiZineDataStore githubDataStore;

    githubDataStore = createCloudDataStore();

    return githubDataStore;
  }

  private MiiZineDataStore createCloudDataStore() {
    return new CloudMiiZineDataStore(githubApi);
  }
}

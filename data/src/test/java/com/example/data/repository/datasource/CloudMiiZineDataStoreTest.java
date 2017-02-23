package com.example.data.repository.datasource;

import com.example.data.ApplicationTestCase;
import com.example.data.net.miizineapi.MiiZineApi;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

public class CloudMiiZineDataStoreTest extends ApplicationTestCase {

  private CloudMiiZineDataStore cloudMiiZineDataStore;

  @Mock
  MiiZineApi mockMiiZineApi;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    cloudMiiZineDataStore = new CloudMiiZineDataStore(mockMiiZineApi);
  }

  @Test
  public void testRepoEntityList() throws Exception {
    cloudMiiZineDataStore.pillEntityList();
    verify(mockMiiZineApi).pillEntityList();
  }
}
package com.example.data.repository.datasource;

import com.example.data.ApplicationTestCase;
import com.example.data.net.miizineapi.MiiZineApiImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class MiiZineDataStoreFactoryTest extends ApplicationTestCase {

  private MiiZineDataStoreFactory miiZineDataStoreFactory;

  @Mock
  MiiZineApiImpl mockMiiZineApiImpl;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    miiZineDataStoreFactory = new MiiZineDataStoreFactory(mockMiiZineApiImpl);
  }

  @Test
  public void testCreate() throws Exception {

    MiiZineDataStore miiZineDataStore = miiZineDataStoreFactory.create();

    assertThat(miiZineDataStore, is(notNullValue()));
    assertThat(miiZineDataStore, is(instanceOf(CloudMiiZineDataStore.class)));
  }
}
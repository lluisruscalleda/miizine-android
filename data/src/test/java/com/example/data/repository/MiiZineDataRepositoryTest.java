package com.example.data.repository;

import com.example.data.ApplicationTestCase;
import com.example.data.entity.PillEntity;
import com.example.data.entity.mapper.PillDataDomainMapper;
import com.example.data.repository.datasource.MiiZineDataStore;
import com.example.data.repository.datasource.MiiZineDataStoreFactory;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import rx.Observable;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class MiiZineDataRepositoryTest extends ApplicationTestCase {

  private MiiZineDataRepository githubDataRepository;

  @Mock
  private MiiZineDataStoreFactory mockMiiZineDataStoreFactory;
  @Mock
  private PillDataDomainMapper mockPillDataDomainMapper;
  @Mock
  private MiiZineDataStore mockMiiZineDataStore;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    githubDataRepository = new MiiZineDataRepository(mockMiiZineDataStoreFactory, mockPillDataDomainMapper);

    given(mockMiiZineDataStoreFactory.create()).willReturn(mockMiiZineDataStore);
  }

  @Test
  public void testRepos() {
    List<PillEntity> pillEntityList = new ArrayList<>();
    given(mockMiiZineDataStore.pillEntityList()).willReturn(Observable.just(pillEntityList));

    githubDataRepository.pills();

    verify(mockMiiZineDataStoreFactory).create();
    verify(mockMiiZineDataStore).pillEntityList();
  }
}
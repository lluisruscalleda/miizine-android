package com.example.domain.interactor;

import com.example.domain.executor.PostExecutionThread;
import com.example.domain.executor.ThreadExecutor;
import com.example.domain.repository.MiiZineRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

public class GetPillListTest {

  private GetPillListUseCase getPillList;

  @Mock
  private ThreadExecutor mockThreadExecutor;
  @Mock
  private PostExecutionThread mockPostExecutionThread;
  @Mock
  private MiiZineRepository mockMiiZineRepository;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    getPillList = new GetPillListUseCase(
        mockThreadExecutor, mockPostExecutionThread, mockMiiZineRepository
    );
  }

  @Test
  public void testBuildUseCaseObservable() throws Exception {
    getPillList.buildUseCaseObservable();

    verify(mockMiiZineRepository).pills();
    verifyNoMoreInteractions(mockMiiZineRepository);
    verifyZeroInteractions(mockThreadExecutor);
    verifyZeroInteractions(mockPostExecutionThread);
  }
}
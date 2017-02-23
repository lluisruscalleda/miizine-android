package com.example.domain.interactor;

import com.example.domain.executor.PostExecutionThread;
import com.example.domain.executor.ThreadExecutor;
import com.example.domain.repository.MiiZineRepository;
import javax.inject.Inject;
import rx.Observable;

/**
 * This class is an implementation of {@link UseCase} that represents a use case for
 * retrieving a collection.
 */
public class GetPillListUseCase extends UseCase {

  private final MiiZineRepository miiZineRepository;

  @Inject
  protected GetPillListUseCase(
      ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, MiiZineRepository miiZineRepository
  ) {
    super(threadExecutor, postExecutionThread);
    this.miiZineRepository = miiZineRepository;
  }

  @Override
  protected Observable buildUseCaseObservable() {
    return this.miiZineRepository.pills();
  }
}

package com.example.domain.interactor;

import com.example.domain.executor.PostExecutionThread;
import com.example.domain.executor.ThreadExecutor;
import com.example.domain.model.PillDomain;
import com.example.domain.repository.UserDataRepository;
import javax.inject.Inject;
import rx.Observable;
import rx.Subscriber;

/**
 * This class is an implementation of {@link UseCase} that represents a use case for
 * retrieving a collection.
 */
public class FavoritePillUseCase extends UseCase {

  private final UserDataRepository userDataRepository;

  private PillDomain pillDomain;

  private boolean toFav;

  @Inject
  protected FavoritePillUseCase(
          PillDomain timmer, boolean fav,
      ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, UserDataRepository userDataRepository
  ) {
    super(threadExecutor, postExecutionThread);
    this.userDataRepository = userDataRepository;
    this.toFav = fav;
    this.pillDomain = timmer;
  }

  @Override
  public void execute(Subscriber useCaseSubscriber) {
    super.execute(useCaseSubscriber);
  }

  @Override
  protected Observable buildUseCaseObservable() {
    if(toFav) {
      return this.userDataRepository.addFavoritePill(this.pillDomain);
    } else {
      return this.userDataRepository.removeFavoritePill(this.pillDomain);
    }
  }
}

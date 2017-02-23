package com.example.data.net.miizineapi;

import android.content.Context;
import android.support.annotation.NonNull;
import com.example.commons.DeviceNetworkUtils;
import com.example.data.entity.PillEntity;
import com.example.data.exception.NetworkConnectionException;
import com.example.data.exception.ServerIssueException;
import com.fernandocejas.frodo.annotation.RxLogObservable;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.AsyncEmitter;
import rx.Observable;

/**
 * Api client implementation
 */
@Singleton
public class MiiZineApiImpl implements MiiZineApi {

  private final Context context;
  private final MiiZineApiService miiZineApiService;

  @Inject MiiZineApiImpl(
      @NonNull
      Context context,
      @NonNull
          MiiZineApiService miiZineApiService
  ) {
    this.context = context;
    this.miiZineApiService = miiZineApiService;
  }

  @RxLogObservable
  @Override
  public Observable<List<PillEntity>> pillEntityList() {
    return Observable.fromAsync(subscriber -> {
      if (DeviceNetworkUtils.isConnected(context)) {
        try {

          miiZineApiService
              .listPills()
              .enqueue(new Callback<List<PillEntity>>() {
                @Override
                public void onResponse(
                    Call<List<PillEntity>> call, Response<List<PillEntity>> response
                ) {
                  subscriber.onNext(response.body());
                  subscriber.onCompleted();
                }

                @Override
                public void onFailure(Call<List<PillEntity>> call, Throwable t) {
                  subscriber.onError(new ServerIssueException(t));
                }
              });
        } catch (Exception e) {

          subscriber.onError(new ServerIssueException(e.getCause()));
        }
      } else {

        subscriber.onError(new NetworkConnectionException());
      }
    }, AsyncEmitter.BackpressureMode.LATEST);
  }
}

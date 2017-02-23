package com.example.data.net.miizineapi;

import com.example.data.entity.PillEntity;
import java.util.List;
import rx.Observable;

/**
 * Rest api for retrieving data from the network
 */
public interface MiiZineApi {

  Observable<List<PillEntity>> pillEntityList();
}

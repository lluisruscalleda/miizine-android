package com.example.data.repository.datasource;

import com.example.data.entity.PillEntity;
import java.util.List;
import rx.Observable;

public interface MiiZineDataStore {

  Observable<List<PillEntity>> pillEntityList();
}

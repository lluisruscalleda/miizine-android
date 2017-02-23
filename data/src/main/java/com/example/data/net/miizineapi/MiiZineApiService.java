package com.example.data.net.miizineapi;

import com.example.data.entity.PillEntity;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface MiiZineApiService {

  @GET("/api/zines")
  Call<List<PillEntity>> listPills();
}

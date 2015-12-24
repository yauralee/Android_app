package com.pm25.service;

import com.pm25.domain.City;


import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

public interface RankService {

    @GET("/api/querys/aqi_ranking.json")
    Call<List<City>> getRank(@Query("token") String token);

}

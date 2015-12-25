package com.pm25.service.details;

import com.pm25.domain.Position;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;


public interface DetailsService {
    @GET("/api/querys/aqi_details.json")
    Call<List<Position>> getRank(@Query("token") String token, @Query("city") String city);
}

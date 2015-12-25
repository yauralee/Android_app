package com.pm25.service.city_info;

import com.pm25.domain.City;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

public interface CityInfoService {

    @GET("/api/querys/aqi_details.json")
    Call<List<City>> getCityInfo(@Query("token") String token,@Query("city") String city);

}

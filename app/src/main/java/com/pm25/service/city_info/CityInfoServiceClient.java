package com.pm25.service.city_info;

import com.pm25.domain.City;
import com.squareup.okhttp.OkHttpClient;

import java.util.List;
import java.util.concurrent.TimeUnit;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class CityInfoServiceClient {

//    private static final String BASE_URL = "http://www.pm25.in/api/querys/aqi_details.json";
    private static final String BASE_URL = "http://www.pm25.in";

    //    private static final String TOKEN = "4esfG6UEhGzNkbszfjAp";
    private static final String TOKEN = "5j1znBVAsnSf5xQyNQyq";

    private static CityInfoServiceClient instance;
    private final CityInfoService cityInfoService;

    private CityInfoServiceClient() {
        OkHttpClient okHttpClient = new OkHttpClient();
      //  okHttpClient.setReadTimeout(20 * 1000, TimeUnit.MILLISECONDS);

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(BASE_URL);
        builder.client(okHttpClient);
        builder.addConverterFactory(GsonConverterFactory.create());
        cityInfoService = builder.build().create(CityInfoService.class);
    }

    public static CityInfoServiceClient getInstance() {
        if (instance == null) {
            instance = new CityInfoServiceClient();
        }
        return instance;
    }

    public void requestCityInfo(String city,Callback<List<City>> callback) {
        Call<List<City>> call = cityInfoService.getCityInfo(TOKEN,city);
        enqueue(call, callback);
    }

    private <T> void enqueue(Call<List<City>> call, Callback<List<City>> callback) {
        if (call != null && callback != null) {
            call.enqueue(callback);
        }
    }
}

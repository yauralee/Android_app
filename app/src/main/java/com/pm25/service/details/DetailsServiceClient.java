package com.pm25.service.details;

import com.pm25.domain.City;
import com.pm25.domain.Position;
import com.squareup.okhttp.OkHttpClient;

import java.util.List;
import java.util.concurrent.TimeUnit;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class DetailsServiceClient {

//    private static final String BASE_URL = "http://www.pm25.in/api/querys/aqi_details.json";
    private static final String BASE_URL = "http://www.pm25.in";

    //    private static final String TOKEN = "4esfG6UEhGzNkbszfjAp";
     private static final String TOKEN = "5j1znBVAsnSf5xQyNQyq";

    private static DetailsServiceClient instance;
    private final DetailsService detailsService;

    private DetailsServiceClient() {
        OkHttpClient okHttpClient = new OkHttpClient();
     //   okHttpClient.setReadTimeout(20 * 1000, TimeUnit.MILLISECONDS);

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(BASE_URL);
        builder.client(okHttpClient);
        builder.addConverterFactory(GsonConverterFactory.create());
        detailsService = builder.build().create(DetailsService.class);
    }

    public static DetailsServiceClient getInstance() {
        if (instance == null) {
            instance = new DetailsServiceClient();
        }
        return instance;
    }

    public void requestDetails(String city, Callback<List<Position>> callback) {
        Call<List<Position>> call = detailsService.getRank(TOKEN,city);
        enqueue(call, callback);
    }

    private <T> void enqueue(Call<List<Position>> call, Callback<List<Position>> callback) {
        if (call != null && callback != null) {
            call.enqueue(callback);
        }
    }
}

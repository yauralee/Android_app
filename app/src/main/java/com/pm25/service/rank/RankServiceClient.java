package com.pm25.service.rank;

import com.pm25.domain.City;
import com.squareup.okhttp.OkHttpClient;

import java.util.List;
import java.util.concurrent.TimeUnit;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class RankServiceClient {

    private static final String BASE_URL = "http://www.pm25.in/api/querys/aqi_ranking.json";
//    private static final String BASE_URL = "http://www.pm25.in";

    //    private static final String TOKEN = "4esfG6UEhGzNkbszfjAp";
    private static final String TOKEN = "5j1znBVAsnSf5xQyNQyq";

    private static RankServiceClient instance;
    private final RankService rankService;

    private RankServiceClient() {
        OkHttpClient okHttpClient = new OkHttpClient();
      //  okHttpClient.setReadTimeout(20 * 1000, TimeUnit.MILLISECONDS);

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(BASE_URL);
        builder.client(okHttpClient);
        builder.addConverterFactory(GsonConverterFactory.create());
        rankService = builder.build().create(RankService.class);
    }

    public static RankServiceClient getInstance() {
        if (instance == null) {
            instance = new RankServiceClient();
        }
        return instance;
    }

    public void requestRank(Callback<List<City>> callback) {
        Call<List<City>> call = rankService.getRank(TOKEN);
        enqueue(call, callback);
    }

    private <T> void enqueue(Call<List<City>> call, Callback<List<City>> callback) {
        if (call != null && callback != null) {
            call.enqueue(callback);
        }
    }
}

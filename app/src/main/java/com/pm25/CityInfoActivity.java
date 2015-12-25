package com.pm25;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pm25.domain.City;
import com.pm25.domain.Position;
import com.pm25.service.city_info.CityInfoServiceClient;
import com.pm25.service.details.DetailsServiceClient;
import com.thoughtworks.application.pm25_monitor.R;

import java.util.List;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class CityInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_info);
        View v = findViewById(R.id.cityInfo);
        v.getBackground().setAlpha(100);
        Intent i1 = getIntent();
        String searchCity = i1.getStringExtra("data");
        CityInfoServiceClient.getInstance().requestCityInfo(searchCity,new Callback<List<City>>() {
            @Override
            public void onResponse(Response<List<City>> response, Retrofit retrofit) {
                showSuccessScreen(response);
            }
            @Override
            public void onFailure(Throwable t) {
                Log.e("msg","failure2");
                t.printStackTrace();
            }
        });
        findViewById(R.id.bottomBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView city = (TextView) findViewById(R.id.city);
                String cityName = city.getText().toString();
                Intent i2 = new Intent(CityInfoActivity.this, DetailsActivity.class);
                i2.putExtra("data2", cityName);
                startActivity(i2);
            }
        });

    }
    private void showSuccessScreen(Response<List<City>> response) {
        List<City> list = response.body();
        City city = list.get(list.size()-1);
        ((TextView)findViewById(R.id.city)).setText(city.getArea());
        ((TextView)findViewById(R.id.quality)).setText(city.getQuality());
        TextView tv2 = (TextView)findViewById(R.id.aqiInfo);
        tv2.setText("AOI:"+Integer.toString(city.getAqi()));

        TextView tv3 = (TextView)findViewById(R.id.pm25Info);
        tv3.setText("PM2.5:"+Integer.toString(city.getPm2_5()));

        TextView tv4 = (TextView)findViewById(R.id.pm10Info);
        tv4.setText("PM10:"+Integer.toString(city.getPm10()));

        TextView tv5 = (TextView)findViewById(R.id.coInfo);
        tv5.setText("CO:"+Double.toString(city.getCo()));

        TextView tv6 = (TextView)findViewById(R.id.no2Info);
        tv6.setText("NO2:"+Integer.toString(city.getNo2()));

        TextView tv7 = (TextView)findViewById(R.id.o3Info);
        tv7.setText("O3:"+Integer.toString(city.getO3()));




    }

}

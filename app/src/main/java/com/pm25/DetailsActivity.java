package com.pm25;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pm25.domain.Position;
import com.pm25.service.details.DetailsServiceClient;
import com.thoughtworks.application.pm25_monitor.R;

import java.util.List;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;


public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        View v = findViewById(R.id.detailsAty);
        v.getBackground().setAlpha(100);

        setContentView(R.layout.activity_details);
        Intent i = getIntent();
        String searchCity = i.getStringExtra("data2");
        DetailsServiceClient.getInstance().requestDetails(searchCity,new Callback<List<Position>>() {
            @Override
            public void onResponse(Response<List<Position>> response, Retrofit retrofit) {
                showSuccessScreen(response);
            }
            @Override
            public void onFailure(Throwable t) {
                Log.e("msg","failure3");
                t.printStackTrace();
            }
        });

    }
    private void showSuccessScreen(Response<List<Position>> response) {
        LinearLayout layout = (LinearLayout) findViewById(R.id.positionContent);
        List<Position> list = response.body();

        TextView header = (TextView)findViewById(R.id.cityName);
        header.setText(list.get(0).getArea());

        for(int i=0;i<list.size();i++){

            LinearLayout sonLayout = new LinearLayout(this);
            sonLayout.setOrientation(LinearLayout.VERTICAL);
            sonLayout.setBackgroundResource(R.drawable.bg2);

            TextView positionName = new TextView(this);
            positionName.setText(list.get(i).getPosition_name());
            positionName.setTextSize(18);
            positionName.setTextColor(0xff50AE1D);
            positionName.setGravity(Gravity.CENTER);
            sonLayout.addView(positionName);

            LinearLayout grandson1Layout = new LinearLayout(this);
            grandson1Layout.setOrientation(LinearLayout.HORIZONTAL);
            grandson1Layout.setGravity(Gravity.CENTER);

            TextView tv11 = new TextView(this);
            tv11.setText("quality:"+list.get(i).getQuality()+"    ");
            tv11.setTextSize(15);
            tv11.setTextColor(Color.WHITE);
            grandson1Layout.addView(tv11);
            TextView tv12 = new TextView(this);
            tv12.setText("AQI:"+Integer.toString(list.get(i).getAqi()));
            tv12.setTextSize(15);
            tv12.setTextColor(Color.WHITE);
            tv12.setGravity(Gravity.CENTER);
            grandson1Layout.addView(tv12);

            LinearLayout grandson2Layout = new LinearLayout(this);
            grandson2Layout.setOrientation(LinearLayout.HORIZONTAL);
            grandson2Layout.setGravity(Gravity.CENTER);

            TextView tv21 = new TextView(this);
            tv21.setText("PM2.5:"+Integer.toString(list.get(i).getPm2_5())+"    ");
            tv21.setTextSize(15);
            tv21.setTextColor(Color.WHITE);
            grandson2Layout.addView(tv21);
            TextView tv22 = new TextView(this);
            tv22.setText("PM10:"+Integer.toString(list.get(i).getPm10()));
            tv22.setTextSize(15);
            tv22.setTextColor(Color.WHITE);
            grandson2Layout.addView(tv22);

            LinearLayout grandson3Layout = new LinearLayout(this);
            grandson3Layout.setOrientation(LinearLayout.HORIZONTAL);
            grandson3Layout.setGravity(Gravity.CENTER);

            TextView tv31 = new TextView(this);
            tv31.setText("CO:"+Double.toString(list.get(i).getCo())+"    ");
            tv31.setTextSize(15);
            tv31.setTextColor(Color.WHITE);
            grandson3Layout.addView(tv31);
            TextView tv32 = new TextView(this);
            tv32.setText("NO2:"+Integer.toString(list.get(i).getNo2()));
            tv32.setTextSize(15);
            tv32.setTextColor(Color.WHITE);
            grandson3Layout.addView(tv32);

            LinearLayout grandson4Layout = new LinearLayout(this);
            grandson4Layout.setOrientation(LinearLayout.HORIZONTAL);
            grandson4Layout.setGravity(Gravity.CENTER);

            TextView tv41 = new TextView(this);
            tv41.setText("O3:"+Integer.toString(list.get(i).getO3())+"    ");
            tv41.setTextSize(15);
            tv41.setTextColor(Color.WHITE);
            grandson4Layout.addView(tv41);
            TextView tv42 = new TextView(this);
            tv42.setText("SO2:"+Integer.toString(list.get(i).getSo2()));
            tv42.setTextSize(15);
            tv42.setTextColor(Color.WHITE);

            grandson4Layout.addView(tv42);
            sonLayout.addView(grandson1Layout);
            sonLayout.addView(grandson2Layout);
            sonLayout.addView(grandson3Layout);
            sonLayout.addView(grandson4Layout);

            layout.addView(sonLayout);



        }
    }

}

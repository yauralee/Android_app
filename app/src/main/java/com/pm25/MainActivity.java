package com.pm25;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.pm25.domain.City;
import com.pm25.service.rank.RankServiceClient;
import com.thoughtworks.application.pm25_monitor.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity {
    private ArrayList<HashMap<String,String>> mainList=new ArrayList<HashMap<String, String>>();
    private ListView list;
    private SimpleAdapter adapter;
    private EditText cityEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View v = findViewById(R.id.mainAtys);
        v.getBackground().setAlpha(100);
        cityEditText = (EditText)findViewById(R.id.inputCity);
        list= (ListView) findViewById(R.id.RankListView);



        adapter = new SimpleAdapter(this,mainList,R.layout.list_item_main,
                new String[] {"ItemTitle", "ItemText"},
                new int[] {R.id.ItemTitle,R.id.ItemText});
        list.setAdapter(adapter);

        RankServiceClient.getInstance().requestRank(new Callback<List<City>>() {
            @Override
            public void onResponse(Response<List<City>>response, Retrofit retrofit) {
                showSuccessScreen(response, mainList);
            }
            @Override
            public void onFailure(Throwable t) {

                Log.e("msg","failure1");
                t.printStackTrace();
            }
        });


        findViewById(R.id.searchBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputCity = cityEditText.getText().toString();

                Intent i = new Intent(MainActivity.this, CityInfoActivity.class);
                i.putExtra("data", inputCity);
                startActivity(i);
            }
        });


    }

    private void showSuccessScreen(Response<List<City>> response, ArrayList arr) {
        if (response != null) {
            populateTop(response.body(),arr,"Top 10");
            populateBottom(response.body(),arr,"Bottom 10");
            adapter.notifyDataSetChanged();
        }
    }
    private void populateTop(List<City> data, ArrayList arr, String title) {
        HashMap<String, String> mapTitle = new HashMap<String, String>();
        mapTitle.put("ItemTitle", title);
        arr.add(mapTitle);
        if (data != null) {
            for(int i=0;i<10;i++){
                HashMap<String, String> mapText = new HashMap<String, String>();
                mapText.put("ItemText", data.get(i).getArea()+" : "+data.get(i).getAqi());
                arr.add(mapText);
            }
        }else{
            System.out.println("can't get data");
        }
    }
    private void populateBottom(List<City> data, ArrayList arr, String title) {
        HashMap<String, String> mapTitle = new HashMap<String, String>();
        mapTitle.put("ItemTitle", title);
        arr.add(mapTitle);
        if (data != null) {
            for(int i=data.size()-1;i>data.size()-10.;i--){
                HashMap<String, String> mapText = new HashMap<String, String>();
                mapText.put("ItemText", data.get(i).getArea()+" : "+data.get(i).getAqi());
                arr.add(mapText);
            }
        }else{
            System.out.println("can't get data");
        }
    }

}

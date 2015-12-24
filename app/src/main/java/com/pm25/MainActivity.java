package com.pm25;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.pm25.domain.City;
import com.pm25.service.RankServiceClient;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View v = findViewById(R.id.mainAtys);
        v.getBackground().setAlpha(100);
        list= (ListView) findViewById(R.id.RankListView);
        adapter = new SimpleAdapter(this,mainList,R.layout.list_item_main,
                new String[] {"ItemTitle", "ItemText"},
                new int[] {R.id.ItemTitle,R.id.ItemText});
        list.setAdapter(adapter);

        RankServiceClient.getInstance().requestRank(new Callback<City[]>() {
            @Override
            public void onResponse(Response<City[]> response, Retrofit retrofit) {
                showSuccessScreen(response, mainList);
            }
            @Override
            public void onFailure(Throwable t) {

                Log.e("msg","failure");
                t.printStackTrace();
            }
        });
    }

    private void showSuccessScreen(Response<City[]> response, ArrayList arr) {
        if (response != null) {
            populateTop(response.body(),arr,"Top 10");
            populateBottom(response.body(),arr,"Bottom 10");
            adapter.notifyDataSetChanged();
        }
    }
    private void populateTop(City[] data, ArrayList arr, String title) {
        HashMap<String, String> mapTitle = new HashMap<String, String>();
        mapTitle.put("ItemTitle", title);
        arr.add(mapTitle);
        if (data != null) {
            for(int i=0;i<10;i++){
                HashMap<String, String> mapText = new HashMap<String, String>();
                mapText.put("ItemText", data[i].getArea()+" : "+data[i].getAqi());
                arr.add(mapText);
            }
        }else{
            System.out.println("can't get data");
        }
    }
    private void populateBottom(City[] data, ArrayList arr, String title) {
        HashMap<String, String> mapTitle = new HashMap<String, String>();
        mapTitle.put("ItemTitle", title);
        arr.add(mapTitle);
        if (data != null) {
            for(int i=data.length-1;i>data.length-10.;i--){
                HashMap<String, String> mapText = new HashMap<String, String>();
                mapText.put("ItemText", data[i].getArea()+" : "+data[i].getAqi());
                arr.add(mapText);
            }
        }else{
            System.out.println("can't get data");
        }
    }

}

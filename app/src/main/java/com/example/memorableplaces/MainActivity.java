package com.example.memorableplaces;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<String> address;

    ListView pinnedPlaces;

    FloatingActionButton goToMaps;
    FloatingActionButton goToMaps1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        goToMaps = findViewById(R.id.openMaps);
        goToMaps1 = findViewById(R.id.openMaps2);
        address = new ArrayList<>();
        pinnedPlaces = findViewById(R.id.pinnedPlaces);
        updateListView("Dummy Location");
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        goToMaps.setScaleX(1);
        goToMaps.setScaleY(1);
        goToMaps.setTranslationY(0);
        goToMaps.setAlpha(1f);
        goToMaps.setVisibility(View.VISIBLE);
        goToMaps1.setScaleX(1);
        goToMaps1.setScaleY(1);
        goToMaps1.setTranslationY(0);
        goToMaps1.setAlpha(1f);
        goToMaps1.setVisibility(View.GONE);
        goToMaps1.animate().alpha(1).start();
        showList(true);
    }

    public void goToMapsActivity(View view){
        Handler handler = new Handler();
        long[] time = {2000};
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if(time[0]<=2000 && time[0]>1300) {
                    showList(false);
                    goToMaps1.setVisibility(View.INVISIBLE);
                    goToMaps.animate().scaleX(3).scaleY(3).translationY(-1000).setDuration(500).start();
                    goToMaps1.animate().scaleX(3).scaleY(3).translationY(-1000).setDuration(500).start();
                    time[0]-=700;
                    handler.postDelayed(this,700);
                }
                else
                if(time[0]<=1300 && time[0]>1200) {
                    goToMaps.setVisibility(View.GONE);
                    goToMaps1.setVisibility(View.VISIBLE);
                    goToMaps1.animate().scaleX(100).scaleY(100).alpha(0.1f).setDuration(300).start();
                    time[0]-=100;
                    handler.postDelayed(this, 100);
                }
                else if(time[0] == 1200){
                    Intent intent = new Intent(getApplicationContext(),MapsActivity.class);
                    startActivity(intent);
                    goToMaps1.setVisibility(View.GONE);
                    time[0]-=1000;
                }
            }
        };
        runnable.run();
    }
    public void showList(boolean show){
        if(show){
            pinnedPlaces.setVisibility(View.VISIBLE);
        }
        else{
            pinnedPlaces.setVisibility(View.GONE);
        }
    }

    private void updateListView(String location){
        address.add(location);
        ArrayAdapter<String> addressAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,address);
        pinnedPlaces.setAdapter(addressAdapter);
    }
}

/**
 *  TODO:
 *      pinnedList of maps needs to be perfected!
 *      A possible firebase integration would be great! To store pinned list
 * **/
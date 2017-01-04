package com.diegusweb.dev.misionarbol.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.diegusweb.dev.misionarbol.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Route;

/**
 * Created by HP on 03/01/2017.
 */

public class RouteActivity extends AppCompatActivity {
    private GoogleMap googleMap;
    private  int newString;

    List<Route> RouteLists = new ArrayList<>();
    private ArrayList<Route> arraylist;

    Marker marker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route);


        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                newString= 0;
            } else {
                newString= extras.getInt("STRING_I_NEED");
            }
        } else {
            newString= (int) savedInstanceState.getSerializable("STRING_I_NEED");
        }

        Log.d("DiegoResults:", "intent  - " +newString);

    }

}

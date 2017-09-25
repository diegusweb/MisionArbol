package com.diegusweb.dev.arbolurbano.activity.streetView;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.diegusweb.dev.arbolurbano.R;
import com.google.android.gms.maps.OnStreetViewPanoramaReadyCallback;
import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.SupportStreetViewPanoramaFragment;
import com.google.android.gms.maps.model.LatLng;


public class StreetActivity extends AppCompatActivity implements
        OnStreetViewPanoramaReadyCallback {  // PanoramaReadyCallback to use StreetViewPanorama.

    private static final LatLng NY_TIME_SQUARE = new LatLng(40.758952, -73.985174); // sample input.
    private StreetViewPanorama mStreetViewPanorama; // the object that handle panorama preview.
    SupportMapFragment mapFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // mapFrag = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.streetviewpanorama);
       // mapFrag.getMapAsync(this);


       SupportStreetViewPanoramaFragment panoramaFragment =
                (SupportStreetViewPanoramaFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.streetviewpanorama);

        panoramaFragment.getStreetViewPanoramaAsync(this); // call this to use the fragment.

        //agregarToolbar();
    }

    /**
     * after panorama get ready, you can declare the position.
     */
    @Override
    public void onStreetViewPanoramaReady(StreetViewPanorama streetViewPanorama) {
        mStreetViewPanorama = streetViewPanorama;
        mStreetViewPanorama.setPosition(NY_TIME_SQUARE); // where the street view will be shown.
        /** you can control the inputs into street view */
        mStreetViewPanorama.setUserNavigationEnabled(false);
        mStreetViewPanorama.setPanningGesturesEnabled(true);
        mStreetViewPanorama.setZoomGesturesEnabled(true);
    }






    private void agregarToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);

        }

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

}

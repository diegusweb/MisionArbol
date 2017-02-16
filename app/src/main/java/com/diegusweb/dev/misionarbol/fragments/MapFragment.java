package com.diegusweb.dev.misionarbol.fragments;


import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;

import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.diegusweb.dev.misionarbol.R;
import com.diegusweb.dev.misionarbol.activity.report.ReportActivity;
import com.diegusweb.dev.misionarbol.activity.treeLibrary.TreeDetailActivity;
import com.diegusweb.dev.misionarbol.helper.InfoConstants;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MapFragment extends Fragment implements OnMapReadyCallback {

    private MapView mapView;
    private GoogleMap googleMapa;

    FloatingActionButton fab;


    private static final int MY_LOCATION_REQUEST_CODE = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_map, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mapView = (MapView) view.findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        mapView.onResume();
        mapView.getMapAsync(this);//when you already implement OnMapReadyCallback in your fragment

        fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.hide();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getActivity(), ReportActivity.class);
                //i.putExtra("STRING_I_NEED", id);
                startActivity(i);

            }
        });

        fab.show();

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMapa = googleMap;

        LatLng augsburg = new LatLng(-17.414,-66.1653);


        LocationManager locationManager = (LocationManager)getActivity().getSystemService(getActivity().LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        String bestProvider = locationManager.getBestProvider(criteria, true);

        if (ActivityCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        Location location = locationManager.getLastKnownLocation(bestProvider);
        if (location != null) {
            onLocationChanged(location);
        }
       // locationManager.requestLocationUpdates(bestProvider, 2000, 0, this);


        googleMapa.setMyLocationEnabled(true);
       // googleMapa.moveCamera(CameraUpdateFactory.newLatLngZoom(augsburg, 13));


    }

    public void onLocationChanged(Location location) {


        LatLng loc = new LatLng(location.getLatitude(), location.getLongitude());

        // create marker
        /*googleMapa.addMarker(new MarkerOptions()
                .position(loc)
                .title("Facebook")
                .snippet("Facebook HQ: Menlo Park"));*/

        //googleMapa.moveCamera(CameraUpdateFactory.newLatLngZoom(loc, 17));
        googleMapa.animateCamera(CameraUpdateFactory.newLatLngZoom(loc, 16.0f));

    }
}


package com.diegusweb.dev.misionarbol.fragments;


import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.LocationManager;
import android.os.Bundle;
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
public class MapFragment extends Fragment implements SearchView.OnQueryTextListener {

    MapView mMapView;
    private GoogleMap googleMap;

    private LocationManager locationManager;
    private ViewGroup mRoot;

    private ArrayList<Place> arraylist;

    private ArrayList<Marker> mMarkerArray = new ArrayList<Marker>();

    Marker marker;

    FloatingActionButton fab;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_map, container, false);
        View v = inflater.inflate(R.layout.fragment_map, container, false);

        mRoot = (RelativeLayout) v.findViewById(R.id.containerMap);

        mMapView = (MapView) v.findViewById(R.id.map);
        mMapView.onCreate(savedInstanceState);
        mMapView.onResume();// needed to get the map to display immediately

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        googleMap = mMapView.getMap();

        setMarkerInitMap();

        fab = (FloatingActionButton) v.findViewById(R.id.fab);
        fab.hide();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getActivity(), ReportActivity.class);
                //i.putExtra("STRING_I_NEED", id);
                startActivity(i);

            }
        });

        return v;
    }

    private void setMarkerInitMap() {

        // googleMap.setMyLocationEnabled(true);

        LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        String provider = locationManager.getBestProvider(criteria, true);


        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location myLocation = locationManager.getLastKnownLocation(provider);
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);


        final double latitude = myLocation.getLatitude();
        final double longitude = myLocation.getLongitude();

        //final double latitude = -17.39167010757299;
        //final double longitude = -66.16013467311859;

        InfoConstants.latCurrent = latitude;
        InfoConstants.lonCurrent = longitude;

        LatLng NYC = new LatLng(latitude, longitude);

        //Info marker  city and country
        setInfoCurrent(latitude, longitude);

        //Marker ubicacion actual
        googleMap.addMarker(new MarkerOptions()
                .position(NYC)
                .title("Ubicacion Actual"))
                .setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));

        //marcador para destino
        /*marker = googleMap.addMarker(new MarkerOptions()
                .position(NYC)
                .title("Ubicacion Destino")
                .snippet("Deslize el mapa para buscar direccion.")
                .flat(true)
                .draggable(false)
                .alpha(0f)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.id_map_marker))
                .draggable(true).visible(true));*/

        // googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(NYC, 17));
        final CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(NYC)      // Sets the center of the map to Mountain View
                .zoom(InfoConstants.zoomMap)                   // Sets the zoom
                //.bearing(90)                // Sets the orientation of the camera to east
                .tilt(20)                   // Sets the tilt of the camera to 30 degrees
                .build();
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        googleMap.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {
            @Override
            public void onCameraChange(CameraPosition position) {
                LatLng centerOfMap = googleMap.getCameraPosition().target;
                try {
                    Geocoder gcd = new Geocoder(getActivity(), Locale.getDefault());
                    List<Address> addresses = gcd.getFromLocation(centerOfMap.latitude, centerOfMap.longitude, 1);

                    InfoConstants.latDes = centerOfMap.latitude;
                    InfoConstants.lonDes = centerOfMap.longitude;

                    if (addresses.size() > 0) {
                        String address = addresses.get(0).getAddressLine(0);
                        String city = addresses.get(0).getLocality();
                        String state = addresses.get(0).getAdminArea();
                        String country = addresses.get(0).getCountryName();
                        String postalCode = addresses.get(0).getPostalCode();
                        Log.d("Actual1", address +" / "+city+"/"+country+"/"+state+"/"+postalCode);
                        // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()

                        //String city = addresses.get(0).getLocality();
                        //String country = addresses.get(0).getCountryName();

                        TextView text = (TextView) getActivity().findViewById(R.id.direccion);
                        text.setText(address);

                        fab.show();


                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                // Update your Marker's position to the center of the Map.
              //  marker.setPosition(centerOfMap);
            }
        });

    }

    private void setInfoCurrent(double latitude, double longitude) {

        //get city
        try {
            Geocoder gcd = new Geocoder(getActivity(), Locale.getDefault());
            List<Address> addresses = gcd.getFromLocation(latitude, longitude,1);
            if (addresses.size() > 0){
                String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                String city = addresses.get(0).getLocality();
                String state = addresses.get(0).getAdminArea();
                String country = addresses.get(0).getCountryName();
                String postalCode = addresses.get(0).getPostalCode();
                String knownName = addresses.get(0).getFeatureName();

                Log.d("Actual1", address +" / "+city+"/"+country+"/"+state+"/"+postalCode);

                InfoConstants.CITY = city;
                InfoConstants.COUNTRY = country;

            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}


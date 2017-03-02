package com.diegusweb.dev.misionarbol.fragments;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
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
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
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
import com.diegusweb.dev.misionarbol.adapter.AdapteTestItems;
import com.diegusweb.dev.misionarbol.api.ApiClient;
import com.diegusweb.dev.misionarbol.api.ApiInterface;
import com.diegusweb.dev.misionarbol.helper.InfoConstants;
import com.diegusweb.dev.misionarbol.models.PointsTree;
import com.diegusweb.dev.misionarbol.models.TestItems;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class MapFragment extends Fragment implements OnMapReadyCallback {

    private MapView mapView;
    private GoogleMap googleMapa;

    List<PointsTree> RouteLists = new ArrayList<>();
    private ArrayList<PointsTree> arraylist;

    //FloatingActionButton fab;


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

        /*fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.hide();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getActivity(), ReportActivity.class);
                //i.putExtra("STRING_I_NEED", id);
                startActivity(i);

            }
        });

        fab.show();*/


        final FrameLayout frameLayout = (FrameLayout) view.findViewById(R.id.frame_layout);
        frameLayout.getBackground().setAlpha(0);
        final FloatingActionsMenu fabMenu = (FloatingActionsMenu) view.findViewById(R.id.fab_menu);
        fabMenu.setOnFloatingActionsMenuUpdateListener(new FloatingActionsMenu.OnFloatingActionsMenuUpdateListener() {
            @Override
            public void onMenuExpanded() {
                frameLayout.getBackground().setAlpha(240);
                frameLayout.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        fabMenu.collapse();
                        return true;
                    }
                });
            }

            @Override
            public void onMenuCollapsed() {
                frameLayout.getBackground().setAlpha(0);
                frameLayout.setOnTouchListener(null);
            }
        });

        view.findViewById(R.id.fab_photo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(getActivity(), "Clicked pink Floating Action Button", Toast.LENGTH_SHORT).show();
                InfoConstants.TYPE_SELECT = 1;
                Intent i = new Intent(getActivity(), ReportActivity.class);
                //i.putExtra("STRING_I_NEED", id);
                startActivity(i);
            }
        });

        view.findViewById(R.id.fab_event).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(getActivity(), "Clicked pink Floating Action Button", Toast.LENGTH_SHORT).show();
                InfoConstants.TYPE_SELECT = 2;
                Intent i = new Intent(getActivity(), ReportActivity.class);
                //i.putExtra("STRING_I_NEED", id);
                startActivity(i);
            }
        });

        view.findViewById(R.id.fab_photo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(getActivity(), "Clicked pink Floating Action Button", Toast.LENGTH_SHORT).show();
                InfoConstants.TYPE_SELECT = 3;
                Intent i = new Intent(getActivity(), ReportActivity.class);
                //i.putExtra("STRING_I_NEED", id);
                startActivity(i);
            }
        });

        view.findViewById(R.id.fab_others).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(getActivity(), "Clicked pink Floating Action Button", Toast.LENGTH_SHORT).show();
                InfoConstants.TYPE_SELECT = 4;
                Intent i = new Intent(getActivity(), ReportActivity.class);
                //i.putExtra("STRING_I_NEED", id);
                startActivity(i);
            }
        });

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

        getPointsForMap();

    }

    public void onLocationChanged(Location location)
    {
        LatLng loc = new LatLng(location.getLatitude(), location.getLongitude());

        // create marker
        /*googleMapa.addMarker(new MarkerOptions()
                .position(loc)
                .title("Facebook")
                .snippet("Facebook HQ: Menlo Park"));*/

        //googleMapa.moveCamera(CameraUpdateFactory.newLatLngZoom(loc, 17));
        googleMapa.animateCamera(CameraUpdateFactory.newLatLngZoom(loc, 16.0f));



    }

    public void getPointsForMap()
    {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Log.d("Resultados","otrooo");

        Call<List<PointsTree>> call = apiService.getPointTree();
        call.enqueue(new Callback<List<PointsTree>>() {
            @Override
            public void onResponse(Call<List<PointsTree>> call, Response<List<PointsTree>> response) {
                List<PointsTree> demo = response.body();

                Log.d("LISTAAAAA ", "num"+demo.size());

                /*if(response.isSuccessful()) {
                    List<TestItems> changesList = response.body();
                    changesList.forEach(change -> System.out.println(change.getTitle()));
                } else {
                    System.out.println(response.errorBody());
                }*/

                setLines(demo);

                StringBuilder builder = new StringBuilder();
                TestItems movie;

                for (PointsTree repo: demo) {
                    builder.append(repo.getTitle() + "->" + repo.getId());

                    movie = new TestItems("asdas",repo.getId(), repo.getTitle());
                }
                Toast.makeText(getActivity(), builder.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<PointsTree>> call, Throwable t) {

            }
        });
    }

    private void setLines(List<PointsTree> routes){

        this.arraylist = new ArrayList<PointsTree>();
        this.arraylist.addAll(routes);


        List<LatLng> pointsIda = new ArrayList<LatLng>();
        List<LatLng> pointsVuelta = new ArrayList<LatLng>();

        List<Marker> markers = new ArrayList<Marker>();

        int height = 150;
        int width = 150;


        for (int i = 0; i < this.arraylist.size(); i++) {

            LatLng LOWER = new LatLng(this.arraylist.get(i).getLat(), this.arraylist.get(i).getLng());
            //pointsIda.add(LOWER);


            if(this.arraylist.get(i).getType_id().getId() == 1){
                BitmapDrawable bitmapdraw=(BitmapDrawable)getResources().getDrawable(R.drawable.id_marker_green);
                Bitmap b = bitmapdraw.getBitmap();
                Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);

                Marker marker = googleMapa.addMarker(new MarkerOptions()
                        .icon(BitmapDescriptorFactory.fromBitmap(smallMarker))
                        .title(this.arraylist.get(i).getType_id().getTitle() )
                        .position(LOWER)); //...

                markers.add(marker);
            }

            if(this.arraylist.get(i).getType_id().getId() == 2){
                BitmapDrawable bitmapdraw=(BitmapDrawable)getResources().getDrawable(R.drawable.id_marker_dead);
                Bitmap b = bitmapdraw.getBitmap();
                Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);

                Marker marker = googleMapa.addMarker(new MarkerOptions()
                        .title(this.arraylist.get(i).getType_id().getTitle() )
                        .icon(BitmapDescriptorFactory.fromBitmap(smallMarker))
                        .position(LOWER)); //...

                markers.add(marker);
            }

            if(this.arraylist.get(i).getType_id().getId() == 5){
                BitmapDrawable bitmapdraw=(BitmapDrawable)getResources().getDrawable(R.drawable.id_marker_trunk);
                Bitmap b = bitmapdraw.getBitmap();
                Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);

                Marker marker = googleMapa.addMarker(new MarkerOptions()
                        .title(this.arraylist.get(i).getType_id().getTitle() )
                        .icon(BitmapDescriptorFactory.fromBitmap(smallMarker))
                        .position(LOWER)); //...

                markers.add(marker);
            }

            if(this.arraylist.get(i).getType_id().getId() == 4){
                BitmapDrawable bitmapdraw=(BitmapDrawable)getResources().getDrawable(R.drawable.id_marker_plant);
                Bitmap b = bitmapdraw.getBitmap();
                Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);

                Marker marker = googleMapa.addMarker(new MarkerOptions()
                        .title(this.arraylist.get(i).getType_id().getTitle() )
                        .icon(BitmapDescriptorFactory.fromBitmap(smallMarker))
                        .position(LOWER)); //...

                markers.add(marker);
            }


        }

// after loop:
        markers.size();

    }
}


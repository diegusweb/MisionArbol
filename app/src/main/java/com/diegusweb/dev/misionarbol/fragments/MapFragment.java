package com.diegusweb.dev.misionarbol.fragments;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.diegusweb.dev.misionarbol.MainActivity;
import com.diegusweb.dev.misionarbol.R;
import com.diegusweb.dev.misionarbol.activity.report.ReportActivity;
import com.diegusweb.dev.misionarbol.api.ApiClient;
import com.diegusweb.dev.misionarbol.api.ApiInterface;
import com.diegusweb.dev.misionarbol.helper.InfoConstants;
import com.diegusweb.dev.misionarbol.models.PointsTree;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class MapFragment extends Fragment implements OnMapReadyCallback, SearchView.OnQueryTextListener {

    private static final int MY_PERMISO_FINE_LOCATION = 1;
    private static final int MY_PERMISO_COURSE_LOCATION = 2 ;
    private static final int MY_PERMISO_CAMARA = 3;
    private static final int MY_PERMISO_STORAGE= 4;
    private static final int MY_PERMISO_STORAGE_READ = 5;
    private static final int MY_PERMISO_STORAGE_READ_NET = 6;
    private MapView mapView;
    private GoogleMap googleMapa;

    List<PointsTree> RouteLists = new ArrayList<>();
    private ArrayList<PointsTree> arraylist;

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
        mapView.getMapAsync(this);


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
                getPointsForMap();
            }
        });

        view.findViewById(R.id.fab_tree_womder).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InfoConstants.TYPE_SELECT = 1;
                Intent i = new Intent(getActivity(), ReportActivity.class);

                startActivity(i);
            }
        });

        view.findViewById(R.id.fab_tree_seco).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InfoConstants.TYPE_SELECT = 2;
                Intent i = new Intent(getActivity(), ReportActivity.class);
                //i.putExtra("STRING_I_NEED", id);
                startActivity(i);
            }
        });

        view.findViewById(R.id.fab_tree_danger).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InfoConstants.TYPE_SELECT = 3;
                Intent i = new Intent(getActivity(), ReportActivity.class);
                //i.putExtra("STRING_I_NEED", id);
                startActivity(i);
            }
        });

        view.findViewById(R.id.fab_tree_plantar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InfoConstants.TYPE_SELECT = 4;
                Intent i = new Intent(getActivity(), ReportActivity.class);
                //i.putExtra("STRING_I_NEED", id);
                startActivity(i);
            }
        });

        view.findViewById(R.id.fab_tree_tronco).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InfoConstants.TYPE_SELECT = 5;
                Intent i = new Intent(getActivity(), ReportActivity.class);
                //i.putExtra("STRING_I_NEED", id);
                startActivity(i);
            }
        });

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMapa = googleMap;

        LocationManager locationManager = (LocationManager)getActivity().getSystemService(getActivity().LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        String bestProvider = locationManager.getBestProvider(criteria, true);



        if (ActivityCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {


            if(ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION))
            {
                new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Atencio!")
                        .setContentText("Debes otorgar permisos para el mapa")
                        .setConfirmText("Solicitar Permisos")
                        .setCancelText("Cancelar")
                        .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                sweetAlertDialog.cancel();
                            }
                        })
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                sweetAlertDialog.cancel();
                                ActivityCompat.requestPermissions(getActivity(), new String[] {Manifest.permission.ACCESS_FINE_LOCATION},
                                        MY_PERMISO_FINE_LOCATION);

                                ActivityCompat.requestPermissions(getActivity(), new String[] {Manifest.permission.ACCESS_COARSE_LOCATION},
                                        MY_PERMISO_COURSE_LOCATION);
                            }
                        }).show();

            }
            else{
                ActivityCompat.requestPermissions(getActivity(), new String[] {Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISO_FINE_LOCATION);
            }
            //return;
        }
        else{
            Location location = locationManager.getLastKnownLocation(bestProvider);
            if (location != null) {
                onLocationChanged(location);
            }

            googleMapa.setMyLocationEnabled(true);

            getPointsForMap();
        }


        //camera
        if (ActivityCompat.checkSelfPermission(getActivity(),
                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ) {

            if(ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.CAMERA))
            {
                new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Atencio!")
                        .setContentText("Debes otorgar permisos para la camara")
                        .setConfirmText("Solicitar Permisos")
                        .setCancelText("Cancelar")
                        .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                sweetAlertDialog.cancel();
                            }
                        })
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                sweetAlertDialog.cancel();
                                ActivityCompat.requestPermissions(getActivity(), new String[] {Manifest.permission.CAMERA},
                                        MY_PERMISO_CAMARA);
                            }
                        }).show();

            }
            else{
                ActivityCompat.requestPermissions(getActivity(), new String[] {Manifest.permission.CAMERA},
                        MY_PERMISO_CAMARA);
            }
        }
        else{
            //startActivityForResult(intent,0);

        }

        //storage
        if (ActivityCompat.checkSelfPermission(getActivity(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ) {

            if(ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE))
            {
                new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Atencio!")
                        .setContentText("Debes otorgar permisos para la Storage")
                        .setConfirmText("Solicitar Permisos")
                        .setCancelText("Cancelar")
                        .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                sweetAlertDialog.cancel();
                            }
                        })
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                sweetAlertDialog.cancel();
                                ActivityCompat.requestPermissions(getActivity(), new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                        MY_PERMISO_STORAGE);
                            }
                        }).show();

            }
            else{
                ActivityCompat.requestPermissions(getActivity(), new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        MY_PERMISO_STORAGE);

            }
        }
        else{
            //startActivityForResult(intent,0);

        }

        //NETWORK
        if (ActivityCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_NETWORK_STATE) != PackageManager.PERMISSION_GRANTED ) {

            if(ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.ACCESS_NETWORK_STATE))
            {
                new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Atencio!")
                        .setContentText("Debes otorgar permisos para la Network")
                        .setConfirmText("Solicitar Permisos")
                        .setCancelText("Cancelar")
                        .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                sweetAlertDialog.cancel();
                            }
                        })
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                sweetAlertDialog.cancel();
                                ActivityCompat.requestPermissions(getActivity(), new String[] {Manifest.permission.ACCESS_NETWORK_STATE},
                                        MY_PERMISO_STORAGE_READ_NET);
                            }
                        }).show();

            }
            else{
                ActivityCompat.requestPermissions(getActivity(), new String[] {Manifest.permission.ACCESS_NETWORK_STATE},
                        MY_PERMISO_STORAGE_READ_NET);
            }
        }
        else{
            //startActivityForResult(intent,0);

        }


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setHasOptionsMenu(true);
    }

    public void onLocationChanged(Location location)
    {
        LatLng loc = new LatLng(location.getLatitude(), location.getLongitude());
        googleMapa.animateCamera(CameraUpdateFactory.newLatLngZoom(loc, 16.0f));
    }

    public void getPointsForMap()
    {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<List<PointsTree>> call = apiService.getPointTree();
        call.enqueue(new Callback<List<PointsTree>>() {
            @Override
            public void onResponse(Call<List<PointsTree>> call, Response<List<PointsTree>> response) {
                List<PointsTree> demo = response.body();

                if(response.isSuccessful()) {
                    setLines(demo);
                } else {
                    System.out.println(response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<List<PointsTree>> call, Throwable t) {

            }
        });
    }

    private void setLines(List<PointsTree> routes){

        this.arraylist = new ArrayList<PointsTree>();

        if(!this.arraylist.isEmpty()){
            this.arraylist.clear();
            this.arraylist = null;
        }

        this.arraylist.addAll(routes);

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
                        .snippet("Population: 4,137,400")
                        .position(LOWER));

                markers.add(marker);
            }

            if(this.arraylist.get(i).getType_id().getId() == 2){
                BitmapDrawable bitmapdraw=(BitmapDrawable)getResources().getDrawable(R.drawable.id_marker_dead);
                Bitmap b = bitmapdraw.getBitmap();
                Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);

                Marker marker = googleMapa.addMarker(new MarkerOptions()
                        .title(this.arraylist.get(i).getType_id().getTitle() )
                        .icon(BitmapDescriptorFactory.fromBitmap(smallMarker))
                        .snippet("Population: 4,137,400")
                        .position(LOWER));

                markers.add(marker);
            }

            if(this.arraylist.get(i).getType_id().getId() == 3){
                BitmapDrawable bitmapdraw=(BitmapDrawable)getResources().getDrawable(R.drawable.id_marker_danger);
                Bitmap b = bitmapdraw.getBitmap();
                Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);

                Marker marker = googleMapa.addMarker(new MarkerOptions()
                        .title(this.arraylist.get(i).getType_id().getTitle() )
                        .icon(BitmapDescriptorFactory.fromBitmap(smallMarker))
                        .snippet("Population: 4,137,400")
                        .position(LOWER));

                markers.add(marker);
            }

            if(this.arraylist.get(i).getType_id().getId() == 5){
                BitmapDrawable bitmapdraw=(BitmapDrawable)getResources().getDrawable(R.drawable.id_marker_trunk);
                Bitmap b = bitmapdraw.getBitmap();
                Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);

                Marker marker = googleMapa.addMarker(new MarkerOptions()
                        .title(this.arraylist.get(i).getType_id().getTitle() )
                        .icon(BitmapDescriptorFactory.fromBitmap(smallMarker))
                        .snippet("Population: 4,137,400")
                        .position(LOWER));

                markers.add(marker);
            }

            if(this.arraylist.get(i).getType_id().getId() == 4){
                BitmapDrawable bitmapdraw=(BitmapDrawable)getResources().getDrawable(R.drawable.id_marker_plant);
                Bitmap b = bitmapdraw.getBitmap();
                Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);

                Marker marker = googleMapa.addMarker(new MarkerOptions()
                        .title(this.arraylist.get(i).getType_id().getTitle() )
                        .icon(BitmapDescriptorFactory.fromBitmap(smallMarker))
                        .snippet("Population: 4,137,400")
                        .position(LOWER));

                markers.add(marker);
            }
        }

        markers.size();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        //super.onCreateOptionsMenu(menu, inflater);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView sv = new SearchView(((MainActivity) getActivity()).getSupportActionBar().getThemedContext());
        MenuItemCompat.setShowAsAction(item, MenuItemCompat.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW | MenuItemCompat.SHOW_AS_ACTION_IF_ROOM);
        MenuItemCompat.setActionView(item, sv);
        sv.setOnQueryTextListener(this);
        sv.setIconifiedByDefault(false);
        sv.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Diego", "ENTROOOO");
            }
        });

        MenuItemCompat.setOnActionExpandListener(item, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                Toast.makeText(getActivity(), "Closed", Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                Toast.makeText(getActivity(), "Opened", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        Log.d("Submitted", query);
        //seachMapCurrent(query);
        final InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        Log.d("Changed", newText);
        return true;
    }
}


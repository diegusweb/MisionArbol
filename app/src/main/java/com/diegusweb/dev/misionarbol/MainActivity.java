package com.diegusweb.dev.misionarbol;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.diegusweb.dev.misionarbol.fragments.BusquedaFragment;
import com.diegusweb.dev.misionarbol.fragments.InfoFragment;
import com.diegusweb.dev.misionarbol.fragments.ListFragment;
import com.diegusweb.dev.misionarbol.fragments.MenuFragment;
import com.diegusweb.dev.misionarbol.fragments.MyReportFragment;
import android.Manifest;
import android.widget.Toast;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;


    private static final int MY_PERMISO_FINE_LOCATION = 1;
    private static final int MY_PERMISO_COURSE_LOCATION = 2 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        agregarToolbar();

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        if (navigationView != null) {
            prepararDrawer(navigationView);
            // Seleccionar item por defecto
            seleccionarItem(navigationView.getMenu().getItem(0));
        }

        hasNetworkConnection();

    }

    public  void hasNetworkConnection() {
        ConnectivityManager cm = (ConnectivityManager) this.getApplicationContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo == null) {
            Toast.makeText(this.getApplicationContext(), "No internet !!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (networkInfo.getTypeName().equalsIgnoreCase("WIFI"))
            if (networkInfo.isConnected())
                Toast.makeText(this.getApplicationContext(), "Mobile internet !!", Toast.LENGTH_SHORT).show();
        if (networkInfo.getTypeName().equalsIgnoreCase("MOBILE"))
            if (networkInfo.isConnected())
                Toast.makeText(this.getApplicationContext(), "Wifi internet !!", Toast.LENGTH_SHORT).show();

    }

    private void agregarToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        if (ab != null) {
            // Poner ícono del drawer toggle
            ab.setHomeAsUpIndicator(R.drawable.drawer_toggle);
            ab.setDisplayHomeAsUpEnabled(true);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);

        MenuItem search = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(search);
        search(searchView);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void search(SearchView searchView) {

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d("Demo",query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d("Demo ss",newText);
               // if (mAdapter != null) mAdapter.getFilter().filter(newText);
                return true;
            }
        });
    }

    private void prepararDrawer(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        seleccionarItem(menuItem);
                        drawerLayout.closeDrawers();
                        return true;
                    }
                });

    }

    private void seleccionarItem(MenuItem itemDrawer) {
        Fragment fragmentoGenerico = null;
        FragmentManager fragmentManager = getSupportFragmentManager();

        switch (itemDrawer.getItemId()) {
            case R.id.item_init:
                fragmentoGenerico = new BusquedaFragment();
                break;
            case R.id.item_contact:
                fragmentoGenerico = new ListFragment();
                break;
            case R.id.item_how_work:
                fragmentoGenerico = new InfoFragment();
                // Fragmento para la sección Categorías
                break;
            case R.id.item_reportes:
                fragmentoGenerico = new MyReportFragment();
                // Fragmento para la sección Categorías
                break;
            case R.id.item_setting:
                // Iniciar actividad de configuración
                break;

        }
        if (fragmentoGenerico != null) {
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.content_primary, fragmentoGenerico)
                    .commit();
        }

        // Setear título actual
        setTitle(itemDrawer.getTitle());
    }

    //permisos
}

package com.diegusweb.dev.arbolurbano.helper;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.text.Html;

/**
 * Created by DIego on 10/8/2017.
 */

public class Utils {

    public static boolean hasNetworkConnection( Context ctx) {
        ConnectivityManager cm = (ConnectivityManager) ctx
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo == null) {
            return true;
        }


        /*if (networkInfo.getTypeName().equalsIgnoreCase("WIFI"))
            if (networkInfo.isConnected())
                Toast.makeText(this.getApplicationContext(), "Mobile internet !!", Toast.LENGTH_SHORT).show();
        if (networkInfo.getTypeName().equalsIgnoreCase("MOBILE"))
            if (networkInfo.isConnected())
                Toast.makeText(this.getApplicationContext(), "Wifi internet !!", Toast.LENGTH_SHORT).show();*/

        return false;

    }
}

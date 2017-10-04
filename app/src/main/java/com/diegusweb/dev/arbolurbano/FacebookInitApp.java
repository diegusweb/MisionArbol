package com.diegusweb.dev.arbolurbano;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

//import com.facebook.FacebookSdk;
//import com.facebook.login.LoginManager;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

/**
 * Created by HP on 09/01/2017.
 */

public class FacebookInitApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //initDb();
       // initFacebook();

    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        DBTearDown();
    }

    private void DBTearDown() {
        FlowManager.destroy();
    }

    private void initDb() {
        //FlowManager.init(this);

        FlowManager.init (
                new FlowConfig.Builder (this)
                        .openDatabasesOnInit (true)
                        .build ());
        Log.d("Demo","ENTOOOOOOO");
    }

    //private void initFacebook() {
      //  FacebookSdk.sdkInitialize(this);
   // }

    
}

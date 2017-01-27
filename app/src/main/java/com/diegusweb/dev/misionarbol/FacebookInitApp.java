package com.diegusweb.dev.misionarbol;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

import com.diegusweb.dev.misionarbol.activity.login.LoginActivity;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

/**
 * Created by HP on 09/01/2017.
 */

public class FacebookInitApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initFacebook();
        initDb();
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
        Log.d("Demo","ENTOOOOOOO");
        FlowManager.init(new FlowConfig.Builder(this).build());
    }

    private void initFacebook() {
        FacebookSdk.sdkInitialize(this);
    }

    public void logout() {
        LoginManager.getInstance().logOut();
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}

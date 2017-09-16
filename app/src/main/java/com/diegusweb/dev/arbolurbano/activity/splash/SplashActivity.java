package com.diegusweb.dev.arbolurbano.activity.splash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.diegusweb.dev.arbolurbano.R;

import com.diegusweb.dev.arbolurbano.MainActivity;

public class SplashActivity extends AppCompatActivity {

    private final int DURACION_SPLASH = 3000; // 3 segundos

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // This instantiates DBFlow
       // FlowManager.init(new FlowConfig.Builder(this).build());

        Thread myThread = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(3000);
                   // Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        myThread.start();
    }
}

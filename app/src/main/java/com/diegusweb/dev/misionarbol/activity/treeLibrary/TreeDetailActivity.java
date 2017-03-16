package com.diegusweb.dev.misionarbol.activity.treeLibrary;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.diegusweb.dev.misionarbol.R;
import com.diegusweb.dev.misionarbol.api.ApiClient;
import com.diegusweb.dev.misionarbol.api.ApiInterface;
import com.diegusweb.dev.misionarbol.helper.InfoConstants;
import com.diegusweb.dev.misionarbol.models.PointsTree;
import com.diegusweb.dev.misionarbol.models.Tree;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by HP on 03/01/2017.
 */

public class TreeDetailActivity extends AppCompatActivity {

    private  int newString;
    ImageView imageView;
    TextView title, description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tree_detail);

        agregarToolbar();

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                newString= 0;
            } else {
                newString= extras.getInt("STRING_I_NEED");

                getTreeId(newString);
            }
        } else {
            newString= (int) savedInstanceState.getSerializable("STRING_I_NEED");
        }

        Log.d("DiegoResults:", "intent  - " +newString);


    }

    private void getTreeId(int id){

        title = (TextView)findViewById(R.id.text_product_name);
        description = (TextView)findViewById(R.id.text_product_stock);

        title.setText(InfoConstants.ONE_TREE_LIBRARY.getTitle());
        description.setText(InfoConstants.ONE_TREE_LIBRARY.getDescription());



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

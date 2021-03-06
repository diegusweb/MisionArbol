package com.diegusweb.dev.misionarbol.activity.treeLibrary;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
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
    TextView nameScientist, descriptionLess, descriptionMore;

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

        nameScientist = (TextView)findViewById(R.id.text_name_scientist);
        descriptionLess = (TextView)findViewById(R.id.text_description_less);
        descriptionMore = (TextView)findViewById(R.id.text_product_description);

        nameScientist.setText(InfoConstants.ONE_TREE_LIBRARY.getName_scientist());
        descriptionLess.setText(InfoConstants.ONE_TREE_LIBRARY.getDescription_less());
        descriptionMore.setText(InfoConstants.ONE_TREE_LIBRARY.getDescription());

        imageView = (ImageView)findViewById(R.id.bgheader);


        Glide.with(this)
                .load(InfoConstants.BASE_URL_IMG+InfoConstants.ONE_TREE_LIBRARY.getPath())
                .into(imageView);
    }

    private void agregarToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(InfoConstants.ONE_TREE_LIBRARY.getTitle());
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

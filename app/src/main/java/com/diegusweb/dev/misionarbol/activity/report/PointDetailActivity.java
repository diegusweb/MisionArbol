package com.diegusweb.dev.misionarbol.activity.report;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.diegusweb.dev.misionarbol.R;
import com.diegusweb.dev.misionarbol.helper.InfoConstants;

public class PointDetailActivity extends AppCompatActivity {

    private  int newString;
    ImageView imageView, image_stock_icon;
    TextView nameScientist, descriptionLess, descriptionMore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_point_detail);

        agregarToolbar();

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                newString= 0;
            } else {
                newString= extras.getInt("STRING_I_NEED");

                getTreeDetailId();
            }
        } else {
            newString= (int) savedInstanceState.getSerializable("STRING_I_NEED");
        }

        Log.d("DiegoResults:", "intent  - " +newString);

    }

    private void getTreeDetailId() {
        nameScientist = (TextView)findViewById(R.id.text_name_scientist);
        descriptionLess = (TextView)findViewById(R.id.text_description);


        nameScientist.setText(InfoConstants.ID_TREE_DETAIL.getTitle());
        descriptionLess.setText(InfoConstants.ID_TREE_DETAIL.getDescription());

        imageView = (ImageView)findViewById(R.id.bgheader);


        image_stock_icon = (ImageView)findViewById(R.id.image_stock_icon);

        switch (InfoConstants.ID_TREE_DETAIL.getType_id().getId()) {
            case 1:  image_stock_icon.setImageResource(R.drawable.ic_tree_womder);
                break;
            case 2:  image_stock_icon.setImageResource(R.drawable.ic_tree_seco);
                break;
            case 3:  image_stock_icon.setImageResource(R.drawable.ic_tree_danger);
                break;
            case 4:  image_stock_icon.setImageResource(R.drawable.ic_tree_plantar);
                break;
            case 5:  image_stock_icon.setImageResource(R.drawable.ic_tree_tronco);
                break;
        }

        Log.d("dmeo",InfoConstants.BASE_URL_IMG_POINT+InfoConstants.ID_TREE_DETAIL.getPath());

        Glide.with(this)
                .load(InfoConstants.BASE_URL_IMG_POINT+InfoConstants.ID_TREE_DETAIL.getPath())
                .into(imageView);
    }

    private void agregarToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(InfoConstants.ID_TREE_DETAIL.getTitle());
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

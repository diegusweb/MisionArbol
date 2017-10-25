package com.diegusweb.dev.arbolurbano.activity.treeLibrary;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.diegusweb.dev.arbolurbano.R;
import com.diegusweb.dev.arbolurbano.helper.InfoConstants;
import com.diegusweb.dev.arbolurbano.models.Image;

import java.util.List;

/**
 * Created by HP on 03/01/2017.
 */

public class TreeDetailActivity extends AppCompatActivity {

    private  int newString;
    ImageView imageView;
    TextView nameScientist,
            descriptionLess,
            descriptionMore,
            location,
            requirements, flowers, tronco, hojas, follaje, frutos,
            nameOther, family;

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
        nameOther = (TextView)findViewById(R.id.text_name_other);
        family = (TextView)findViewById(R.id.text_name_family);
        //descriptionLess = (TextView)findViewById(R.id.text_description_less);
        //descriptionMore = (TextView)findViewById(R.id.text_product_description);

        location = (TextView)findViewById(R.id.text_product_ubicacion);
        flowers = (TextView)findViewById(R.id.text_product_flowers);
        requirements = (TextView)findViewById(R.id.text_product_requiremnts);
        tronco = (TextView)findViewById(R.id.text_product_tronco);
        follaje = (TextView)findViewById(R.id.text_product_follage);
        hojas = (TextView)findViewById(R.id.text_product_hojas);
        frutos = (TextView)findViewById(R.id.text_product_frutos);



        nameScientist.setText(InfoConstants.ONE_TREE_LIBRARY.getScientificName());
       // descriptionMore.setText(Html.fromHtml("<div style=\"text-align:justify\">"+InfoConstants.ONE_TREE_LIBRARY.getDescription()+"</div>"));
        location.setText(Html.fromHtml("<div style=\"text-align:justify\">"+InfoConstants.ONE_TREE_LIBRARY.getLocation()+"</div>"));
        flowers.setText(Html.fromHtml("<div style=\"text-align:justify\">"+InfoConstants.ONE_TREE_LIBRARY.getFlowers()+"</div>"));
        requirements.setText(Html.fromHtml("<div style=\"text-align:justify\">"+InfoConstants.ONE_TREE_LIBRARY.getRequirement()+"</div>"));
        tronco.setText(Html.fromHtml("<div style=\"text-align:justify\">"+InfoConstants.ONE_TREE_LIBRARY.getStem()+"</div>"));
        follaje.setText(Html.fromHtml("<div style=\"text-align:justify\">"+InfoConstants.ONE_TREE_LIBRARY.getFoliage()+"</div>"));
        hojas.setText(Html.fromHtml("<div style=\"text-align:justify\">"+InfoConstants.ONE_TREE_LIBRARY.getLeaves()+"</div>"));
        frutos.setText(Html.fromHtml("<div style=\"text-align:justify\">"+InfoConstants.ONE_TREE_LIBRARY.getFruits()+"</div>"));

        nameOther.setText(InfoConstants.ONE_TREE_LIBRARY.getOtherTitle());
        family.setText(InfoConstants.ONE_TREE_LIBRARY.getFamily());

        imageView = (ImageView)findViewById(R.id.bgheader);

        String path = "";
        if(InfoConstants.ONE_TREE_LIBRARY.getListImage().size() == 0)
            path = InfoConstants.BASE_URL_IMG + "/img/no-featured.jpg";
        else
            path = InfoConstants.BASE_URL_IMG + InfoConstants.ONE_TREE_LIBRARY.getListImage().get(0).getThumb();

        Glide.with(this)
                .load(path)
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

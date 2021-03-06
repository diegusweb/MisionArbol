package com.diegusweb.dev.misionarbol.activity.report;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.diegusweb.dev.misionarbol.MainActivity;
import com.diegusweb.dev.misionarbol.R;
import com.diegusweb.dev.misionarbol.adapter.AdapteTestItems;
import com.diegusweb.dev.misionarbol.api.ApiClient;
import com.diegusweb.dev.misionarbol.api.ApiInterface;
import com.diegusweb.dev.misionarbol.fragments.MyDialogFragment;
import com.diegusweb.dev.misionarbol.helper.InfoConstants;
import com.diegusweb.dev.misionarbol.models.ServerResponse;
import com.diegusweb.dev.misionarbol.models.TestItems;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReportActivity extends AppCompatActivity {


    ImageView imageView;
    String mediaPath, mediaPath1;
    String[] mediaColumns = {MediaStore.Video.Media._ID};
    ProgressDialog progressDialog;
    TextView str1, str2;

    ImageView imageView4;
    ImageView image_ic_ok;


    File destination;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        InfoConstants.latDes = 0;
        InfoConstants.latDes = 0;

        agregarToolbar();

        //permisoos
        dialogOpen();
        //----


        Button boton = (Button) findViewById(R.id.btnUbicaion);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ReportActivity.this, MapActivity.class);
                startActivity(i);
            }
        });

        imageView4 = (ImageView)findViewById(R.id.imageView4);
        switch (InfoConstants.TYPE_SELECT) {
            case 1:
                InfoConstants.TYPE_NAME = "Arbol Magnifico";
                imageView4.setImageResource(R.drawable.ic_tree_womder);
                break;
            case 2:
                InfoConstants.TYPE_NAME = "Arbol Seco";
                imageView4.setImageResource(R.drawable.ic_tree_seco);
                break;
            case 3:
                InfoConstants.TYPE_NAME = "Arbol En Peligro";
                imageView4.setImageResource(R.drawable.ic_tree_danger);
                break;
            case 4:
                InfoConstants.TYPE_NAME = "Sitio Para PLantar";
                imageView4.setImageResource(R.drawable.ic_tree_plantar);
                break;
            case 5:
                InfoConstants.TYPE_NAME = "Tronco Cortado";
                imageView4.setImageResource(R.drawable.ic_tree_tronco);
                break;
        }

        image_ic_ok = (ImageView)findViewById(R.id.image_ic_ok);
        image_ic_ok.setVisibility(View.INVISIBLE);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.save, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_save_report) {

            TextView descr = (TextView)findViewById(R.id.txtDescription);
            String value = descr.getText().toString();

            if(InfoConstants.latDes == 0 || InfoConstants.latDes == 0 || value == null){

                Snackbar.make(findViewById(android.R.id.content),
                        Html.fromHtml("<font color=\"#FA3E3E\">Campos Vacios</font>")
                        , Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
            else{
                uploadFile();
                this.finish();
                return true;
            }


        }
        return super.onOptionsItemSelected(item);
    }


    public void dialogOpen(){


        imageView = (ImageView)findViewById(R.id.imageView);

        new AlertDialog.Builder(ReportActivity.this, R.style.Theme_RicoPaRico_Dark_Dialog)
                .setTitle("3 botones")
                .setMessage("¿A donde quieres ir?")
                .setPositiveButton("CANCELAR",
                        new DialogInterface.OnClickListener() {
                            @TargetApi(11)
                            public void onClick(DialogInterface dialog, int id) {


                                dialog.cancel();
                            }
                        })
                .setNeutralButton("CAMARA",
                        new DialogInterface.OnClickListener() {
                            @TargetApi(11)
                            public void onClick(DialogInterface dialog, int id) {
                                InfoConstants.SELECT_OPTION = 1;
                                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                startActivityForResult(intent,0);

                            }
                        })
                .setNegativeButton("GALERIA",
                        new DialogInterface.OnClickListener() {
                            @TargetApi(11)
                            public void onClick(DialogInterface dialog, int id) {
                                InfoConstants.SELECT_OPTION = 2;
                                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                startActivityForResult(galleryIntent, 0);
                            }
                        }).show();
    }

   /* @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmap = (Bitmap)data.getExtras().get("data");
        imageView.setImageBitmap(bitmap);
    }*/


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try {
            // When an Image is picked
            if (requestCode == 0 && resultCode == RESULT_OK && null != data) {


                if(InfoConstants.SELECT_OPTION == 2){
                    Uri selectedImage = data.getData();
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};

                    Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                    assert cursor != null;
                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    mediaPath = cursor.getString(columnIndex);
                    // Set the Image in ImageView for Previewing the Media
                    imageView.setImageBitmap(BitmapFactory.decodeFile(mediaPath));
                    cursor.close();
                }
                else{
                    Bitmap bitmapPhoto = (Bitmap)data.getExtras().get("data");
                    imageView.setImageBitmap(bitmapPhoto);

                    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                    bitmapPhoto.compress(Bitmap.CompressFormat.JPEG, 50, bytes);
                    destination = new File(Environment.getExternalStorageDirectory(),"temp.jpg");

                }


            } // When an Video is picked
            /*else if (requestCode == 1 && resultCode == RESULT_OK && null != data) {

                // Get the Video from data
                Uri selectedVideo = data.getData();
                String[] filePathColumn = {MediaStore.Video.Media.DATA};

                Cursor cursor = getContentResolver().query(selectedVideo, filePathColumn, null, null, null);
                assert cursor != null;
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);

                mediaPath1 = cursor.getString(columnIndex);
                //str2.setText(mediaPath1);
                // Set the Video Thumb in ImageView Previewing the Media
                imageView.setImageBitmap(getThumbnailPathForLocalFile(ReportActivity.this, selectedVideo));
                cursor.close();

            } */
            else {
                Snackbar.make(findViewById(android.R.id.content),
                        Html.fromHtml("<font color=\"#FA3E3E\">No has seleccionado Imagen</font>"), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        } catch (Exception e) {
            Snackbar.make(findViewById(android.R.id.content),
                    Html.fromHtml("<font color=\"#FA3E3E\">No has seleccionado Imagen</font>"), Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }

    }


    // Uploading Image/Video
    private void uploadFile() {
       // progressDialog.show();

        File file;
        // Map is used to multipart the file using okhttp3.RequestBody
        if(InfoConstants.SELECT_OPTION == 2)
            file = new File(mediaPath);
        else
            file = destination;

        // Parsing any Media type file
        RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), file);
        MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("file", file.getName(), requestBody);
        RequestBody filename = RequestBody.create(MediaType.parse("text/plain"), file.getName());

        TextView descr = (TextView)findViewById(R.id.txtDescription);
        String value = descr.getText().toString();

        ApiInterface getResponse = ApiClient.getClient().create(ApiInterface.class);
        Call<ServerResponse> call = getResponse.uploadFile(fileToUpload, filename, InfoConstants.TYPE_NAME, InfoConstants.TYPE_SELECT, InfoConstants.USER_ID, value,1, InfoConstants.latDes, InfoConstants.lonDes, InfoConstants.COUNTRY, InfoConstants.CITY);
        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                ServerResponse serverResponse = response.body();
                if (serverResponse != null) {
                    if (serverResponse.getSuccess()) {
                        Toast.makeText(getApplicationContext(), serverResponse.getMessage(),Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), serverResponse.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                } else {
                    assert serverResponse != null;
                    Log.v("Response", serverResponse.toString());
                }
               // progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {

            }
        });
    }

    // Providing Thumbnail For Selected Image
    public Bitmap getThumbnailPathForLocalFile(Activity context, Uri fileUri) {
        long fileId = getFileId(context, fileUri);
        return MediaStore.Video.Thumbnails.getThumbnail(context.getContentResolver(),
                fileId, MediaStore.Video.Thumbnails.MICRO_KIND, null);
    }

    // Getting Selected File ID
    public long getFileId(Activity context, Uri fileUri) {
        Cursor cursor = context.managedQuery(fileUri, mediaColumns, null, null, null);
        if (cursor.moveToFirst()) {
            int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Video.Media._ID);
            return cursor.getInt(columnIndex);
        }
        return 0;
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

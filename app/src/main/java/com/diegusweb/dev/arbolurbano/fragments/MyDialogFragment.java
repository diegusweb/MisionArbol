package com.diegusweb.dev.misionarbol.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.diegusweb.dev.misionarbol.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyDialogFragment extends DialogFragment {


    public MyDialogFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_my_dialog, container, false);
        View rootView = inflater.inflate(R.layout.fragment_my_dialog, container, false);
        getDialog().setTitle("FOTO");

        Button dismiss = (Button) rootView.findViewById(R.id.dismiss);
        dismiss.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        Button camera = (Button) rootView.findViewById(R.id.camara);
        camera.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,0);
            }
        });

        return rootView;
    }

}

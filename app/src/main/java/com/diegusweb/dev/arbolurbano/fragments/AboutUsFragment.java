package com.diegusweb.dev.arbolurbano.fragments;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.diegusweb.dev.arbolurbano.R;

import static android.R.id.message;

/**
 * A simple {@link Fragment} subclass.
 */
public class AboutUsFragment extends Fragment {


    public AboutUsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

       /* ImageButton btn = (ImageButton)getActivity().findViewById(R.id.btnMail);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto","email@email.com", null));
                intent.putExtra(Intent.EXTRA_SUBJECT, "diego.rueda2@gmail.com");
                intent.putExtra(Intent.EXTRA_TEXT, message);
                startActivity(Intent.createChooser(intent, "Choose an Email client :"));
            }
        });*/

       View v = inflater.inflate(R.layout.fragment_about_us, container, false);

        ImageButton camBt = (ImageButton)v.findViewById(R.id.btnMail);
        camBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto","info@arbolurbano.com", null));
                intent.putExtra(Intent.EXTRA_SUBJECT, "Contacto desde App");
                intent.putExtra(Intent.EXTRA_TEXT, message);
                startActivity(Intent.createChooser(intent, "Choose an Email client :"));
            }
        });

        return v;
    }



}

package com.diegusweb.dev.misionarbol.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.diegusweb.dev.misionarbol.R;
import com.diegusweb.dev.misionarbol.activity.InfoActivity;

/**
 * Created by HP on 06/01/2017.
 */

public class InfoFragment extends Fragment {


    public InfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_list, container, false);

        Intent intent = new Intent(getActivity(), InfoActivity.class);
        startActivity(intent);

        return null;
    }

}
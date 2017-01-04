package com.diegusweb.dev.misionarbol;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.diegusweb.dev.misionarbol.Adapter.AdapterInit;
import com.diegusweb.dev.misionarbol.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class InitFragment extends Fragment {

    private RecyclerView reciclador;
    private LinearLayoutManager layoutManager;
    private AdapterInit adaptador;

    public InitFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       // return inflater.inflate(R.layout.fragment_init, container, false);

        View view = inflater.inflate(R.layout.fragment_init, container, false);

        reciclador = (RecyclerView) view.findViewById(R.id.reciclador);
        layoutManager = new LinearLayoutManager(getActivity());
        reciclador.setLayoutManager(layoutManager);

        adaptador = new AdapterInit();
        reciclador.setAdapter(adaptador);
        return view;
    }

}

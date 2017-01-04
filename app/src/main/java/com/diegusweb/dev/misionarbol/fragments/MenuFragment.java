package com.diegusweb.dev.misionarbol.fragments;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.diegusweb.dev.misionarbol.Adapter.AdaptadorMenu;
import com.diegusweb.dev.misionarbol.MainActivity;
import com.diegusweb.dev.misionarbol.R;
import com.diegusweb.dev.misionarbol.activity.RouteActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class MenuFragment extends Fragment {

    private RecyclerView reciclador;
    private GridLayoutManager layoutManager;
    private AdaptadorMenu adaptador;


    public MenuFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        adaptador = new AdaptadorMenu(getActivity());

        adaptador.setOnItemClickListener(new AdaptadorMenu.OnItemClickListener() {

            @Override
            public void onItemClick(View itemView, int position) {
                String name = adaptador.getItem(position).getNombre();
                int id = adaptador.getItem(position).getIdDrawable();
                Toast.makeText(getActivity(),  "diego was clicked! " + name, Toast.LENGTH_SHORT).show();

                Intent i = new Intent(getActivity(), RouteActivity.class);
                i.putExtra("STRING_I_NEED", id);


                //startActivity(i);
            }
        });

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if(activity instanceof MainActivity)
            Log.i("Si se ejecuto", "Yes");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_menu, container, false);

        reciclador = (RecyclerView) root.findViewById(R.id.reciclador);
        setupArtistList();
        // setDummyContent();
        return root;
    }

    private void setupArtistList(){
        reciclador.setLayoutManager(new LinearLayoutManager(getActivity()));
        //poniendo adaptador
        reciclador.setAdapter(adaptador);
    }

}

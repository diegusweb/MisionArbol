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

import com.diegusweb.dev.misionarbol.MainActivity;
import com.diegusweb.dev.misionarbol.R;
import com.diegusweb.dev.misionarbol.activity.RouteActivity;
import com.diegusweb.dev.misionarbol.adapter.AdapterTree;
import com.diegusweb.dev.misionarbol.models.Tree;

import java.util.ArrayList;
import java.util.List;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * A simple {@link Fragment} subclass.
 */
public class TreeLibraryFragment extends Fragment {

    private RecyclerView reciclador;
    private GridLayoutManager layoutManager;
    private AdapterTree adaptador;

    private List<Tree> movieList = new ArrayList<>();


    public TreeLibraryFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adaptador = new AdapterTree(movieList);

        /*adaptador.setOnItemClickListener(new AdapterTree.OnItemClickListener() {

            @Override
            public void onItemClick(View itemView, int position) {
                String name = adaptador.getItem(position).getNombre();
                int id = adaptador.getItem(position).getId();
                //Toast.makeText(getActivity(),  "Arbolde was clicked! " + name, Toast.LENGTH_SHORT).show();

                Intent i = new Intent(getActivity(), RouteActivity.class);
                i.putExtra("STRING_I_NEED", "123");


                startActivity(i);
            }
        });*/

        prepareMovieData();
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

        View root = inflater.inflate(R.layout.fragment_three_library, container, false);

        reciclador = (RecyclerView) root.findViewById(R.id.recicladorThree);
        setupArtistList();
        // setDummyContent();
        return root;
    }

    private void setupArtistList(){
        reciclador.setLayoutManager(new LinearLayoutManager(getActivity()));
        //poniendo adaptador
        reciclador.setAdapter(adaptador);
        reciclador.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), reciclador, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Tree movie = movieList.get(position);

                int id = movie.getId();
                //Toast.makeText(getActivity(), movie.getName() + " is selected!", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getActivity(), RouteActivity.class);
                i.putExtra("STRING_I_NEED", id);
                startActivity(i);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

    private void prepareMovieData() {
        Tree movie = new Tree(1,"Molle", R.drawable.molle,"dexription1");
        movieList.add(movie);

        movie = new Tree(2,"jacaranda", R.drawable.jacaranda,"dexription2");
        movieList.add(movie);

        movie = new Tree(3,"sauce", R.drawable.sauce_lloron,"dexription3");
        movieList.add(movie);

        movie = new Tree(4,"Eucalipto", R.drawable.eucalipto,"dexription4");
        movieList.add(movie);

        adaptador.notifyDataSetChanged();

    }

}

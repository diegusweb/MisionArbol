package com.diegusweb.dev.misionarbol.fragments;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.diegusweb.dev.misionarbol.MainActivity;
import com.diegusweb.dev.misionarbol.R;
import com.diegusweb.dev.misionarbol.activity.report.PointDetailActivity;
import com.diegusweb.dev.misionarbol.activity.treeLibrary.TreeDetailActivity;
import com.diegusweb.dev.misionarbol.adapter.AdapterPointUser;
import com.diegusweb.dev.misionarbol.adapter.AdapterTree;
import com.diegusweb.dev.misionarbol.api.ApiClient;
import com.diegusweb.dev.misionarbol.api.ApiInterface;
import com.diegusweb.dev.misionarbol.helper.InfoConstants;
import com.diegusweb.dev.misionarbol.models.PointsTree;
import com.diegusweb.dev.misionarbol.models.Tree;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyReportFragment extends Fragment {

    private RecyclerView reciclador;
    private AdapterPointUser adaptador;
    private List<PointsTree> movieList = new ArrayList<>();
    private ArrayList<PointsTree> arraylist;

    private ArrayList<PointsTree> data;

    private SwipeRefreshLayout swipeContainer;

    public MyReportFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adaptador = new AdapterPointUser(movieList);

        getLibraryTreeMap();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if(activity instanceof MainActivity)
            Log.i("Si se ejecuto", "Yes");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View root = inflater.inflate(R.layout.fragment_my_report, container, false);

        reciclador = (RecyclerView) root.findViewById(R.id.recicladorPointUser);
        setupTreeList();

        swipeContainer = (SwipeRefreshLayout) root.findViewById(R.id.swipeRefreshLayout);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override

            public void onRefresh() {
                fetchTimelineAsync(0);
            }
        });

        // Configure the refreshing colors

        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        // setDummyContent();
        return root;
    }

    public void fetchTimelineAsync(int page) {
        getLibraryTreeMap();
        Toast.makeText(getActivity(), " is selected!", Toast.LENGTH_SHORT).show();

    }


    private void setupTreeList(){
        reciclador.setLayoutManager(new LinearLayoutManager(getActivity()));
        //poniendo adaptador
        reciclador.setAdapter(adaptador);
        reciclador.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), reciclador, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                PointsTree movie = movieList.get(position);
                InfoConstants.ID_TREE_DETAIL = movie;

                int id = movie.getId();
                //Toast.makeText(getActivity(), movie.getName() + " is selected!", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getActivity(), PointDetailActivity.class);
                i.putExtra("STRING_I_NEED", id);
                startActivity(i);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

    public void getLibraryTreeMap()
    {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<List<PointsTree>> call = apiService.getReportPoints(12);
        call.enqueue(new Callback<List<PointsTree>>() {
            @Override
            public void onResponse(Call<List<PointsTree>> call, Response<List<PointsTree>> response) {
                List<PointsTree> alTree = response.body();

                setTreeList(alTree);

                Log.d("LISTAAAAA ", "num"+alTree.size());
            }

            @Override
            public void onFailure(Call<List<PointsTree>> call, Throwable t) {

            }
        });
    }

    private void setTreeList(List<PointsTree> alTree)
    {
        movieList.clear();
        movieList.addAll(alTree);
        adaptador.notifyDataSetChanged();
        swipeContainer.setRefreshing(false);
    }

}

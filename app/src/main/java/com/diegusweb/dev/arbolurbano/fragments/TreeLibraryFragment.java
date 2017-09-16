package com.diegusweb.dev.arbolurbano.fragments;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.diegusweb.dev.arbolurbano.MainActivity;
import com.diegusweb.dev.arbolurbano.R;
import com.diegusweb.dev.arbolurbano.activity.treeLibrary.TreeDetailActivity;
import com.diegusweb.dev.arbolurbano.adapter.AdapterTree;
import com.diegusweb.dev.arbolurbano.api.ApiClient;
import com.diegusweb.dev.arbolurbano.api.ApiInterface;
import com.diegusweb.dev.arbolurbano.helper.InfoConstants;
import com.diegusweb.dev.arbolurbano.models.Tree;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class TreeLibraryFragment extends Fragment implements SearchView.OnQueryTextListener {

    private RecyclerView reciclador;
    private AdapterTree adaptador;
    private List<Tree> treesList = new ArrayList<>();

    private SwipeRefreshLayout swipeContainer;

    public TreeLibraryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adaptador = new AdapterTree(treesList);

        getLibraryTreeMap();
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
        setupTreeList();

        swipeContainer = (SwipeRefreshLayout) root.findViewById(R.id.swipeRefreshLayout);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override

            public void onRefresh() {
                fetchTimelineAsync(0);
            }
        });

        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        return root;
    }

    public void fetchTimelineAsync(int page) {
        getLibraryTreeMap();
    }


    private void setupTreeList(){
        reciclador.setLayoutManager(new LinearLayoutManager(getActivity()));
        reciclador.setAdapter(adaptador);
        reciclador.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), reciclador, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Tree movie = treesList.get(position);
                InfoConstants.ONE_TREE_LIBRARY = movie;

                int id = movie.getId();
                //Toast.makeText(getActivity(), movie.getName() + " is selected!", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getActivity(), TreeDetailActivity.class);
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

        Call<List<Tree>> call = apiService.getTreeLibrary();
        call.enqueue(new Callback<List<Tree>>() {
            @Override
            public void onResponse(Call<List<Tree>> call, Response<List<Tree>> response) {
                List<Tree> alTree = response.body();

                setTreeList(alTree);
            }

            @Override
            public void onFailure(Call<List<Tree>> call, Throwable t) {

            }
        });
    }

    private void setTreeList(List<Tree> alTree)
    {
        treesList.clear();
        treesList.addAll(alTree);
        adaptador.addListTransport(treesList);
        adaptador.notifyDataSetChanged();
        swipeContainer.setRefreshing(false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView sv = new SearchView(((MainActivity) getActivity()).getSupportActionBar().getThemedContext());
        MenuItemCompat.setShowAsAction(item, MenuItemCompat.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW | MenuItemCompat.SHOW_AS_ACTION_IF_ROOM);
        MenuItemCompat.setActionView(item, sv);

        sv.setOnQueryTextListener(this);
        sv.setIconifiedByDefault(false);
        sv.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        MenuItemCompat.setOnActionExpandListener(item, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                getLibraryTreeMap();
                return true;
            }

            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                return true;
            }
        });

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        adaptador.filter(query);
        final InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        adaptador.filter(newText);
        return true;
    }
}

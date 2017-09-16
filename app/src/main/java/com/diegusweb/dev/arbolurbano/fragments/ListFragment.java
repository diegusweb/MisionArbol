package com.diegusweb.dev.arbolurbano.fragments;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.diegusweb.dev.arbolurbano.MainActivity;
import com.diegusweb.dev.arbolurbano.R;
import com.diegusweb.dev.arbolurbano.adapter.AdapteTestItems;
import com.diegusweb.dev.arbolurbano.api.ApiClient;
import com.diegusweb.dev.arbolurbano.api.ApiInterface;
import com.diegusweb.dev.arbolurbano.models.TestItems;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends Fragment {


    private RecyclerView reciclador;
    private AdapteTestItems adaptador;
    private List<TestItems> movieLists = new ArrayList<>();

    public ListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //adaptador = new AdapterTree(movieList);

        //prepareTreeDataSample();
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

        View root = inflater.inflate(R.layout.fragment_list, container, false);

        reciclador = (RecyclerView) root.findViewById(R.id.recicladorListDemo);
        setupTreeList();
        // setDummyContent();
        return root;
    }


    private void setupTreeList(){
        reciclador.setLayoutManager(new LinearLayoutManager(getActivity()));
        //poniendo adaptador
       // reciclador.setAdapter(adaptador);

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Log.d("Resultados","otrooo");

        Call<List<TestItems>>  call = apiService.getListTestItems();
        call.enqueue(new Callback<List<TestItems>>() {
            @Override
            public void onResponse(Call<List<TestItems>> call, Response<List<TestItems>> response) {
                List<TestItems> StudentData = response.body();

                Log.d("LISTAAAAA ", "num"+StudentData.size());

                /*if(response.isSuccessful()) {
                    List<TestItems> changesList = response.body();
                    changesList.forEach(change -> System.out.println(change.getTitle()));
                } else {
                    System.out.println(response.errorBody());
                }*/

                StringBuilder builder = new StringBuilder();
                TestItems movie;

                for (TestItems repo: StudentData) {
                    builder.append(repo.getTitle() + "->" + repo.getId());

                    movie = new TestItems("asdas",repo.getId(), repo.getTitle());
                    movieLists.add(movie);
                }

                adaptador = new AdapteTestItems(movieLists);
                reciclador.setAdapter(adaptador);
                adaptador.notifyDataSetChanged();

                Toast.makeText(getActivity(), builder.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<TestItems>> call, Throwable t) {

            }
        });


        reciclador.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), reciclador, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                TestItems movie = movieLists.get(position);

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

    private void prepareTreeDataSample() {

    }

}

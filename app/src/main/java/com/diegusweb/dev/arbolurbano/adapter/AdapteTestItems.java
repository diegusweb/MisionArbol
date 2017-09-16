package com.diegusweb.dev.misionarbol.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.diegusweb.dev.misionarbol.R;
import com.diegusweb.dev.misionarbol.models.TestItems;

import java.util.List;

/**
 * Created by HP on 17/01/2017.
 */

public class AdapteTestItems extends RecyclerView.Adapter<AdapteTestItems.MovieViewHolder> {

    private List<TestItems> movies;
    private int rowLayout;
    private Context context;


    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        LinearLayout moviesLayout;
        TextView movieTitle;
        TextView movieDescription;


        public MovieViewHolder(View v) {
            super(v);
            movieTitle = (TextView) v.findViewById(R.id.movieTitle);
            movieDescription = (TextView) v.findViewById(R.id.movieDescription);
        }
    }

    //adaptador

    public AdapteTestItems(List<TestItems> movies) {
        this.movies = movies;
    }

    @Override
    public AdapteTestItems.MovieViewHolder onCreateViewHolder(ViewGroup parent,
                                                              int viewType) {
        /*View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_movie, parent, false);
        return new MovieViewHolder(view);*/

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_movie, parent, false);

        return new MovieViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(MovieViewHolder holder, final int position) {
        holder.movieTitle.setText(movies.get(position).getTitle());
        holder.movieDescription.setText(movies.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }
}

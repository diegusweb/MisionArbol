package com.diegusweb.dev.misionarbol.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.diegusweb.dev.misionarbol.R;
import com.diegusweb.dev.misionarbol.models.Comida;
import com.diegusweb.dev.misionarbol.models.Tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HP on 05/01/2017.
 */

public class AdapterTree extends RecyclerView.Adapter<AdapterTree.MyViewHolder> {

    private List<Tree> moviesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView nombre, precio;
        public ImageView imagen;

        public MyViewHolder(final View itemView) {
            super(itemView);
            nombre = (TextView) itemView.findViewById(R.id.nombre_comida);
            precio = (TextView) itemView.findViewById(R.id.precio_comida);
            imagen = (ImageView) itemView.findViewById(R.id.miniatura_comida);
        }
    }

    public AdapterTree(List<Tree> moviesList) {
        this.moviesList = moviesList;
    }

    @Override
    public AdapterTree.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_three_layout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AdapterTree.MyViewHolder holder, int position) {
        Tree movie = moviesList.get(position);
        Glide.with(holder.itemView.getContext())
                .load(movie.getPath())
                .centerCrop()
                .into(holder.imagen);

        holder.nombre.setText(movie.getTitle());

        holder.precio.setText("Demooo");

    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}

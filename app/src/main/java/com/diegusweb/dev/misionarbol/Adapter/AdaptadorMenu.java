package com.diegusweb.dev.misionarbol.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.diegusweb.dev.misionarbol.models.Comida;

import java.util.ArrayList;
import java.util.List;

import com.diegusweb.dev.misionarbol.R;

/**
 * Created by HP on 03/01/2017.
 */

public class AdaptadorMenu extends RecyclerView.Adapter<AdaptadorMenu.ViewHolder> {


    List<Comida> tranportsLists = new ArrayList<>();
    private ArrayList<Comida> arraylist;

    public AdaptadorMenu(Context context) {

    }

    // Define listener member variable
    private OnItemClickListener listener;

    // Define the listener interface
    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }

    // Define the method that allows the parent activity or fragment to define the listener
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public TextView nombre;
        public TextView precio;
        public ImageView imagen;

        public ViewHolder(final View itemView) {
            super(itemView);
            nombre = (TextView) itemView.findViewById(R.id.nombre_comida);
            precio = (TextView) itemView.findViewById(R.id.precio_comida);
            imagen = (ImageView) itemView.findViewById(R.id.miniatura_comida);

            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    if (listener != null)
                        listener.onItemClick(itemView, getLayoutPosition());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return Comida.COMIDAS_POPULARES.size();
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    public Comida getItem(int position) {
        return Comida.COMIDAS_POPULARES.get(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.row_menu_layout, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Comida item = Comida.COMIDAS_POPULARES.get(i);

        Glide.with(viewHolder.itemView.getContext())
                .load(item.getIdDrawable())
                .centerCrop()
                .into(viewHolder.imagen);
        viewHolder.nombre.setText(item.getNombre());
        viewHolder.precio.setText("$" + item.getPrecio());



    }
}
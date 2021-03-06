package com.diegusweb.dev.misionarbol.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.diegusweb.dev.misionarbol.R;
import com.diegusweb.dev.misionarbol.helper.InfoConstants;
import com.diegusweb.dev.misionarbol.models.Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by HP on 05/01/2017.
 */

public class AdapterTree extends RecyclerView.Adapter<AdapterTree.MyViewHolder> {

    private List<Tree> treesAllResponse;
    List<Tree> tranportsLists = new ArrayList<>();
    private ArrayList<Tree> arraylist;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView nombre, precio;
        public ImageView imagen;

        public MyViewHolder(final View itemView) {
            super(itemView);
            nombre = (TextView) itemView.findViewById(R.id.nombre_comida);
            precio = (TextView) itemView.findViewById(R.id.precio_comida);
            imagen = (ImageView) itemView.findViewById(R.id.miniatura_comida);
        }

        public void bind(Tree androidVersion){
            this.nombre.setText(androidVersion.getTitle());
            //Picasso.with(imageView.getContext()).load(androidVersion.getUrl()).into(imageView);

        }
    }

    public AdapterTree(List<Tree> treesAllResponse) {
        this.treesAllResponse = treesAllResponse;
    }

    @Override
    public AdapterTree.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_three_layout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AdapterTree.MyViewHolder holder, int position) {
        Tree treeInfo = treesAllResponse.get(position);
        Glide.with(holder.itemView.getContext())
                .load(InfoConstants.BASE_URL_IMG+treeInfo.getPath())
                .centerCrop()
                .into(holder.imagen);

        holder.nombre.setText(treeInfo.getTitle());

        holder.precio.setText("Demooo");

    }

    @Override
    public int getItemCount() {
        return treesAllResponse.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void addListTransport(List<Tree> androidVersionsList){
        this.arraylist = new ArrayList<Tree>();
        this.arraylist.addAll(androidVersionsList);

        this.tranportsLists.clear();
        Log.d("DiegoResult:", "addAndroidVersions - " + androidVersionsList.size());
        this.tranportsLists.addAll(androidVersionsList);
        notifyDataSetChanged();

    }

    public void clear(){
        this.arraylist.clear();
        notifyDataSetChanged();
    }

    public void filter(String charText){
        charText = charText.toLowerCase(Locale.getDefault());
        Log.d("DiegoResult:", "addAndroidVersions - " + arraylist.size());
        treesAllResponse.clear();
        if (charText.length() == 0) {
            treesAllResponse.addAll(arraylist);
        }
        else
        {

            for (Tree wp : arraylist)
            {

                if (wp.getTitle().toLowerCase(Locale.getDefault()).contains(charText))
                {
                    treesAllResponse.add(wp);
                    Log.d("DiegoResult:", "addAndroidVersions - " + treesAllResponse.size());
                }
            }
        }
        notifyDataSetChanged();
    }



}

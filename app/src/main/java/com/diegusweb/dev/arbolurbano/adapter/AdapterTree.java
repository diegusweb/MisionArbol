package com.diegusweb.dev.arbolurbano.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.diegusweb.dev.arbolurbano.R;
import com.diegusweb.dev.arbolurbano.helper.InfoConstants;
import com.diegusweb.dev.arbolurbano.models.Image;
import com.diegusweb.dev.arbolurbano.models.Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by HP on 05/01/2017.
 */

public class AdapterTree extends RecyclerView.Adapter<AdapterTree.MyViewHolder> {

    private List<Tree> treesAllResponse;
    List<Tree> newTreeLists = new ArrayList<>();
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

        List<Image> listImage = treeInfo.getListImage();




        Glide.with(holder.itemView.getContext())
                .load(InfoConstants.BASE_URL_IMG+treeInfo.getListImage().get(0).getThumb())
                .centerCrop()
                .into(holder.imagen);

        holder.nombre.setText(treeInfo.getTitle());

        holder.precio.setText(treeInfo.getScientificName());

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

        this.newTreeLists.clear();
        this.newTreeLists.addAll(androidVersionsList);
        notifyDataSetChanged();

    }

    public void filter(String charText){
        charText = charText.toLowerCase(Locale.getDefault());

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
                }
            }
        }
        notifyDataSetChanged();
    }

}

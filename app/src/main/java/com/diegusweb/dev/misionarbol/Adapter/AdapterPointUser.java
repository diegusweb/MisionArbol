package com.diegusweb.dev.misionarbol.adapter;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.diegusweb.dev.misionarbol.R;
import com.diegusweb.dev.misionarbol.models.PointsTree;
import com.diegusweb.dev.misionarbol.models.Tree;

import java.util.List;

/**
 * Created by HP on 14/03/2017.
 */

public class AdapterPointUser extends RecyclerView.Adapter<AdapterPointUser.MyViewHolder> {

    private List<PointsTree> pointsList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, description;
        public ImageView imagen;

        public MyViewHolder(final View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.cardview_list_title);
            description = (TextView) itemView.findViewById(R.id.short_description);
            imagen = (ImageView) itemView.findViewById(R.id.cardview_image);
        }
    }

    public AdapterPointUser(List<PointsTree> pointsList) {
        this.pointsList = pointsList;
    }

    @Override
    public AdapterPointUser.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_points_user, parent, false);

        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(AdapterPointUser.MyViewHolder holder, int position) {
        PointsTree movie = pointsList.get(position);

        if(movie.getType_id().getId() == 1){
            Glide.with(holder.itemView.getContext())
                    .load(R.drawable.id_marker_green)
                    .centerCrop()
                    .into(holder.imagen);
        }

        if(movie.getType_id().getId() == 2){
            Glide.with(holder.itemView.getContext())
                    .load(R.drawable.id_marker_dead)
                    .centerCrop()
                    .into(holder.imagen);
        }

        if(movie.getType_id().getId() == 3){
            Glide.with(holder.itemView.getContext())
                    .load(R.drawable.id_marker_danger)
                    .centerCrop()
                    .into(holder.imagen);
        }

        if(movie.getType_id().getId() == 4){
            Glide.with(holder.itemView.getContext())
                    .load(R.drawable.id_marker_plant)
                    .centerCrop()
                    .into(holder.imagen);
        }

        if(movie.getType_id().getId() == 5){
            Glide.with(holder.itemView.getContext())
                    .load(R.drawable.id_marker_trunk)
                    .centerCrop()
                    .into(holder.imagen);
        }



        holder.title.setText(movie.getTitle());

        holder.description.setText("Demooo");
    }

    @Override
    public int getItemCount() {
        return pointsList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}

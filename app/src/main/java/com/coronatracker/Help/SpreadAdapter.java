package com.coronatracker.Help;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.coronatracker.R;
import com.coronatracker.Utils.SpreadHelper;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SpreadAdapter extends RecyclerView.Adapter<SpreadAdapter.SpreadViewHolder> {
    ArrayList<SpreadHelper> spreadLocation;

    public SpreadAdapter(ArrayList<SpreadHelper> spreadLocation) {
        this.spreadLocation = spreadLocation;
    }

    @NonNull
    @Override
    public SpreadAdapter.SpreadViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.spread_cards_design,parent,false);
        SpreadViewHolder spreadViewHolder = new SpreadViewHolder(view);

        return spreadViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SpreadAdapter.SpreadViewHolder holder, int position) {
        SpreadHelper spreadHelper = spreadLocation.get(position);

        holder.image.setImageResource(spreadHelper.getImage());
        holder.title.setText(spreadHelper.getTitle());

    }

    @Override
    public int getItemCount() {
        return spreadLocation.size();
    }

    public static class SpreadViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView title;

        public SpreadViewHolder(@NonNull View itemView) {
            super(itemView);

            //hooks
            image = itemView.findViewById(R.id.spread_image);
            title = itemView.findViewById(R.id.spread_title);
        }
    }
}

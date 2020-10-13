package com.coronatracker.Help;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.coronatracker.R;
import com.coronatracker.Utils.PreventionHelper;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PreventionAdapter extends RecyclerView.Adapter<PreventionAdapter.PreventionViewHolder> {


    ArrayList<PreventionHelper> preventionLocation;

    public PreventionAdapter(ArrayList<PreventionHelper> preventionLocation) {
        this.preventionLocation = preventionLocation;
    }

    @NonNull
    @Override
    public PreventionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.prevention_cards_design,parent,false);
        PreventionViewHolder preventionViewHolder = new PreventionViewHolder(view);

        return preventionViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PreventionViewHolder holder, int position) {
        PreventionHelper preventionHelper = preventionLocation.get(position);

        holder.image.setImageResource(preventionHelper.getImage());
        holder.title.setText(preventionHelper.getTitle());

    }

    @Override
    public int getItemCount() {
        return preventionLocation.size();
    }

    public static class PreventionViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView title;

        public PreventionViewHolder(@NonNull View itemView) {
            super(itemView);
             //hooks
            image = itemView.findViewById(R.id.prevention_image);
            title = itemView.findViewById(R.id.prevention_title);
        }
    }
}

package com.coronatracker.Help;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.coronatracker.R;
import com.coronatracker.Utils.PreventionHelper;
import com.coronatracker.Utils.SelfCareHelper;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SelfCareAdapter extends RecyclerView.Adapter<SelfCareAdapter.SelfCareViewHolder> {


    ArrayList<SelfCareHelper> selfCareLocation;

    public SelfCareAdapter(ArrayList<SelfCareHelper> selfCareLocation) {
        this.selfCareLocation = selfCareLocation;
    }

    @NonNull
    @Override
    public SelfCareViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.self_care_cards_design,parent,false);
        SelfCareViewHolder selfCareViewHolder = new SelfCareViewHolder(view);

        return selfCareViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SelfCareViewHolder holder, int position) {
        SelfCareHelper selfCareHelper = selfCareLocation.get(position);

        holder.image.setImageResource(selfCareHelper.getImage());
        holder.title.setText(selfCareHelper.getTitle());

    }

    @Override
    public int getItemCount() {
        return selfCareLocation.size();
    }

    public static class SelfCareViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView title;

        public SelfCareViewHolder(@NonNull View itemView) {
            super(itemView);
             //hooks
            image = itemView.findViewById(R.id.selfCare_image);
            title = itemView.findViewById(R.id.selfCare_title);
        }
    }
}

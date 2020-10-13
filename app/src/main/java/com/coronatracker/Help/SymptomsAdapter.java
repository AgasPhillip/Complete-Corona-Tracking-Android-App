package com.coronatracker.Help;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.coronatracker.R;
import com.coronatracker.Utils.SymptomsHelper;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SymptomsAdapter extends RecyclerView.Adapter<SymptomsAdapter.SymptomsViewHolder> {
    private static final String TAG = "SymptomsAdapter";
    ArrayList<SymptomsHelper> symptomsLocation;


    public SymptomsAdapter(ArrayList<SymptomsHelper> symptomsLocation) {
        this.symptomsLocation = symptomsLocation;
    }

    @NonNull
    @Override
    public SymptomsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.symptoms_cards_design,parent,false);
        SymptomsViewHolder symptomsViewHolder = new SymptomsViewHolder(view);

        return symptomsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SymptomsViewHolder holder, int position) {
        SymptomsHelper symptomsHelper = symptomsLocation.get(position);

        holder.image.setImageResource(symptomsHelper.getImage());
        holder.title.setText(symptomsHelper.getTitle());
        holder.desc.setText(symptomsHelper.getDescription());

    }

    @Override
    public int getItemCount() {
        return symptomsLocation.size();
    }

    public static class SymptomsViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView title, desc;

        public SymptomsViewHolder(@NonNull View itemView) {
            super(itemView);
            Log.d(TAG, "symptomsViewHolder: Hooks");

            //Hooks
            image = itemView.findViewById(R.id.symptoms_image);
            title = itemView.findViewById(R.id.symptoms_text);
            desc = itemView.findViewById(R.id.symptoms_desc);
        }
    }
}

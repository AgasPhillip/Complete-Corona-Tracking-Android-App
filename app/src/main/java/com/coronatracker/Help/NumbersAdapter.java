package com.coronatracker.Help;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.coronatracker.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NumbersAdapter extends RecyclerView.Adapter<NumbersAdapter.ViewHolder> {
    private static final String TAG = "NumbersAdapter";


    private Context context;
    private int[] cIcons;
    private String[] cCodes;
    private String[] cNumbers;

    public NumbersAdapter(Context context, int[] cIcon, String[] cCode, String[] cNumber) {
        this.context = context;
        this.cIcons = cIcon;
        this.cCodes = cCode;
        this.cNumbers = cNumber;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.help_line_number_design,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.cIcon.setImageResource(cIcons[position]);
        holder.cCode.setText(cCodes[position]);
        holder.cNumber.setText(cNumbers[position]);

        holder.cNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mobileNumber = cNumbers[holder.getAdapterPosition()];
                String call = "tel:"+mobileNumber;
                Intent callingIntent = new Intent(Intent.ACTION_DIAL);
                callingIntent.setData(Uri.parse(call));
                context.startActivity(callingIntent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return cIcons.length;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView cIcon;
        TextView cCode, cNumber;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cIcon = itemView.findViewById(R.id.imgCountryFlag);
            cCode = itemView.findViewById(R.id.countryCode);
            cNumber = itemView.findViewById(R.id.countryNumber);
        }
    }
}
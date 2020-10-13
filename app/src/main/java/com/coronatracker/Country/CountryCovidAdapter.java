package com.coronatracker.Country;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.coronatracker.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CountryCovidAdapter extends RecyclerView.Adapter<CountryCovidAdapter.ViewHolder> implements Filterable {
    private List<CovidCountry> covidCountries;
    private List<CovidCountry> covidCountriesFull;
    private Context context;

    public CountryCovidAdapter (List<CovidCountry> covidCountries, Context context){
        this.covidCountries = covidCountries;
        this.context = context;
        covidCountriesFull = new ArrayList<>(covidCountries);
    }
    @NonNull
    @Override
    public CountryCovidAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_covid_country,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryCovidAdapter.ViewHolder holder, int position) {
        CovidCountry covidCountry = covidCountries.get(position);
        //holder.tvTotalCases.setText(covidCountry.getmCases());
        holder.tvCountryName.setText(covidCountry.getmCovidCountry());

        //Glide
        Glide.with(context).load(covidCountry.getmFlags())
                .apply(new RequestOptions().override(240,160))
                .into(holder.imgCountryFlag);

    }

    @Override
    public int getItemCount() {
        return covidCountries.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTotalCases, tvCountryName;
        ImageView imgCountryFlag;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
           // tvTotalCases = itemView.findViewById(R.id.tvTotalCases);
            tvCountryName = itemView.findViewById(R.id.tvCountryName);
            imgCountryFlag = itemView.findViewById(R.id.imgCountryFlag);


        }
    }

    @Override
    public Filter getFilter() {
        return covidCountriesFilters;
    }
    private Filter covidCountriesFilters = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<CovidCountry> filterCovidCountry = new ArrayList<>();

            if(constraint==null || constraint.length()==0){
                filterCovidCountry.addAll(covidCountriesFull);
            }else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for(CovidCountry itemCovidCountry:covidCountriesFull){
                    if(itemCovidCountry.getmCovidCountry().toLowerCase().contains(filterPattern)){
                        filterCovidCountry.add(itemCovidCountry);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filterCovidCountry;
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults results) {
            covidCountries.clear();
            covidCountries.addAll((List)results.values);
            notifyDataSetChanged();

        }
    };
}

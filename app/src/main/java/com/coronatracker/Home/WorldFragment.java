package com.coronatracker.Home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.coronatracker.Country.CountryCovidAdapter;
import com.coronatracker.Country.CovidCountry;
import com.coronatracker.Country.CovidCountryDetails;
import com.coronatracker.Country.ItemClickSupport;
import com.coronatracker.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class WorldFragment extends Fragment {
    private static final String TAG = WorldFragment.class.getSimpleName();


    private RecyclerView rvCovidCountry;
    private ProgressBar progressBar;
    CountryCovidAdapter countryCovidAdapter;
    SwipeRefreshLayout swipeRefreshLayout;


    List<CovidCountry> covidCountries;
    private TextView mUpdate;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_world, container, false);


        //set has option menu as true due present of menu
        setHasOptionsMenu(true);


        //Hooks
        rvCovidCountry = (RecyclerView) view.findViewById(R.id.rvCovidCountry);
        progressBar = (ProgressBar) view.findViewById(R.id.progress_circular_country);
        mUpdate = (TextView) view.findViewById(R.id.countryLastUpdate);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshCountry);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                countryCovidAdapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                if(swipeRefreshLayout != null){
                    swipeRefreshLayout.setRefreshing(true);
                }
                getDataFromServer();

            }
        });






        rvCovidCountry.setLayoutManager(new LinearLayoutManager(getActivity()));

        //Line divider
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvCovidCountry.getContext(), DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(getContext(), R.drawable.line_divider));
        rvCovidCountry.addItemDecoration(dividerItemDecoration);

        //Call List
        covidCountries = new ArrayList<>();

        //Request Volley methods






        return view;
    }



    private void showRecyclerView() {
        countryCovidAdapter = new CountryCovidAdapter(covidCountries, getActivity());
        rvCovidCountry.setAdapter(countryCovidAdapter);

        ItemClickSupport.addTo(rvCovidCountry).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                showSelectedCovidCountry(covidCountries.get(position));
            }
        });
    }

    private void showSelectedCovidCountry(CovidCountry covidCountry) {
        Intent covidCountryDetails = new Intent(getActivity(), CovidCountryDetails.class);
        covidCountryDetails.putExtra("EXTRA_COVID", covidCountry);
        startActivity(covidCountryDetails);
    }


    private void getDataFromServer() {

        if(swipeRefreshLayout != null){
            swipeRefreshLayout.setRefreshing(true);
        }
        String url = "https://disease.sh/v3/covid-19/countries";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressBar.setVisibility(View.GONE);
                if (response != null) {
                    Log.e(TAG, "onResponse: " + response);
                    try {
                        JSONArray jsonArray = new JSONArray(response.toString());


                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject data = jsonArray.getJSONObject(i);

                            //Extract JSONObject inside JSONObject
                            JSONObject countryInfo = data.getJSONObject("countryInfo");


                            covidCountries.add(new CovidCountry(data.getString("country"),
                                    data.getString("cases"), data.getString("todayCases"),
                                    data.getString("deaths"), data.getString("todayDeaths"),
                                    data.getString("recovered"), data.getString("active"),
                                    data.getString("critical"), countryInfo.getString("flag"),
                                    data.getLong("updated")
                            ));
                        }



                        //  tvTotalCountry.setText(jsonArray.length()+" Countries");

                        //ActionBar title
                        getActivity().setTitle("Stay Alert & Save Lives");


                        showRecyclerView();




                        swipeRefreshLayout.setRefreshing(false);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressBar.setVisibility(View.GONE);
                        swipeRefreshLayout.setRefreshing(false);
                        Log.e(TAG, "onErrorResponse: " + error);
                    }
                });
        Volley.newRequestQueue(getActivity()).add(stringRequest);
    }


    //Date format method
    private String getDate(long milliseconds) {
        //format Mon, 15 May 2020 22:15:46 PM
        SimpleDateFormat formatter = new SimpleDateFormat("EEE, dd MMM yyyy hh:mm:ss aaa");

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliseconds);

        return formatter.format(calendar.getTime());
    }




    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.search_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = new SearchView(getActivity());
        searchView.setQueryHint("Search...");
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (countryCovidAdapter != null) {
                    countryCovidAdapter.getFilter().filter(newText);
                }
                return false;
            }
        });


        searchItem.setActionView(searchView);


        super.onCreateOptionsMenu(menu, inflater);
    }


}

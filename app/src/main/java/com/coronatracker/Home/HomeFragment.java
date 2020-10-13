package com.coronatracker.Home;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.coronatracker.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {
    private static final String TAG = "HomeFragment";
    private TextView tvTotalConfirmed, tvTotalDeaths, tvTotalRecovered, tvTotalActive, tvLastUpdate;
    private ProgressBar progressBar;






    private Context mContext = getActivity();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);




        //Hooks
        tvTotalConfirmed = (TextView) view.findViewById(R.id.tvTotalConfirmed);
        tvTotalDeaths = (TextView) view.findViewById(R.id.tvTotalDeaths);
        tvTotalRecovered = (TextView) view.findViewById(R.id.tvTotalRecovered);
        tvTotalActive = (TextView) view.findViewById(R.id.tvTotalActive);
        tvLastUpdate = (TextView) view.findViewById(R.id.tvLastUpdate);
        progressBar = (ProgressBar) view.findViewById(R.id.progress_circular);



        //ActionBar title
        Log.d(TAG, "onCreateView: Set ActionBar title");
       // getActivity().setTitle("World Wide Stats");

        //Volley request
        collectData();
        return view;


    }

    //string converter
    private String numberSeparator(String numbers){
        StringBuilder stringBuilder = new StringBuilder(numbers);
        for (int i = stringBuilder.length() - 3; i > 0; i-=3){
            stringBuilder.insert(i,",");
        }
        return stringBuilder.toString();
    }


    //Date format method
    private String getDate(long milliseconds){
        //format Mon, 15 May 2020 22:15:46 PM
        SimpleDateFormat formatter = new SimpleDateFormat("EEE, dd MMM yyyy hh:mm:ss aaa");

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliseconds);

        return formatter.format(calendar.getTime()) ;
    }

    private void collectData() {
        RequestQueue queue = Volley.newRequestQueue(getActivity());

        String url = "https://disease.sh/v3/covid-19/all";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressBar.setVisibility(View.GONE);

                try {
                    Log.d(TAG, "onResponse: Reguest Data from  database");
                    JSONObject jsonObject = new JSONObject(response.toString());

                    tvTotalConfirmed.setText(numberSeparator(jsonObject.getString("cases")));
                    tvTotalDeaths.setText(numberSeparator(jsonObject.getString("deaths")));
                    tvTotalRecovered.setText(numberSeparator(jsonObject.getString("recovered")));
                    tvTotalActive.setText(numberSeparator(jsonObject.getString("active")));
                    tvLastUpdate.setText("Last Update"+ "\n"+getDate(jsonObject.getLong("updated")));

                    tvTotalDeaths.setTextColor(getResources().getColor(R.color.totalDeaths));


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.setVisibility(View.GONE);
                Log.d(TAG, "onErrorResponse: " + error.toString());

            }

        });
        queue.add(stringRequest);
    }

}

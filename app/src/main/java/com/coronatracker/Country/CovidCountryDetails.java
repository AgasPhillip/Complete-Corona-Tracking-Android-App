package com.coronatracker.Country;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.coronatracker.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

public class CovidCountryDetails extends AppCompatActivity {
    private static final String TAG = "CovidCountryDetails";
    TextView tvDetailCountryName, tvDetailTotalCases, tvDetailTodayCases, tvDetailTotalDeath,
            tvDetailTodayDeath, tvDetailTotalRecovered, tvDetailActive, tvDetailTotalCritical, lastUpdate;
    private FloatingActionButton share;
    private File imagePath;
    Context mContext = CovidCountryDetails.this;
    private String sharePath = "no";
    private CardView cardview;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covid_country_details);

        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_GRANTED);

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        //Hooks
        tvDetailCountryName = (TextView) findViewById(R.id.tvDetailCountryName);
        tvDetailTotalCases = (TextView) findViewById(R.id.tvDetailsTotalCases);
        tvDetailTodayCases = (TextView) findViewById(R.id.tvDetailsTotalTodayCases);
        tvDetailTotalDeath = (TextView) findViewById(R.id.tvDetailsTotalDeaths);
        tvDetailTodayDeath = (TextView) findViewById(R.id.tvDetailsTotalDeathsToday);
        tvDetailTotalRecovered = (TextView) findViewById(R.id.tvDetailsTotalRecovered);
        tvDetailActive = (TextView) findViewById(R.id.tvDetailsTotalActive);
        tvDetailTotalCritical = (TextView) findViewById(R.id.tvDetailsTotalCritical);
        lastUpdate = (TextView) findViewById(R.id.countryLastUpdate);
        share = (FloatingActionButton) findViewById(R.id.share_tbn);
        cardview = (CardView) findViewById(R.id.detailsCardView);


        //Share OnclickListener

        /**
         * _____________________________________________________________________________________________________
         */

//        share.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // takeScreenshot(cardview);
//
//                takeScreenshotAndShare(view);
//
//
//
//            }
//        });


        //Request country information
        CovidCountry covidCountry = getIntent().getParcelableExtra("EXTRA_COVID");

        //set text view
        tvDetailCountryName.setText(covidCountry.getmCovidCountry());
        tvDetailTotalCases.setText(numberSeparator(covidCountry.getmCases()));
        tvDetailTodayCases.setText("+ " + numberSeparator(covidCountry.getmTodayCases()));
        tvDetailTotalDeath.setText(numberSeparator(covidCountry.getmDeaths()));
        tvDetailTodayDeath.setText("+ " + numberSeparator(covidCountry.getmTodayDeaths()));
        tvDetailTotalRecovered.setText(numberSeparator(covidCountry.getmRecovered()));
        tvDetailActive.setText(numberSeparator(covidCountry.getmActive()));
        tvDetailTotalCritical.setText(numberSeparator(covidCountry.getmCritical()));
        lastUpdate.setText("Last Updated" + "\n" + getDate(covidCountry.getmUpdated()));


    }

    public void takeScreenshotAndShare(View view1) {
        Date now = new Date();
        android.text.format.DateFormat.format("yyyy-MM-dd_hh:mm:ss", now);


        try{
            //creating a screen capture
            cardview.setDrawingCacheEnabled(true);
            Bitmap bitmap = Bitmap.createBitmap(cardview.getDrawingCache());
            cardview.setDrawingCacheEnabled(false);

            String mPath = Environment.getExternalStorageDirectory().toString() + "/" + now + ".jpeg";
            File imageFile = new File (mPath);
            FileOutputStream outputStream = new FileOutputStream(imageFile);
            int quality = 100;
            bitmap.compress(Bitmap.CompressFormat.JPEG,quality,outputStream);
            outputStream.flush();
            outputStream.close();

            openScreenshot(imageFile);




        }catch (Throwable e){
            e.printStackTrace();
        }

    }
    //sending captured screenshot via image sharing app
    private void openScreenshot(File imageFile ){
        Log.d(TAG, "shareIt: Attempt to share screenshot");
        Uri uri = Uri.fromFile(imageFile);
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setDataAndType(uri,"image/*");
        sharingIntent.putExtra(Intent.EXTRA_STREAM,uri);
        startActivity(Intent.createChooser(sharingIntent,"share via"));
    }


    //Date format method
    private String getDate(long milliseconds) {
        //format Mon, 15 May 2020 22:15:46 PM
        SimpleDateFormat formatter = new SimpleDateFormat("EEE, dd MMM yyyy hh:mm:ss aaa");

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliseconds);

        return formatter.format(calendar.getTime());
    }

    //string converter
    private String numberSeparator(String numbers) {
        StringBuilder stringBuilder = new StringBuilder(numbers);
        for (int i = stringBuilder.length() - 3; i > 0; i -= 3) {
            stringBuilder.insert(i, ",");
        }
        return stringBuilder.toString();
    }



}
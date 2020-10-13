package com.coronatracker.About;

import androidx.appcompat.app.AppCompatActivity;
import de.hdodenhof.circleimageview.CircleImageView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.coronatracker.R;

public class Socials extends AppCompatActivity {
    private static final String TAG = "Socials";
    //Variables declaration
    CircleImageView mCDC;
    ImageView mWorldHealthOrg, mTwitter, mFaceBook, mWorldoMeter ;
    TextView tWorldHealth, tTwitter, tFaceBook, tWorldMeter, tCDC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socials);

        mWorldHealthOrg = findViewById(R.id.icon_holder2);
        mTwitter = findViewById(R.id.icon_holder);
        mFaceBook = findViewById(R.id.icon_holder1);
        mWorldoMeter = findViewById(R.id.icon_holder3);
        mCDC = findViewById(R.id.icon_holder4);

        tWorldMeter = findViewById(R.id.tWorldMeter);
        tCDC = findViewById(R.id.tCDC);
        tFaceBook = findViewById(R.id.tFacebook);
        tTwitter = findViewById(R.id.tTwitter);
        tWorldHealth = findViewById(R.id.tWorldHealth);


        //Image Onclick


    }

    public void clickCDC(View view) {
        Log.d(TAG, "clickCDC: cdc onclick function");
        goToThisURL("https://www.cdc.gov/coronavirus/2019-ncov/index.html");
    }

    private void goToThisURL(String s) {
        Uri linkURI = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW, linkURI));
    }

    public void clickWorldMeter(View view) {
        Log.d(TAG, "clickWorldMeter: clicking on World o meter" + Socials.class);
        goToThisURL("https://www.worldometers.info/coronavirus/");
    }

    public void clickWorldHealthOrg(View view) {
        Log.d(TAG, "clickWorldHealthOrg: clicking on World Health Org" + Socials.class);
        goToThisURL("https://covid19.who.int/");
    }

    public void clickFaceBook(View view) {
        Log.d(TAG, "clickFaceBook: clicking on FaceBook" + Socials.class);
        Toast.makeText(this, "We do not have a Facebook page yet!", Toast.LENGTH_SHORT).show();
    }

    public void clickTwitter(View view) {
        Log.d(TAG, "clickTwitter: clicking on Twitter" + Socials.class);
        Toast.makeText(this, "We do not have a Twitter page yet!", Toast.LENGTH_SHORT).show();
    }
}
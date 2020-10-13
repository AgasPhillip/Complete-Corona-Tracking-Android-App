package com.coronatracker.About;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.coronatracker.R;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

public class AppInfo extends AppCompatActivity {
    private static final String TAG = "AppInfo";
    TextView license, devic_info;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_info);
        Log.d(TAG, "onCreate: clicking ");
        license = (TextView) findViewById(R.id.license_word);
        devic_info = (TextView) findViewById(R.id.device_info);


        //Device Info displayed
        devic_info.setText(
                "" + Build.MANUFACTURER + " " + Build.MODEL + "\n"
                        + Build.ID + "\n"
                        + Build.DISPLAY + "\n"
                        + Build.HARDWARE + "\n"
                        + Build.USER + "\n"
                        + "CoronaStats BETA - 001"
        );


        //Licence

        license.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setLicence();

//
            }
        });


    }

    private void setLicence() {
        Log.d(TAG, "setLicence: Alert Dialog");

        //licence Dialog
        ContextThemeWrapper wrappedContext = new ContextThemeWrapper(AppInfo.this, R.style.AlertDialog);
        final AlertDialog licence = new AlertDialog.Builder(wrappedContext)
                .setTitle("Licence")
                .setMessage(getResources().getString(R.string.licence))
                .setPositiveButton(getResources().getString(R.string.cancel), null)
                .setCancelable(true)
                .show();


        licence.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.dark_grey));

    }


}
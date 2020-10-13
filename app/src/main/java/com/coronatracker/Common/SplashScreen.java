package com.coronatracker.Common;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.fonts.Font;
import android.graphics.fonts.FontFamily;
import android.graphics.fonts.FontStyle;
import android.os.Bundle;
import android.os.Handler;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.WindowManager;
import android.widget.TextView;

import com.coronatracker.Home.HomeActivity;
import com.coronatracker.R;

public class SplashScreen extends AppCompatActivity {
    private static final String TAG = "SplashScreen";
    private static int SPLASH_TIMER = 1200;
    SharedPreferences sharedPreferences;
    TextView colouredCovid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        //text colour
        colouredCovid = findViewById(R.id.covid19);

        colorChange();





        new Handler().postDelayed(new Runnable() {


            @Override
            public void run() {

                sharedPreferences = getSharedPreferences("boardingSCreen", MODE_PRIVATE);
                boolean newUser = sharedPreferences.getBoolean("newUser", true);

                if (newUser) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("newUser", false);
                    editor.apply();

                    Intent intent = new Intent(getApplicationContext(), OnBoardingScreen.class);
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(intent);
                    finish();


                }


            }
        }, SPLASH_TIMER);
    }
    private  void colorChange(){
        String covid19 = "COVID-19";
        SpannableString spannableString = new SpannableString(covid19);
        ForegroundColorSpan blue = new ForegroundColorSpan(Color.BLUE);
        ForegroundColorSpan red = new ForegroundColorSpan(Color.RED);
        ForegroundColorSpan black = new ForegroundColorSpan(Color.BLACK);


        spannableString.setSpan(blue, 0, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(red, 6, 8, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(black, 5, 6, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        colouredCovid.setText(covid19);

    }
}
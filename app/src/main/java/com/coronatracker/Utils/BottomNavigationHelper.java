package com.coronatracker.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.MenuItem;

import com.coronatracker.About.AboutActivity;
import com.coronatracker.Help.HelpActivity;
import com.coronatracker.Home.HomeActivity;
import com.coronatracker.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import androidx.annotation.NonNull;

public class BottomNavigationHelper {
    private static final String TAG = "BottomNavigationHelper";
    public  static void setupBottomNavigationView(BottomNavigationViewEx bottomNavigationViewEx){
        Log.d(TAG, "setupBottomNavigationView: Setting up BottomNavigation ");
        bottomNavigationViewEx.enableAnimation(false);
        bottomNavigationViewEx.enableShiftingMode(false);
        bottomNavigationViewEx.setTextVisibility(false);
    }
    public  static void enableNavigation (final Context context, final Activity callingActivity , BottomNavigationViewEx view){
        view.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){

                    case R.id.homeItem:
                        Intent intent1 = new Intent(context, HomeActivity.class); //ACTIVITY_NUM = 0
                        context.startActivity(intent1);
                        callingActivity.overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                        break;
                    case R.id.helpItem:
                        Intent intent2 = new Intent(context, HelpActivity.class);//ACTIVITY_NUM = 1
                        context.startActivity(intent2);
                        callingActivity.overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                        break;

                    case R.id.aboutItem:
                        Intent intent3 = new Intent(context, AboutActivity.class);//ACTIVITY_NUM = 2
                        context.startActivity(intent3);
                        callingActivity.overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                        break;
                }
                return false;
            }
        });
    }
}

package com.coronatracker.Help;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.coronatracker.R;
import com.coronatracker.Utils.BottomNavigationHelper;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class HelpActivity extends AppCompatActivity {
    private Context mContext = HelpActivity.this;

    private static final String TAG = "HelpActivity";
    private static final int ACTIVITY_NUM = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        Log.d(TAG, "onCreate: Help");

        init();
        setupBottomNavigationView();

    }

    @Override
    public void onBackPressed() {
        finish();
    }

    private void init() {
        Log.d(TAG, "init: Navigation to help Fragment " + getString(R.string.help_fragment));
        HelpFragment fragment = new HelpFragment();
        FragmentTransaction transaction = HelpActivity.this.getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.setCustomAnimations(R.anim.slide_in_right,R.anim.slide_out_left);
        transaction.addToBackStack(getString(R.string.help_fragment));
        transaction.commit();


    }

    /*
    Setup Navigation View
     */
    private void setupBottomNavigationView() {
        Log.d(TAG, "setupBottomNavigationView: setting up Navigation View");
        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottomNavigationBar);
        BottomNavigationHelper.setupBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationHelper.enableNavigation(mContext, this, bottomNavigationViewEx);
        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);

    }
}
package com.coronatracker.Home;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.coronatracker.R;
import com.coronatracker.Utils.BottomNavigationHelper;
import com.coronatracker.Utils.SectionPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class HomeActivity extends AppCompatActivity {
    private Context mContext = HomeActivity.this;
    private static final String TAG = "ActivityHome";
    private static final int ACTIVITY_NUM = 0;


    //Firebase
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Log.d(TAG, "onCreate: starting");




        setupBottomNavigationView();
        setupViewPager();




    }

    @Override
    public void onBackPressed() {
        this.finish();
    }


    /**
     * Adding tab home
     */

    public void setupViewPager() {
        SectionPagerAdapter adapter = new SectionPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new HomeFragment());//index = 0
        adapter.addFragment(new WorldFragment());//index = 1
        ViewPager viewPager = (ViewPager) findViewById(R.id.container);
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

//        tabLayout.getTabAt(0).setIcon(R.drawable.covid19);
//        tabLayout.getTabAt(1).setIcon(R.drawable.glob_ico);

        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#e60000"));

    }

    /*
    Setup Navigation View
     */
    private void setupBottomNavigationView() {
        Log.d(TAG, "setupBottomNavigationView: setting up Navigation View");
        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottomNavigationBar);
        BottomNavigationHelper.setupBottomNavigationView(bottomNavigationViewEx);
        bottomNavigationViewEx.enableAnimation(true);
        BottomNavigationHelper.enableNavigation(mContext, this,bottomNavigationViewEx);
        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);


    }


}
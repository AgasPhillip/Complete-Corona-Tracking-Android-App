package com.coronatracker.Common;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.coronatracker.Home.HomeActivity;
import com.coronatracker.R;
import com.coronatracker.Utils.SliderAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class OnBoardingScreen extends AppCompatActivity {
    private static final String TAG = "OnBoardingScreen";
    ViewPager viewPager;
    LinearLayout dotsLayout;
    Button letsGo,skip,next;
    int currentPosition;
    SliderAdapter sliderAdapter;
    TextView[] dots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding_screen);

        //hooks
        viewPager = findViewById(R.id.slider);
        dotsLayout = findViewById(R.id.dots);
        letsGo = (Button) findViewById(R.id.letsGo_Btn);
        skip = (Button) findViewById(R.id.skip_Btn);
        next = (Button) findViewById(R.id.next_Btn);

        //calling home screen
        letsGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //call adapter
        sliderAdapter = new SliderAdapter(this);

        viewPager.setAdapter(sliderAdapter);


        addDots(0);
        viewPager.addOnPageChangeListener(changeListener);
    }

    public void skip(View view) {
        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
        finish();

    }

    public void next(View view) {
        viewPager.setCurrentItem(currentPosition + 1);

    }


    private void addDots(int position) {
        Log.d(TAG, "addDots: change from one slider to next");

        dots = new TextView[4];
        dotsLayout.removeAllViews();

        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextSize(36);

            dotsLayout.addView(dots[i]);
        }
        if (dots.length > 0) {
            dots[position].setTextColor(getResources().getColor(R.color.colorPrimary));
        }

    }

    ViewPager.OnPageChangeListener changeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDots(position);
            currentPosition = position;

            if (position == 0) {
                letsGo.setVisibility(View.INVISIBLE);
                skip.setVisibility(View.VISIBLE);
                next.setVisibility(View.VISIBLE);
                dotsLayout.setVisibility(View.VISIBLE);

            } else if (position == 1) {
                letsGo.setVisibility(View.INVISIBLE);
                skip.setVisibility(View.VISIBLE);
                next.setVisibility(View.VISIBLE);
                dotsLayout.setVisibility(View.VISIBLE);

            } else if (position == 2) {
                letsGo.setVisibility(View.INVISIBLE);
                skip.setVisibility(View.VISIBLE);
                next.setVisibility(View.VISIBLE);
                dotsLayout.setVisibility(View.VISIBLE);

            } else {
                letsGo.setVisibility(View.VISIBLE);
                skip.setVisibility(View.INVISIBLE);
                next.setVisibility(View.INVISIBLE);
                dotsLayout.setVisibility(View.INVISIBLE);


            }


        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}
package com.coronatracker.Help;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.coronatracker.R;
import com.coronatracker.Utils.PreventionHelper;
import com.coronatracker.Utils.SelfCareHelper;
import com.coronatracker.Utils.SpreadHelper;
import com.coronatracker.Utils.SymptomsHelper;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class HelpFragment extends Fragment  {
    private static final String TAG = "HelpFragment";
    RecyclerView symptoms, preventions, selfCare, helpLineNumbers,spread;
    RecyclerView.Adapter adapter;
    private Context mContext = getActivity();
    NumbersAdapter numbersAdapter;
    private ImageView ambulance_image;

    int[] cIcon;
    String[] cCode;
    String[] cNumber;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_help, container, false);

//        cIcon = view.findViewById(R.id.imgCountryFlag);
//        cCode = view.findViewById(R.id.countryCode);
//        cNumber = view.findViewById(R.id.countryNumber);


        //Hooks
        ambulance_image = view.findViewById(R.id.ambulance_image);
        symptoms = view.findViewById(R.id.symptoms_recycler);
        helpLineNumbers = view.findViewById(R.id.helpNumbers);
        preventions = view.findViewById(R.id.prevention_recycler);
        selfCare = view.findViewById(R.id.self_care_recycler);
        spread = view.findViewById(R.id.spread_recycler);
        symptoms.setFocusable(false);

        ambulance_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveAmbulance();

            }
        });






        //This is hot line methods
        cIcon = new int []{R.drawable.ic_angola
                ,R.drawable.ic_botswana //1 Botswana
                ,R.drawable.ic_botswana //2 Botswana
                ,R.drawable.ic_botswana //3 Botswana
                ,R.drawable.ic_botswana //4 Botswana

                ,R.drawable.ic_comoros //1 Comoros
                ,R.drawable.ic_comoros //1 Comoros
                ,R.drawable.ic_comoros //1 Comoros
                ,R.drawable.ic_comoros //1 Comoros

                ,R.drawable.ic_democratic_republic_congo //1 DRC
                ,R.drawable.ic_democratic_republic_congo //1 DRC

                ,R.drawable.ic_swaziland // Esw
                ,R.drawable.ic_lesotho // Les

                ,R.drawable.ic_madagascar // mad

                ,R.drawable.ic_malawi // Mal

                ,R.drawable.ic_mauritius // Mal

                ,R.drawable.ic_mozambique // Moz
                ,R.drawable.ic_mozambique // Moz
                ,R.drawable.ic_mozambique // Moz
                ,R.drawable.ic_mozambique // Moz
                ,R.drawable.ic_mozambique // Moz


                ,R.drawable.ic_namibia // Nam

                ,R.drawable.ic_seychelles // Sey

                ,R.drawable.ic_south_africa // RSA
                ,R.drawable.ic_south_africa // RSA

                ,R.drawable.ic_tanzania // Tan
                ,R.drawable.ic_tanzania // Tan
                ,R.drawable.ic_tanzania // Tan

                ,R.drawable.ic_zambia // Zam
                ,R.drawable.ic_zambia // Zam
                ,R.drawable.ic_zambia // Zam
                ,R.drawable.ic_zambia // Zam

                ,R.drawable.ic_zimbabwe // Zim
                ,R.drawable.ic_zimbabwe // Zim

                };
        cCode = new String[]{getResources().getString(R.string.ang_CountryCode)
                ,getResources().getString(R.string.bot_CountryCode) //1 Botswana
                ,getResources().getString(R.string.bot1_CountryCode) //2 Botswana
                ,getResources().getString(R.string.bot2_CountryCode) //3 Botswana
                ,getResources().getString(R.string.bot3_CountryCode) //4 Botswana

                ,getResources().getString(R.string.com_CountryCode) //1 Comoros
                ,getResources().getString(R.string.com1_CountryCode) //2 Comoros
                ,getResources().getString(R.string.com2_CountryCode) //3 Comoros
                ,getResources().getString(R.string.com3_CountryCode) //4 Comoros

                ,getResources().getString(R.string.drc_CountryCode) //1 DRC
                ,getResources().getString(R.string.drc1_CountryCode) //2 DRC

                ,getResources().getString(R.string.esw_CountryCode) // Esw

                ,getResources().getString(R.string.les_CountryCode) // les

                ,getResources().getString(R.string.mad_CountryCode) // Mad

                ,getResources().getString(R.string.mal_CountryCode) // Mal

                ,getResources().getString(R.string.mau_CountryCode) // Mal

                ,getResources().getString(R.string.moz_CountryCode) // Moz
                ,getResources().getString(R.string.moz1_CountryCode) // Moz
                ,getResources().getString(R.string.moz2_CountryCode) // Moz
                ,getResources().getString(R.string.moz3_CountryCode) // Moz
                ,getResources().getString(R.string.moz4_CountryCode) // Moz

                ,getResources().getString(R.string.nam_CountryCode) // Nam

                ,getResources().getString(R.string.sey_CountryCode) // Sey

                ,getResources().getString(R.string.rsa_CountryCode) // Sey
                ,getResources().getString(R.string.rsa1_CountryCode) // RSA

                ,getResources().getString(R.string.tan_CountryCode) // Tan
                ,getResources().getString(R.string.tan1_CountryCode) // Tan
                ,getResources().getString(R.string.tan2_CountryCode) // Tan

                ,getResources().getString(R.string.zam_CountryCode) // Zam
                ,getResources().getString(R.string.zam1_CountryCode) // Zam
                ,getResources().getString(R.string.zam2_CountryCode) // Zam
                ,getResources().getString(R.string.zam3_CountryCode) // Zam

                ,getResources().getString(R.string.zim_CountryCode) // Zim
                ,getResources().getString(R.string.zim1_CountryCode) // Zim
        };
        cNumber = new String[]{getResources().getString(R.string.ang_helpNumber)
                , getResources().getString(R.string.bot_helpNumber)
                ,getResources().getString(R.string.bot1_helpNumber)
                ,getResources().getString(R.string.bot2_helpNumber)
                ,getResources().getString(R.string.bot3_helpNumber)

                ,getResources().getString(R.string.com_helpNumber)
                ,getResources().getString(R.string.com2_helpNumber)
                ,getResources().getString(R.string.com2_helpNumber)
                ,getResources().getString(R.string.com3_helpNumber)

                ,getResources().getString(R.string.drc_helpNumber) //DRC
                ,getResources().getString(R.string.drc1_helpNumber) //DRC

                ,getResources().getString(R.string.esw_helpNumber) //Esw

                ,getResources().getString(R.string.les_helpNumber) //les

                ,getResources().getString(R.string.mad_helpNumber) //mad

                ,getResources().getString(R.string.mal_helpNumber) //mal

                ,getResources().getString(R.string.mau_helpNumber) //Mau

                ,getResources().getString(R.string.moz_helpNumber) //Moz
                ,getResources().getString(R.string.moz1_helpNumber) //Moz
                ,getResources().getString(R.string.moz2_helpNumber) //Moz
                ,getResources().getString(R.string.moz3_helpNumber) //Moz
                ,getResources().getString(R.string.moz4_helpNumber) //Moz

                ,getResources().getString(R.string.nam_helpNumber) //Nam

                ,getResources().getString(R.string.sey_helpNumber) //Sey

                ,getResources().getString(R.string.rsa_helpNumber) //RSA
                ,getResources().getString(R.string.rsa1_helpNumber) //RSA

                ,getResources().getString(R.string.tan_helpNumber) //Tan
                ,getResources().getString(R.string.tan1_helpNumber) //Tan
                ,getResources().getString(R.string.tan2_helpNumber) //Tan

                ,getResources().getString(R.string.zam_helpNumber) //Zam
                ,getResources().getString(R.string.zam1_helpNumber) //Zam
                ,getResources().getString(R.string.zam2_helpNumber) //Zam
                ,getResources().getString(R.string.zam3_helpNumber) //Zam

                ,getResources().getString(R.string.zim_helpNumber) //Zim
                ,getResources().getString(R.string.zim1_helpNumber) //Zim
        };


        numbersAdapter = new NumbersAdapter(getContext(),cIcon,cCode,cNumber);
        helpLineNumbers.setLayoutManager(new LinearLayoutManager(mContext));
        helpLineNumbers.setAdapter(numbersAdapter);







        featuredRecycler();
        preventionRecycler();
        selfCareRecycler();
        setSpreadRecycler();


        helpLineNumbers.setLayoutManager(new LinearLayoutManager(getActivity()));

//        //Line divider
//        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(helpLineNumbers.getContext(), DividerItemDecoration.VERTICAL);
//        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(getContext(), R.drawable.line_divider));
//        helpLineNumbers.addItemDecoration(dividerItemDecoration);

        return view;
    }

    private void moveAmbulance(){
        //moving ambulance
        TranslateAnimation animation = new TranslateAnimation (-50.0f, 400.0f,0.0f,0.0f);
        animation.setDuration(5000);
        animation.setRepeatCount(5);
        animation.setRepeatMode(2);
        ambulance_image.startAnimation(animation);
    }


    private void selfCareRecycler() {
        selfCare.setHasFixedSize(true);
        selfCare.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<SelfCareHelper> selfCareLocation = new ArrayList<>();
        selfCareLocation.add(new SelfCareHelper(R.drawable.vitamins, getResources().getString(R.string.take_vit)));
        selfCareLocation.add(new SelfCareHelper(R.drawable.ic_sleep, getResources().getString(R.string.rest_sleep)));
        selfCareLocation.add(new SelfCareHelper(R.drawable.ic_jacket, getResources().getString(R.string.keep_warm)));
        selfCareLocation.add(new SelfCareHelper(R.drawable.ic_mug, getResources().getString(R.string.drink_liquids)));
        selfCareLocation.add(new SelfCareHelper(R.drawable.ic_bath_tub, getResources().getString(R.string.take_hot)));

        adapter = new SelfCareAdapter(selfCareLocation);
        selfCare.setAdapter(adapter);


    }

    //How it spread method
    private void setSpreadRecycler(){
        spread.setHasFixedSize(true);
        spread.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL,false));

        ArrayList<SpreadHelper> spreadLocation = new ArrayList<>();
        spreadLocation.add(new SpreadHelper(R.drawable.ic_staircase,getResources().getString(R.string.staircase)));
        spreadLocation.add(new SpreadHelper(R.drawable.ic_elevator,getResources().getString(R.string.elevator)));
        spreadLocation.add(new SpreadHelper(R.drawable.ic_multiple_devices,getResources().getString(R.string.gadgets)));
        spreadLocation.add(new SpreadHelper(R.drawable.ic_steak,getResources().getString(R.string.contaminated)));
        spreadLocation.add(new SpreadHelper(R.drawable.ic_money,getResources().getString(R.string.money)));
        spreadLocation.add(new SpreadHelper(R.drawable.ic_bus,getResources().getString(R.string.public_transport)));

        adapter = new SpreadAdapter(spreadLocation);
        spread.setAdapter(adapter);

    }



    private void preventionRecycler() {
        preventions.setHasFixedSize(true);
        preventions.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<PreventionHelper> preventionLocation = new ArrayList<>();
        preventionLocation.add(new PreventionHelper(R.drawable.hand_wash, getResources().getString(R.string.hand_wash)));
        preventionLocation.add(new PreventionHelper(R.drawable.medical_mask, getResources().getString(R.string.wear_mask)));
        preventionLocation.add(new PreventionHelper(R.drawable.avoid, getResources().getString(R.string.avoid_sick)));
        preventionLocation.add(new PreventionHelper(R.drawable.cough, getResources().getString(R.string.cover_your)));
        preventionLocation.add(new PreventionHelper(R.drawable.avoid_fly, getResources().getString(R.string.avoid_fly)));
        preventionLocation.add(new PreventionHelper(R.drawable.distance, getResources().getString(R.string.keep_distance)));
        preventionLocation.add(new PreventionHelper(R.drawable.hand_sanitizer, getResources().getString(R.string.sanitizer)));
        preventionLocation.add(new PreventionHelper(R.drawable.wet_wipes, getResources().getString(R.string.wet_wipe)));

        adapter = new PreventionAdapter(preventionLocation);
        preventions.setAdapter(adapter);


    }

    private void featuredRecycler() {
        symptoms.setHasFixedSize(true);
        symptoms.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<SymptomsHelper> symptomsLocations = new ArrayList<>();

        symptomsLocations.add(new SymptomsHelper(R.drawable.ic_fever, getResources().getString(R.string.high_fever), getResources().getString(R.string.temper)));
        symptomsLocations.add(new SymptomsHelper(R.drawable.throat, getResources().getString(R.string.sore_throat), getResources().getString(R.string.irritation_throat)));
        symptomsLocations.add(new SymptomsHelper(R.drawable.ic_cough, getResources().getString(R.string.dry_cough), getResources().getString(R.string.a_dry_coug)));
        symptomsLocations.add(new SymptomsHelper(R.drawable.tiredness, getResources().getString(R.string.tiredness), getResources().getString(R.string.feeling_tired)));
        symptomsLocations.add(new SymptomsHelper(R.drawable.lungs, getResources().getString(R.string.difficulty_breath), getResources().getString(R.string.difficulty_brea)));

        adapter = new SymptomsAdapter(symptomsLocations);
        symptoms.setAdapter(adapter);
    }





}

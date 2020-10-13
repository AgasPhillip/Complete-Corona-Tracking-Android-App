package com.coronatracker.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class SectionStatePageAdapter extends FragmentStatePagerAdapter {
    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final HashMap<Fragment, Integer> mFragments = new HashMap<>();
    private final HashMap<String,Integer> mFragmentsNumber = new HashMap<>();
    private final HashMap<Integer,String> mFragmentsNames = new HashMap<>();

    public SectionStatePageAdapter( FragmentManager fm) {
        super(fm);
    }


    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }
    public void addFragment(Fragment fragment,String fragmentName){
        mFragmentList.add(fragment);
        mFragments.put(fragment,mFragmentList.size()-1);
        mFragmentsNumber.put(fragmentName,mFragmentList.size()-1);
        mFragmentsNames.put(mFragmentList.size()-1,fragmentName);
    }


    /**
     * Returns the fragment with the name Param
     * @param fragmentName
     * @return
     */
    public Integer getFragmentNumber(String fragmentName){
        if(mFragmentsNumber.containsKey(fragmentName)){
            return mFragmentsNumber.get(fragmentName);
        }else {
            return null;
        }
    }
    /**
     * Returns the fragment with the name Param
     * @param fragment
     * @return
     */
    public Integer getFragmentNumber(Fragment fragment){
        if(mFragmentsNumber.containsKey(fragment)){
            return mFragmentsNumber.get(fragment);
        }else {
            return null;
        }
    }

    /**
     * Returns the fragment with the name Param
     * @param fragmentNumber
     * @return
     */
    public String getFragmentName(Integer fragmentNumber){
        if(mFragmentsNames.containsKey(fragmentNumber)){
            return mFragmentsNames.get(fragmentNumber);
        }else {
            return null;
        }
    }

}

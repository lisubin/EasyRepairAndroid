package com.repair.lsb.easyrepair.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import java.util.ArrayList;

public class MyFragmentStatePagerAdapter extends FragmentStatePagerAdapter {
    private ArrayList<Fragment> data;

    public MyFragmentStatePagerAdapter(FragmentManager fm,ArrayList<Fragment> data) {
        super(fm);
        this.data = data;
    }

    @Override
    public Fragment getItem(int i) {
        return data.get(i);
    }



    @Override
    public int getCount() {
        return data.size();
    }

}

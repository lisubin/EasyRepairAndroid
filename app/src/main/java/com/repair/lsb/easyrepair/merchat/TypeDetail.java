package com.repair.lsb.easyrepair.merchat;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.repair.lsb.easyrepair.R;
import com.repair.lsb.easyrepair.adapter.MyFragmentPagerAdapter;
import com.repair.lsb.easyrepair.adapter.MyTypePagerAdapter;
import com.repair.lsb.easyrepair.base.BaseActivity;
import com.repair.lsb.easyrepair.fragment.MineFragment;
import com.repair.lsb.easyrepair.model.Type;

import java.util.ArrayList;

public class TypeDetail extends BaseActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ArrayList<Type> types;

    @Override
    public int setContentView() {
        return R.layout.type_detail;
    }

    @Override
    public void initView() {
        types = new ArrayList<>();
        tabLayout = findView(R.id.tabs2);
        viewPager = findView(R.id.viewPager3);
    }

    @Override
    public void initData() {
        viewPager.setAdapter(new MyTypePagerAdapter(context, types));
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void onClick(View v) {

    }
}

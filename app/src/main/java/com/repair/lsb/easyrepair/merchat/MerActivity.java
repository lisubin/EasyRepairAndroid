package com.repair.lsb.easyrepair.merchat;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.repair.lsb.easyrepair.R;
import com.repair.lsb.easyrepair.adapter.MyFragmentPagerAdapter;
import com.repair.lsb.easyrepair.adapter.MyViewPager;
import com.repair.lsb.easyrepair.base.BaseActivity;
import com.repair.lsb.easyrepair.fragment.EvaFragment;
import com.repair.lsb.easyrepair.fragment.MerFragment;
import com.repair.lsb.easyrepair.model.Merchant;

import java.util.ArrayList;


public class MerActivity extends BaseActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private ImageView imageView;
    private TextView merName, merAddress;
    private LinearLayout linearLayout;

    @Override
    public int setContentView() {
        return R.layout.mer_interface;
    }

    @Override
    public void initView() {
        viewPager = findView(R.id.viewPager2);
        tabLayout = findView(R.id.tabs);
        imageView = findView(R.id.imageView);
        merName = findView(R.id.merName);
        merAddress = findView(R.id.merAddress);
        linearLayout = findView(R.id.bottom2);


    }

    @Override
    public void initData() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        ArrayList<String> titles = new ArrayList<>();
        fragments.add(new MerFragment());
        fragments.add(new EvaFragment());
        titles.add("首页");
        titles.add("评论");
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            Merchant merchant = (Merchant) bundle.getSerializable("merchant");
            // Merchant merchant = (Merchant) intent.getSerializableExtra("merchant");
            if (merchant != null) {
                Glide.with(this).load(merchant.getImage()).apply(new RequestOptions().centerCrop()).into(imageView);
                merName.setText(merchant.getName());
                merAddress.setText(merchant.getAddress());
            }
        }

        viewPager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(), fragments, titles));
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    public void initListener() {

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                if(i == 1){
                    linearLayout.setVisibility(View.GONE);
                }else {
                    linearLayout.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

    }

    @Override
    public void onClick(View v) {

    }
}

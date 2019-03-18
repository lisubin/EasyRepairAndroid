package com.repair.lsb.easyrepair;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureMimeType;
import com.repair.lsb.easyrepair.adapter.MyFragmentStatePagerAdapter;
import com.repair.lsb.easyrepair.base.BaseActivity;
import com.repair.lsb.easyrepair.constat.AppConfig;
import com.repair.lsb.easyrepair.customview.NoScrollViewPager;
import com.repair.lsb.easyrepair.fragment.ChatFragment;
import com.repair.lsb.easyrepair.fragment.Community;
import com.repair.lsb.easyrepair.fragment.MainFragment;
import com.repair.lsb.easyrepair.fragment.MineFragment;
import com.repair.lsb.easyrepair.fragment.OrderFragment;
import com.repair.lsb.easyrepair.user.UserGoRepair;
import com.repair.lsb.easyrepair.utils.ActivityUtils;
import com.repair.lsb.easyrepair.utils.DialogUtils;
import com.repair.lsb.easyrepair.utils.OkHttp3Utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends BaseActivity {

    private BottomNavigationView bottomNavigationView;
    private NoScrollViewPager viewPager;
    private ArrayList<Fragment> data;
    private TextView textView;
    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener;

    @Override
    public int setContentView() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        bottomNavigationView = findView(R.id.navigation);
        viewPager = findView(R.id.viewPager);
        textView = findView(R.id.goRepair);
        data = new ArrayList<>();
    }

    @Override
    public void initData() {
       // BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);
        data.add(new MainFragment());
        data.add(new ChatFragment());
        data.add(new Community());
        data.add(new MineFragment());


        viewPager.setScroll(false);
        viewPager.setOffscreenPageLimit(0);
        viewPager.setAdapter(new MyFragmentStatePagerAdapter(getSupportFragmentManager(),data));

     //DialogUtils.showDialog(this,"haha");
//        File file = new File("F:\\360DownLoad\\EasyRepair\\app\\src\\main\\res\\drawable\\answer_bubble.9.png");
//        OkHttp3Utils.loadFile(AppConfig.GET, file,"dd", new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                Log.i("ds",e.getMessage());
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//
//            }
//        });


 //       Intent intent = getIntent();
//        Bundle phone = intent.getExtras();
//        if(phone!=null){
//        bottomNavigationView.setSelectedItemId(R.id.wode);
//        data.get(3).setArguments(phone);
//        data.get(1).setArguments(phone);

    }

    @Override
    public void initListener() {
        textView.setOnClickListener(this);

        onNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.zhuye:
                        viewPager.setCurrentItem(0);
                        return true;
                    case R.id.chat:
                        viewPager.setCurrentItem(1);
                        return true;
                    case R.id.dingdan:
                        viewPager.setCurrentItem(2);
                        return true;
                    case R.id.wode:
                        viewPager.setCurrentItem(3);
                        return true;
                }
                return false;
            }
        };
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
            bottomNavigationView.getMenu().getItem(i).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

    }

    @Override
    public void onClick(View v) {
     switch (v.getId()){
         case R.id.goRepair:
             ActivityUtils.startActivityIntent(this,UserGoRepair.class);
             break;
     }
    }
}

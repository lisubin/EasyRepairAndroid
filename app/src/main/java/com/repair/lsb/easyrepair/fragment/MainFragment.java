package com.repair.lsb.easyrepair.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.google.gson.Gson;
import com.repair.lsb.easyrepair.R;
import com.repair.lsb.easyrepair.adapter.MyImagePagerAdapter;
import com.repair.lsb.easyrepair.adapter.MyRecyclerViewAdapter;
import com.repair.lsb.easyrepair.base.BaseFragment;
import com.repair.lsb.easyrepair.constat.AppConfig;
import com.repair.lsb.easyrepair.merchat.MerActivity;
import com.repair.lsb.easyrepair.merchat.TypeDetail;
import com.repair.lsb.easyrepair.model.Merchant;
import com.repair.lsb.easyrepair.utils.JSONUtils;
import com.repair.lsb.easyrepair.utils.OkHttp3Utils;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;


import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainFragment extends BaseFragment {
    private ViewPager banner;//广告图片
    private LinearLayout linearLayout;//小白点线性
    private TextView title;//标题

    private int[] imageIds;//图片ID

    private ArrayList<ImageView> imageViews;
    private String[] texts;

    private int previous;

    //图片轮询
    private boolean isAuto = true;
    //间隔时间
    private long intervalTime;
    //分类
    private GridView gridView;
      private MyHandle myHandle;
    private MyRecyclerViewAdapter myRecyclerViewAdapter;
    private RecyclerView merList;

    private ArrayList<Merchant> merchants;


    @Override
    protected int setLayoutResourceId() {
        return R.layout.activity_scrolling;
    }

    @Override
    public void initView() {
        banner = findView(R.id.banner);
        linearLayout = findView(R.id.ll_point_container);
        title = findView(R.id.tv_desc);
        gridView = findView(R.id.gridView);
        merList = findView(R.id.merList);
        merchants = new ArrayList<>();
        myHandle = new MyHandle(this);
    }

    @Override
    public void initData(Bundle arguments) {
        imageIds = new int[]{R.drawable.bannertupian, R.drawable.bannertupian2, R.drawable.bannertupian2,
                R.drawable.bannertupian2, R.drawable.bannertupian2, R.drawable.bannertupian2, R.drawable.bannertupian2,
                R.drawable.bannertupian2, R.drawable.bannertupian2, R.drawable.bannertupian2, R.drawable.bannertupian2,
                R.drawable.bannertupian2};
        texts = new String[]{"手机", "fdsfa", "fdsfa", "fdsfa", "fdsfa", "fdsfa", "fdsfa", "fdsfa", "fdsfa", "fdsfa", "fdsfa", "fdsfa"};
        imageViews = new ArrayList<>();
        title.setText(texts[0]);
        previous = 0;
        intervalTime = 2000;
        isAuto = false;
       // doBanner();
        final ArrayList<HashMap<String, Object>> data = new ArrayList<>();
        for (int i = 0; i < imageIds.length; i++) {
            HashMap<String, Object> item = new HashMap<>();
            item.put("typeImage", imageIds[i]);
            item.put("typeName", texts[i]);
            data.add(item);
        }
//        String[] from = new String[]{"typeImage", "typeName"};
//        int[] to = new int[]{R.id.typeImage, R.id.typeName};
//        SimpleAdapter simpleAdapter = new SimpleAdapter(mActivity, data, R.layout.type_item, from, to);
//
//        gridView.setAdapter(simpleAdapter);
        gridView.setAdapter(new CommonAdapter<HashMap<String,Object>>(getContext(),R.layout.type_item,data) {
            @Override
            protected void convert(ViewHolder viewHolder, HashMap<String, Object> item, int position) {
                        viewHolder.setText(R.id.typeName,(String)item.get("typeName"));
                        viewHolder.setImageResource(R.id.typeImage, (Integer)item.get("typeImage"));
            }
        });
        getMers();
    }


    @Override
    public void initListener() {

        banner.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                title.setText(texts[i]);
                linearLayout.getChildAt(previous).setEnabled(false);
                linearLayout.getChildAt(i).setEnabled(true);
                previous = i;

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView type = view.findViewById(R.id.typeName);
                Intent intent = new Intent();
                intent.setClass(mActivity,TypeDetail.class);
                intent.putExtra("type",type.getText());
                startActivity(intent);
            }
        });


    }

    @Override
    public void onClick(View v) {
    }


    private void doBanner() {
        ImageView imageView;
        View pointView;
        LinearLayout.LayoutParams layoutParams;


        for (int i = 0; i < imageIds.length; i++) {
            // 初始化要显示的图片对象
            imageView = new ImageView(mActivity);
            imageView.setBackgroundResource(imageIds[i]);
            imageViews.add(imageView);

            // 加小白点, 指示器
            pointView = new View(getContext());
            pointView.setBackgroundResource(R.drawable.selector_point);
            //  pointView.setBackgroundColor(R.drawable.selector_point);
            layoutParams = new LinearLayout.LayoutParams(5, 5);
            if (i != 0)
                layoutParams.leftMargin = 10;
            // 设置默认所有都不可用
            pointView.setEnabled(false);
            linearLayout.addView(pointView, layoutParams);
        }
        linearLayout.getChildAt(0).setEnabled(true);
        banner.setAdapter(new MyImagePagerAdapter(imageViews));


// 开启轮询
        new Thread() {
            public void run() {
                while (isAuto) {
                    try {
                        Thread.sleep(intervalTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // 往下跳一位
                    mActivity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            banner.setCurrentItem((banner.getCurrentItem() + 1) % imageIds.length);
                        }
                    });
                }
            }
        }.start();

    }

    private void getMers() {
        OkHttp3Utils.doGet(AppConfig.GET_MERCHANT, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("获取失败", e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String data = response.body().string();
                merchants = JSONUtils.jsonToArrayObj(data, Merchant.class);

                Message message = new Message();
                message.obj = merchants;
                myHandle.sendMessage(message);

            }
        });
    }

    private static class MyHandle extends Handler{
        private WeakReference<Fragment> fragmentWeakReference;

        private MyHandle(Fragment fragment){
          fragmentWeakReference = new WeakReference<>(fragment);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            final MainFragment mainFragment = (MainFragment) fragmentWeakReference.get();
            final  ArrayList<Merchant> data = (ArrayList<Merchant>) msg.obj;
            mainFragment.myRecyclerViewAdapter = new MyRecyclerViewAdapter(mainFragment.getContext(),data);
            mainFragment.merList.setAdapter(mainFragment.myRecyclerViewAdapter);
            mainFragment.merList.setLayoutManager(new LinearLayoutManager(mainFragment.getContext()));
            mainFragment.myRecyclerViewAdapter.setOnItemClickListener(new MyRecyclerViewAdapter.OnItemClickListener() {
                @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(mainFragment.getContext(),MerActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("merchant",data.get(position));
                        intent.putExtras(bundle);
                        //intent.putExtra("merchant",merchants.get(position));
                        mainFragment.startActivity(intent);
                }
            });
        }
    }

}



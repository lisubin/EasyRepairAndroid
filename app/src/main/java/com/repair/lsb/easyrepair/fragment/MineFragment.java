package com.repair.lsb.easyrepair.fragment;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.bumptech.glide.Glide;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureMimeType;
import com.repair.lsb.easyrepair.R;
import com.repair.lsb.easyrepair.adapter.ListAdapter;
import com.repair.lsb.easyrepair.base.BaseFragment;
import com.repair.lsb.easyrepair.user.UserLogin;
import com.repair.lsb.easyrepair.utils.ActivityUtils;
import com.repair.lsb.easyrepair.utils.ConstantUtil;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import static android.support.v4.content.ContextCompat.checkSelfPermission;


public class MineFragment extends BaseFragment {
    private ListView listView;
    private LinearLayout linearLayout;
    private ArrayList<String> data;
    private TextView textView;
    private ImageView avatar;
    private int value;

    private AMapLocationClient aMapLocationClient;
    //声明AMapLocationClient类对象
    private AMapLocationClient mLocationClient = null;
    //声明定位回调监听器
    private AMapLocationListener mLocationListener;

    public AMapLocationClientOption mLocationOption = null;

    @Override
    protected int setLayoutResourceId() {
        return R.layout.activity_mine;
    }

    @Override
    public void initView() {
        listView = findView(R.id.listView);
        linearLayout = findView(R.id.up);
        textView = findView(R.id.username);
        avatar = findView(R.id.touxiang);

    }

    @Override
    public void initData(Bundle arguments) {
        value = 101;
        Glide.with(this).load(R.drawable.touxiang).into(avatar);
        SharedPreferences login = mActivity.getSharedPreferences("login", Context.MODE_PRIVATE);
        String phone1 = login.getString("phone", null);

       if(phone1!=null){
        textView.setText(phone1);}
        data = new ArrayList<>();
        data.add("haha");
        data.add("afa");
        data.add("afa");
        data.add("afa");
        listView.setAdapter(new CommonAdapter<String>(getContext(),R.layout.mine_item,data) {
            @Override
            protected void convert(ViewHolder viewHolder, String item, int position) {
                viewHolder.setText(R.id.text,data.get(position));
            }
        });
        requestPermission();

    }

    @Override
    public void initListener() {
        linearLayout.setOnClickListener(this);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("fdasfasf", position + "");
            }
        });


    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.up) {
            if (value == ConstantUtil.UN_LOGIN) {
                ActivityUtils.startActivityIntent(getContext(), UserLogin.class);
            }
        }
    }

    public void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(getContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.CHANGE_WIFI_STATE,
                    Manifest.permission.READ_PHONE_STATE,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_WIFI_STATE,
                    Manifest.permission.CAMERA
            }, 1);
        }
    }
}

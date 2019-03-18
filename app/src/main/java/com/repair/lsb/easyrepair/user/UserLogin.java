package com.repair.lsb.easyrepair.user;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.entity.LocalMedia;
import com.repair.lsb.easyrepair.MainActivity;

import com.repair.lsb.easyrepair.R;
import com.repair.lsb.easyrepair.base.BaseActivity;
import com.repair.lsb.easyrepair.constat.AppConfig;
import com.repair.lsb.easyrepair.model.UserEntity;
import com.repair.lsb.easyrepair.utils.ActivityUtils;
import com.repair.lsb.easyrepair.utils.DialogUtils;
import com.repair.lsb.easyrepair.utils.JSONUtils;
import com.repair.lsb.easyrepair.utils.OkHttp3Utils;
import com.repair.lsb.easyrepair.utils.StringUtils;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class UserLogin extends BaseActivity {
   private EditText phone,pwd;
   private Button login;
   private TextView textView,title;
   private SharedPreferences logins;
   private ImageView avatar;
   private SwipeRefreshLayout swipeRefreshLayout;
    @Override
    public int setContentView() {
        return R.layout.user_login;
    }

    @Override
    public void initView() {
        phone = findView(R.id.phoneNumber);
        pwd = findView(R.id.passWord);
        login = findView(R.id.userLogin);
        textView = findView(R.id.goRegister);
        title= findView(R.id.layout_title).findViewById(R.id.content);
        avatar = findView(R.id.avatar4);
    }

    @Override
    public void initData() {
         title.setText("登录");
    }

    @Override
    public void initListener() {
        login.setOnClickListener(this);
        textView.setOnClickListener(this);
        avatar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
       switch (v.getId()){
           case R.id.userLogin:
               final String phoneNum = phone.getText().toString().trim();
               String password = pwd.getText().toString().trim();
               JSONObject jsonObject = JSONUtils.objToJson(new UserEntity(phoneNum,password));
               OkHttp3Utils.doPost(AppConfig.SEARCH_USER, jsonObject.toString(), new Callback() {
                   @Override
                   public void onFailure(Call call, IOException e) {
                       Log.i("result",e.getMessage());
                   }

                   @Override
                   public void onResponse(Call call, Response response) throws IOException {
                       String content = response.body().string();
                       int id = StringUtils.stringIsInteger(content);
                       logins =getSharedPreferences("login", Context.MODE_PRIVATE);
                       SharedPreferences.Editor editor = logins.edit();
                       if(id > 0 ){
                           editor.putString("phone",phoneNum);
                           editor.putInt("id",id);
                           editor.apply();
                           ActivityUtils.startActivityIntent(context,MainActivity.class);
                       }

                   }
               });
               break;
           case R.id.goRegister:
               startActivity(new Intent(context,UserRegister.class));
               break;
           case R.id.iv_back:
               ActivityUtils.startActivityIntent(context,MainActivity.class);
               break;
           case R.id.avatar4:
               DialogUtils.showDialog(context);

       }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK) switch (requestCode) {
            case 2:
                List<LocalMedia> localMedia = PictureSelector.obtainMultipleResult(data);
                Glide.with(context).load(localMedia.get(0).getCompressPath()).into(avatar);
                File file = new File(localMedia.get(0).getCompressPath());
                Log.i("ssssssssssss",file.length()+"");
                OkHttp3Utils.loadFile(AppConfig.GET, new File(localMedia.get(0).getCompressPath()), "ss", new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.i("失败原因",e.getMessage());
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {

                    }
                });
                break;
        }
        }
    }


package com.repair.lsb.easyrepair.user;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.repair.lsb.easyrepair.R;
import com.repair.lsb.easyrepair.base.BaseActivity;
import com.repair.lsb.easyrepair.constat.AppConfig;
import com.repair.lsb.easyrepair.model.UserEntity;
import com.repair.lsb.easyrepair.utils.JSONUtils;
import com.repair.lsb.easyrepair.utils.OkHttp3Utils;

import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class UserRegister extends BaseActivity {
    private EditText phone,valid;
    private Button send,register;
    @Override
    public int setContentView() {
        return R.layout.user_register;
    }

    @Override
    public void initView() {
        phone = findView(R.id.phone);
        valid = findView(R.id.valid);
        send = findView(R.id.send);
        register = findView(R.id.register);

    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
       send.setOnClickListener(this);
       register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
    switch (v.getId()){
        case R.id.send:
            break;
        case R.id.register:
            String getPhone = getContent(phone);
            String pwd = getContent(valid);
            JSONObject data = JSONUtils.objToJson(new UserEntity(getPhone, pwd));

            OkHttp3Utils.doPost(AppConfig.INSERT_USER, data.toString(), new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.i("result",e.getMessage());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String content = response.body().string();
                    Log.i("content",content);

                }
            });

            break;
    }

    }
}

package com.repair.lsb.easyrepair.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.repair.lsb.easyrepair.R;
import com.repair.lsb.easyrepair.adapter.MyChatItemAdapter;

import com.repair.lsb.easyrepair.base.BaseFragment;

import com.repair.lsb.easyrepair.chat.ChatActivity;
import com.repair.lsb.easyrepair.constat.AppConfig;
import com.repair.lsb.easyrepair.model.ChatItem;

import com.repair.lsb.easyrepair.user.UserLogin;
import com.repair.lsb.easyrepair.utils.ActivityUtils;
import com.repair.lsb.easyrepair.utils.JSONUtils;
import com.repair.lsb.easyrepair.utils.OkHttp3Utils;

import org.json.JSONObject;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ChatFragment extends BaseFragment {
    private RecyclerView chatList;
    private MyChatItemAdapter myChatItemAdapter;
    private ArrayList<ChatItem> contents;
    private MyHandle myHandle;
    private int id;

    @Override
    protected int setLayoutResourceId() {
        return R.layout.activity_chat;
    }

    @Override
    public void initView() {
        chatList = findView(R.id.chatList);
        contents = new ArrayList<>();
        myHandle = new MyHandle(this);

    }

    @Override
    public void initData(Bundle arguments) {
        SharedPreferences login = mActivity.getSharedPreferences("login", Context.MODE_PRIVATE);
        id = login.getInt("id", 0);
        if (id != 0) {
            mIsVisible = true;
            onLazyLoad();
        }


    }

    @Override
    protected void onLazyLoad() {
        super.onLazyLoad();


        Map<String, String> data = new HashMap<>();
        data.put("UId", id + "");
        JSONObject object = JSONUtils.objToJson(data);

        OkHttp3Utils.doPost(AppConfig.GET_DIALOGUE, object.toString(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("失败原因", e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String content = response.body().string();
                contents = JSONUtils.jsonToArrayObj(content, ChatItem.class);
//                if(chatList.getAdapter()==null){
//                myChatItemAdapter = new MyChatItemAdapter(getContext(), contents);
//                chatList.setAdapter(myChatItemAdapter);}
//                chatList.setLayoutManager(new LinearLayoutManager(getContext()));

                    Message message = new Message();
                    message.obj = contents;
                    myHandle.sendMessage(message);

            }
        });
    }

    @Override
    public void initListener() {

    }

    @Override
    public void onClick(View v) {

    }

    private static class MyHandle extends Handler {
        private WeakReference<Fragment> fragmentWeakReference;

        private MyHandle(Fragment fragment) {
            fragmentWeakReference = new WeakReference<>(fragment);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            final ChatFragment mainFragment = (ChatFragment) fragmentWeakReference.get();
            final ArrayList<ChatItem> data = (ArrayList<ChatItem>) msg.obj;
            mainFragment.myChatItemAdapter = new MyChatItemAdapter(mainFragment.getContext(), data);
            mainFragment.chatList.setAdapter(mainFragment.myChatItemAdapter);
            mainFragment.chatList.setLayoutManager(new LinearLayoutManager(mainFragment.getContext()));
            mainFragment.myChatItemAdapter.setOnItemClickListener(new MyChatItemAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    Intent intent = new Intent(mainFragment.getContext(), ChatActivity.class);
                    intent.putExtra("content", data.get(position));
                    mainFragment.startActivity(intent);
                }
            });

        }
    }
}

package com.repair.lsb.easyrepair.chat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.repair.lsb.easyrepair.R;
import com.repair.lsb.easyrepair.adapter.MyChatViewAdapter;
import com.repair.lsb.easyrepair.adapter.MyRecyclerViewAdapter;
import com.repair.lsb.easyrepair.base.BaseActivity;
import com.repair.lsb.easyrepair.constat.AppConfig;
import com.repair.lsb.easyrepair.fragment.MainFragment;
import com.repair.lsb.easyrepair.merchat.MerActivity;
import com.repair.lsb.easyrepair.model.Chat;
import com.repair.lsb.easyrepair.model.ChatItem;
import com.repair.lsb.easyrepair.model.Merchant;
import com.repair.lsb.easyrepair.utils.ActivityUtils;
import com.repair.lsb.easyrepair.utils.ConstantUtil;
import com.repair.lsb.easyrepair.utils.DateUtils;
import com.repair.lsb.easyrepair.utils.JSONUtils;
import com.repair.lsb.easyrepair.utils.OkHttp3Utils;

import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class ChatActivity extends BaseActivity {
    private TextView title,lastTime;
    private RecyclerView recyclerView;
    private MyHandle myHandle;
    private MyChatViewAdapter myChatViewAdapter;
    private Button send;
    private EditText talk;
    private ArrayList<Chat> chats;
    private ChatItem chatItem;

    @Override
    public int setContentView() {
        return R.layout.chat_interface;
    }

    @Override
    public void initView() {
       title = findView(R.id.layout_title).findViewById(R.id.content);
       recyclerView = findView(R.id.chatContents);
       lastTime = findView(R.id.date);
       send = findView(R.id.send);
       talk = findView(R.id.talk);
       chats = new ArrayList<>();
       myHandle = new MyHandle(this);
    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        chatItem =(ChatItem)intent.getSerializableExtra("content");
        title.setText(chatItem.getName());
        lastTime.setText(chatItem.getLastTime());
        Map<String,Integer> data = new HashMap<>();
        data.put("userId",chatItem.getUserId());
        data.put("merId",chatItem.getMerId());
        JSONObject obj = JSONUtils.objToJson(data);
        OkHttp3Utils.doPost(AppConfig.GET_CONTENTS, obj.toString(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("获取失败",e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String content = response.body().string();
                chats = JSONUtils.jsonToArrayObj(content, Chat.class);
//                if(myChatViewAdapter==null){
//                myChatViewAdapter = new MyChatViewAdapter(getApplicationContext(),chats);}
//                recyclerView.setAdapter(myChatViewAdapter);
//                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                Message message = new Message();
                message.obj = chats;
                myHandle.sendMessage(message);
            }
        });
    }

    @Override
    public void initListener() {
      send.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.send:
                send();
                break;
            case R.id.iv_back:
                this.finish();

        }
    }

    private void send() {
       String content = talk.getText().toString().trim();
       if(!content.equals("")){
          chats.add(new Chat(chatItem.getUserId(),chatItem.getMerId(),ConstantUtil.SEND,DateUtils.getCurrentDate(),content,chatItem.getUserAvatar()));
          myChatViewAdapter.notifyDataSetChanged();
          talk.setText("");
          recyclerView.scrollToPosition(myChatViewAdapter.getItemCount()-1);
       }
    }

    private static class MyHandle extends Handler{
        private WeakReference<Activity> activityWeakReference;

        private MyHandle(Activity activity){
            activityWeakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            ChatActivity mainFragment = (ChatActivity) activityWeakReference.get();
            ArrayList<Chat> data = (ArrayList<Chat>) msg.obj;
            mainFragment.myChatViewAdapter = new MyChatViewAdapter(mainFragment,data);
            mainFragment.recyclerView.setAdapter(mainFragment.myChatViewAdapter);
            mainFragment.recyclerView.setLayoutManager(new LinearLayoutManager(mainFragment));
        }
    }


}

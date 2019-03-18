package com.repair.lsb.easyrepair.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.repair.lsb.easyrepair.R;
import com.repair.lsb.easyrepair.model.Chat;
import com.repair.lsb.easyrepair.utils.ConstantUtil;

import java.util.ArrayList;

public class MyChatViewAdapter extends RecyclerView.Adapter {
    private Context context;
    private ArrayList<Chat> data;

    class SendViewHolder extends RecyclerView.ViewHolder {
        ImageView avatar;
        TextView content;

        public SendViewHolder(@NonNull View itemView) {
            super(itemView);
            avatar = itemView.findViewById(R.id.avatar1);
            content = itemView.findViewById(R.id.title1);
        }
    }

    class ReceiveViewHolder extends RecyclerView.ViewHolder {
        ImageView avatar;
        TextView content;

        public ReceiveViewHolder(@NonNull View itemView) {
            super(itemView);
            avatar = itemView.findViewById(R.id.avatar2);
            content = itemView.findViewById(R.id.title2);
        }
    }

    public MyChatViewAdapter(Context context, ArrayList<Chat> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getItemViewType(int position) {
        return data.get(position).getType();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i == ConstantUtil.SEND) {
            View view = LayoutInflater.from(context).inflate(R.layout.send_message, viewGroup, false);
            return new SendViewHolder(view);
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.receive_message, viewGroup, false);
            return new ReceiveViewHolder(view);
        }


    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        Chat chat = data.get(i);

        if (chat.getType() == ConstantUtil.SEND) {
            if (viewHolder instanceof SendViewHolder) {
              SendViewHolder sendViewHolder =  (SendViewHolder)viewHolder;
              Glide.with(context).load(chat.getUserAvatar()).into(sendViewHolder.avatar);
              sendViewHolder.content.setText(chat.getContent());
            }
        } else {
            if (viewHolder instanceof ReceiveViewHolder) {
                ReceiveViewHolder receiveViewHolder = (ReceiveViewHolder) viewHolder;
                Glide.with(context).load(chat.getMerAvatar()).into(receiveViewHolder.avatar);
                receiveViewHolder.content.setText(chat.getContent());
            }
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}

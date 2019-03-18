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
import com.repair.lsb.easyrepair.model.ChatItem;

import java.util.ArrayList;

public class MyChatItemAdapter extends RecyclerView.Adapter {
    private Context context;
    private ArrayList<ChatItem> data;
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, lastText, lastTime;
        ImageView avatar;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.title3);
            lastText = itemView.findViewById(R.id.lastText);
            lastTime = itemView.findViewById(R.id.lastTime);
            avatar = itemView.findViewById(R.id.avatar3);
        }
    }

    public MyChatItemAdapter(Context context, ArrayList<ChatItem> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.chat_item, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, int i) {
        ChatItem chatItem = data.get(i);

        if (onItemClickListener != null) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int i = viewHolder.getLayoutPosition();
                    onItemClickListener.onItemClick(viewHolder.itemView, i);
                }
            });
        }

        if (viewHolder instanceof MyViewHolder) {
            MyViewHolder myViewHolder = (MyViewHolder) viewHolder;
            Glide.with(context).load(chatItem.getMerAvatar()).into(myViewHolder.avatar);
            myViewHolder.name.setText(chatItem.getName());
            myViewHolder.lastText.setText(chatItem.getLastText());
            myViewHolder.lastTime.setText(chatItem.getLastTime());
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}

package com.repair.lsb.easyrepair.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.repair.lsb.easyrepair.R;

import java.util.ArrayList;

public class ListAdapter extends BaseAdapter {
    private int select;
    private Context context;
    private ArrayList<String> data;

    class MyViewHolder{
        TextView textView;
    }

    public ListAdapter(Context context,ArrayList<String> data){
        super();
        this.context = context;
        this.data = data;
    }
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        MyViewHolder myViewHolder;
        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.pro_item2,parent,false);
            myViewHolder = new MyViewHolder();
            myViewHolder.textView = view.findViewById(R.id.type);
            view.setTag(myViewHolder);
        }else {
            myViewHolder = (MyViewHolder) view.getTag();
        }
        myViewHolder.textView.setText(data.get(position));

        if(select == position){
            myViewHolder.textView.setBackgroundColor(Color.WHITE);
        }else {
            myViewHolder.textView.setBackgroundColor(Color.GRAY);
        }
        return view;
    }

    public void changeSelect(int position){
        if(position!=select){
            select = position;
            notifyDataSetChanged();
        }
    }
}

package com.repair.lsb.easyrepair.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.repair.lsb.easyrepair.R;
import com.repair.lsb.easyrepair.model.Merchant;
import com.zhy.adapter.abslistview.*;

import com.repair.lsb.easyrepair.model.Type;

import java.util.ArrayList;

public class MyTypePagerAdapter extends PagerAdapter {
    private Context context;
    private ArrayList<Type> types;

    public MyTypePagerAdapter(Context context,ArrayList<Type> types){
        this.context = context;
        this.types = types;
    }
    @Override
    public int getCount() {
        return types.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        RecyclerView recyclerView = new RecyclerView(context);
        MyRecyclerViewAdapter myRecyclerViewAdapter = new MyRecyclerViewAdapter(context,types.get(position).getMerchants());
        recyclerView.setAdapter(myRecyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        container.addView(recyclerView);
        return recyclerView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return types.get(position).getType();
    }
}

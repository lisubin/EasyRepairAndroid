package com.repair.lsb.easyrepair.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.repair.lsb.easyrepair.R;
import com.repair.lsb.easyrepair.adapter.ListAdapter;
import com.repair.lsb.easyrepair.base.BaseFragment;
import com.repair.lsb.easyrepair.model.ProItem;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MerFragment extends BaseFragment {
    private ListView a;
    private ListView b;
    private ListAdapter adapter;
    private  CommonAdapter<ProItem> commonAdapter;
    private ArrayList<ProItem> data;
    private ArrayList<ProItem> results;
    private ArrayList<String> datas;

    @Override
    protected int setLayoutResourceId() {
        return R.layout.production;
    }

    @Override
    public void initView() {
        a= findView(R.id.a);
        b = findView(R.id.b);
        data = new ArrayList<>();
        datas = new ArrayList<>();
    }

    @Override
    public void initData(Bundle arguments) {


        datas.add("1");
        datas.add("4");
        datas.add("3");
        datas.add("2");
        adapter = new ListAdapter(getContext(),datas);
        data.add(new ProItem("dd","dd","dd","dd","1"));
        data.add(new ProItem("dd","dd","dd","dd","1"));
        data.add(new ProItem("dd","dd","dd","dd","1"));
        data.add(new ProItem("dd","dd","dd","dd","1"));
        data.add(new ProItem("s1","11","11","dd","2"));
        data.add(new ProItem("s2","22","22","22","2"));
        data.add(new ProItem("s3","33","dd","33","2"));
        data.add(new ProItem("ss","44","44","44","2"));
        b.setAdapter(adapter);

        results = query("2");
        for (int i = 0; i < 4; i++) {
            System.out.println(results.size()+results.get(i).getName());
        }
       commonAdapter = new CommonAdapter<ProItem>(getContext(),R.layout.pro_item,results) {
            @Override
            protected void convert(ViewHolder viewHolder, ProItem item, final int position) {
                ProItem proItem =results.get(position);
                viewHolder.setText(R.id.idname, proItem.getName());
                viewHolder.setText(R.id.decri,proItem.getDescription());
                viewHolder.setText(R.id.fare,proItem.getFare());
                viewHolder.setImageResource(R.id.photo,R.drawable.bannertupian2);
                viewHolder.setOnClickListener(R.id.xiadan, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
//
            }
        };
        a.setAdapter(commonAdapter);
    }

    private  ArrayList<ProItem> query(String type) {

        ArrayList<ProItem> result = new ArrayList<>();
        if(data!=null){
            for(ProItem proItem: data){
                Log.i("iiiiii",type);
             if(proItem.getType().equals(type)){
                 result.add(proItem);
                 System.out.println(result.size());
             }
            }
        }
        return result;
    }

    @Override
    public void initListener() {
        b.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.changeSelect(position);
                 if(datas.get(position).equals("1"))
                {
                    results= query("1");
                   // System.out.println("dfd"+results.size());
                    //a.setAdapter(commonAdapter);
                    commonAdapter.notifyDataSetChanged();
//                    a.setAdapter(commonAdapter);
                }else{
                    results = query("2");
                    commonAdapter.notifyDataSetChanged();
                }
//                if(data.get(position).equals("5")){

//                    a.setAdapter( commonAdapter);
//                }else {
//                    a.setAdapter(adapter);
//                }


            }
        });

    }



    @Override
    public void onClick(View v) {

    }
}

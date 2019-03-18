package com.repair.lsb.easyrepair.user;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.lljjcoder.Interface.OnCityItemClickListener;
import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.citywheel.CityConfig;
import com.lljjcoder.style.citypickerview.CityPickerView;
import com.repair.lsb.easyrepair.R;
import com.repair.lsb.easyrepair.base.BaseActivity;

import java.util.ArrayList;


public class UserGoRepair extends BaseActivity {

    private ArrayList<String> data;
    private Spinner spinner;
    private ArrayAdapter<String> arrayAdapter;
    private CityPickerView cityPickerView;

    @Override
    public int setContentView() {
        return R.layout.order_item;
    }

    @Override
    public void initView() {
    spinner = findView(R.id.spinner);
    data = new ArrayList<>();
    cityPickerView = new CityPickerView();
    cityPickerView.init(this);
    }

    @Override
    public void initData() {
        CityConfig  cityConfig = new CityConfig.Builder().build();
        cityPickerView.setConfig(cityConfig);

        data.add("");
        data.add("jjj");
        data.add("aaa");
        data.add("ddd");
        data.add("zzz");
        arrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,data);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setSelection(0);
        spinner.setAdapter(arrayAdapter);


    }

    @Override
    public void initListener() {
      cityPickerView.setOnCityItemClickListener(new OnCityItemClickListener() {
          @Override
          public void onSelected(ProvinceBean province, CityBean city, DistrictBean district) {
              super.onSelected(province, city, district);
              System.out.println(province.getName()+city.getName()+district.getName());
          }

          @Override
          public void onCancel() {
              super.onCancel();
          }


      });

      findView(R.id.wei).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
    switch (v.getId()){
        case R.id.wei:
            this.cityPickerView.showCityPicker();
            break;
    }
    }
}

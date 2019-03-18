package com.repair.lsb.easyrepair.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.repair.lsb.easyrepair.R;
import com.repair.lsb.easyrepair.base.BaseFragment;

public class EvaFragment extends BaseFragment {
    private TextView all, good, middle, worse;
    private ListView listView;

    @Override
    protected int setLayoutResourceId() {
        return R.layout.evaluation;
    }

    @Override
    public void initView() {
        all = findView(R.id.all);
        good = findView(R.id.good);
        middle = findView(R.id.middle);
        worse = findView(R.id.worse);
        listView = findView(R.id.evaList);

    }

    @Override
    public void initData(Bundle arguments) {

    }

    @Override
    public void initListener() {
        all.setOnClickListener(this);
        good.setOnClickListener(this);
        worse.setOnClickListener(this);
        middle.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.all:
                break;
            case R.id.good:
                break;
            case R.id.middle:
                break;
            case R.id.worse:
                break;
        }

    }
}

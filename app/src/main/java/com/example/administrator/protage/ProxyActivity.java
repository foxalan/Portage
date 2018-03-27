package com.example.administrator.protage;

import com.example.administrator.protage.baidumap.BaiduMapFragment;
import com.example.fox_core.BaseActivity;

/**
 * @Author Alan
 * Date 2018/3/27 0027
 * Function
 * Issue
 */

public class ProxyActivity extends BaseActivity {



    @Override
    public Object getActivityLayout() {
        return R.layout.activity_proxy;
    }

    @Override
    public void onBindView() {
        getSupportFragmentManager().beginTransaction().add(R.id.fl_container,new BaiduMapFragment()).commit();
    }
}

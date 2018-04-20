package com.example.administrator.protage;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.fox_core.LatteDelegate;
import com.example.fox_ui.fragment.WebViewFragment;

/**
 * @Author Alan
 * Date 2018/4/20 0020
 * Function
 * Issue
 */

public class HomeActivity extends ProxyActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public LatteDelegate setRootDelegate() {
        return new WebViewFragment();
    }

}

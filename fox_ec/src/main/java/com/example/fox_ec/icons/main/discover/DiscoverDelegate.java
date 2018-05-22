package com.example.fox_ec.icons.main.discover;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.fox_core.delegate.bottom.BottomItemDelegate;
import com.example.fox_core.web.WebDelegateImpl;
import com.example.fox_ec.R;

/**
 * @Author Alan
 * Date 2018/5/22 0022
 * Function
 * Issue
 */

public class DiscoverDelegate extends BottomItemDelegate {

    @Override
    public Object getLayout() {
        return R.layout.delegate_discover;
    }

    @Override
    public void onBindView(View rootView) throws Exception {


    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);

        final WebDelegateImpl delegate = WebDelegateImpl.create("index.html");
        delegate.setTopDelegate(this.getParentDelegate());
        getSupportDelegate().loadRootFragment(R.id.web_discovery_container, delegate);

    }
}

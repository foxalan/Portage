package com.example.fox_ec.icons.main.sort;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.fox_core.delegate.bottom.BottomItemDelegate;
import com.example.fox_ec.R;

/**
 * @Author Alan
 * Date 2018/5/22 0022
 * Function
 * Issue
 */

public class SortDelegate extends BottomItemDelegate {




    @Override
    public Object getLayout() {
        return R.layout.delegate_sort;
    }

    @Override
    public void onBindView(View rootView) throws Exception {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);

    }
}

package com.example.fox_core.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import me.yokeyword.fragmentation_swipeback.SwipeBackFragment;


abstract class BaseDelegate extends SwipeBackFragment {

    /**
     * 得到布局
     * @return
     */
    public abstract Object getLayout();

    /**
     * 處理事件
     */
    public abstract void onBindView(View rootView);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView;

        if (getLayout() instanceof Integer) {
            rootView = inflater.inflate((Integer) getLayout(), container, false);
        } else if (getLayout() instanceof View) {
            rootView = (View) getLayout();
        }else {
            throw new RuntimeException("the rootView type is error");
        }

        if (rootView != null) {

            onBindView(rootView);
        }

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}

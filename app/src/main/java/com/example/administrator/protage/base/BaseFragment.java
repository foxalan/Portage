package com.example.administrator.protage.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @Author Alan
 * Date 2018/3/27 0027
 * Function Fragment的基类
 * Issue 1.未加入与Activity的联动
 *       2.未加入懒加载
 *       3.未加入导常处理
 */

public abstract class BaseFragment extends Fragment {

    /**
     * 得到Fragment的Layout
     *
     * @return
     */
    public abstract Object getFragmentLayout();

    /**
     * 绑定View 进行操作
     * @param rootView
     */
    public abstract void onBindView(View rootView);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = null;

        if (getFragmentLayout() instanceof View) {
            rootView = (View) getFragmentLayout();
        } else if (getFragmentLayout() instanceof Integer) {
            int layoutId = (int) getFragmentLayout();
            rootView = inflater.inflate(layoutId, container, false);
        } else {

        }

        onBindView(rootView);

        return rootView;
    }
}

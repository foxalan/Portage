package com.example.fox_ec.icons.main.sort;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.fox_core.delegate.bottom.BottomItemDelegate;
import com.example.fox_ec.R;
import com.example.fox_ec.icons.main.sort.content.ContentDelegate;
import com.example.fox_ec.icons.main.sort.list.VerticalListDelegate;

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
        final VerticalListDelegate listDelegate = new VerticalListDelegate();
        getSupportDelegate().loadRootFragment(R.id.vertical_list_container, listDelegate);
        //设置右侧第一个分类显示，默认显示分类一
        getSupportDelegate().loadRootFragment(R.id.sort_content_container, ContentDelegate.newInstance(1));
    }
}

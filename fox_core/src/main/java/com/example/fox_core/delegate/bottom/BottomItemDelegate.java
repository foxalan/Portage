package com.example.fox_core.delegate.bottom;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.example.fox_core.R;
import com.example.fox_core.delegate.BottomItemBean;
import com.example.fox_core.fragment.LatteDelegate;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @Author Alan
 * Date 2018/5/10 0010
 * Function
 * Issue
 */

public abstract class BottomItemDelegate extends LatteDelegate {


    private FrameLayout mFrameLayout;
    private LinearLayoutCompat mLinnerLayountCompat;

    private HashMap<BottomItemBean,BottomItemDelegate> bottomItemDelegateHashMap = new HashMap<>();
    private LinkedList<BottomItemBean> bottomItemBeans = new LinkedList<>();
    private LinkedList<BottomItemDelegate> bottomItemDelegates = new LinkedList<>();

    /**
     * 获取数据源
     * @return
     */
    public abstract HashMap<BottomItemBean,BottomItemDelegate> getData();


    @Override
    public Object getLayout() {
        return R.layout.delegate_bottom;
    }

    @Override
    public void onBindView(View rootView) throws Exception {
        mLinnerLayountCompat = rootView.findViewById(R.id.ll_bottom_container);

        initData();
    }

    private void initData(){
        bottomItemDelegateHashMap = getData();
        //获取数据
        for (Map.Entry<BottomItemBean, BottomItemDelegate> item : bottomItemDelegateHashMap.entrySet()) {
            final BottomItemBean key = item.getKey();
            final BottomItemDelegate value = item.getValue();
            bottomItemBeans.add(key);
            bottomItemDelegates.add(value);
        }

        setBottomView();
    }

    /**
     * 设置底部View
     */
    private void setBottomView(){


        for(BottomItemBean itemBean:bottomItemBeans){
            View itemView = LayoutInflater.from(getContext()).inflate(R.layout.view_bottom_item,mLinnerLayountCompat);
            IconTextView iconTextView = itemView.findViewById(R.id.itv_icon_item);
            AppCompatTextView compatTextView = itemView.findViewById(R.id.itv_title_item);
            iconTextView.setText(itemBean.getIcon());
            compatTextView.setText(itemBean.getTitle());

            mLinnerLayountCompat.addView(itemView);
        }

    }
}

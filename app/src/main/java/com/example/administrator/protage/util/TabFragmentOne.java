package com.example.administrator.protage.util;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.administrator.protage.R;
import com.example.fox_ec.icons.main.personal.ListAdapter;
import com.example.fox_ec.icons.main.personal.ListBean;
import com.example.fox_ec.icons.main.personal.ListItemType;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Alan
 * Date 2018/5/11 0011
 * Function
 * Issue
 */

public class TabFragmentOne extends Fragment {

    private RecyclerView lv_tab_one;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_tab_one,container,false);

        lv_tab_one = rootView.findViewById(R.id.lv_tab_one);
        final List<ListBean> data = new ArrayList<>();

        for(int i = 0 ;i<5;i++){
            final ListBean address_1 = new ListBean.Builder()
                    .setItemType(ListItemType.ITEM_NORMAL)
                    .setId(i+10)
                    .setText("收货地址"+i)
                    .build();

            data.add(address_1);
        }

        final ListBean address = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setId(1)
                .setText("收货地址")
                .build();

        final ListBean system = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setId(2)
                .setText("系统设置")
                .build();

        data.add(address);
        data.add(system);

        //设置RecyclerView
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        lv_tab_one.setLayoutManager(manager);
        final ListAdapter adapter = new ListAdapter(data);
        lv_tab_one.setAdapter(adapter);

        return rootView;
    }
}

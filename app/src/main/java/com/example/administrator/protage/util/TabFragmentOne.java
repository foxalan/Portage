package com.example.administrator.protage.util;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.administrator.protage.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Alan
 * Date 2018/5/11 0011
 * Function
 * Issue
 */

public class TabFragmentOne extends Fragment {

    private ListView lv_tab_one;
    private List<String> list = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_tab_one,container,false);

        lv_tab_one = rootView.findViewById(R.id.lv_tab_one);

        for(int i = 0 ;i<5;i++){
            list.add(i+"=====");
        }

        lv_tab_one.setAdapter(new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,list));

        return lv_tab_one;
    }
}

package com.example.fox_ec.icons.main;

import android.graphics.Color;

import com.example.fox_core.delegate.bottom.BaseBottomDelegate;
import com.example.fox_core.delegate.bottom.BottomItemDelegate;
import com.example.fox_core.delegate.bottom.BottomTabBean;
import com.example.fox_core.delegate.bottom.ItemBuilder;
import com.example.fox_ec.icons.main.discover.DiscoverDelegate;
import com.example.fox_ec.icons.main.index.IndexDelegate;
import com.example.fox_ec.icons.main.sort.SortDelegate;

import java.util.LinkedHashMap;

/**
 * @Author Alan
 * Date 2018/5/11 0011
 * Function
 * Issue
 */

public class EcBottomDelegate extends BaseBottomDelegate {



    @Override
    public int setIndexDelegate() {
        return 0;
    }

    @Override
    public int setClickedColor() {
        return Color.CYAN;
    }

    @Override
    public LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(ItemBuilder builder) {
        final LinkedHashMap<BottomTabBean, BottomItemDelegate> items = new LinkedHashMap<>();
        items.put(new BottomTabBean("{fa-home}", "主页"), new IndexDelegate());
        items.put(new BottomTabBean("{fa-sort}", "分类"), new SortDelegate());
        items.put(new BottomTabBean("{fa-compass}", "发现"), new DiscoverDelegate());
        items.put(new BottomTabBean("{fa-home}", "主页"), new IndexDelegate());
        items.put(new BottomTabBean("{fa-home}", "主页"), new IndexDelegate());
//        items.put(new BottomTabBean("{fa-shopping-cart}", "购物车"), new ShopCartDelegate());
//        items.put(new BottomTabBean("{fa-user}", "我的"), new PersonalDelegate());
        return builder.addItems(items).build();
    }
}

package com.example.fox_ui.recycler;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * @Author Alan
 * Date 2018/5/12 0012
 * Function
 * Issue
 */

public class MultipleRecyclerAdapter extends BaseMultiItemQuickAdapter<MultipleItemEntity,MultipleViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public MultipleRecyclerAdapter(List<MultipleItemEntity> data) {
        super(data);
    }

    @Override
    protected void convert(MultipleViewHolder helper, MultipleItemEntity item) {

    }


}

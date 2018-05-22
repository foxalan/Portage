package com.example.fox_ec.icons.main.index;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.fox_core.delegate.bottom.BottomItemDelegate;
import com.example.fox_ec.R;
import com.example.fox_ec.icons.main.EcBottomDelegate;
import com.example.fox_ui.recycler.BaseDecoration;
import com.example.fox_ui.refresh.RefreshHandler;

/**
 * @Author Alan
 * Date 2018/5/11 0011
 * Function :
 * Issue :
 */

public class IndexDelegate extends BottomItemDelegate implements View.OnFocusChangeListener{

    SwipeRefreshLayout mSwipeRefreshLayout;
    RecyclerView mRecyclerView;
    AppCompatEditText mSearchView;

    private RefreshHandler mRefreshHandler = null;

    @Override
    public Object getLayout() {
        return R.layout.delegate_index;
    }

    @Override
    public void onBindView(View rootView) throws Exception {

        mSwipeRefreshLayout = rootView.findViewById(R.id.srl_index);
        mRecyclerView = rootView.findViewById(R.id.ryc_index);
        mSearchView = rootView.findViewById(R.id.et_search_view);

        mRefreshHandler = RefreshHandler.create(mSwipeRefreshLayout, mRecyclerView, new IndexDataConverter());

        mSearchView.setOnFocusChangeListener(this);
    }


    private void initRefreshLayout() {

        mSwipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light
        );
        mSwipeRefreshLayout.setProgressViewOffset(true, 120, 300);

    }

    private void initRecyclerView() {
        final GridLayoutManager manager = new GridLayoutManager(getContext(), 4);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.addItemDecoration
                (BaseDecoration.create(ContextCompat.getColor(getContext(), R.color.app_background), 5));
        final EcBottomDelegate ecBottomDelegate = getParentDelegate();
        mRecyclerView.addOnItemTouchListener(IndexItemClickListener.create(ecBottomDelegate));
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initRefreshLayout();
        initRecyclerView();

        mRefreshHandler.firstPage("index.php");
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {

    }
}

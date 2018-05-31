package com.example.fox_ec.icons.main.sort.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.fox_core.fragment.LatteDelegate;
import com.example.fox_core.net.RestClient;
import com.example.fox_core.net.callback.ISuccess;
import com.example.fox_ec.R;
import com.example.fox_ec.icons.main.sort.SortDelegate;
import com.example.fox_ui.recycler.MultipleItemEntity;

import java.util.List;

/**
 * @Author Alan
 * Date 2018/5/22 0022
 * Function
 * Issue
 */

public class VerticalListDelegate extends LatteDelegate {

    RecyclerView mRecyclerView = null;


    @Override
    public Object getLayout() {
        return R.layout.delegate_vertical_list;
    }

    @Override
    public void onBindView(View rootView) throws Exception {
        mRecyclerView = rootView.findViewById(R.id.rv_vertical_menu_list);
        initRecyclerView();
    }

    private void initRecyclerView() {
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        //屏蔽动画效果
        mRecyclerView.setItemAnimator(null);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        String url = "sort_list.php";
//        RestClient.builder()
//                .setUrl(url)
//                .loader(getContext())
//                .success(new ISuccess() {
//                    @Override
//                    public void onSuccess(String response) {
////                        final List<MultipleItemEntity> data =
////                                new VerticalListDataConverter().setJsonData(response).convert();
////                        final SortDelegate delegate = getParentDelegate();
//                    //    final SortRecyclerAdapter adapter = new SortRecyclerAdapter(data, delegate);
//                    //    mRecyclerView.setAdapter(adapter);
//
//                    }
//                })
//                .build()
//                .get();
    }
}

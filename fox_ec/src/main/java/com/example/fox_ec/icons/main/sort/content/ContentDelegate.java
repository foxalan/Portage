package com.example.fox_ec.icons.main.sort.content;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.example.fox_core.fragment.LatteDelegate;
import com.example.fox_core.net.RestClient;
import com.example.fox_core.net.callback.ISuccess;
import com.example.fox_ec.R;

import java.util.List;

/**
 * @Author Alan
 * Date 2018/5/22 0022
 * Function
 * Issue
 */

public class ContentDelegate extends LatteDelegate {

    private static final String ARG_CONTENT_ID = "CONTENT_ID";
    private int mContentId = -1;
    private List<SectionBean> mData = null;

    RecyclerView mRecyclerView = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        if (args != null) {
            mContentId = args.getInt(ARG_CONTENT_ID);
        }
    }

    public static ContentDelegate newInstance(int contentId) {
        final Bundle args = new Bundle();
        args.putInt(ARG_CONTENT_ID, contentId);
        final ContentDelegate delegate = new ContentDelegate();
        delegate.setArguments(args);
        return delegate;
    }


    @Override
    public Object getLayout() {
        return R.layout.delegate_list_content;
    }

    @Override
    public void onBindView(View rootView) throws Exception {
        mRecyclerView = rootView.findViewById(R.id.rv_list_content);

        final StaggeredGridLayoutManager manager =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);

        initData();
    }

    /**
     * 初始化数据
     */
    private void initData() {

        String url = "sort_content_list.php?contentId=" + mContentId;

//        RestClient.builder()
//                .setUrl(url)
//                .success(new ISuccess() {
//                    @Override
//                    public void onSuccess(String response) {
//
//
////                        mData = new SectionDataConverter().convert(response);
////                        final SectionAdapter sectionAdapter =
////                                new SectionAdapter(R.layout.item_section_content,
////                                        R.layout.item_section_header, mData);
////                        mRecyclerView.setAdapter(sectionAdapter);
//                    }
//                })
//                .build()
//                .get();
    }
}

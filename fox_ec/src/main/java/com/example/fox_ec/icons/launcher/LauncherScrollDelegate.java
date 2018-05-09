package com.example.fox_ec.icons.launcher;

import android.view.View;


import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.example.fox_core.fragment.LatteDelegate;
import com.example.fox_ec.R;
import com.example.fox_ui.launcher.LauncherHolderCreator;

import java.util.ArrayList;

/**
 * @Author Alan
 * Date 2018/5/9 0009
 * Function
 * Issue
 */

public class LauncherScrollDelegate extends LatteDelegate implements OnItemClickListener {

    private ConvenientBanner<Integer> convenientBanner;
    private static final ArrayList<Integer> INTEGERS = new ArrayList<>();

    @Override
    public Object getLayout() {
        convenientBanner = new ConvenientBanner<>(getContext());
        return convenientBanner;
    }

    @Override
    public void onBindView(View rootView) throws Exception {
        initView();
    }

    private void initView() {
        INTEGERS.add(R.mipmap.launcher_01);
        INTEGERS.add(R.mipmap.launcher_02);
        INTEGERS.add(R.mipmap.launcher_03);
        INTEGERS.add(R.mipmap.launcher_04);
        INTEGERS.add(R.mipmap.launcher_05);
        convenientBanner.setPages(new LauncherHolderCreator(),INTEGERS)
                .setPageIndicator(new int[]{R.drawable.dot_normal, R.drawable.dot_focus})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnItemClickListener(this);
    }


    @Override
    public void onItemClick(int position) {
        if (position == (INTEGERS.size()-1)){

        }
    }
}

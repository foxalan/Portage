package com.example.administrator.protage.util;

import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;



import com.example.administrator.protage.R;
import com.example.fox_core.fragment.LatteDelegate;

/**
 * @Author Alan
 * Date 2018/5/11 0011
 * Function coo
 * Issue
 */

public class ExpDelegate extends LatteDelegate {

    private ViewPager viewPager;
    private TabLayout tableLayout;

    private String[] titles = {"TAB1","TAB2","TAB3"};

    @Override
    public Object getLayout() {
        return R.layout.delegate_exp;
    }

    @Override
    public void onBindView(View rootView) throws Exception {

        viewPager = rootView.findViewById(R.id.vp_exp);
        tableLayout = rootView.findViewById(R.id.tb_exp);

        viewPager.setAdapter(new FragmentPagerAdapter(getFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return new TabFragmentOne();
            }

            @Override
            public int getCount() {
                return titles.length;
            }

            @Override
            public CharSequence getPageTitle(int position) {

                return titles[position];
            }
        });

        tableLayout.setupWithViewPager(viewPager);



        rootView.findViewById(R.id.fab_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v,"FAB",Snackbar.LENGTH_LONG)
                        .setAction("cancel", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //这里的单击事件代表点击消除Action后的响应事件

                            }
                        })
                        .show();
            }
        });
    }
}

package com.example.administrator.protage.baidumap;

import android.view.View;
import android.widget.RelativeLayout;

import com.baidu.mapapi.map.MapView;
import com.example.administrator.protage.R;
import com.example.fox_core.BaseFragment;

/**
 * @Author Alan
 * Date 2018/3/27 0027
 * Function
 * Issue
 */

public class BaiduMapFragment extends BaseFragment {

    private MapView mMapView;
    private RelativeLayout mMarkerLy;

    @Override
    public Object getFragmentLayout() {
        return R.layout.fragment_baidu_map;
    }

    @Override
    public void onBindView(View rootView) {

        mMapView = rootView.findViewById(R.id.id_mapView);
        mMarkerLy = rootView.findViewById(R.id.id_maker_ly);

    }

}

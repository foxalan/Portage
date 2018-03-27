package com.example.administrator.protage.baidumap;

import android.view.View;
import android.widget.RelativeLayout;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;
import com.example.administrator.protage.R;
import com.example.administrator.protage.baidumap.impl.MapInitializeImpl;
import com.example.fox_core.BaseFragment;

/**
 * @Author Alan
 * Date 2018/3/27 0027
 * Function
 * Issue
 */

public class BaiduMapFragment extends BaseFragment {

    private MapView mMapView;
    private BaiduMap mBaiduMap = null;

    private RelativeLayout mMarkerLy;
    private MapInitializeImpl mapInitializeImpl;

    private LocationClient mLocationClient;
    private MyLocationListener locationListener;

    @Override
    public Object getFragmentLayout() {
        return R.layout.fragment_baidu_map;
    }

    @Override
    public void onBindView(View rootView) {
        mMapView = rootView.findViewById(R.id.id_mapView);
        mBaiduMap = mMapView.getMap();
        mMarkerLy = rootView.findViewById(R.id.id_maker_ly);

        mLocationClient = new LocationClient(getContext());
        locationListener = new MyLocationListener();

        mapInitializeImpl = new MapInitializeImpl();
        mapInitializeImpl.initLocationClient(mLocationClient);
        mapInitializeImpl.initBaiduMap(mMapView, mBaiduMap);
    }

    @Override
    public void onResume() {
        super.onResume();
        mLocationClient.registerLocationListener(locationListener);
        mLocationClient.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mLocationClient.unRegisterLocationListener(locationListener);
    }

    private class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            mapInitializeImpl.location(mBaiduMap, location);
        }
    }
}

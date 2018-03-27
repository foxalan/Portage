package com.example.administrator.protage.baidumap;

import android.view.View;
import android.widget.RelativeLayout;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;
import com.example.administrator.protage.R;
import com.example.administrator.protage.util.L;
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
    private MapInitializeImpl mapInitalizeimpl;

    /**
     * 定位
     * @return
     */
    private LocationClient mLocationClient;
    private MyLocationListener locationListener;


    @Override
    public Object getFragmentLayout() {
        return R.layout.fragment_baidu_map;
    }

    @Override
    public void onBindView(View rootView) {
        L.e("map fragment onBindView");
        mMapView = rootView.findViewById(R.id.id_mapView);
        mMarkerLy = rootView.findViewById(R.id.id_maker_ly);

        mLocationClient = new LocationClient(getContext());
        locationListener = new MyLocationListener();

        mapInitalizeimpl = new MapInitializeImpl();
        mapInitalizeimpl.initLocationClient(mLocationClient);
        mapInitalizeimpl.initBaiduMap(mMapView,mBaiduMap);

    }

    @Override
    public void onResume() {
        super.onResume();
        L.e("map fragment resume");
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
            L.e(location.getLatitude()+"------"+location.getLongitude()+"====");
//            MyLocationData data = new MyLocationData.Builder()
//                    .direction(currentX)
//                    .accuracy(location.getRadius())
//                    .latitude(location.getLatitude())
//                    .longitude(location.getLongitude())
//                    .build();
//            mBaiduMap.setMyLocationData(data);
//
//            MyLocationConfiguration config = new MyLocationConfiguration(
//                    mLocationMode, true, mIconLocation);
//            mBaiduMap.setMyLocationConfigeration(config);
//
//            mLatitude = location.getLatitude();
//            mLongtitude = location.getLongitude();
//
////            //定义Maker坐标点
////            LatLng point = new LatLng(mLatitude,mLongtitude);
////            //构建MarkerOption，用于在地图上添加Marker
////            OverlayOptions option = new MarkerOptions()
////                    .position(point)
////                    .icon(mIconLocation);
////            //在地图上添加Marker，并显示
////            mBaiduMap.addOverlay(option);
//
//            if (isFirstIn)
//            {
//                LatLng latLng = new LatLng(location.getLatitude(),
//                        location.getLongitude());
//                MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(latLng);
//                mBaiduMap.animateMapStatus(msu);
//                isFirstIn = false;
//
//                Toast.makeText(MainActivity.this, location.getAddrStr(),
//                        Toast.LENGTH_SHORT).show();
//            }
        }
    }
}

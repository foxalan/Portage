package com.example.administrator.protage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.Toast;


import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.example.administrator.protage.baidumap.MapInfo;
import com.example.administrator.protage.sensor.MyOrientationListener;
import com.example.administrator.protage.util.L;
import com.baidu.mapapi.map.MyLocationConfiguration.LocationMode;

import java.util.List;

/**
 * @author  alan
 * 1.定位功能，定位自己的位置
 *  (1)使用LocationClient，和BDLocationListener
 */

public class MainActivity extends AppCompatActivity {

    private MapView mMapView;

    private LocationClient mLocationClient;
    private MyLocationListener locationListener;
    private boolean isFirstIn = true;
    private double mLatitude;
    private double mLongtitude;
    private LocationMode mLocationMode;

    private BaiduMap mBaiduMap;
    private BitmapDescriptor mIconLocation;

    private MyOrientationListener orientationListener;
    private float currentX;
    //标识物
    private BitmapDescriptor mMarker;
    private RelativeLayout mMarkerLy;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        initView();
        initLocation();
        initMarker();
    }

    /**
     * 设置地图
     */
    private void initView() {
        mMapView = findViewById(R.id.id_mapView);
        mBaiduMap = mMapView.getMap();
        MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(15.0f);
        mBaiduMap.setMapStatus(msu);
    }

    /**
     * 初始化位置设置
     */
    private void initLocation() {

        mLocationMode = LocationMode.NORMAL;
        mLocationClient = new LocationClient(this);
        locationListener = new MyLocationListener();
        //没有将我的位置标识出来
        mLocationClient.registerLocationListener(locationListener);
        LocationClientOption option = new LocationClientOption();
        option.setCoorType("bd09ll");
        option.setIsNeedAddress(true);
        option.setOpenGps(true);
        option.setScanSpan(1000);
        //设置图标
        mIconLocation = BitmapDescriptorFactory
                .fromResource(R.drawable.navi_map_gps_locked);
        //设置传感器
        orientationListener = new MyOrientationListener(this);
        orientationListener.start();
        orientationListener.setOnOrientationListener(new MyOrientationListener.OnOrientationListener() {
            @Override
            public void onOrientationChanged(float x) {
                currentX =x;
            }
        });

        mLocationClient.setLocOption(option);
    }


    private void initMarker()
    {
        mMarker = BitmapDescriptorFactory.fromResource(R.drawable.maker);
        mMarkerLy = findViewById(R.id.id_maker_ly);
    }

    private class MyLocationListener implements BDLocationListener{

        @Override
        public void onReceiveLocation(BDLocation location) {
            L.e(location.getLatitude()+"------"+location.getLongitude()+"===="+currentX);
            MyLocationData data = new MyLocationData.Builder()
                    .direction(currentX)
                    .accuracy(location.getRadius())
                    .latitude(location.getLatitude())
                    .longitude(location.getLongitude())
                    .build();
            mBaiduMap.setMyLocationData(data);

            MyLocationConfiguration config = new MyLocationConfiguration(
                    mLocationMode, true, mIconLocation);
            mBaiduMap.setMyLocationConfigeration(config);

            mLatitude = location.getLatitude();
            mLongtitude = location.getLongitude();

//            //定义Maker坐标点
//            LatLng point = new LatLng(mLatitude,mLongtitude);
//            //构建MarkerOption，用于在地图上添加Marker
//            OverlayOptions option = new MarkerOptions()
//                    .position(point)
//                    .icon(mIconLocation);
//            //在地图上添加Marker，并显示
//            mBaiduMap.addOverlay(option);

            if (isFirstIn)
            {
                LatLng latLng = new LatLng(location.getLatitude(),
                        location.getLongitude());
                MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(latLng);
                mBaiduMap.animateMapStatus(msu);
                isFirstIn = false;

                Toast.makeText(MainActivity.this, location.getAddrStr(),
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mBaiduMap.setMyLocationEnabled(true);
        mLocationClient.start();
        orientationListener.start();
    }

    @Override
    protected void onResume() {

        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        orientationListener.stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mLocationClient.unRegisterLocationListener(locationListener);
        mMapView.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.map_item,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_location:
                L.e("item location");
                centerToMyLocation();
                break;
            case R.id.item_map_model_normal:
                mLocationMode = LocationMode.NORMAL;
                break;
            case R.id.item_map_model_follow:
                mLocationMode = LocationMode.FOLLOWING;
                break;
            case R.id.item_map_model_compass:
                mLocationMode = LocationMode.COMPASS;
                break;
            case R.id.item_map_obstacle:
                addOverlays(MapInfo.infos);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 开始定位
     * 1.得到所在位置的Latitude和Longtitude
     *
     */
    private void centerToMyLocation()
    {
        LatLng latLng = new LatLng(mLatitude, mLongtitude);
        MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(latLng);
        mBaiduMap.animateMapStatus(msu);
    }

    private void addOverlays(List<MapInfo> infos)
    {
        mBaiduMap.clear();
        LatLng latLng = null;
        Marker marker = null;
        OverlayOptions options;
        for (MapInfo info : infos)
        {

            latLng = new LatLng(info.getLatitude(), info.getLongitude());

            options = new MarkerOptions().position(latLng).icon(mMarker)
                    .zIndex(5);
            marker = (Marker) mBaiduMap.addOverlay(options);
            Bundle arg0 = new Bundle();
            arg0.putSerializable("info", info);
            marker.setExtraInfo(arg0);
        }

        MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(latLng);
        mBaiduMap.setMapStatus(msu);

    }
}

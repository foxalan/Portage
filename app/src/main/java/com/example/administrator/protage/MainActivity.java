package com.example.administrator.protage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.example.administrator.protage.util.L;

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
    private MyLocationConfiguration.LocationMode mLocationMode;

    private BaiduMap mBaiduMap;
    private BitmapDescriptor mIconLocation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_main);

        initView();
        initLocation();
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

        mLocationMode = MyLocationConfiguration.LocationMode.NORMAL;
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

        mLocationClient.setLocOption(option);
        mLocationClient.start();
    }


    private class MyLocationListener implements BDLocationListener{

        @Override
        public void onReceiveLocation(BDLocation location) {
            L.e(location.getLatitude()+"------"+location.getLongitude());
            MyLocationData data = new MyLocationData.Builder()
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

            //定义Maker坐标点
            LatLng point = new LatLng(mLatitude,mLongtitude);
            //构建MarkerOption，用于在地图上添加Marker
            OverlayOptions option = new MarkerOptions()
                    .position(point)
                    .icon(mIconLocation);
            //在地图上添加Marker，并显示
            mBaiduMap.addOverlay(option);

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
}

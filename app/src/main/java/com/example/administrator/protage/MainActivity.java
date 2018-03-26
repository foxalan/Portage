package com.example.administrator.protage;

import android.graphics.Color;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeOption;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.example.administrator.protage.baidumap.MapInfo;
import com.example.administrator.protage.sensor.MyOrientationListener;
import com.example.administrator.protage.util.L;
import com.baidu.mapapi.map.MyLocationConfiguration.LocationMode;

import java.util.List;

/**
 * @author  alan
 * 1.定位功能，定位自己的位置
 *  (1)使用LocationClient，和BDLocationListener
 * 2.方向识别功能
 *  (1)建立传感器，MyOrientationListener,创建Sensor,SensorManager
 *  (2)重写onSensorChanged方法，将X坐标通过回调传出
 *  (3)利用定位功能中的一秒刷新，将值给direction,进行重新绘制
 * 3.自定义的标识物
 *   (1)设置标识物的坐标和显示图标
 *   (2)将信息坐标传递给Map，Map将保存所有信息，用于点击事件
 * 4.标识物的点击处理
 *   (1)setOnMarkerClickListener
 *
 *
 */

public class MainActivity extends AppCompatActivity implements OnGetGeoCoderResultListener {

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

    private BitmapDescriptor mMarker;
    private RelativeLayout mMarkerLy;

    /**
     * 定位到具体位置
     * @param savedInstanceState
     */
    private Button mBtSearch;
    private EditText mEtCity;
    private EditText mEtAddress;
    private GeoCoder mSearch = null;
    // 搜索模块，也可去掉地图模块独立使用


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        initView();
        initLocation();
        initMarker();
        initEvent();
        initSearch();
    }

    /**
     * 设置地图
     */

    private void initView() {
        mBtSearch =findViewById(R.id.bt_search);
        mEtAddress =findViewById(R.id.et_address);
        mEtCity = findViewById(R.id.et_city);
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

    /**
     * 初始化定位设置
     */
    private void initSearch() {
        // 初始化搜索模块，注册事件监听
        mSearch = GeoCoder.newInstance();
        mSearch.setOnGetGeoCodeResultListener(this);
    }

    /**
     * 设置标识物的图标
     */
    private void initMarker()
    {
        mMarker = BitmapDescriptorFactory.fromResource(R.drawable.maker);
        mMarkerLy = findViewById(R.id.id_maker_ly);
    }

    private void initEvent() {

        mBtSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String city = mEtCity.getText().toString();
                String address = mEtAddress.getText().toString();
                if (!"".equals(city)&&!"".equals(address)){
                    // Geo搜索
                    mSearch.geocode(new GeoCodeOption().city(city)
                    .address(address));
                }
            }
        });

        mBaiduMap.setOnMapClickListener(new BaiduMap.OnMapClickListener(){

			@Override
			public boolean onMapPoiClick(MapPoi arg0)
			{
				return false;
			}

			@Override
			public void onMapClick(LatLng arg0)
			{
				mMarkerLy.setVisibility(View.GONE);
				mBaiduMap.hideInfoWindow();
			}
		});


        mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
        @Override
        public boolean onMarkerClick(Marker marker)
        {
            L.e("Marker Click");
            Bundle extraInfo = marker.getExtraInfo();
            MapInfo info = (MapInfo) extraInfo.getSerializable("info");
            ImageView iv =  mMarkerLy.findViewById(R.id.id_info_img);
            TextView distance =  mMarkerLy.findViewById(R.id.id_info_distance);
            TextView name =  mMarkerLy.findViewById(R.id.id_info_name);
            TextView zan =  mMarkerLy.findViewById(R.id.id_info_zan);
            iv.setImageResource(info.getImgId());
            distance.setText(info.getDistance());
            name.setText(info.getName());
            zan.setText(info.getZan() + "");

            InfoWindow infoWindow;
            TextView tv = new TextView(MainActivity.this);
            tv.setBackgroundResource(R.drawable.location_tips);
            tv.setPadding(30, 20, 30, 50);
            tv.setText(info.getName());
            tv.setTextColor(Color.parseColor("#ffffff"));

            final LatLng latLng = marker.getPosition();
            Point p = mBaiduMap.getProjection().toScreenLocation(latLng);
            p.y -= 47;
            LatLng ll = mBaiduMap.getProjection().fromScreenLocation(p);

            infoWindow = new InfoWindow(tv, ll,0);
            mBaiduMap.showInfoWindow(infoWindow);
            mMarkerLy.setVisibility(View.VISIBLE);
            return true;
        }
    });
    }

    /**
     *定位到具体位置
     */
    @Override
    public void onGetGeoCodeResult(GeoCodeResult result) {
        if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
            Toast.makeText(MainActivity.this, "抱歉，未能找到结果", Toast.LENGTH_LONG)
                    .show();
            return;
        }
        mBaiduMap.clear();
        mBaiduMap.addOverlay(new MarkerOptions().position(result.getLocation())
                .icon(BitmapDescriptorFactory
                        .fromResource(R.drawable.icon_marka)));
        mBaiduMap.setMapStatus(MapStatusUpdateFactory.newLatLng(result
                .getLocation()));
        String strInfo = String.format("纬度：%f 经度：%f",
                result.getLocation().latitude, result.getLocation().longitude);
        Toast.makeText(MainActivity.this, strInfo, Toast.LENGTH_LONG).show();

    }

    @Override
    public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {

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
        mSearch.destroy();
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

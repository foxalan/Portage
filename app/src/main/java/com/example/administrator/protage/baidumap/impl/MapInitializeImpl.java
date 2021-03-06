package com.example.administrator.protage.baidumap.impl;

import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.example.administrator.protage.util.L;


/**
 * @Author Alan
 * Date 2018/3/27 0027
 * Function 百度地图的实现类
 * Issue
 */

public class MapInitializeImpl implements IMapInitialize, IMapOperate {

    private boolean isFirst = true;
    private double latitude;
    private double longitude;

    public MapInitializeImpl() {
    }

    @Override
    public void initBaiduMap(BaiduMap baiduMap) {

        MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(15.0f);
        baiduMap.setMapStatus(msu);
        baiduMap.setMyLocationEnabled(true);
    }

    @Override
    public void initLocationClient(LocationClient locationClient) {

        LocationClientOption option = new LocationClientOption();
        option.setCoorType("bd09ll");
        option.setIsNeedAddress(true);
        option.setOpenGps(true);
        option.setScanSpan(1000);
        locationClient.setLocOption(option);
    }

    @Override
    public void location(BaiduMap baiduMap, BDLocation location) {

        L.e(location.getLatitude() + "------" + location.getLongitude() + "====");
        MyLocationData data = new MyLocationData.Builder()
                .accuracy(location.getRadius())
                .latitude(location.getLatitude())
                .longitude(location.getLongitude())
                .build();
        baiduMap.setMyLocationData(data);

        if (isFirst) {
            LatLng latLng = new LatLng(location.getLatitude(),
                    location.getLongitude());
            latitude = location.getLatitude();
            longitude = location.getLongitude();
            MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(latLng);
            baiduMap.animateMapStatus(msu);
            isFirst = false;
        }
    }


    @Override
    public void centerToMyLocation(BaiduMap baiduMap) {
        LatLng latLng = new LatLng(latitude, longitude);
        MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(latLng);
        baiduMap.animateMapStatus(msu);
    }
}

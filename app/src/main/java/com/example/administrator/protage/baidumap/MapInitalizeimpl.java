package com.example.administrator.protage.baidumap;

import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;

/**
 * @Author Alan
 * Date 2018/3/27 0027
 * Function
 * Issue
 */

public class MapInitalizeimpl implements IMapInitialize {

    @Override
    public void initBaiduMap(MapView mapView, BaiduMap baiduMap) {
        baiduMap = mapView.getMap();
        MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(15.0f);
        baiduMap.setMapStatus(msu);
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
}

package com.example.administrator.protage.baidumap;

import com.baidu.location.LocationClient;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;

/**
 * @Author Alan
 * Date 2018/3/27 0027
 * Function 百度地图中初始化操作
 * Issue
 */

public interface IMapInitialize {

    /**
     * 初始化地图设置
     * @param mapView
     */
    void initBaiduMap(MapView mapView, BaiduMap baiduMap);

    /**
     * 初始化定位操作
     * @param locationClient
     */
    void initLocationClient(LocationClient locationClient);


}

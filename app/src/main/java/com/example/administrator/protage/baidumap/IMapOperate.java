package com.example.administrator.protage.baidumap;

import android.location.Location;

import com.baidu.location.BDLocation;
import com.baidu.mapapi.map.BaiduMap;

/**
 * @Author Alan
 * Date 2018/3/27 0027
 * Function
 * Issue
 */

public interface IMapOperate {
    /**
     * 定位功能
     */
    void location(BaiduMap baiduMap,BDLocation location);
}

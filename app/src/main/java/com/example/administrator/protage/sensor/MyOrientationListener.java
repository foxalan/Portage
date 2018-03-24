package com.example.administrator.protage.sensor;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

/**
 * Created by Administrator on 2018/3/24 0024.
 * @author alan
 * 传感器
 */

public class MyOrientationListener implements SensorEventListener {

    private SensorManager mSensorManager;
    private Sensor mSensor;
    private Context mContext;
    private float lastX;
    private OnOrientationListener mOnOrientationListener;

    public MyOrientationListener(Context context){
        this.mContext = context;
    }

    public void setOnOrientationListener(OnOrientationListener mOnOrientationListener) {
        this.mOnOrientationListener = mOnOrientationListener;
    }

    public void start(){

        mSensorManager = (SensorManager) mContext
                .getSystemService(Context.SENSOR_SERVICE);
        if (mSensorManager != null)
        {
            // 获得方向传感器
            mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        }

        if (mSensor != null)
        {
            mSensorManager.registerListener(this, mSensor,
                    SensorManager.SENSOR_DELAY_UI);
        }

    }

    public void stop(){
        mSensorManager.unregisterListener(this);

    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        if (event.sensor.getType() == Sensor.TYPE_ORIENTATION)
        {
            float x = event.values[SensorManager.DATA_X];

            if (Math.abs(x - lastX) > 1.0)
            {
                if (mOnOrientationListener != null)
                {
                    mOnOrientationListener.onOrientationChanged(x);
                }
            }
            lastX = x;
        }
    }


    public interface OnOrientationListener{
        /**
         * 回调刷新
         * @param x
         */
        void  onOrientationChanged(float x);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}

package com.example.administrator.protage.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * @Author Alan
 * Date 2018/3/27 0027
 * Function activity的基类
 * Issue
 */

public abstract class BaseActivity extends AppCompatActivity{

    /**
     * 设置Activity的Layout
     * @return
     */
    public abstract Object getActivityLayout();

    /**
     * 绑定View，进行事件操作
     */
    public abstract void onBindView();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (isActivityNoTitle()){
            //去掉标题栏
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            //设置全屏
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }

        if (getActivityLayout() instanceof View){
            View view = (View) getActivityLayout();
            setContentView(view);
        }else if (getActivityLayout() instanceof Integer){
            int layoutId = (int) getActivityLayout();
            setContentView(layoutId);
        }else {

        }

        onBindView();
    }

    /**
     * 是否设置无状态栏
     * @return
     */
    public boolean isActivityNoTitle(){
        return false;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}

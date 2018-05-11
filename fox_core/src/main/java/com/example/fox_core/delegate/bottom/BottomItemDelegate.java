package com.example.fox_core.delegate.bottom;

import android.view.View;
import android.widget.Toast;

import com.example.fox_core.R;
import com.example.fox_core.app.Latte;
import com.example.fox_core.fragment.LatteDelegate;

/**
 * @Author Alan
 * Date 2018/5/10 0010
 * Function
 * Issue
 */

public abstract class BottomItemDelegate extends LatteDelegate {

    /**
     * 两次点击的间隔
     */
    private static final long WAIT_TIME = 2000L;
    private long TOUCH_TIME = 0;
    @Override
    public boolean onBackPressedSupport() {

        if (System.currentTimeMillis() - TOUCH_TIME < WAIT_TIME) {
            _mActivity.finish();
        } else {
            TOUCH_TIME = System.currentTimeMillis();
            Toast.makeText(_mActivity, "双击退出" + Latte.getApplicationContext().getString(R.string.app_name), Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}

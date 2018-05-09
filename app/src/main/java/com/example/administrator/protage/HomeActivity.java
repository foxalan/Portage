package com.example.administrator.protage;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.fox_core.fragment.LatteDelegate;
import com.example.fox_ec.icons.launcher.LauncherScrollDelegate;
import com.example.fox_ui.tankgame.TankFragment;

/**
 * @Author Alan
 * Date 2018/4/20 0020
 * Function
 * Issue
 */


public class HomeActivity extends PermissionsActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public LatteDelegate setRootDelegate() {
        //请求读写权限
     //   requestPermission(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_PHONE_STATE}, 0x0002);
     //   return new TankFragment();

        return new LauncherScrollDelegate();
    }

    @Override
    public void permissionSuccess(int requestCode) {
        super.permissionSuccess(requestCode);
    }
}

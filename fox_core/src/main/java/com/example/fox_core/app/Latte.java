package com.example.fox_core.app;

import android.content.Context;
import android.os.Handler;

/**
 * @Author Alan
 * Date 2018/5/3 0003
 * Function
 * Issue
 */

public class Latte {

    public static Configurator init() {
        return Configurator.getInstance();
    }

    public static <T> T getConfiguration(ConfigKeys key) {
        return getConfigurator().getConfiguration(key);
    }

    public static Configurator getConfigurator() {
        return Configurator.getInstance();
    }

    public static Context getApplicationContext() {
        return getConfiguration(ConfigKeys.APPLICATION_CONTEXT);
    }

    public static Handler getHandler() {
        return getConfiguration(ConfigKeys.HANDLER);
    }
}

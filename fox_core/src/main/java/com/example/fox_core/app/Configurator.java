package com.example.fox_core.app;

import android.content.Context;
import android.os.Handler;

import java.util.HashMap;

/**
 * @Author Alan
 * Date 2018/5/3 0003
 * Function
 * Issue
 */

public class Configurator {

    private static final HashMap<String, Object> LATTE_CONFIGS = new HashMap<>();


    public Configurator() {

    }

    /**
     * 内部类单例模式
     */
    static class Holder {
        private static final Configurator INSTANCE = new Configurator();

    }

    public static Configurator getInstance() {
        return Holder.INSTANCE;
    }

    public Configurator withApiHost(String host) {
        LATTE_CONFIGS.put(ConfigKeys.API_HOST.name(), host);
        return this;
    }

    public Configurator withContext(Context context) {
        LATTE_CONFIGS.put(ConfigKeys.APPLICATION_CONTEXT.name(), context);
        return this;
    }

    public Configurator withHandler(Handler handler) {
        LATTE_CONFIGS.put(ConfigKeys.HANDLER.name(), handler);
        return this;
    }

    @SuppressWarnings("unchecked")
    public <T> T getConfiguration(ConfigKeys key) {
        checkConfiguration();
        Object value = LATTE_CONFIGS.get(key.name());

        if (value == null) {
            throw new NullPointerException(key.toString() + " IS NULL");
        }

        return (T) LATTE_CONFIGS.get(key.name());

    }

    private void checkConfiguration() {
        boolean isReady = (boolean) LATTE_CONFIGS.get(ConfigKeys.READY.name());
        if (!isReady) {
            throw new RuntimeException("Configuration is not ready,call configure");
        }
    }

    /**
     * 作为一个开始的标示
     */
    public void configure() {
        LATTE_CONFIGS.put(ConfigKeys.READY.name(), true);
    }


}

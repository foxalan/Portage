package com.example.fox_core.app;

import android.content.Context;
import android.os.Handler;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @Author Alan
 * Date 2018/5/3 0003
 * Function
 * Issue 1.设置字体不是完全懂
 */

public class Configurator {

    private static final HashMap<String, Object> LATTE_CONFIGS = new HashMap<>();
    private static final ArrayList<IconFontDescriptor> ICONS = new ArrayList<>();

    public Configurator() {
        LATTE_CONFIGS.put(ConfigKeys.READY.name(), false);
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


    /**
     * 设置公共属性
     * @param host
     * @return
     */

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

    public final Configurator withIcon(IconFontDescriptor descriptor) {
        ICONS.add(descriptor);
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
        initIcons();
        LATTE_CONFIGS.put(ConfigKeys.READY.name(), true);
    }

    private void initIcons() {
        if (ICONS.size() > 0) {
            final Iconify.IconifyInitializer initializer = Iconify.with(ICONS.get(0));
            for (int i = 1; i < ICONS.size(); i++) {
                initializer.with(ICONS.get(i));
            }
        }
    }


}

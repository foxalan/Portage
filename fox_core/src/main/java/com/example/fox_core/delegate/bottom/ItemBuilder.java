package com.example.fox_core.delegate.bottom;

import java.util.LinkedHashMap;

/**
 * Created by 傅令杰
 */

public final class ItemBuilder {

    private final LinkedHashMap<BottomTabBean, BaseBottomDelegate> ITEMS = new LinkedHashMap<>();

    static ItemBuilder builder() {
        return new ItemBuilder();
    }

    public final ItemBuilder addItem(BottomTabBean bean, BaseBottomDelegate delegate) {
        ITEMS.put(bean, delegate);
        return this;
    }

    public final ItemBuilder addItems(LinkedHashMap<BottomTabBean, BaseBottomDelegate> items) {
        ITEMS.putAll(items);
        return this;
    }

    public final LinkedHashMap<BottomTabBean, BaseBottomDelegate> build() {
        return ITEMS;
    }
}

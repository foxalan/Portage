package com.example.fox_core.delegate.bottom;

import com.joanzapata.iconify.widget.IconTextView;

/**
 * @Author Alan
 * Date 2018/5/10 0010
 * Function
 * Issue
 */

public class BottomTabBean {

    private final CharSequence ICON;
    private final CharSequence TITLE;

    public BottomTabBean(CharSequence icon, CharSequence title) {
        this.ICON = icon;
        this.TITLE = title;
    }

    public CharSequence getIcon() {
        return ICON;
    }

    public CharSequence getTitle() {
        return TITLE;
    }
}

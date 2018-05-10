package com.example.fox_core.delegate;

import com.joanzapata.iconify.widget.IconTextView;

/**
 * @Author Alan
 * Date 2018/5/10 0010
 * Function
 * Issue
 */

public class BottomItemBean {

    private String title;
    private String icon;

    public BottomItemBean(String title, String icon) {
        this.title = title;
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}

package com.example.fox_ec.icons;

import com.joanzapata.iconify.Icon;
import com.joanzapata.iconify.IconFontDescriptor;

/**
 * @Author Alan
 * Date 2018/5/4 0004
 * Function
 * Issue
 */

public class FontEcModule implements IconFontDescriptor {
    @Override
    public String ttfFileName() {
        return "";
    }

    @Override
    public Icon[] characters() {
        return EcIcons.values();
    }
}

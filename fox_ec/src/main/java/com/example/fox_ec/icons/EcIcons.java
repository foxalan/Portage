package com.example.fox_ec.icons;


import com.joanzapata.iconify.Icon;

/**
 * @Author Alan
 * Date 2018/5/4 0004
 * Function
 * Issue
 */

public enum  EcIcons implements Icon {

    icon_scan('\ue602'),
    icon_ali_pay('\ue606');

    private char character;

    EcIcons(char character){
        this.character = character;
    }

    @Override
    public String key() {
        return name().replace('_', '-');
    }

    @Override
    public char character() {
        return character;
    }
}

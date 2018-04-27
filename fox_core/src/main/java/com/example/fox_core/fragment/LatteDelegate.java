package com.example.fox_core.fragment;

/**
 * @author Alan
 */

public abstract  class LatteDelegate extends BaseDelegate {

    @SuppressWarnings("unchecked")
    public <T extends LatteDelegate> T getParentDelegate() {
        return (T) getParentFragment();
    }
}

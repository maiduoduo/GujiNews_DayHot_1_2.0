package com.cnews.guji.smart.ui.model;

/**
 * 左右分页九宫格bean
 */
public class Menu9Model {
    public String name;
    public int iconRes;

    public Menu9Model(String name, int iconRes) {
        this.name = name;
        this.iconRes = iconRes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIconRes() {
        return iconRes;
    }

    public void setIconRes(int iconRes) {
        this.iconRes = iconRes;
    }
}
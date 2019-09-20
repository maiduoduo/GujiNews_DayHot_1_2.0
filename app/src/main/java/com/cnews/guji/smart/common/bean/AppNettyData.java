package com.cnews.guji.smart.common.bean;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.List;

public class AppNettyData implements Serializable {

    public List<HotkeywordBean> hotkeyword;
    public ShareBean share;
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

}

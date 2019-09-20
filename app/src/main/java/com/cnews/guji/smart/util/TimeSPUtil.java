package com.cnews.guji.smart.util;

import android.content.Context;
import android.content.SharedPreferences;

public class TimeSPUtil {
    private static final String PREFERENCE_NAME = "sp";
    private static SharedPreferences mSharedPreferences;
    private static TimeSPUtil mPreferenceUtils;
    private static SharedPreferences.Editor editor;

    private TimeSPUtil(Context cxt) {
        mSharedPreferences = cxt.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
    }

    /**
     * 单例模式，获取instance实例
     */
    public static TimeSPUtil getInstance(Context cxt) {
        if (mPreferenceUtils == null) {
            mPreferenceUtils = new TimeSPUtil(cxt);
        }
        editor = mSharedPreferences.edit();
        return mPreferenceUtils;
    }

    public void setRefreshTime(String str_name, String str_value) {
        editor.putString(str_name, str_value);
        editor.commit();
    }


    public String getRefreshTime(String str_name) {
        return mSharedPreferences.getString(str_name, "");
    }

}

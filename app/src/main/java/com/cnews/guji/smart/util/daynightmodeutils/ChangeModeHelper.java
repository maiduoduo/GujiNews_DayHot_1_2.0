package com.cnews.guji.smart.util.daynightmodeutils;

import android.content.Context;

import com.cnews.guji.smart.util.SharePrefUtil;

/**
 * 夜间模式辅助类
 */
public class ChangeModeHelper {
    public static final int MODE_DAY = 1;

    public static final int MODE_NIGHT = 2;
    private static String Mode = "mode";
    public static void setChangeMode(Context ctx,int mode){
        SharePrefUtil.saveInt(ctx,Mode, mode);
    }
    public static int getChangeMode(Context ctx){
        return SharePrefUtil.getInt(ctx,Mode, MODE_DAY);
    }
}

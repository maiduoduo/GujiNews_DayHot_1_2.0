package com.cnews.guji.smart.ui.model;

import android.content.Context;

import com.cnews.guji.smart.common.bean.HotNewsBestBean;
import com.cnews.guji.smart.ui.constant.NewsConstant;
import com.cnews.guji.smart.ui.contract.HotNewsBestContract;
import com.cnews.guji.smart.util.FileUtils;
import com.github.library.entity.MultiItemEntity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.List;

/**
 * @author：dingcl. home
 * |- 热闻精选
 */
public class HotNewsBestModel<S> implements HotNewsBestContract.model {
    @Override
    public HotNewsBestBean getHotNewsBestData(Context context) {
        return (HotNewsBestBean) getMultiIndexJsonData(context,NewsConstant.ASSET_HOT_NEWS_BEST);
    }

    @Override
    public HotNewsBestBean getHotNewsBestWares(Context context) {
        return (HotNewsBestBean) getMultiIndexJsonData(context,NewsConstant.ASSET_HOT_NEWS_BEST);
    }

    @Override
    public HotNewsBestBean getHotNewsBestMoreWares(Context context) {
        return (HotNewsBestBean)getMultiIndexData(context,(Class<S>) HotNewsBestBean.class, NewsConstant.ASSET_HOT_NEWS_BEST_MORE);
    }


    public S getMultiIndexJsonData(Context context,final String fileName) {
        String json = FileUtils.getJson(context, fileName);
        Gson gson = new Gson();
        Type type = new TypeToken<HotNewsBestBean>() {
        }.getType();
        return gson.fromJson(json, type);
    }


    public S getMultiIndexData(Context context,final Class<S> clazz , final String fillName) {
        InputStream is = null;
        S s = null;
        try {
            is = context.getAssets().open(fillName);
            String text = FileUtils.readTextFromFile(is);
            Gson gson = new Gson();
            s = gson.fromJson(text, clazz);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return s;

    }
}

package com.cnews.guji.smart.ui.model;

import android.content.Context;

import com.cnews.guji.smart.common.bean.basebean.HomeTophotIndexBean;
import com.cnews.guji.smart.ui.constant.NewsConstant;
import com.cnews.guji.smart.ui.contract.HomeHotTopContract;
import com.cnews.guji.smart.ui.model.source.NewsSource;
import com.cnews.guji.smart.util.FileUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;

/**
 * @package: HomeHotTopModel
 * @author： JSYL-DCL
 * @date: 2019/4/12
 * @describe： 热门数据处理
 * @email： 11442865
 */
public class HomeHotTopModel<S> implements HomeHotTopContract.model {


    /**
     * 热门刷新数据
     * @param context
     * @param flag
     * @param videoCurrentType
     * @return
     */
    @Override
    public HomeTophotIndexBean getHomeHotTopData(Context context, int flag, int videoCurrentType) {
        Type type = new TypeToken<HomeTophotIndexBean>() {}.getType();
        return (HomeTophotIndexBean) getMultiIndexJsonData
                (context, flag == 1 ? NewsSource.getHomeRefreshTypeSource(videoCurrentType): NewsSource.getHomeRefreshTypeSource(videoCurrentType), type);
    }

    /**
     * 热门添加数据
     * @param context
     * @param videoCurrentType
     * @return
     */
    @Override
    public HomeTophotIndexBean getHomeHotTopWares(Context context, int videoCurrentType) {
        Type type = new TypeToken<HomeTophotIndexBean>() {}.getType();
        return  (HomeTophotIndexBean) getMultiIndexJsonData(context,NewsConstant.ASSET_HOME_HOT_TOP,type);
    }

    /**
     * 热门加载更多数据
     * @param context
     * @param videoCurrentType
     * @return
     */
    @Override
    public HomeTophotIndexBean getMoreHomeHotTopWares(Context context, int videoCurrentType) {
        Type type = new TypeToken<HomeTophotIndexBean>() {}.getType();
        return (HomeTophotIndexBean)getMultiIndexJsonData(context,NewsSource.getHomeLoadMoreTypeSource(videoCurrentType),type);
    }


    /**
     * 解析json
     */
    public S getMultiIndexJsonData(Context context, final String fileName, Type type) {
        String json = FileUtils.getJson(context, fileName);
        Gson gson = new Gson();
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

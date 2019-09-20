package com.cnews.guji.smart.ui.model;


import android.content.Context;

import com.cnews.guji.smart.base.AppConstant;
import com.cnews.guji.smart.common.bean.ClothesBean;
import com.cnews.guji.smart.common.bean.FrontNewsBean;
import com.cnews.guji.smart.common.bean.NewsMainBean;
import com.cnews.guji.smart.common.net.RetrofitClient;
import com.cnews.guji.smart.ui.constant.NewsConstant;
import com.cnews.guji.smart.ui.contract.FrontNewsContract;
import com.cnews.guji.smart.util.FileUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;

import io.reactivex.Flowable;

/**
 * author：JSYL-DCL on 2019/2/13
 * 要闻数据
 */
public class FrontNewsModel<S> implements FrontNewsContract.model {
    @Override
    public FrontNewsBean getFrontNewsData(Context context, int flag) {
        return  (FrontNewsBean) getMultiIndexJsonData
                (flag == 1 ? NewsConstant.ASSET_FRONT_NEWS : NewsConstant.ASSET_FRONT_NEWS_MORE,context);
    }

    @Override
    public FrontNewsBean getFrontNewsWares(Context context) {
        return (FrontNewsBean) getMultiIndexJsonData(NewsConstant.ASSET_FRONT_NEWS_MORE,context);
    }

    @Override
    public FrontNewsBean getFrontNewsMoreWares(Context context) {
        return   (FrontNewsBean)getMultiIndexJsonData(NewsConstant.ASSET_FRONT_NEWS_MORE,context);
    }

    /**
     * 新闻—头条
     * @param context
     * @return
     */
    @Override
    public Flowable<ClothesBean> lodeMineChannelsRequest(Context context) {

//        http://c.m.163.com/nc/article/headline/T1348647909107/0-20.html
        return  RetrofitClient.getInstance(context).getApi().getClothes(AppConstant.GOODS_TYPE_CODE, AppConstant.GOODS_TYPE_NAME);
    }

    /**
     * 解析json
     */
    public S getMultiIndexJsonData(final String fileName, Context context) {
        String json = FileUtils.getJson(context, fileName);
        Gson gson = new Gson();
        Type type = new TypeToken<FrontNewsBean>() {
        }.getType();
        return gson.fromJson(json, type);
    }


    /**
     * 解析txt文本
     * @param clazz
     * @param fileName
     * @return
     */
    public S getMultiIndexData(final Class<S> clazz , final String fileName, Context context) {
        InputStream is = null;
        S s = null;
        try {
            is = context.getAssets().open(fileName);
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

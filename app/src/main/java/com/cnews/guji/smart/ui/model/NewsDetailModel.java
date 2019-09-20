package com.cnews.guji.smart.ui.model;

import android.content.Context;

import com.cnews.guji.smart.common.bean.NewsDetailBean;
import com.cnews.guji.smart.common.net.RetrofitClient;
import com.cnews.guji.smart.ui.contract.NewsDetailContract;

import io.reactivex.Flowable;

/**
 * 新闻详情业务处理
 * author：JSYL-DCL on 2019/3/11
 */
public class NewsDetailModel implements NewsDetailContract.Model {
    @Override
    public Flowable<NewsDetailBean> deatailItemRequest(Context context, String articleUrl) {
        return  RetrofitClient.getInstance(context)
                .getApi()
                .getNewsDetail(articleUrl);
    }
}

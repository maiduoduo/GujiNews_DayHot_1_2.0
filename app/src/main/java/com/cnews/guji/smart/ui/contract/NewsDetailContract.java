package com.cnews.guji.smart.ui.contract;

import android.content.Context;

import com.cnews.guji.smart.common.bean.NewsDetailBean;
import com.cnews.guji.smart.ui.model.NewsDetailModel;
import com.cnews.guji.smart.ui.mvp.IBaseView;

import io.reactivex.Flowable;

/**
 * 新闻详情
 */
public interface NewsDetailContract {
    interface Model {
        Flowable<NewsDetailBean> deatailItemRequest(Context context, String articleUrl);
    }

    interface View extends IBaseView {
        void detailItemData(NewsDetailBean data);
    }
    interface Presenter{
       void deatailItemRequest(Context context,String articleUrl);
    }
}

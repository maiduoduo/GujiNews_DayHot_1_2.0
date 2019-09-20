package com.cnews.guji.smart.ui.contract;

import android.content.Context;

import com.cnews.guji.smart.common.bean.ClothesBean;
import com.cnews.guji.smart.common.bean.FrontNewsBean;
import com.cnews.guji.smart.common.bean.NewsMainBean;
import com.cnews.guji.smart.ui.mvp.IBaseView;

import io.reactivex.Flowable;


/**
 * @author dingcl
 * 要闻业务
 */
public interface FrontNewsContract {
    interface model{
        FrontNewsBean getFrontNewsData(Context context, int flag);
        FrontNewsBean getFrontNewsWares(Context context);
        FrontNewsBean getFrontNewsMoreWares(Context context);
        Flowable<ClothesBean> lodeMineChannelsRequest(Context context);
    }
    interface View extends IBaseView {
        void setFrontNewsData(FrontNewsBean data);
        void setFrontNewsWares(FrontNewsBean data);
        void setFrontNewsMoreWares(FrontNewsBean data);
        void setMineChannelsRequest(ClothesBean data);
    }
    interface Presenter {
        void getFrontNewsData(Context context,int flag);
        void getFrontNewsWares(Context context);
        void getFrontNewsMoreWares(Context context);
        void lodeMineChannelsRequest(Context context);
    }

}
package com.cnews.guji.smart.ui.contract;

import android.content.Context;

import com.cnews.guji.smart.common.bean.basebean.HomeTophotIndexBean;
import com.cnews.guji.smart.ui.mvp.IBaseView;


/**
 * @author dingcl
 */
public interface HomeHotTopContract {
    interface model{
        HomeTophotIndexBean getHomeHotTopData(Context context,int flag, int videoCurrentType);
        HomeTophotIndexBean getHomeHotTopWares(Context context,int videoCurrentType);
        HomeTophotIndexBean getMoreHomeHotTopWares(Context context,int videoCurrentType);
    }
    interface View extends IBaseView {
        void setHomeHotTopData(HomeTophotIndexBean tophotBean);
        void setHomeHotTopWares(HomeTophotIndexBean tophotBean);
        void setMoreHomeHotTopWares(HomeTophotIndexBean tophotBean);
    }
    interface Presenter {
        void getHomeHotTopData(Context context,int flag, int videoCurrentType);
        void getHomeHotTopWares(Context context,int videoCurrentType);
        void getMoreHomeHotTopWares(Context context,int videoCurrentType);
    }

}
package com.cnews.guji.smart.ui.contract;
import android.content.Context;

import com.cnews.guji.smart.common.bean.HotNewsBestBean;
import com.cnews.guji.smart.ui.model.HotNewsBestModel;
import com.cnews.guji.smart.ui.mvp.IBasePresenter;
import com.cnews.guji.smart.ui.mvp.IBaseView;


/**
 * @author dingcl
 * 热闻精选业务
 */

public interface HotNewsBestContract {
    interface model{
        HotNewsBestBean getHotNewsBestData(Context context);
        HotNewsBestBean getHotNewsBestWares(Context context);
        HotNewsBestBean getHotNewsBestMoreWares(Context context);
    }

    interface View extends IBaseView {
        void setHotNewsBestData(HotNewsBestBean hotNewsBestBean);
        void setHotNewsBestWares(HotNewsBestBean hotNewsBestBean);
        void setHotNewsBestMoreWares(HotNewsBestBean hotNewsBestBean);
    }
    interface Presenter {
        void getHotNewsBestData(Context context);
        void getHotNewsBestWares(Context context);
        void getHotNewsBestMoreWares(Context context);
    }

}
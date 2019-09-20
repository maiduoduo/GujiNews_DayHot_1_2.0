package com.cnews.guji.smart.ui.contract;
import android.content.Context;
import com.cnews.guji.smart.common.bean.ProfileCareBean;
import com.cnews.guji.smart.ui.mvp.BaseRxPresenter;
import com.cnews.guji.smart.ui.mvp.IBaseView;

import rx.Observable;


/**
 * 个人契约接口
 * @author JSYL-DCL
 */
public interface ProfileCareContract {
    interface Model{
        Observable<ProfileCareBean> getData(Context context);
        Observable<ProfileCareBean> getDataWares(Context context);
        Observable<ProfileCareBean> getDataMoreWares(Context context);
    }
    interface View extends IBaseView {
        void setData(ProfileCareBean data);
        void setDataWares(ProfileCareBean data);
        void setDataMoreWares(ProfileCareBean data);
    }
    abstract class Presenter extends BaseRxPresenter<View,Model> {
        public abstract void getData(Context context);
        public abstract void getDataWares(Context context);
        public abstract void getDataMoreWares(Context context);
    }

}
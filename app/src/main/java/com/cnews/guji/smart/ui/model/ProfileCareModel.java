package com.cnews.guji.smart.ui.model;


import android.content.Context;

import com.cnews.guji.smart.app.BaseApplication;
import com.cnews.guji.smart.base.AppConstant;
import com.cnews.guji.smart.base.baserx.RxSchedulers;
import com.cnews.guji.smart.common.bean.ProfileCareBean;
import com.cnews.guji.smart.common.db.ProfileCareManager;
import com.cnews.guji.smart.ui.contract.ProfileCareContract;
import com.cnews.guji.smart.util.ACache;

import rx.Observable;
import rx.Subscriber;

/**
 * author：JSYL-DCL on 2019/3/4
 * 私人信息
 */
public class ProfileCareModel implements ProfileCareContract.Model {


    @Override
    public Observable<ProfileCareBean> getData(Context context) {
        return Observable.create(new Observable.OnSubscribe <ProfileCareBean>() {
            @Override
            public void call(Subscriber<? super ProfileCareBean> subscriber) {
                ProfileCareBean profileCareBean = (ProfileCareBean) ACache.get(BaseApplication.getAppContext()).getAsObject(AppConstant.PROFILE_MORE);
                if(profileCareBean==null){
                    profileCareBean = (ProfileCareBean)ProfileCareManager.loadProfileCareData();
                    subscriber.onNext(profileCareBean);
                }
                subscriber.onCompleted();
            }
        }).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<ProfileCareBean> getDataWares(Context context) {
        return null;
    }

    @Override
    public Observable<ProfileCareBean> getDataMoreWares(Context context) {
        return  null;
    }
}

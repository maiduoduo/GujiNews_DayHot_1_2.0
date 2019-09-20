package com.cnews.guji.smart.ui.presenter;


import android.content.Context;

import com.cnews.guji.smart.base.baserx.RxManager;
import com.cnews.guji.smart.base.baserx.RxSubscriber;
import com.cnews.guji.smart.common.bean.ProfileCareBean;
import com.cnews.guji.smart.ui.constant.NewsConstant;
import com.cnews.guji.smart.ui.contract.ProfileCareContract;
import com.cnews.guji.smart.ui.model.ProfileCareModel;
import com.cnews.guji.smart.util.FileUtils;
import com.cnews.guji.smart.util.ILog;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 个人关注数据收发
 * @author JSYL-DCL
 */
public class ProfileCarePresenterimpl<S> extends ProfileCareContract.Presenter {

    @Override
    public void getData(Context context) {
        mView.showProgress("加载..");
        mRxManager.add(mModel.getData(context).subscribe(new RxSubscriber<ProfileCareBean>(context, false) {
            @Override
            protected void _onNext(ProfileCareBean profileCareBean) {
                mView.setData(profileCareBean);
                if (profileCareBean != null){
                    mView.cancelProgress();
//                    final Timer t2 = new Timer();
//                    t2.schedule(new TimerTask() {
//                        @Override
//                        public void run() {
//                            mView.cancelProgress();
//                            t2.cancel();
//                        }
//                    }, 5000);
                }
            }

            @Override
            protected void _onError(Throwable e,String message) {
                mView.cancelProgress();
                mView.showError(e);
            }
        }));
    }

    @Override
    public void getDataWares(Context context) {

    }

    @Override
    public void getDataMoreWares(Context context) {

    }
}

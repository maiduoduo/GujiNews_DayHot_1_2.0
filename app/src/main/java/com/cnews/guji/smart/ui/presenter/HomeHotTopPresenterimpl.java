package com.cnews.guji.smart.ui.presenter;


import android.content.Context;

import com.cnews.guji.smart.common.bean.basebean.HomeTophotIndexBean;
import com.cnews.guji.smart.ui.contract.HomeHotTopContract;
import com.cnews.guji.smart.ui.model.HomeHotTopModel;
import com.cnews.guji.smart.ui.mvp.IBasePresenter;

/**
 * Created by dingcl
 * 热门精选数据收发
 */
public class HomeHotTopPresenterimpl<S> extends IBasePresenter<HomeHotTopContract.View> implements HomeHotTopContract.Presenter{
    private HomeHotTopContract.model model;
    public HomeHotTopPresenterimpl() {
        model = new HomeHotTopModel();
    }

    @Override
    public void getHomeHotTopData(Context context,int flag,int videoCurrentType) {
        if (!isViewAttached())return;
        mView.showProgress("加载中..");
        HomeTophotIndexBean homeHotTopData = model.getHomeHotTopData(context, flag, videoCurrentType);
        if (homeHotTopData != null){
            mView.cancelProgress();
            mView.setHomeHotTopData(homeHotTopData);
        }
    }


    @Override
    public void getHomeHotTopWares(Context context,int videoCurrentType) {
        if (!isViewAttached())return;
        mView.showProgress("加载中..");
        HomeTophotIndexBean homeHotTopData = model.getHomeHotTopWares(context,videoCurrentType);
        if (homeHotTopData != null){
            mView.cancelProgress();
            mView.setHomeHotTopWares(homeHotTopData);
        }
    }

    @Override
    public void getMoreHomeHotTopWares(Context context,int videoCurrentType) {
        if (!isViewAttached())return;
        mView.showProgress("加载中..");
        HomeTophotIndexBean homeHotTopData = model.getMoreHomeHotTopWares(context,videoCurrentType);
        if (homeHotTopData != null){
            mView.cancelProgress();
            mView.setMoreHomeHotTopWares(homeHotTopData);
        }
    }




}

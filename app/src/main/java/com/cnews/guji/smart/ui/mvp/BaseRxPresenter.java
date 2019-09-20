package com.cnews.guji.smart.ui.mvp;

import android.content.Context;

import com.cnews.guji.smart.base.baserx.RxManager;

/**
 * des:基类presenter
 */
public abstract class BaseRxPresenter<T,E>{
    public Context mContext;
    public T mView;
    public E mModel;
    public RxManager mRxManager = new RxManager();

    public void setVM(T v, E m) {
        this.mView = v;
        this.mModel = m;
        this.onStart();
    }
    public void onStart(){
    };
    public void onDestroy() {
        mRxManager.clear();
    }
}

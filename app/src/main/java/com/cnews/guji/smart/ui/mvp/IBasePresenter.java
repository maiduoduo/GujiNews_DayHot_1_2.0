package com.cnews.guji.smart.ui.mvp;

import android.content.Context;

/**
 * @package: IBasePresenter
 * @author： JSYL-DCL
 * @describe： Presenter的基类
 * @email： 11442865
 */
public class IBasePresenter<V extends IBaseView> {
    protected V mView;


    /**
     * 绑定View,一般在初始化的时候初始化
     * @param view
     */
    public void attachView(V view){
        this.mView = view;
    }

    /**
     * 解绑View,一般在ondestroy()执行的时候销毁
     */
    public void detachView(){
        this.mView = null;
    }

    /**
     * View是否绑定
     * @return
     */
    public boolean isViewAttached(){
        return this.mView != null;
    }

    /*
        public Context mContext;
        public E mModel;
        public T mView;

        public void setVM(T v, E m) {
            this.mView = v;
            this.mModel = m;
            this.onStart();
        }
        public void onStart(){
        };
        public void onDestroy() {
        }
    */
}

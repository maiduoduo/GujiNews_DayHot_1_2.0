package com.cnews.guji.smart.ui.presenter;

import android.content.Context;

import com.cnews.guji.smart.base.baserx.RxSchedulers;
import com.cnews.guji.smart.common.bean.NewsDetailBean;
import com.cnews.guji.smart.ui.contract.NewsDetailContract;
import com.cnews.guji.smart.ui.model.NewsDetailModel;
import com.cnews.guji.smart.ui.mvp.IBasePresenter;

import io.reactivex.functions.Consumer;

/**
 * 新闻详情数据收发
 * @author JSYL-DCL
 */
public class NewsDetailPresenterImpl extends IBasePresenter<NewsDetailContract.View> implements NewsDetailContract.Presenter {
    private NewsDetailContract.Model mModel;

    public NewsDetailPresenterImpl() {
        this.mModel = new NewsDetailModel();
    }
    /**
     * 新闻详情
     * @param context
     */
    @Override
    public void deatailItemRequest(Context context,String articleUrl) {
        if (!isViewAttached())return;
        mView.showProgress("加载数据..");
        mModel.deatailItemRequest(context,articleUrl)
                .compose(RxSchedulers.Flo_io_main())
                .as(mView.bindAutoDispose())
                .subscribe(new Consumer<NewsDetailBean>() {
                               @Override
                               public void accept(NewsDetailBean newsDetailBean) throws Exception {
                                    mView.cancelProgress();
                                    mView.detailItemData(newsDetailBean);
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {
                                   mView.cancelProgress();
                                   mView.showError(throwable);
                               }
                           }
                );
    }
}

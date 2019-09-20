package com.cnews.guji.smart.ui.presenter;


import com.cnews.guji.smart.base.AppConstant;
import com.cnews.guji.smart.base.baserx.RxSubscriber;
import com.cnews.guji.smart.common.bean.NewsChannelTable;
import com.cnews.guji.smart.ui.contract.NewsChannelManagerContract;

import java.util.ArrayList;
import java.util.List;

/**
 * 新闻频道
 * @author JSYL-DCL
 */
public class NewsChanelPresenter extends NewsChannelManagerContract.Presenter{
    @Override
    public void lodeChannelsRequest() {
        mRxManager.add(mModel.lodeMineNewsChannels().subscribe(new RxSubscriber<List<NewsChannelTable>>(mContext,false) {
            @Override
            protected void _onNext(List<NewsChannelTable> newsChannelTables) {
               mView.returnMineNewsChannels(newsChannelTables);
            }

            @Override
            protected void _onError(Throwable e,String message) {

            }
        }));
        mRxManager.add(mModel.lodeMoreNewsChannels().subscribe(new RxSubscriber<List<NewsChannelTable>>(mContext,false) {
            @Override
            protected void _onNext(List<NewsChannelTable> newsChannelTables) {
                mView.returnMoreNewsChannels(newsChannelTables);
            }

            @Override
            protected void _onError(Throwable e,String message) {

            }
        }));
    }

    @Override
    public void onItemSwap(final ArrayList<NewsChannelTable> newsChannelTableList, int fromPosition, int toPosition) {
        mRxManager.add( mModel.swapDb(newsChannelTableList,fromPosition,toPosition).subscribe(new RxSubscriber<String>(mContext,false) {
            @Override
            protected void _onNext(String s) {
                mRxManager.post(AppConstant.NEWS_CHANNEL_CHANGED,newsChannelTableList);
            }

            @Override
            protected void _onError(Throwable e,String message) {

            }
        }));
       ;
    }

    @Override
    public void onItemAddOrRemove(final ArrayList<NewsChannelTable> mineChannelTableList, ArrayList<NewsChannelTable> moreChannelTableList) {
        mRxManager.add(mModel.updateDb(mineChannelTableList,moreChannelTableList).subscribe(new RxSubscriber<String>(mContext,false) {
            @Override
            protected void _onNext(String s) {
             mRxManager.post(AppConstant.NEWS_CHANNEL_CHANGED,mineChannelTableList);
            }

            @Override
            protected void _onError(Throwable e,String message) {

            }
        }));
    }
}

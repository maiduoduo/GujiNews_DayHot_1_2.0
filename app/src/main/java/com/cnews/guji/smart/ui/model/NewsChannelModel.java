package com.cnews.guji.smart.ui.model;

import com.cnews.guji.smart.R;
import com.cnews.guji.smart.app.BaseApplication;
import com.cnews.guji.smart.base.AppConstant;
import com.cnews.guji.smart.base.baserx.RxSchedulers;
import com.cnews.guji.smart.common.bean.NewsChannelTable;
import com.cnews.guji.smart.common.db.NewsChannelTableManager;
import com.cnews.guji.smart.ui.contract.NewsChannelManagerContract;
import com.cnews.guji.smart.util.ACache;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * 新闻频道
 * @author JSYL-DCL
 */
public class NewsChannelModel implements NewsChannelManagerContract.Model {
    @Override
    public Observable<List<NewsChannelTable>> lodeMineNewsChannels() {

        return Observable.create(new Observable.OnSubscribe<List<NewsChannelTable>>() {
            @Override
            public void call(Subscriber<? super List<NewsChannelTable>> subscriber) {
                ArrayList<NewsChannelTable> newsChannelTableList = (ArrayList<NewsChannelTable>) ACache.get(BaseApplication.getAppContext()).getAsObject(AppConstant.CHANNEL_MINE);
               if(newsChannelTableList==null){
                   newsChannelTableList= (ArrayList<NewsChannelTable>) NewsChannelTableManager.loadNewsChannelsStatic();
               }
                subscriber.onNext(newsChannelTableList);
                subscriber.onCompleted();
            }
        }).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<List<NewsChannelTable>> lodeMoreNewsChannels() {
        return Observable.create(new Observable.OnSubscribe<List<NewsChannelTable>>() {
            @Override
            public void call(Subscriber<? super List<NewsChannelTable>> subscriber) {
                ArrayList<NewsChannelTable> newsChannelTableList = (ArrayList<NewsChannelTable>) ACache.get(BaseApplication.getAppContext()).getAsObject(AppConstant.CHANNEL_MORE);
               if(newsChannelTableList==null) {
                   List<String> channelName = Arrays.asList(BaseApplication.getAppContext().getResources().getStringArray(R.array.news_channel_name));
                   List<String> channelId = Arrays.asList(BaseApplication.getAppContext().getResources().getStringArray(R.array.news_channel_id));
                   newsChannelTableList = new ArrayList<>();
                   for (int i = 0; i < channelName.size(); i++) {
                       NewsChannelTable entity = new NewsChannelTable(channelName.get(i), channelId.get(i)
                               , AppConstant.getType(channelId.get(i)), i <= 5, i, false);
                       newsChannelTableList.add(entity);
                   }
               }
                subscriber.onNext(newsChannelTableList);
                subscriber.onCompleted();
            }
        }).compose(RxSchedulers.<List<NewsChannelTable>>io_main());
    }

    @Override
    public Observable<String> swapDb(final ArrayList<NewsChannelTable> newsChannelTableList, int fromPosition, int toPosition) {
       return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                ACache.get(BaseApplication.getAppContext()).put(AppConstant.CHANNEL_MINE,newsChannelTableList);
                subscriber.onNext("");
                subscriber.onCompleted();
            }
        }).compose(RxSchedulers.<String>io_main());

    }

    @Override
    public Observable<String> updateDb(final ArrayList<NewsChannelTable> mineChannelTableList, final ArrayList<NewsChannelTable> moreChannelTableList) {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                ACache.get(BaseApplication.getAppContext()).put(AppConstant.CHANNEL_MINE,mineChannelTableList);
                ACache.get(BaseApplication.getAppContext()).put(AppConstant.CHANNEL_MORE,moreChannelTableList);
                subscriber.onNext("");
                subscriber.onCompleted();
            }
        }).compose(RxSchedulers.<String>io_main());
    }
}

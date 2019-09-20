package com.cnews.guji.smart.ui.contract;

import com.cnews.guji.smart.common.bean.NewsChannelTable;
import com.cnews.guji.smart.ui.mvp.BaseRxPresenter;
import com.cnews.guji.smart.ui.mvp.IBaseView;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

/**
 * 新闻频道契约接口
 * @author JSYL-DCL
 */
public interface NewsChannelManagerContract {

    interface Model {
        Observable lodeMineNewsChannels();

        Observable<List<NewsChannelTable>> lodeMoreNewsChannels();

        Observable<String> swapDb(ArrayList<NewsChannelTable> newsChannelTableList, int fromPosition, int toPosition);

        Observable<String> updateDb(ArrayList<NewsChannelTable> mineChannelTableList, ArrayList<NewsChannelTable> moreChannelTableList);
    }

    interface View extends IBaseView {
        void returnMineNewsChannels(List<NewsChannelTable> newsChannelsMine);
        void returnMoreNewsChannels(List<NewsChannelTable> newsChannelsMore);
    }

    abstract static class Presenter extends BaseRxPresenter<View, Model> {
        public abstract void lodeChannelsRequest();
        public abstract void onItemSwap(ArrayList<NewsChannelTable> newsChannelTableList, int fromPosition, final int toPosition);
        public abstract void onItemAddOrRemove(ArrayList<NewsChannelTable> mineChannelTableList, ArrayList<NewsChannelTable> moreChannelTableList);
    }
}

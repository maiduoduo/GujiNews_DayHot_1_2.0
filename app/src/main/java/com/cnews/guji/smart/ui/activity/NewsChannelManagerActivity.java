package com.cnews.guji.smart.ui.activity;

import android.arch.lifecycle.Lifecycle;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import com.cnews.guji.smart.R;
import com.cnews.guji.smart.base.AppConstant;
import com.cnews.guji.smart.base.BaseRxActivity;
import com.cnews.guji.smart.common.bean.NewsChannelTable;
import com.cnews.guji.smart.event.ChannelItemMoveEvent;
import com.cnews.guji.smart.helper.ItemDragHelperCallback;
import com.cnews.guji.smart.ui.adapter.ChannelAdapter;
import com.cnews.guji.smart.ui.contract.NewsChannelManagerContract;
import com.cnews.guji.smart.ui.model.NewsChannelModel;
import com.cnews.guji.smart.ui.presenter.NewsChanelPresenter;
import com.cnews.guji.smart.util.statusbar.StatusBarCompatUtils;
import com.jaeger.library.StatusBarUtil;
import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.AutoDisposeConverter;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;

import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;
import rx.functions.Action1;

/**
 * des:选择关注频道
 */
public class NewsChannelManagerActivity extends BaseRxActivity<NewsChanelPresenter, NewsChannelModel> implements NewsChannelManagerContract.View {
    @BindView(R.id.news_channel_mine_rv)
    RecyclerView newsChannelMineRv;
    @BindView(R.id.news_channel_more_rv)
    RecyclerView newsChannelMoreRv;
    private ChannelAdapter channelAdapterMine;
    private ChannelAdapter channelAdapterMore;
    private Context context;

    @Override
    public int getLayoutId() {
        return R.layout.act_news_channel;
    }

    /**
     * 入口
     * @param context
     */
    public static void startAction(Context context){
        Intent intent = new Intent(context, NewsChannelManagerActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = NewsChannelManagerActivity.this;
        mRxManager.on(AppConstant.CHANNEL_SWAP, new Action1<ChannelItemMoveEvent>() {
            @Override
            public void call(ChannelItemMoveEvent channelItemMoveEvent) {
                if (channelItemMoveEvent!=null) {
                    mPresenter.onItemSwap((ArrayList<NewsChannelTable>) channelAdapterMine.getAll(),channelItemMoveEvent.getFromPosition(),channelItemMoveEvent.getToPosition());
                }
            }
        });
    }


    @Override
    public void initPresenter() {
            mPresenter.setVM(this, mModel);
    }


    @Override
    public void initView(Bundle savedInstanceState) {
//        initToolBar();
        mPresenter.lodeChannelsRequest();
    }

    @Override
    public void initListener() {

    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected void getBundleExtras(Intent intent) {

    }


    @OnClick({R.id.ibnCloseChannel})
    public void bindViewClick(View view){
        switch (view.getId()){
            case R.id.ibnCloseChannel://关闭频道
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    finishAfterTransition();
                } else {
                    finish();
                }
                break;
        }
    }

    private void initToolBar() {
        // 设置toolbar
//        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                    finishAfterTransition();
//                } else {
//                    finish();
//                }
//            }
//        });
    }

    @Override
    public void returnMineNewsChannels(List<NewsChannelTable> newsChannelsMine) {
        channelAdapterMine = new ChannelAdapter(mContext,R.layout.item_news_channel);
        newsChannelMineRv.setLayoutManager(new GridLayoutManager(this, 4, LinearLayoutManager.VERTICAL, false));
        newsChannelMineRv.setItemAnimator(new DefaultItemAnimator());
        newsChannelMineRv.setAdapter(channelAdapterMine);
        channelAdapterMine.replaceAll(newsChannelsMine);
        channelAdapterMine.setOnItemClickListener(new ChannelAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                NewsChannelTable newsChannel = channelAdapterMine.get(position);
                channelAdapterMore.add(newsChannel);
                channelAdapterMine.removeAt(position);
                mPresenter.onItemAddOrRemove((ArrayList<NewsChannelTable>) channelAdapterMine.getAll(), (ArrayList<NewsChannelTable>)channelAdapterMore.getAll());

            }
        });


        ItemDragHelperCallback itemDragHelperCallback = new ItemDragHelperCallback(channelAdapterMine);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemDragHelperCallback);
        itemTouchHelper.attachToRecyclerView(newsChannelMineRv);
        channelAdapterMine.setItemDragHelperCallback(itemDragHelperCallback);
    }

    @Override
    public void returnMoreNewsChannels(List<NewsChannelTable> newsChannelsMore) {
        channelAdapterMore = new ChannelAdapter(mContext,R.layout.item_news_channel);
        newsChannelMoreRv.setLayoutManager(new GridLayoutManager(this, 4, LinearLayoutManager.VERTICAL, false));
        newsChannelMoreRv.setItemAnimator(new DefaultItemAnimator());
        newsChannelMoreRv.setAdapter(channelAdapterMore);
        channelAdapterMore.replaceAll(newsChannelsMore);
        channelAdapterMore.setOnItemClickListener(new ChannelAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                NewsChannelTable newsChannel = channelAdapterMore.get(position);
                channelAdapterMine.add(newsChannel);
                channelAdapterMore.removeAt(position);
                mPresenter.onItemAddOrRemove((ArrayList<NewsChannelTable>) channelAdapterMine.getAll(), (ArrayList<NewsChannelTable>)channelAdapterMore.getAll());
            }
        });
    }


    @Override
    protected void setStatusBar() {
        int mStatusBarColor = getResources().getColor(R.color.white);
        StatusBarUtil.setColor(NewsChannelManagerActivity.this, mStatusBarColor, 0);
//        mToolbar.setBackgroundColor(mStatusBarColor);
        StatusBarCompatUtils.setLightMode(NewsChannelManagerActivity.this);
    }

    @Override
    public void showProgress(String content) {

    }

    @Override
    public void cancelProgress() {

    }

    @Override
    public void showError(Throwable throwable) {

    }

    @Override
    public void showError(String error) {

    }

    /**
     * 绑定生命周期，防止MVP内存泄漏
     * @param <T>
     * @return
     */
    @Override
    public <T> AutoDisposeConverter<T> bindAutoDispose() {
        return AutoDispose.autoDisposable(AndroidLifecycleScopeProvider
                .from(this, Lifecycle.Event.ON_DESTROY));
    }

}

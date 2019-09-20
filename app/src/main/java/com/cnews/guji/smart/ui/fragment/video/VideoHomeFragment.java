package com.cnews.guji.smart.ui.fragment.video;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.cnews.guji.smart.R;
import com.cnews.guji.smart.base.AppConstant;
import com.cnews.guji.smart.base.BaseMvpFragment;
import com.cnews.guji.smart.common.bean.VideoNewsBean;
import com.cnews.guji.smart.helper.loadmore.CustomLoadMoreView;
import com.cnews.guji.smart.helper.refresh.qqmailrefresh.GJMailRefreshHeaderView;
import com.cnews.guji.smart.helper.refresh.qqmailrefresh.QQMailRefreshView;
import com.cnews.guji.smart.ui.adapter.VideoNewsAdapter;
import com.cnews.guji.smart.ui.contract.VideoNewsContract;
import com.cnews.guji.smart.ui.presenter.VideoNewsPresenterimpl;
import com.cnews.guji.smart.util.ILog;
import com.cnews.guji.smart.view.widget.TipView;
import com.github.library.BaseQuickAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 视频专区
 */
public class VideoHomeFragment extends BaseMvpFragment<VideoNewsPresenterimpl> implements OnRefreshListener, VideoNewsContract.View, BaseQuickAdapter.RequestLoadMoreListener {
    private static int SIZE = 20;
    private int mStartPage = 1;
    private VideoNewsAdapter mAdapter;
    private List<VideoNewsBean.Articles> data = new ArrayList<>();
    private int intSize = 0;
//    String tipInfo = "咕唧新闻视频专区为您精选%s条内容";
    /**
     * 加载样式标记
     */
    private int flag = 1;
    private static int semeColor = 0;
    private int videoFrType = 0;
    @BindView(R.id.swipe_target)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipeToLoadLayout)
    SwipeToLoadLayout mSwipeToLoadLayout;
    @BindView(R.id.mailRefresh)
    QQMailRefreshView mMailRefresh;
    @BindView(R.id.swipe_refresh_header)
    GJMailRefreshHeaderView mRefreshCustomRoot;
    @BindView(R.id.tipView)
    TipView mTipView;

    public static VideoHomeFragment getInstance(String title, int bgColor,int videoType) {
        semeColor = bgColor;
        VideoHomeFragment videoHomeFragment = new VideoHomeFragment();
        return videoHomeFragment;
    }


    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case AppConstant.SUCCESS:
                    mSwipeToLoadLayout.setRefreshing(false);
                    break;
                case AppConstant.FAILED:
                    mSwipeToLoadLayout.setRefreshing(false);
                    break;
                default:
                    break;
            }
        }

        ;
    };

    public void newInstance(String titlr, String title) {
        VideoHomeFragment videoMainFragment = new VideoHomeFragment();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.frag_video;
    }

    @Override
    public void intBase() {

    }

    @Override
    public void initPresenter() {
        mPresenter = new VideoNewsPresenterimpl();
        mPresenter.attachView(this);
    }

    @Override
    public void initView() {
        String avatarUrl = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1516644385815&di=c0552674db9f07a5f889d7c0980e33db&imgtype=0&src=http%3A%2F%2Fimg.mp.itc.cn%2Fupload%2F20170529%2F83d3ce719e9d4c0a8f1cd033ecac3692_th.jpg";
//        Glide.with(this).load(avatarUrl).into(mArticleImg);
//        mArticleImg.setImageURI(avatarUrl);
        iniRecyclerView();
    }

    @Override
    protected void initData() {
        setRefreshBg();
        mPresenter.getVideoData(mActivity,flag,videoFrType);
        flag = 0;
        mSwipeToLoadLayout.setOnRefreshListener(this);
        mSwipeToLoadLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                mSwipeToLoadLayout.setRefreshing(true);
            }
        }, 100);
    }

    private void setRefreshBg() {
        if (semeColor == 0){
            semeColor = getActivity().getResources().getColor(R.color.white);
        }
        mRefreshCustomRoot.setBackgroundColor(semeColor);
    }

    @Override
    protected void initListener() {
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if (!ViewCompat.canScrollVertically(recyclerView, 1)) {
                        mSwipeToLoadLayout.setLoadingMore(true);
                    }
                }
            }
        });
    }


    @Override
    public void onRefresh() {
        mSwipeToLoadLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
//                mSwipeToLoadLayout.setRefreshing(false);
                mAdapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载
                mPresenter.getVideoData(mActivity,flag,videoFrType);
                if (flag == 0) {
                    flag = 1;
                } else {
                    flag = 0;
                }
            }
        }, 1000);
    }

    /**
     * 视频数据
     * @param data
     */
    @Override
    public void setVideoData(VideoNewsBean data) {
        if (data == null) {
            handler.sendEmptyMessage(AppConstant.FAILED);
            return;
        }

        mTipView.show(String.format(getResources().getString(R.string.tip_text),"视频",data.getArticles().size()));
        ILog.e("video", "[setVideoData] ===>> :" + new Gson().toJson(data));
        mAdapter.getData().clear();
        mAdapter.setNewData(data.getArticles());
        mAdapter.notifyDataSetChanged();
        intSize = data.getArticles().size();
        handler.sendEmptyMessage(AppConstant.SUCCESS);
    }

    /**
     * 刷新数据
     * @param data
     */
    @Override
    public void setVideoWares(VideoNewsBean data) {

    }

    /**
     * 加载更多数据
     * @param data
     */
    @Override
    public void setVideoMoreWares(VideoNewsBean data) {
        if (data != null) {
            if (data.getArticles() != null) {
                mTipView.show(String.format(getResources().getString(R.string.tip_text),"视频",data.getArticles().size()));
                mAdapter.getData().addAll(data.getArticles());
                mAdapter.loadMoreComplete();
            }
        }


    }

    /**
     * 加载更多监听
     */
    @Override
    public void onLoadMoreRequested() {
        mRecyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                ILog.e("videomore", "video.getData().size():" + mAdapter.getData().size());
                ILog.e("videomore", "intSize:" + intSize);
                if (mAdapter.getData().size() >= intSize * 2) {
                    mAdapter.loadMoreEnd(false);
                } else {
                    mPresenter.getVideoMoreWares(mActivity,videoFrType);
                }
            }
        }, 1000);
    }

    private void iniRecyclerView() {
        mAdapter = new VideoNewsAdapter(getActivity(), R.layout.item_video_home, data);
        mAdapter.setOnLoadMoreListener(this);
        mAdapter.setEnableLoadMore(true);
        mAdapter.openLoadAnimation();
        mAdapter.setLoadMoreView(new CustomLoadMoreView());
//        adapter.closeLoadAnimation();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);
    }

    public void setVideoCurrentType(int videoType){
        videoFrType = videoType;
//        mSwipeToLoadLayout.setRefreshing(true);
        ILog.e("video", "-------------[videoFrType] ===>>222222 :" + videoFrType);
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
}

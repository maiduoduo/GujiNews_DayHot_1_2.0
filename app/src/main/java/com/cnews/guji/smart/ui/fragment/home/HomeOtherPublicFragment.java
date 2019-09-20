package com.cnews.guji.smart.ui.fragment.home;

import android.content.Context;
import android.os.Build;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.cnews.guji.smart.R;
import com.cnews.guji.smart.base.BaseFragment;
import com.cnews.guji.smart.base.BaseMvpFragment;
import com.cnews.guji.smart.common.bean.basebean.HomeTophotIndexBean;
import com.cnews.guji.smart.helper.headerview.HeaderView;
import com.cnews.guji.smart.ui.adapter.HomeHottopMultipleRecycleAdapter;
import com.cnews.guji.smart.ui.constant.NewsConstant;
import com.cnews.guji.smart.ui.contract.HomeHotTopContract;
import com.cnews.guji.smart.ui.presenter.HomeHotTopPresenterimpl;
import com.cnews.guji.smart.util.DensityUtil;
import com.cnews.guji.smart.util.ILog;
import com.cnews.guji.smart.util.statusbar.StatusBarCompatUtils;
import com.cnews.guji.smart.view.widget.TipView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.jzvd.Jzvd;

/**
 * 头条推荐
 */
public class HomeOtherPublicFragment extends BaseMvpFragment<HomeHotTopPresenterimpl> implements HeaderView.RefreshDistanceListener, HomeHotTopContract.View, OnRefreshListener {
    private static int SIZE = 20;
    private int mStartPage = 1;
    private Context mContext;
    private static final String TAG = HomeOtherPublicFragment.class.getSimpleName();
    private HomeHottopMultipleRecycleAdapter adapter;
    /**
     * 改变titlebar中icon颜色时的距离
     */
    private static int DISTANCE_WHEN_TO_SELECTED = 40;
    private int distanceY = 0;
    private int pageFrType = 0;
    @BindView(R.id.topbar)
    LinearLayout topbar;
    @BindView(R.id.view2)
    TextView view2;
    @BindView(R.id.swipe_target)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipeToLoadLayout)
    SwipeToLoadLayout mSwipeToLoadLayout;
    @BindView(R.id.fbToTop)
    ImageButton mFbToTop;
    @BindView(R.id.tipView)
    TipView tipView;


    private List<HomeTophotIndexBean.Articles> topDatasList;
    private String TAG1 = HomeOtherPublicFragment.class.getSimpleName();
    public static HomeOtherPublicFragment getInstance(String title, int homeHotType) {
        HomeOtherPublicFragment homeHotTopFragment = new HomeOtherPublicFragment();
        return homeHotTopFragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_home_other_public;
    }

    @Override
    public void intBase() {
        if (topDatasList != null) topDatasList.clear();
        else topDatasList = new ArrayList<>();
    }

    @Override
    public void initPresenter() {
        mContext = getActivity();
        mPresenter = new HomeHotTopPresenterimpl();
        mPresenter.attachView(this);
    }

    @Override
    public void initView() {
        //初始化头部刷新
        initPtrFrame();
        //初始化列表
        initRecyclerView();

    }

    private void initRecyclerView() {
        //取消焦点滑动，解决卡顿
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemViewCacheSize(10);
//        mRecyclerView.addItemDecoration(new SpaceItemDecoration1(DensityUtil.dip2px(mContext,3)));
        adapter = new HomeHottopMultipleRecycleAdapter(mContext, topDatasList);
        //防止数据错乱
        adapter.setHasStableIds(true);
//        adapter.setOnLoadMoreListener(this);
//        adapter.setEnableLoadMore(true);
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.item_headerview, null, false);
        adapter.addHeaderView(inflate);
        mRecyclerView.setAdapter(adapter);
        mPresenter.getHomeHotTopData(mContext,NewsConstant.FLAG_NEWS, pageFrType);
//        flag = 0;
    }

    @Override
    protected void initData() {
        mSwipeToLoadLayout.setOnRefreshListener(this);
        mSwipeToLoadLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                mSwipeToLoadLayout.setRefreshing(true);
            }
        }, 100);
    }

    @Override
    protected void initListener() {
        StatusBarCompatUtils.setDarkMode(getActivity());
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                distanceY += dy;
                if (distanceY > DensityUtil.dip2px(mContext, 10)) {
                    topbar.setBackgroundColor(getResources().getColor(R.color.title_bg_red));
//                    view2.setVisibility(View.VISIBLE);
//                    view2.setBackgroundResource(R.color.title_bg_red);
//                    int height = view2.getHeight();
//                    int visibility = view2.getVisibility();
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//4.4到5.0
//                        view2.setVisibility(View.GONE);
//                        view2.setBackgroundColor(0);
//                    } else {
//                        view2.setVisibility(View.VISIBLE);
//                        view2.setBackgroundResource(R.color.title_bg_red);
//                    }
                    if (Build.VERSION.SDK_INT > 10) {
                        topbar.setAlpha(distanceY * 1.0f / DensityUtil.dip2px(mContext, 100));
                    } else {
                        DISTANCE_WHEN_TO_SELECTED = 20;
                    }
                } else {
                    topbar.setBackgroundColor(0);
                    view2.setVisibility(View.GONE);
                    view2.setBackgroundColor(0);
                }
                if (distanceY > DensityUtil.dip2px(mContext, DISTANCE_WHEN_TO_SELECTED)) {
                } else if (distanceY <= DensityUtil.dip2px(mContext, DISTANCE_WHEN_TO_SELECTED)) {
                }
            }

            //设置RecyclerView滑动监听器 addOnScrollListener(),其中setOnScrollListener()方法已过时
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //获得recyclerView的线性布局管理器
                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                //获取到第一个item的显示的下标  不等于0表示第一个item处于不可见状态 说明列表没有滑动到顶部 显示回到顶部按钮
                int firstVisibleItemPosition = manager.findFirstVisibleItemPosition();
                // 当不滚动时
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    // 判断是否滚动超过一屏
                    if (firstVisibleItemPosition == 0) {
                        mFbToTop.setVisibility(View.GONE);
                    } else {
                        //显示回到顶部按钮
                        mFbToTop.setVisibility(View.VISIBLE);
                    }
                    //获取RecyclerView滑动时候的状态
                } else if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {//拖动中
                    mFbToTop.setVisibility(View.GONE);
                }
            }
        });
    }


    @OnClick({
            R.id.fbToTop
    })
    public void bindViewClick(View view){
        switch (view.getId()){
            case  R.id.fbToTop:
                mRecyclerView.smoothScrollToPosition(0);
                break;
        }
    }



    /**
     * 初始化下拉刷新
     */
    private void initPtrFrame() {
//        mPtrFrame.setOnRefreshDistanceListener(this);
//        mPtrFrame.setPtrHandler(new PtrHandler() {
//            @Override
//            public void onRefreshBegin(PtrFrameLayout frame) {
//                updateData();
//            }
//        });

        // 是否进入页面就开始显示刷新动作
        /*mPtrFrame.postDelayed(new Runnable() {
            @Override
            public void run() {
                mPtrFrame.autoRefresh();
            }
        }, 100);*/
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            ILog.e(TAG, "setUserVisibleHint22:" + isVisibleToUser);
            // 相当于onResume()方法--获取焦点
            if (pageFrType == 2) {
                Jzvd.releaseAllVideos();
            }
        } else {
            ILog.e(TAG, "else setUserVisibleHint22:" + isVisibleToUser);
            // 相当于onpause()方法---失去焦点
        }

        //设置滑动沉浸式
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//4.4到5.0
//            StatusBarCompatUtils.setTintStatusBg(getActivity(), topbar, scrollView);
//        }else {
//            StatusBarCompatUtils.setTintStatusBg(getActivity(), topbar, scrollView, view2);
//        }

        //即可获取焦点。
//        ivTopAd.setFocusable(true);
//        ivTopAd.setFocusableInTouchMode(true);
//        ivTopAd.requestFocus();
    }

    @Override
    public void onPositionChange(int currentPosY) {

    }

    @Override
    public void setHomeHotTopData(HomeTophotIndexBean tophotBean) {
        if(tophotBean == null){
            mSwipeToLoadLayout.setRefreshing(false);
            return;
        }
        tipView.show(String.format(getResources().getString(R.string.tip_text),"首页",tophotBean.getArticles().size()));
        adapter.getData().clear();
        adapter.setNewData(tophotBean.getArticles());
        mSwipeToLoadLayout.setRefreshing(false);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void setHomeHotTopWares(HomeTophotIndexBean tophotBean) {
        if(tophotBean == null){
            mSwipeToLoadLayout.setRefreshing(false);
            return;
        }
        tipView.show(String.format(getResources().getString(R.string.tip_text),"首页",tophotBean.getArticles().size()));
        adapter.getData().addAll(tophotBean.getArticles());
        adapter.loadMoreComplete();
    }

    @Override
    public void setMoreHomeHotTopWares(HomeTophotIndexBean tophotBean) {
        if(tophotBean == null){
            mSwipeToLoadLayout.setRefreshing(false);
            return;
        }
        tipView.show(String.format(getResources().getString(R.string.tip_text),"首页",tophotBean.getArticles().size()));
        adapter.getData().addAll(tophotBean.getArticles());
        adapter.loadMoreComplete();
    }

    public void setCurrentPageType(int videoType) {
        pageFrType = videoType;
//        mSwipeToLoadLayout.setRefreshing(true);
        ILog.e("home", "-------------[pageFrType] ===>>222222 :" + pageFrType);
    }

    /**
     * 下拉刷新
     */
    @Override
    public void onRefresh() {
        mSwipeToLoadLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                mPresenter.getHomeHotTopData(mActivity,NewsConstant.FLAG_NEWS,pageFrType);
                mSwipeToLoadLayout.setRefreshing(false);
            }
        }, 1000);
    }


    /**
     * 当前界面是否隐藏
     *     这种方法适用于界面替换用到hide()和show()方法
     * @param hidden
     */
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        ILog.e(TAG1,"other hidden："+hidden);
        if(!hidden){//...显示的操作
        } else {//...隐藏的操作
            if (pageFrType == 2) {
                Jzvd.releaseAllVideos();
            }
        }
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

package com.cnews.guji.smart.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.cnews.guji.smart.R;
import com.cnews.guji.smart.base.BaseMvpActivity;
import com.cnews.guji.smart.common.bean.HotNewsBestBean;
import com.cnews.guji.smart.ui.adapter.HotNewsBestMultipleRecycleAdapter;
import com.cnews.guji.smart.ui.contract.HotNewsBestContract;
import com.cnews.guji.smart.ui.presenter.HotNewsBestPresenterimpl;
import com.cnews.guji.smart.util.ILog;
import com.cnews.guji.smart.util.statusbar.StatusBarCompatUtils;
import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author：JSYL-DCL on 2019/1/29
 * 热闻精选详情界面
 */
public class HotNewsBestActivity extends BaseMvpActivity<HotNewsBestPresenterimpl> implements HotNewsBestContract.View {
    @BindView(R.id.ivHideBack)
    ImageView ivHideBack;
    @BindView(R.id.hnNestedScrollView)
    NestedScrollView hnNestedScrollView;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private Context mContext;
    private List<HotNewsBestBean.Articles> hotNewsBestList;
    private HotNewsBestMultipleRecycleAdapter adapter;

    public static void newInstance(Context context, Object data,int fromDirect) {
        context.startActivity(new Intent(context,HotNewsBestActivity.class));
    }

    @Override
    public void initBase() {
        if (hotNewsBestList != null) hotNewsBestList.clear();
        else hotNewsBestList = new ArrayList<>();
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mContext = HotNewsBestActivity.this;
        //初始化列表
        initRecyclerView();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_hotnews_best;
    }

    @Override
    public void initPresenter() {
        mPresenter = new HotNewsBestPresenterimpl();
        mPresenter.attachView(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    @OnClick({R.id.ivHideBack})
    public void bindViewClick(View view){
        switch (view.getId()){
            case R.id.ivHideBack:
                finishThis();
                break;
        }

    }

    private void initRecyclerView() {
        //取消焦点滑动，解决卡顿
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemViewCacheSize(10);
//        mRecyclerView.addItemDecoration(new SpaceItemDecoration1(DensityUtil.dip2px(mContext,3)));
        adapter = new HotNewsBestMultipleRecycleAdapter(mContext,hotNewsBestList);
        //防止数据错乱
        adapter.setHasStableIds(true);
//        adapter.setOnLoadMoreListener(this);
//        adapter.setEnableLoadMore(true);
        mRecyclerView.setAdapter(adapter);
        mPresenter.getHotNewsBestData(mContext);
//        flag = 0;
    }


    @Override
    protected int setImmersiveStatusBarColor() {
        return 0;
    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected void getBundleExtras(Intent intent) {

    }

    @Override
    protected void setStatusBar() {
        int mStatusBarColor = getResources().getColor(R.color.white);
        StatusBarUtil.setColor(HotNewsBestActivity.this, mStatusBarColor, 0);
//        mToolbar.setBackgroundColor(mStatusBarColor);
        StatusBarCompatUtils.setLightMode(HotNewsBestActivity.this);
    }

    @Override
    public void setHotNewsBestData(HotNewsBestBean HotNewsBestBean) {
        if(HotNewsBestBean == null){
            return;
        }
        ILog.e("HomeHottop","[setHomeHotTopData] ===>> :"+new Gson().toJson(HotNewsBestBean));
        adapter.getData().clear();
        adapter.setNewData(HotNewsBestBean.getArticles());
//        mPtrFrame.refreshComplete();

        adapter.notifyDataSetChanged();
    }

    @Override
    public void setHotNewsBestWares(HotNewsBestBean HotNewsBestBean) {

    }

    @Override
    public void setHotNewsBestMoreWares(HotNewsBestBean HotNewsBestBean) {

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

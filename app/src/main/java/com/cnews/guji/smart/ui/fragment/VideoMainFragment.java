package com.cnews.guji.smart.ui.fragment;

import android.content.Context;
import android.support.annotation.ColorInt;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.cnews.guji.smart.R;
import com.cnews.guji.smart.app.BaseApplication;
import com.cnews.guji.smart.base.BaseFragment;
import com.cnews.guji.smart.ui.fragment.video.VideoHomeFragment;
import com.cnews.guji.smart.ui.model.source.NewsSource;
import com.cnews.guji.smart.util.ILog;
import com.cnews.guji.smart.util.statusbar.StatusBarCompatUtils;
import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindArray;
import butterknife.BindView;
import cn.jzvd.Jzvd;

/**
 * 视频专区
 */
public class VideoMainFragment extends BaseFragment  implements ViewPager.OnPageChangeListener, OnTabSelectListener {
    private static final String TAG1 = VideoMainFragment.class.getSimpleName();
    private static final String TAG2 = "mainFragment";
    private Context _context;
    private List<Fragment> mFragments = new ArrayList<>();
//    private VideoViewPageAdapter mAdapter;
    private MyPagerAdapter viewpagerAdapter;
    private String[] videoCategorys;
    private  int bgColor = 0;
    @BindView(R.id.newsToolbar)
    Toolbar _toolbar;
    @BindView(R.id.fake_status_bar)
    View mFakeStatusBar;
    @BindView(R.id.slidingTabLayout)
    SlidingTabLayout _tabLayout;
    @BindView(R.id.newsViewpager)
    ViewPager mViewPager;
    @BindArray(R.array.risk_importtant_tab_title)
    String[] taskTabTitles;
    public static VideoMainFragment getInstance(String title) {
        VideoMainFragment sf = new VideoMainFragment();
//        sf.mTitle = title;
        return sf;
    }
    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_video_main_news;
    }

    @Override
    public void intBase() {
    }

    @Override
    public void initPresenter() {
    }

    @Override
    protected void initView() {
        _context = getActivity();
        _toolbar.setTitle("");
        int color1 = getResources().getColor(R.color.white);
        StatusBarCompatUtils.setLightMode(getActivity());
        setTvTitleBackgroundColor(color1);
        videoCategorys = NewsSource.getVideoCategoryStr(getActivity());
        if (videoCategorys.length > 0){
            for (int i = 0; i < NewsSource.getVideoCategory(getActivity()).size(); i++) {
                mFragments.add(VideoHomeFragment.getInstance(videoCategorys[i],bgColor,i));
            }
        }
        View decorView = getActivity().getWindow().getDecorView();
//        mAdapter = new VideoViewPageAdapter(getActivity().getSupportFragmentManager(), mFragments, videoCategorys);
//        mAdapter = new VideoViewPageAdapter(getChildFragmentManager(), mFragments, videoCategorys);
         viewpagerAdapter = new MyPagerAdapter(getChildFragmentManager());


        mViewPager.setAdapter(viewpagerAdapter);
        mViewPager.setOffscreenPageLimit(2);
//        _tabLayout.setTabData(videoCategorysStr);
        _tabLayout.setViewPager(mViewPager, videoCategorys);
        _tabLayout.setCurrentTab(0);
        _tabLayout.getTitleView(0).setTypeface(BaseApplication.getsInstance().getFangZhengSong3());
        _tabLayout.setTextSelectColor(getResources().getColor(R.color.title_bg_red ));
        _tabLayout.setTextUnselectColor(getResources().getColor(R.color.Gray3));
        for (int i = 0; i < mFragments.size(); i++) {
            _tabLayout.getTitleView(i).setTypeface(BaseApplication.getsInstance().getFangZhengSong3());
            VideoHomeFragment fragment = (VideoHomeFragment)mFragments.get(i);
            fragment.setVideoCurrentType(i);
        }
        //显示未读红点
        _tabLayout.showDot(1);
        _tabLayout.showMsg(5,2);
        _tabLayout.setMsgMargin(5, 0, 10);
        mViewPager.setCurrentItem(0);
        VideoHomeFragment fragment = (VideoHomeFragment)mFragments.get(0);
        fragment.setVideoCurrentType(0);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        mViewPager.addOnPageChangeListener(this);
        _tabLayout.setOnTabSelectListener(this);
    }

    /**
     * 界面滑动
     * @param position
     * @param positionOffset
     * @param positionOffsetPixels
     */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    /**
     * 界面滑动停止选中界面
     * @param position
     */
    @Override
    public void onPageSelected(int position) {
        _tabLayout.setCurrentTab(position);
        VideoHomeFragment fragment = (VideoHomeFragment)mFragments.get(position);
        fragment.setVideoCurrentType(position);
        //切换界面，停止所有播放状态
        Jzvd.releaseAllVideos();
        //退出全屏或小窗
//        Jzvd.quitFullscreenOrTinyWindow();
    }

    /**
     * 界面滑动状态改变
     * @param state
     */
    @Override
    public void onPageScrollStateChanged(int state) {

    }

    /**
     * 页签选中
     * @param position
     */
    @Override
    public void onTabSelect(int position) {
        mViewPager.setCurrentItem(position);
    }

    /**
     * 页签复选
     * @param position
     */
    @Override
    public void onTabReselect(int position) {

    }

    public void setTvTitleBackgroundColor(@ColorInt int color) {
        bgColor = color;
        _toolbar.setBackgroundColor(color);
        mFakeStatusBar.setBackgroundColor(color);
    }


    /**
     * 当前界面是否隐藏
     *     这种方法适用于界面替换用到hide()和show()方法
     * @param hidden
     */
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden){//展示，可见  // 相当于Fragment的onResume()
            ILog.e(TAG2,"onHiddenChanged VideoMainFragment:"+hidden);
            // 相当于Fragment的onResume()
            int color1 = getResources().getColor(R.color.white);
            StatusBarCompatUtils.setLightMode(getActivity());
            setTvTitleBackgroundColor(color1);
        }else {
            //相当于Fragment的onPause()
            Jzvd.releaseAllVideos();
            ILog.e(TAG2,"[else]onHiddenChanged VideoMainFragment:"+hidden);
        }
    }

    /**
     * 当前界面是否对用户可见
     *    这种党法适用于FragmentTransaction的replace()
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        try{
            if(getUserVisibleHint()){//界面可见时
            }else {
                Jzvd.releaseAllVideos();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        if (isVisibleToUser){//展示，可见   // 相当于onResume()方法--获取焦点
            ILog.e(TAG2,"isVisibleToUser VideoMainFragment:"+isVisibleToUser);
        }else {
            // 相当于onpause()方法---失去焦点
            ILog.e(TAG2,"[else]isVisibleToUser VideoMainFragment:"+isVisibleToUser);
        }
    }


    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return videoCategorys[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        ILog.e(TAG1,"isVisibleToUser VideoMainFragment  onResume---------------");
    }
}

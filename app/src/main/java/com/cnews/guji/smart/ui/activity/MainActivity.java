package com.cnews.guji.smart.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.cnews.guji.smart.R;
import com.cnews.guji.smart.app.BaseApplication;
import com.cnews.guji.smart.base.BaseActivity;
import com.cnews.guji.smart.common.bean.TabEntity;
import com.cnews.guji.smart.ui.fragment.FrontNewsMainFragment;
import com.cnews.guji.smart.ui.fragment.NewsMainFragment;
import com.cnews.guji.smart.ui.fragment.ProfileMainFragment;
import com.cnews.guji.smart.ui.fragment.VideoMainFragment;
import com.cnews.guji.smart.util.statusbar.StatusBarCompatUtils;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.flyco.tablayout.widget.MsgView;
import com.jaeger.library.StatusBarUtil;

import java.util.ArrayList;
import java.util.Random;

import butterknife.BindView;
import cn.jzvd.Jzvd;

/**
 * 主界面
 */
public class MainActivity extends BaseActivity {
    private static final String TAG1 = MainActivity.class.getSimpleName();
    @BindView(R.id.commonTabLayout)
    CommonTabLayout mTabLayout;
    @BindView(R.id.mainViewpager)
    ViewPager mViewPager;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private String[] mTitles = {"咕唧", "要闻", "视频", "关注"};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private int[] mIconUnselectIds = {
            R.mipmap.ic_home_normal, R.mipmap.ic_importnews_nornal,
            R.mipmap.ic_video_normal, R.mipmap.ic_care_normal};
    private int[] mIconSelectIds = {
            R.mipmap.ic_home_selected, R.mipmap.ic_importnews_selected,
            R.mipmap.ic_video_selected, R.mipmap.ic_care_selected};

//    private int[] mIconUnselectIds = {
//            R.mipmap.ic_home_normal, R.mipmap.ic_importnews_nornal, R.mipmap.ic_video_normal, R.mipmap.ic_care_normal};
//    private int[] mIconSelectIds = {
//            R.mipmap.ic_home_selected, R.mipmap.ic_importnews_selected, R.mipmap.ic_video_selected, R.mipmap.ic_care_selected};
//    private static int tabLayoutHeight;

    /**
     * 入口
     *
     * @param activity
     */
    public static void startAction(Activity activity) {
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @Override
    public int getLayoutId() {
        return R.layout.act_main;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        StatusBarCompatUtils.setLightMode(this);
        initMainPageSetting();
    }

    /**
     * 初始化配置
     */
    private void initMainPageSetting() {
//        for (String title : mTitles) {
//            mFragments.add(FrontNewsMainFragment.getInstance(title));
//        }
        for (int i = 0; i < mTitles.length; i++) {
            if (0 == i) {
                mFragments.add(NewsMainFragment.getInstance(mTitles[i]));
            }
            if (1 == i) mFragments.add(FrontNewsMainFragment.getInstance(mTitles[i]));
            if (2 == i) mFragments.add(VideoMainFragment.getInstance(mTitles[i]));
            if (3 == i) mFragments.add(ProfileMainFragment.getInstance(mTitles[i]));

            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        mViewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        mViewPager.setOffscreenPageLimit(4);
        tl_2();
        //两位数
        mTabLayout.showMsg(0, 55);
        mTabLayout.setMsgMargin(0, -5, 5);

        //三位数
        mTabLayout.showMsg(1, 100);
        mTabLayout.setMsgMargin(1, -5, 5);

        //设置未读消息红点
        mTabLayout.showDot(2);
        //设置未读消息背景
        mTabLayout.showMsg(3, 5);
        mTabLayout.setMsgMargin(3, 0, 5);
        MsgView rtv_2_3 = mTabLayout.getMsgView(3);
        if (rtv_2_3 != null) {
            rtv_2_3.setBackgroundColor(Color.parseColor("#6D8FB0"));
        }
    }


    private void tl_2() {
        StatusBarCompatUtils.setDarkMode(MainActivity.this);
        Random mRandom = new Random();
        int color = getResources().getColor(R.color.white);
        mTabLayout.setTabData(mTabEntities);
        mTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mViewPager.setCurrentItem(position);
                if (0 == position) {
                } else if (1 == position) {
                    FrontNewsMainFragment fragment = (FrontNewsMainFragment) mFragments.get(position);
                    if (fragment != null) {
                        fragment.setTvTitleBackgroundColor(color);
                        StatusBarCompatUtils.setLightMode(MainActivity.this);
                    }
                } else if (2 == position){
                    VideoMainFragment fragment = (VideoMainFragment) mFragments.get(position);
                    if (fragment != null) {
                        fragment.setTvTitleBackgroundColor(color);
                        StatusBarCompatUtils.setLightMode(MainActivity.this);
                    }
                } else if (3 == position){
                    ProfileMainFragment fragment = (ProfileMainFragment) mFragments.get(position);
                    if (fragment != null) {
                        StatusBarCompatUtils.setDarkMode(MainActivity.this);
                    }
                }
            }

            @Override
            public void onTabReselect(int position) {
                if (position == 0) {
                    mTabLayout.showMsg(0, mRandom.nextInt(100) + 1);
//                    UnreadMsgUtils.show(mTabLayout_2.getMsgView(0), mRandom.nextInt(100) + 1);
                }
            }
        });
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mTabLayout.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mViewPager.setCurrentItem(0);
    }


    @Override
    public void initPresenter() {
    }


    @Override
    public void initData() {


    }

    @Override
    public void initListener() {

    }

    @Override
    public void initBase() {
    }

    @Override
    protected int setImmersiveStatusBarColor() {
        return R.color.blue;
    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected void getBundleExtras(Intent intent) {

    }

    /**
     * 监听全屏视频时返回键
     */
    @Override
    public void onBackPressed() {
        if (Jzvd.backPress()) {
            return;
        }
        //清除缓存
        BaseApplication.frescoClearMem();
        finishThis();
        super.onBackPressed();
    }



    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Jzvd.releaseAllVideos();
    }

    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();

    }

    @Override
    protected void setStatusBar() {
        StatusBarUtil.setTransparentForImageViewInFragment(this, null);
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
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }
}

package com.cnews.guji.smart.ui.activity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.ViewGroup;

import com.cnews.guji.smart.R;
import com.cnews.guji.smart.app.BaseApplication;
import com.cnews.guji.smart.base.AppConstant;
import com.cnews.guji.smart.base.BaseActivity;
import com.cnews.guji.smart.common.bean.TabEntity;
import com.cnews.guji.smart.ui.fragment.ProfileMainFragment;
import com.cnews.guji.smart.ui.fragment.FrontNewsMainFragment;
import com.cnews.guji.smart.ui.fragment.NewsMainFragment;
import com.cnews.guji.smart.ui.fragment.VideoMainFragment;
import com.cnews.guji.smart.util.ILog;
import com.cnews.guji.smart.util.statusbar.StatusBarCompatUtils;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.jaeger.library.StatusBarUtil;

import java.util.ArrayList;
import java.util.Random;

import butterknife.BindView;
import cn.jzvd.Jzvd;

/**
 * des:主界面
 */
public class MainActivity_temp extends BaseActivity {
    private static final String TAG1 = MainActivity.class.getSimpleName();
    @BindView(R.id.commonTabLayout)
    CommonTabLayout tabLayout;

    private String[] mTitles = {"首页", "要闻", "视频", "关注"};
    private int[] mIconUnselectIds = {
            R.mipmap.ic_home_normal, R.mipmap.ic_importnews_nornal, R.mipmap.ic_video_normal, R.mipmap.ic_care_normal};
    private int[] mIconSelectIds = {
            R.mipmap.ic_home_selected, R.mipmap.ic_importnews_selected, R.mipmap.ic_video_selected, R.mipmap.ic_care_selected};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();

    private NewsMainFragment newsMainFragment;
    private FrontNewsMainFragment frontNewsMainFragment;
    private VideoMainFragment videoMainFragment;
    private ProfileMainFragment careMainFragment;
    private static int tabLayoutHeight;

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
        //初始化菜单
        initTab();
        //切换daynight模式要立即变色的页面
//        ChangeModeController.getInstance().init(this,R.attr.class);
        //初始化frament
        initFragment(savedInstanceState);
        tabLayout.measure(0, 0);
        tabLayoutHeight = tabLayout.getMeasuredHeight();
        //监听菜单显示或隐藏
//        startAnimation(hideOrShow);
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
     * 初始化tab
     */
    private void initTab() {
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        tabLayout.setTabData(mTabEntities);
        //点击监听
        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                SwitchTo(position);
            }

            @Override
            public void onTabReselect(int position) {
            }
        });
    }

    /**
     * 初始化碎片
     */
    private void initFragment(Bundle savedInstanceState) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        int currentTabPosition = 0;
        if (savedInstanceState != null) {
            newsMainFragment = (NewsMainFragment) getSupportFragmentManager().findFragmentByTag("newsMainFragment");
            frontNewsMainFragment = (FrontNewsMainFragment) getSupportFragmentManager().findFragmentByTag("frontNewsMainFragment");
            videoMainFragment = (VideoMainFragment) getSupportFragmentManager().findFragmentByTag("videoMainFragment");
            careMainFragment = (ProfileMainFragment) getSupportFragmentManager().findFragmentByTag("careMainFragment");
            currentTabPosition = savedInstanceState.getInt(AppConstant.HOME_CURRENT_TAB_POSITION);
        } else {
            newsMainFragment = new NewsMainFragment();
            frontNewsMainFragment = new FrontNewsMainFragment();
            videoMainFragment = new VideoMainFragment();
            careMainFragment = new ProfileMainFragment();

//            transaction.add(R.id.fl_body, newsMainFragment, "newsMainFragment");
//            transaction.add(R.id.fl_body, frontNewsMainFragment, "frontNewsMainFragment");
//            transaction.add(R.id.fl_body, videoMainFragment, "videoMainFragment");
//            transaction.add(R.id.fl_body, careMainFragment, "careMainFragment");
        }
        transaction.commit();
        SwitchTo(currentTabPosition);
        tabLayout.setCurrentTab(currentTabPosition);
    }

    /**
     * 切换
     */
    private void SwitchTo(int position) {
        ILog.e(TAG1, "主页菜单position: " + position);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        StatusBarCompatUtils.setDarkMode(MainActivity_temp.this);
        switch (position) {
            //首页
            case 0:
                hideShowFragment(transaction, frontNewsMainFragment, videoMainFragment, careMainFragment, newsMainFragment);
//                StatusBarCompatUtils.setStatusTextColor(true, MainActivity.this);
                break;
            //要闻
            case 1:
                hideShowFragment(transaction, newsMainFragment, videoMainFragment, careMainFragment, frontNewsMainFragment);
//                StatusBarCompatUtils.setStatusTextColor(false, MainActivity.this);
                Random random = new Random();
//                int color = 0xff000000 | random.nextInt(0xffffff);
                int color = getResources().getColor(R.color.white);
                if (frontNewsMainFragment != null) {
                    frontNewsMainFragment.setTvTitleBackgroundColor(color);
                    StatusBarCompatUtils.setLightMode(MainActivity_temp.this);
                }
                break;
            //视频
            case 2:
                hideShowFragment(transaction, newsMainFragment, frontNewsMainFragment, careMainFragment, videoMainFragment);
//                StatusBarCompatUtils.setStatusTextColor(false, MainActivity.this);
                Random random1 = new Random();
//                int color1 = 0xff000000 | random1.nextInt(0xffffff);
                int color1 = getResources().getColor(R.color.white);
                if (videoMainFragment != null) {
                    videoMainFragment.setTvTitleBackgroundColor(color1);
                    StatusBarCompatUtils.setLightMode(MainActivity_temp.this);
                }
                break;

            //关注
            case 3:
                hideShowFragment(transaction, newsMainFragment, frontNewsMainFragment, videoMainFragment, careMainFragment);
//                StatusBarCompatUtils.setStatusTextColor(true, MainActivity.this);
                Random random2 = new Random();
                int color2 = 0xff000000 | random2.nextInt(0xffffff);
                if (careMainFragment != null) {
                    careMainFragment.setTvTitleBackgroundColor(color2);
                }
                break;
            default:
                break;
        }
    }

    private void hideShowFragment(FragmentTransaction transaction, Fragment fragment1, Fragment fragment2, Fragment fragment3, Fragment fragment4) {
        transaction.hide(fragment1);
        transaction.hide(fragment2);
        transaction.hide(fragment3);
        transaction.show(fragment4);
        transaction.commitAllowingStateLoss();
    }


    /**
     * 菜单显示隐藏动画
     *
     * @param showOrHide
     */
    private void startAnimation(boolean showOrHide) {
        final ViewGroup.LayoutParams layoutParams = tabLayout.getLayoutParams();
        ValueAnimator valueAnimator;
        ObjectAnimator alpha;
        if (!showOrHide) {
            valueAnimator = ValueAnimator.ofInt(tabLayoutHeight, 0);
            alpha = ObjectAnimator.ofFloat(tabLayout, "alpha", 1, 0);
        } else {
            valueAnimator = ValueAnimator.ofInt(0, tabLayoutHeight);
            alpha = ObjectAnimator.ofFloat(tabLayout, "alpha", 0, 1);
        }
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                layoutParams.height = (int) valueAnimator.getAnimatedValue();
                tabLayout.setLayoutParams(layoutParams);
            }
        });
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(500);
        animatorSet.playTogether(valueAnimator, alpha);
        animatorSet.start();
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

//    /**
//     * 监听返回键
//     *
//     * @param keyCode
//     * @param event
//     * @return
//     */
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
////        if (keyCode == KeyEvent.KEYCODE_BACK) {
////            moveTaskToBack(false);
////            return true;
////        }
////        return super.onKeyDown(keyCode, event);
//        switch (keyCode) {
//            case KeyEvent.KEYCODE_BACK:
//                moveTaskToBack(false);
//                return false;//拦截事件
//            case KeyEvent.KEYCODE_MENU:
//                Log.i("main", "KeyEvent.KEYCODE_MENU");
//                break;
//            case KeyEvent.KEYCODE_HOME:
//                Log.i("main", "KeyEvent.KEYCODE_HOME");
//                // 收不到
//                break;
//            case KeyEvent.KEYCODE_APP_SWITCH:
//                Log.i("main", "KeyEvent.KEYCODE_APP_SWITCH");
//                // 收不到
//                break;
//            default:
//                break;
//        }
//        return super.onKeyDown(keyCode, event);
//    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //奔溃前保存位置
        ILog.e(TAG1, "onSaveInstanceState进来了1");
        if (tabLayout != null) {
            ILog.e(TAG1, "onSaveInstanceState进来了2");
            outState.putInt(AppConstant.HOME_CURRENT_TAB_POSITION, tabLayout.getCurrentTab());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
//        UpdateFunGO.onResume(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
//        UpdateFunGO.onStop(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        ChangeModeController.onDestory();
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
}

package com.cnews.guji.smart.ui.activity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.ViewGroup;

import com.cnews.guji.smart.R;
import com.cnews.guji.smart.app.BaseApplication;
import com.cnews.guji.smart.base.AppConstant;
import com.cnews.guji.smart.base.BaseActivity;
import com.cnews.guji.smart.common.bean.TabEntity;
import com.cnews.guji.smart.ui.fragment.FrontNewsMainFragment;
import com.cnews.guji.smart.ui.fragment.NewsMainFragment;
import com.cnews.guji.smart.ui.fragment.ProfileMainFragment;
import com.cnews.guji.smart.ui.fragment.VideoMainFragment;
import com.cnews.guji.smart.util.ILog;
import com.cnews.guji.smart.util.statusbar.StatusBarCompatUtils;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.jaeger.library.StatusBarUtil;

import java.util.ArrayList;

import butterknife.BindView;
import cn.jzvd.Jzvd;

/**
 * des:主界面
 */
public class MainActivity_temp2 extends BaseActivity {
    private static final String TAG1 = MainActivity_temp2.class.getSimpleName();
    @BindView(R.id.commonTabLayout)
    CommonTabLayout tabLayout;

    private String[] mTitles = {"首页", "要闻", "视频", "关注"};
    private int[] mIconUnselectIds = {
            R.mipmap.ic_home_normal, R.mipmap.ic_importnews_nornal, R.mipmap.ic_video_normal, R.mipmap.ic_care_normal};
    private int[] mIconSelectIds = {
            R.mipmap.ic_home_selected, R.mipmap.ic_importnews_selected, R.mipmap.ic_video_selected, R.mipmap.ic_care_selected};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private static int tabLayoutHeight;
    private FragmentManager mFm;
    private ArrayList<Fragment> mFragmentList = new ArrayList<Fragment>();
    private String[] mFragmentTagList = {"OneFragment", "TwoFragment", "ThreeFragment","FourFragment"};
    private Fragment mCurrentFragmen = null;  // 记录当前显示的Fragment

    /**
     * 入口
     *
     * @param activity
     */
    public static void startAction(Activity activity) {
        Intent intent = new Intent(activity, MainActivity_temp2.class);
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
//        initFragment(savedInstanceState);
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
        NewsMainFragment newsMainFragment = new NewsMainFragment();
        FrontNewsMainFragment frontNewsMainFragment = new FrontNewsMainFragment();
        VideoMainFragment videoMainFragment = new VideoMainFragment();
        ProfileMainFragment careMainFragment = new ProfileMainFragment();
        mFragmentList.add(0, newsMainFragment);
        mFragmentList.add(1, frontNewsMainFragment);
        mFragmentList.add(2, videoMainFragment);
        mFragmentList.add(3, careMainFragment);

        mCurrentFragmen = mFragmentList.get(0);

        // 初始化首次进入时的Fragment
        mFm = getSupportFragmentManager();
        FragmentTransaction transaction = mFm.beginTransaction();
//        transaction.add(R.id.fl_body, mCurrentFragmen, mFragmentTagList[0]);
        transaction.commitAllowingStateLoss();
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
//                SwitchTo(position);
                switchFragment(position,mFragmentList.get(position), mFragmentTagList[position]);
            }

            @Override
            public void onTabReselect(int position) {
            }
        });
    }


    /**
     * 初始化碎片
     */
//    private void initFragment(Bundle savedInstanceState) {
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        int currentTabPosition = 0;
//        if (savedInstanceState != null) {
//            newsMainFragment = (NewsMainFragment) getSupportFragmentManager().findFragmentByTag("newsMainFragment");
//            frontNewsMainFragment = (FrontNewsMainFragment) getSupportFragmentManager().findFragmentByTag("frontNewsMainFragment");
//            videoMainFragment = (VideoMainFragment) getSupportFragmentManager().findFragmentByTag("videoMainFragment");
//            careMainFragment = (ProfileMainFragment) getSupportFragmentManager().findFragmentByTag("careMainFragment");
//            currentTabPosition = savedInstanceState.getInt(AppConstant.HOME_CURRENT_TAB_POSITION);
//        } else {
//            newsMainFragment = new NewsMainFragment();
//            frontNewsMainFragment = new FrontNewsMainFragment();
//            videoMainFragment = new VideoMainFragment();
//            careMainFragment = new ProfileMainFragment();
//        }
//        tabLayout.setCurrentTab(currentTabPosition);
//    }

    /**
     * 切换
     */
    private void switchFragment(int position, Fragment to, String tag){
        ILog.e(TAG1, "主页菜单position: " + position);
        StatusBarCompatUtils.setDarkMode(MainActivity_temp2.this);
        if(mCurrentFragmen != to){
            FragmentTransaction transaction = mFm.beginTransaction();
            if(!to.isAdded()){
                // 没有添加过:
                // 隐藏当前的，添加新的，显示新的
//                transaction.hide(mCurrentFragmen).add(R.id.fl_body, to, tag).show(to);

//                Random random = new Random();
//                int color = 0xff000000 | random.nextInt(0xffffff);
//                int color1 = getResources().getColor(R.color.white);
//                if (tag.equals("OneFragment")){
//
//                }else if (tag.equals("TwoFragment")){
//                    StatusBarCompatUtils.setStatusTextColor(true, MainActivity.this);
//                    FrontNewsMainFragment to1 = (FrontNewsMainFragment) to;
//                    if (to1 != null) {
//                        to1.setTvTitleBackgroundColor(color1);
//                        StatusBarCompatUtils.setLightMode(MainActivity.this);
//                    }
//                }else if (tag.equals("ThreeFragment")){
//                    StatusBarCompatUtils.setStatusTextColor(true, MainActivity.this);
//                    VideoMainFragment to1 = (VideoMainFragment) to;
//                    if (to1 != null) {
//                        to1.setTvTitleBackgroundColor(color1);
//                        StatusBarCompatUtils.setLightMode(MainActivity.this);
//                    }
//                }else if (tag.equals("FourFragment")){
//                    StatusBarCompatUtils.setStatusTextColor(true, MainActivity.this);
//                    ProfileMainFragment to1 = (ProfileMainFragment) to;
//                    if (to1 != null) {
//                        to1.setTvTitleBackgroundColor(color1);
//                        StatusBarCompatUtils.setLightMode(MainActivity.this);
//                    }
//                }
            }else{
                // 隐藏当前的，显示新的
                transaction.hide(mCurrentFragmen).show(to);
            }
            mCurrentFragmen = to;
            transaction.commitAllowingStateLoss();
        }
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


    /**
     * 当activity非正常销毁时被调用
     * @param outState
     * @param outPersistentState
     */
    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        //奔溃前保存位置
        ILog.e(TAG1, "onSaveInstanceState进来了1");
        if (tabLayout != null) {
            ILog.e(TAG1, "onSaveInstanceState进来了2");
            outState.putInt(AppConstant.HOME_CURRENT_TAB_POSITION, tabLayout.getCurrentTab());
        }
        // 重置Fragment，防止当内存不足时导致Fragment重叠
        updateFragment(outState);

    }

    /**
     * 重置Fragment
     * @param outState
     */
    private void updateFragment(Bundle outState) {
        mFm = getSupportFragmentManager();
        if(outState == null){
            FragmentTransaction transaction = mFm.beginTransaction();
            NewsMainFragment oneFragment = new NewsMainFragment();
            mCurrentFragmen = oneFragment;
//            transaction.add(R.id.fl_body, oneFragment, mFragmentTagList[0]).commitAllowingStateLoss();
        }else{
            // 通过tag找到fragment并重置
            NewsMainFragment newsMainFragment = (NewsMainFragment) mFm.findFragmentByTag(mFragmentTagList[0]);
            FrontNewsMainFragment frontNewsMainFragment = (FrontNewsMainFragment) mFm.findFragmentByTag(mFragmentTagList[1]);
            VideoMainFragment videoMainFragment = (VideoMainFragment) mFm.findFragmentByTag(mFragmentTagList[2]);
            ProfileMainFragment careMainFragment = (ProfileMainFragment) mFm.findFragmentByTag(mFragmentTagList[3]);
            mFm.beginTransaction().show(newsMainFragment).hide(frontNewsMainFragment).hide(videoMainFragment).hide(careMainFragment);
        }
    }


}

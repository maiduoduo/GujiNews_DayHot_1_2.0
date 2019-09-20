package com.cnews.guji.smart.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.cnews.guji.smart.R;
import com.cnews.guji.smart.app.BaseApplication;
import com.cnews.guji.smart.base.AppConstant;
import com.cnews.guji.smart.base.BaseActivity;
import com.cnews.guji.smart.helper.imageview.ExpandImageView;
import com.cnews.guji.smart.helper.timer.CountDownView;
import com.cnews.guji.smart.ui.model.source.NewsQuerySourceHelper;
import com.cnews.guji.smart.util.ImageLoaderUtils;
import com.cnews.guji.smart.util.statusbar.StatusBarCompatUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 启动页
 */
public class SplashActivity extends BaseActivity {
    @BindView(R.id.iv_logo)
    ImageView ivLogo;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.ImgSplash)
    ExpandImageView ImgSplash;
    @BindView(R.id.countDownView)
    CountDownView mCountDownView;
    private int duration = 3000;
//    private String url = "https://img2.epetbar.com/nowater/2019-02/18/21/eae4d5ed4c0ef7a6d2d9e4f7536385bf.gif";

    @Override
    public int getLayoutId() {
        return R.layout.act_splash;
    }

    @Override
    public void initPresenter() {
        AppConstant.COUNTDOWN_TIMER = true;
//        list.add(0, list.remove(3));
    }


    @Override
    protected void initView(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // 透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        startLoadGif();
        mCountDownView.start();
//        PropertyValuesHolder alpha = PropertyValuesHolder.ofFloat("alpha", 0.3f, 1f);
//        PropertyValuesHolder alpha1 = PropertyValuesHolder.ofFloat("alpha", 0.9f, 1f);
//        PropertyValuesHolder scaleX = PropertyValuesHolder.ofFloat("scaleX", 0.3f, 1f);
//        PropertyValuesHolder scaleX1 = PropertyValuesHolder.ofFloat("scaleX", 0.9f, 1f);
//        PropertyValuesHolder scaleY = PropertyValuesHolder.ofFloat("scaleY", 0.3f, 1f);
//        PropertyValuesHolder scaleY1 = PropertyValuesHolder.ofFloat("scaleY", 0.9f, 1f);
//        ObjectAnimator objectAnimator1 = ObjectAnimator.ofPropertyValuesHolder(tvName, alpha1, scaleX1, scaleY1);
//        ObjectAnimator objectAnimator2 = ObjectAnimator.ofPropertyValuesHolder(ivLogo, alpha1, scaleX1, scaleY1);
//
//        AnimatorSet animatorSet = new AnimatorSet();
//        animatorSet.playTogether(objectAnimator1, objectAnimator2);
//        animatorSet.setInterpolator(new AccelerateInterpolator());
//        animatorSet.setDuration(duration);
//        animatorSet.addListener(new Animator.AnimatorListener() {
//            @Override
//            public void onAnimationStart(Animator animator) {
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animator animator) {
//            }
//
//            @Override
//            public void onAnimationCancel(Animator animator) {
//
//            }
//
//            @Override
//            public void onAnimationRepeat(Animator animator) {
//
//            }
//        });
//        animatorSet.start();

    }

    private void startLoadGif() {
        if (AppConstant.START_GIF) {//GIF
            AppConstant.START_GIF = false;
            StatusBarCompatUtils.setDarkMode(SplashActivity.this);
            int splashGif = NewsQuerySourceHelper.getSplashGif();
            ImageLoaderUtils.displayGlideGif(mContext, ImgSplash, splashGif);
        } else {//静态图
            AppConstant.START_GIF = true;
            StatusBarCompatUtils.setLightMode(SplashActivity.this);
            ImgSplash.setImageURI(Uri.parse(AppConstant.RES_SOURCE + R.drawable.splash_quite_pick));
//            ImageLoaderUtils.displayGlideGif(mContext,ImgSplash,R.drawable.splash_quite_pick);

        }
    }

    @OnClick({
            R.id.llSplashPage
    })
    public void bindClick(View view) {
        switch (view.getId()) {
            case R.id.llSplashPage:
//                AppConstant.COUNTDOWN_TIMER = false;
//                MainActivity.startAction(SplashActivity.this);
//                finishThis();
                break;
        }
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        mCountDownView.setOnLoadingFinishListener(new CountDownView.OnLoadingFinishListener() {
            @Override
            public void finish() {
                if (AppConstant.COUNTDOWN_TIMER) {
                    //清除缓存
                    BaseApplication.frescoClearMem();
                    MainActivity.startAction(SplashActivity.this);
                    finishThis();
                }
            }
        });
    }

    @Override
    public void initBase() {

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

    /**
     * 监听返回键
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
//                moveTaskToBack(false);
                return false;//拦截事件
            case KeyEvent.KEYCODE_MENU:
                Log.i("main", "KeyEvent.KEYCODE_MENU");
                break;
            case KeyEvent.KEYCODE_HOME:
                Log.i("main", "KeyEvent.KEYCODE_HOME");
                // 收不到
                break;
            case KeyEvent.KEYCODE_APP_SWITCH:
                Log.i("main", "KeyEvent.KEYCODE_APP_SWITCH");
                // 收不到
                break;
            default:
                break;
        }
        return super.onKeyDown(keyCode, event);
    }
}

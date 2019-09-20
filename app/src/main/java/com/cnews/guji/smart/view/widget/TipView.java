package com.cnews.guji.smart.view.widget;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cnews.guji.smart.R;
import com.cnews.guji.smart.util.ScreenUtil;


/**
 * 顶部提示的View
 * @author JSYL-DCL
 */
public class TipView extends LinearLayout {

    private Context mContext;

    private int mBackGroundColor;
    private int mTextColor;
    private String mText;
    private int mTextSize;
    private TextView mTvTip;
    private TextView mTvDashLeft;
    private TextView mTvDashRight;

    //显示所停留的时间
    private int mStayTime = 3000;
    private boolean isShowing;
    private Handler mHandler = new Handler();

    public TipView(Context context) {
        this(context,null);
    }

    public TipView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TipView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mContext = context;

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TipView);
        mBackGroundColor = ta.getColor(R.styleable.TipView_tipBackgroundColor, Color.parseColor("#ffffff"));
        mTextColor = ta.getColor(R.styleable.TipView_tipTextColor, Color.parseColor("#666666"));
        mText = ta.getString(R.styleable.TipView_tipText);
        mTextSize = ta.getDimensionPixelSize(R.styleable.TipView_tipTextSize, ScreenUtil.sp2px(context, 12));
        ta.recycle();

        init();
    }

    private void init() {
        setGravity(Gravity.CENTER);
        setBackgroundColor(mBackGroundColor);
        mTvTip = new TextView(mContext);
        mTvTip.setGravity(Gravity.CENTER);
        mTvTip.getPaint().setTextSize(mTextSize);
        mTvTip.setTextColor(mTextColor);
        mTvTip.setText(mText);

        mTvDashLeft = new TextView(mContext);
        mTvDashRight = new TextView(mContext);

        //获取屏幕宽度
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();
        mTvDashLeft.setWidth(width/5);
        mTvDashRight.setWidth(width/5);
        mTvDashLeft.setHeight(500);
        mTvDashRight.setHeight(500);
        mTvDashLeft.setBackgroundColor(getResources().getColor(R.color.white));
        mTvDashRight.setBackgroundColor(getResources().getColor(R.color.white));
        addView(mTvDashLeft);
        addView(mTvTip);
        addView(mTvDashRight);
    }

    public void show(String content){
        if (TextUtils.isEmpty(content)){
            show();
            return;
        }
        mTvTip.setText(content);//设置内容
        show();
    }

    public void show(){
        if (isShowing){
            return;
        }

        isShowing = true;

        setVisibility(VISIBLE);

        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(mTvTip, "scaleX", 0, 1f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(mTvTip, "scaleY", 0, 1f);
        animatorSet.setDuration(1000);
//        animatorSet.play(scaleX).with(scaleY);
        animatorSet.playTogether(scaleX,scaleY);
        animatorSet.start();
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                       hide();
                    }
                },mStayTime);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
       });

        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mTvDashLeft, "translationX", 0, -300);
        ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(mTvDashRight, "translationX", 0, 300);
        objectAnimator.setDuration(1000);
        objectAnimator1.setDuration(1000);
        objectAnimator.start();
        objectAnimator1.start();

        objectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (View.VISIBLE == mTvDashLeft.getVisibility()){
                    mTvDashLeft.setVisibility(GONE);
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        objectAnimator1.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (View.VISIBLE == mTvDashRight.getVisibility()){
                    mTvDashRight.setVisibility(GONE);
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

    }

    /**隐藏，收起*/
    private void hide() {
        TranslateAnimation hideAnim = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f,Animation.RELATIVE_TO_SELF
                ,0.0f,Animation.RELATIVE_TO_SELF,-1.0f);

        hideAnim.setDuration(300);
        startAnimation(hideAnim);
        hideAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                setVisibility(GONE);
                isShowing = false;
                mTvTip.setText(mText); //重新设置回原来的内容
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}

package com.cnews.guji.smart.helper.refresh.qqmailrefresh;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;

import java.util.ArrayList;
import java.util.List;

/**
 * 滚动翻转刷新视图
 */
public class QQMailRefreshView extends View {
    public static final String TAG = "QQMailRefreshView";
    private static final int PLAY_STOP_ANIMATION = 0;
    private static final int PLAY_START_ANIMATION = 1;
    private int mWidth;
    private int mHeight;
    private final float MAX_CIRCLE_RADIUS = dp2px(getContext(), 6);
    private final float MIN_CIRCLE_RADIUS = dp2px(getContext(), 5);
    private final int MAX_PAINT_ALPHA = 255;
    private final int MIN_PAINT_ALPHA = 150;
    private final int MAX_CHANGE_WIDTH = dp2px(getContext(), 20);

    private Paint mPaint;
    private int mColors[] = new int[]{0xffffe464, 0xffef4a4a, 0xffceee88};
    private int DEFAULT_DURATION = 900;
    private List<Animator> animatorList = new ArrayList<>();
    private float mChangeWidth;
    private int playState;
    private float offset = dp2px(getContext(), 10);

    public QQMailRefreshView(Context context) {
        super(context);
        initView();
    }

    public QQMailRefreshView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public QQMailRefreshView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        initPaint();
        init();
    }

    private void init() {
        mChangeWidth = 0;
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < mColors.length; i++) {
            mPaint.setColor(mColors[i]);
            drawCirce(canvas, MAX_CHANGE_WIDTH * (i - 1) + mChangeWidth, mPaint);
        }

    }

    /**
     * 用来模拟运动的速率,中间快,边缘慢
     */
    private float getImitationTranslateX(float canvasTranslateX) {
//        return canvasTranslateX;

        if (canvasTranslateX >= -MAX_CHANGE_WIDTH / 2f && canvasTranslateX <= MAX_CHANGE_WIDTH / 2f) {
            return canvasTranslateX * 1.25f;
        } else if (canvasTranslateX > MAX_CHANGE_WIDTH / 2f) {
            return 0.75f * canvasTranslateX + MAX_CHANGE_WIDTH / 4f;
        } else {
            return 0.75f * canvasTranslateX - MAX_CHANGE_WIDTH / 4f;
        }
//        if (canvasTranslateX >= -MAX_CHANGE_WIDTH / 2f && canvasTranslateX <= MAX_CHANGE_WIDTH / 2f) {
//            return canvasTranslateX * 1.5f;
//        } else if (canvasTranslateX > MAX_CHANGE_WIDTH / 2f) {
//            return 0.5f * canvasTranslateX + MAX_CHANGE_WIDTH / 2f;
//        } else {
//            return 0.5f * canvasTranslateX - MAX_CHANGE_WIDTH / 2f;
//        }
    }

    /**
     * 通过数学计算得到的表达式,x代表变化的长度的值,根据变化的长度,计算出圆圈的半径
     * y = (M - N) / a * x + M (x < 0)
     * y = (N - M) / a * x + M (x > 0)
     */
    private float getFuncRadius(float canvasTranslateX) {
        if (canvasTranslateX < 0) {
            return (MAX_CIRCLE_RADIUS - MIN_CIRCLE_RADIUS) / MAX_CHANGE_WIDTH * canvasTranslateX + MAX_CIRCLE_RADIUS;
        } else {
            return (MIN_CIRCLE_RADIUS - MAX_CIRCLE_RADIUS) / MAX_CHANGE_WIDTH * canvasTranslateX + MAX_CIRCLE_RADIUS;
        }
    }

    /**
     * 通过数学计算得到的表达式,x代表变化的长度的值,根据变化的长度,计算出颜色的alpha值 ,跟上面的解析式是一样的
     * y = (M - N) / a * x + M (x < 0)
     * y = (N - M) / a * x + M (x > 0)
     */
    private int getFuncAlpha(float canvasTranslateX) {
        if (canvasTranslateX < 0) {
            return (int) (canvasTranslateX * (MAX_PAINT_ALPHA - MIN_PAINT_ALPHA) / MAX_CHANGE_WIDTH + MAX_PAINT_ALPHA);
        } else {
            return (int) (canvasTranslateX * (MIN_PAINT_ALPHA - MAX_PAINT_ALPHA) / MAX_CHANGE_WIDTH + MAX_PAINT_ALPHA);
        }
    }

    private void drawCirce(Canvas canvas, float canvasTranslateX, @NonNull Paint paint) {
        float radius;
        float imitationTranslateX;
        int alpha;
        if (canvasTranslateX >= MAX_CHANGE_WIDTH && canvasTranslateX <= MAX_CHANGE_WIDTH + offset) {
            radius = getFuncRadius(canvasTranslateX);
            alpha = getFuncAlpha(canvasTranslateX);
            canvasTranslateX = MAX_CHANGE_WIDTH;
            imitationTranslateX = getImitationTranslateX(canvasTranslateX);
        } else if (canvasTranslateX > MAX_CHANGE_WIDTH + offset && canvasTranslateX <= MAX_CHANGE_WIDTH + 2 * offset) {
            radius = getFuncRadius((MAX_CHANGE_WIDTH + offset) * 2 - canvasTranslateX);
            alpha = getFuncAlpha((MAX_CHANGE_WIDTH + offset) * 2 - canvasTranslateX);
            canvasTranslateX = -MAX_CHANGE_WIDTH;
            imitationTranslateX = getImitationTranslateX(canvasTranslateX);
        } else if (canvasTranslateX > MAX_CHANGE_WIDTH + 2 * offset && canvasTranslateX <= 3 * MAX_CHANGE_WIDTH + 2 * offset) {
            canvasTranslateX = canvasTranslateX - 2 * MAX_CHANGE_WIDTH - 2 * offset;
            imitationTranslateX = getImitationTranslateX(canvasTranslateX);
            radius = getFuncRadius(imitationTranslateX);
            alpha = getFuncAlpha(imitationTranslateX);
        } else {
            imitationTranslateX = getImitationTranslateX(canvasTranslateX);
            radius = getFuncRadius(imitationTranslateX);
            alpha = getFuncAlpha(imitationTranslateX);
        }
        paint.setAlpha(alpha);
        canvas.translate(imitationTranslateX, 0);
        canvas.drawCircle(mWidth / 2, mHeight / 2, radius, paint);
        canvas.translate(-imitationTranslateX, 0);
    }


    public void start() {
        if (playState == PLAY_STOP_ANIMATION) {
            clearAnimator();
            playState = PLAY_START_ANIMATION;
            startChangeWidthAnimation();
        }
    }

    public void stop() {
        if (playState == PLAY_START_ANIMATION) {
            playState = PLAY_STOP_ANIMATION;
            clearAnimator();
            init();
            invalidate();
        }
    }

    public void startChangeWidthAnimation() {
        ValueAnimator lengthAnimator = ValueAnimator.ofFloat(0, 2 * MAX_CHANGE_WIDTH + 2 * offset);
        lengthAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mChangeWidth = (float) animation.getAnimatedValue();
                invalidate();
            }
        });

        lengthAnimator.setDuration(DEFAULT_DURATION);
        lengthAnimator.setInterpolator(new LinearInterpolator());
        lengthAnimator.setRepeatCount(Integer.MAX_VALUE);
        animatorList.add(lengthAnimator);
        lengthAnimator.start();
    }

    private void clearAnimator() {
        for(int i = 0; i < animatorList.size(); i++) {
            Animator animator = animatorList.get(i);
            if (animator.isRunning()) {
                animator.cancel();
            }
        }
        animatorList.clear();
    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        Log.e(TAG, "mWidth == " + mWidth + ", mHeight == " + mHeight);
    }

    private int dp2px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

}

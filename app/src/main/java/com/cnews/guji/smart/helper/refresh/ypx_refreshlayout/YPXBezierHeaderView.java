package com.cnews.guji.smart.helper.refresh.ypx_refreshlayout;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import com.cnews.guji.smart.R;

/**
 * bezier头部绘制View
 * 博客主页：http://blog.csdn.net/qq_16674697?viewmode=list
 */
public class YPXBezierHeaderView extends View {
    float offset = 1.0f;
    float delayY;
    float lastY;
    int color = Color.parseColor("#999999");
    int drawableID = R.mipmap.refresh_q;
    OnAnimResetListener listener;
    ObjectAnimator anim;
    /**
     * 圆的画笔
     */
    private Paint circlePaint;
    /**
     * 画笔的路径
     */
    private Path circlePath;
    /**
     * 可拖动的最远距离
     */
    private int maxHeight;
    /**
     * 刷新图标
     */
    protected Bitmap bt;
    private float topCircleRadius;//默认上面圆形半径
    private float topCircleX;//默认上面圆形x
    private float topCircleY;//默认上面圆形y
    private float bottomCircleRadius;//默认上面圆形半径
    private float bottomCircleX;//默认下面圆形x
    private float bottomCircleY;//默认下面圆形y
    private float defaultRadius;//默认上面圆形半径


    public YPXBezierHeaderView(Context context) {
        this(context, null);
    }

    public YPXBezierHeaderView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public YPXBezierHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void setOnAnimResetListener(OnAnimResetListener lt) {
        this.listener = lt;
    }

    protected void init() {
        maxHeight = dp(300);
        topCircleX = getScreenWidth() / 2;
        topCircleY = dp(100);
        topCircleRadius = dp(30);
        resetBottomCricle();

        circlePath = new Path();
        circlePaint = new Paint();
        circlePaint.setAntiAlias(true);
        circlePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        circlePaint.setStrokeWidth(1);
        circlePaint.setColor(color);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastY = event.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                delayY = event.getRawY() - lastY;//滑动高度的偏移量
                if (delayY < 0) {
                    return true;
                }
                offset = 1 - delayY / maxHeight;//滑动的偏移量offset 范围 offset∈(1,0)
                //如果偏移量大于等于0.2的时候我们就让它开始重绘,
                // 这样可以给下面的圆留下一点可见半径,要不然offset为0的时候下面的圆就成了点
                if (offset >= 0.2) {
                    bottomCircleRadius = defaultRadius * offset;
                    bottomCircleX = topCircleX;
                    bottomCircleY = topCircleY + delayY;
                    topCircleRadius = (float) (defaultRadius * (Math.pow(offset, 1 / 3.0)));
                    postInvalidate();
                }
                break;
            case MotionEvent.ACTION_UP:
                animToReset(false);
                break;
            case MotionEvent.ACTION_CANCEL:
                animToReset(false);
                break;
        }
        return true;
    }

    public void setOffset(float offset) {
        this.offset = offset;
    }

    public void animToReset(boolean lock) {
        if (!lock) {
            anim = ObjectAnimator.ofFloat(offset, "ypx", 0.0F, 1.0F).setDuration(200);
            //使用反弹算法插值器,貌似没有什么太大的效果 - -!
            // anim.setInterpolator(new BounceInterpolator());
            anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    float cVal = (Float) animation.getAnimatedValue();
                    reset(cVal);
                }
            });
            anim.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {

                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    if (listener != null) {
                        listener.onReset();
                    }
                }

                @Override
                public void onAnimationCancel(Animator animator) {

                }

                @Override
                public void onAnimationRepeat(Animator animator) {

                }
            });
            anim.start();
        }
    }

    /**
     * 收回
     *
     * @param moffset 收回偏移量
     */
    public void reset(float moffset) {
        if (moffset < 0.2) {
            return;
        }
        offset = moffset;
        bottomCircleRadius = defaultRadius * offset;
        bottomCircleY = topCircleY + delayY * (1 - offset);
        topCircleRadius = (float) (defaultRadius * (Math.pow(offset, 1 / 3.0)));
        postInvalidate();

    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        drawPath();
        float left = topCircleX - topCircleRadius;
        float top = topCircleY - topCircleRadius;

        canvas.drawPath(circlePath, circlePaint);
        canvas.drawCircle(bottomCircleX, bottomCircleY, bottomCircleRadius, circlePaint);
        canvas.drawCircle(topCircleX, topCircleY, topCircleRadius, circlePaint);

        int btWidth = (int) topCircleRadius * 2 - dp(6);
        if ((btWidth) > 0) {
            bt = BitmapFactory.decodeResource(getResources(), drawableID);
            if (bt != null) {
                bt = Bitmap.createScaledBitmap(bt, btWidth, btWidth, true);
            }
            canvas.drawBitmap(bt, left + dp(3), top + dp(2), null);
            bt.recycle();
        }
        super.onDraw(canvas);

    }

    private void drawPath() {

        float p1X = topCircleX - topCircleRadius;
        float p1Y = topCircleY;
        float p2X = topCircleX + topCircleRadius;
        float p2Y = topCircleY;
        float p3X = bottomCircleX - bottomCircleRadius;
        float p3Y = bottomCircleY;
        float p4X = bottomCircleX + bottomCircleRadius;
        float p4Y = bottomCircleY;


        float anchorX = (p1X + p4X) / 2 - topCircleRadius * offset;
        float anchorY = (p1Y + p4Y) / 2;

        float anchorX2 = (p2X + p3X) / 2 + topCircleRadius * offset;
        float anchorY2 = (p2Y + p3Y) / 2;

        /* 画粘连体 */
        circlePath.reset();
        circlePath.moveTo(p1X, p1Y);
        circlePath.quadTo(anchorX, anchorY, p3X, p3Y);
        circlePath.lineTo(p4X, p4Y);
        circlePath.quadTo(anchorX2, anchorY2, p2X, p2Y);
        circlePath.lineTo(p1X, p1Y);

    }

    public float getTopCircleRadius() {
        return topCircleRadius;
    }

    public void setTopCircleRadius(float topCircleRadius) {
        this.topCircleRadius = topCircleRadius;
    }

    public float getTopCircleX() {
        return topCircleX;
    }

    public void setTopCircleX(float topCircleX) {
        this.topCircleX = topCircleX;
    }

    public float getTopCircleY() {
        return topCircleY;
    }

    public void setTopCircleY(float topCircleY) {
        this.topCircleY = topCircleY;
    }

    public float getBottomCircleRadius() {
        return bottomCircleRadius;
    }

    public void setBottomCircleRadius(float bottomCircleRadius) {
        this.bottomCircleRadius = bottomCircleRadius;
    }

    public float getBottomCircleX() {
        return bottomCircleX;
    }

    public void setBottomCircleX(float bottomCircleX) {
        this.bottomCircleX = bottomCircleX;
    }

    public float getBottomCircleY() {
        return bottomCircleY;
    }

    public void setBottomCircleY(float bottomCircleY) {
        this.bottomCircleY = bottomCircleY;
    }

    public int getMaxHeight() {
        return maxHeight;
    }

    public void setMaxHeight(int maxHeight) {
        this.maxHeight = maxHeight;
    }

    public float getDefaultRadius() {
        return defaultRadius;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
        circlePaint.setColor(color);
        invalidate();
    }

    public int getDrawableID() {
        return drawableID;
    }

    public void setDrawableID(int drawableID) {
        this.drawableID = drawableID;
        invalidate();
    }

    /**
     * 设置小圆为大圆的大小
     */
    public void resetBottomCricle() {
        bottomCircleRadius = topCircleRadius;
        bottomCircleY = topCircleY;
        bottomCircleX = topCircleX;
        defaultRadius = topCircleRadius;
    }

    public int dp(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dp, getContext().getResources().getDisplayMetrics());
    }

    /**
     * 获得屏幕宽度
     *
     * @return  屏幕宽度
     */
    public int getScreenWidth() {
        WindowManager wm = (WindowManager) getContext()
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    public interface OnAnimResetListener {
        void onReset();
    }

}

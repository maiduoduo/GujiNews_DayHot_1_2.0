package com.cnews.guji.smart.helper.horizontaldragmoreview.HorizontalScrollSlideView;

import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 横向滑动加载更多
 * https://blog.csdn.net/tangzhide/article/details/76549477
 */
public class PulToLeftViewGroup extends LinearLayout implements ViewTreeObserver.OnGlobalLayoutListener {

    OnPullToLeftListener mOnPullToLeftListener;

    public void setOnPullToLeftListener(OnPullToLeftListener leftListener) {
        this.mOnPullToLeftListener = leftListener;
    }

    public interface OnPullToLeftListener {
        /**
         * 松开手指去更新-可能需要动画在此函数实现
         */
        void onReleaseFingerToUpload();

        /**
         * 开始拉出查看更多-可能需要动画在此函数实现
         */
        void onStartToUpload();
    }

    /**
     * 动画时间
     */
    private static final long ANIM_TIME = 200;

    //recyclerview
    private View childView;

    // 用于记录正常的布局位置
    private Rect originalRect = new Rect();

    //滚动时，移动的view和位置
    private List<View> mMoveViews = new ArrayList<>();
    private List<Rect> mMoveRects = new ArrayList<>();

    // 在手指滑动的过程中记录是否移动了布局
    private boolean isMoved = false;

    // 如果按下时不能上拉和下拉， 会在手指移动时更新为当前手指的Y值
    private float startY;

    //阻尼
    private static final float OFFSET_RADIO = 0.6f;

    private boolean isRecyclerReuslt = false;

    public PulToLeftViewGroup(Context context) {
        super(context);
    }

    public PulToLeftViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PulToLeftViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 完成更新调用
     */
    public void completeToUpload() {

        postDelayed(new Runnable() {
            @Override
            public void run() {
                recoverLayout();
            }
        }, 1500);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

        originalRect.set(childView.getLeft(), childView.getTop(), childView.getRight(), childView.getBottom());
        for (int i = 0; i < mMoveViews.size(); i++) {
            final View v = mMoveViews.get(i);
            v.addOnLayoutChangeListener(new OnLayoutChangeListener() {
                @Override
                public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                    Rect rect = new Rect();
                    rect.set(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());
                    mMoveRects.add(rect);
                    v.removeOnLayoutChangeListener(this);
                }
            });
        }
    }

    /**
     * 加载布局后初始化,这个方法会在加载完布局后调用
     */
    @Override
    protected void onFinishInflate() {
        if (getChildCount() > 0) {
            for (int i = 0; i < getChildCount(); i++) {
                if (getChildAt(i) instanceof RecyclerView) {
                    if (childView == null) {
                        childView = getChildAt(i);
                    } else {
                        throw new RuntimeException("只能存在一个RecyclerView");
                    }
                }
            }
        }

        if (childView == null) {
            throw new RuntimeException("子容器中必须有一个RecyclerView");
        }
        //布局重绘监听，比如华为屏幕键盘可以弹出和隐藏，改变布局，加监听就可以虽键盘弹出关闭的变化而变化
        getViewTreeObserver().addOnGlobalLayoutListener(this);

        super.onFinishInflate();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onGlobalLayout() {
        requestLayout();
        getViewTreeObserver().removeOnGlobalLayoutListener(this);
    }

    /**
     * 跟随弹性移动的view
     *
     * @param view
     */
    public void setMoveViews(View view) {
        this.mMoveViews.add(view);
        requestLayout();
    }


    /**
     * 位置还原
     */
    private void recoverLayout() {
        if (!isMoved) {
            return;//如果没有移动布局，则跳过执行
        }
        for (int i = 0; i < mMoveViews.size(); i++) {
            if (i < mMoveRects.size()) {
                if (mMoveRects.get(i) != null) {
                    final int indexAt = i;
                    TranslateAnimation anims = new TranslateAnimation(mMoveViews.get(i).getRight(), mMoveRects.get(i).right, 0, 0);
                    anims.setDuration(ANIM_TIME);
                    mMoveViews.get(i).startAnimation(anims);
                    mMoveViews.get(indexAt).layout(
                            mMoveRects.get(indexAt).left,
                            mMoveRects.get(indexAt).top,
                            mMoveRects.get(indexAt).right,
                            mMoveRects.get(indexAt).bottom);

                }
            }
        }
        TranslateAnimation anim = new TranslateAnimation(childView.getRight() - originalRect.right, 0, 0, 0);
        anim.setDuration(ANIM_TIME);
        childView.startAnimation(anim);
        childView.layout(originalRect.left, originalRect.top, originalRect.right, originalRect.bottom);
        isMoved = false;

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;  //不拦截  直接传递给子的view
    }

    /**
     * 判断是否可以上拉
     *
     * @return
     */
    private boolean isCanPullUp() {
        final RecyclerView.Adapter adapter = ((RecyclerView) childView).getAdapter();
        if (null == adapter) {
            return true;
        }

        final int lastItemPosition = adapter.getItemCount() - 1;
        final int lastVisiblePosition = ((LinearLayoutManager) ((RecyclerView) childView)
                .getLayoutManager()).findLastVisibleItemPosition();

        if (lastVisiblePosition >= lastItemPosition) {
            final int childIndex = lastVisiblePosition - ((LinearLayoutManager) ((RecyclerView) childView)
                    .getLayoutManager()).findFirstVisibleItemPosition();
            final int childCount = ((RecyclerView) childView).getChildCount();
            final int index = Math.max(childIndex, childCount - 1);
            final View lastVisibleChild = ((RecyclerView) childView).getChildAt(index);
            if (lastVisibleChild != null) {
                return lastVisibleChild.getRight() <= childView.getRight() - childView.getLeft();
            }
        }
        return false;
    }

    /**
     * 事件分发
     */

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (childView == null) {
            return super.dispatchTouchEvent(ev);
        }
        boolean isTouchOutOfScrollView = ev.getX() >= originalRect.right || ev.getX() <= originalRect.left; //如果当前view的Y上的位置
        if (isTouchOutOfScrollView) {//如果不在view的范围内
            if (isMoved) {      //当前容器已经被移动
                recoverLayout();
            }
            return true;
        }
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                //记录按下时的X
                startY = ev.getX();
            case MotionEvent.ACTION_MOVE:
                float nowX = ev.getX();
                int scrollX = (int) (nowX - startY);
                if ((isCanPullUp() && scrollX < 0)) {

                    int offset = (int) (scrollX * OFFSET_RADIO);

                    childView.layout(originalRect.left + offset, originalRect.top, originalRect.right + offset, originalRect.bottom);


                    for (int i = 0; i < mMoveViews.size(); i++) {
                        if (i < mMoveRects.size()) {
                            if (mMoveViews.get(i) != null && mMoveRects.get(i) != null) {
                                mMoveViews.get(i).layout(mMoveRects.get(i).left + offset,
                                        mMoveRects.get(i).top,
                                        mMoveRects.get(i).right + offset,
                                        mMoveRects.get(i).bottom);
                            }
                        }
                    }

                    isMoved = true;
                    isRecyclerReuslt = false;

                    if (mOnPullToLeftListener != null) {
                        mOnPullToLeftListener.onStartToUpload();
                    }

                    return true;
                } else {
                    startY = ev.getX();
                    isMoved = false;
                    isRecyclerReuslt = true;
                    recoverLayout();
                    return super.dispatchTouchEvent(ev);
                }
            case MotionEvent.ACTION_UP:

                if (isMoved && mOnPullToLeftListener != null) {
                    //recoverLayout();
                    mOnPullToLeftListener.onReleaseFingerToUpload();
                }

                if (isRecyclerReuslt) {
                    return super.dispatchTouchEvent(ev);
                } else {
                    return true;
                }
            default:
                return true;
        }
    }

}

package com.cnews.guji.smart.helper.horizontaldragmoreview;

import android.animation.Animator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import static com.cnews.guji.smart.helper.horizontaldragmoreview.DragState.STATE_IDLE;


/**
 * <p>
 * 自定义横向水平拖拽加载更多View
 * <p>
 * 配合RecyclerView或者Viewpager实现图片浏览左滑查看更多功能(当然也可以配合任何的View进行使用)
 */
public class HorizontalDragMoreView extends FrameLayout {

    private static final int MIN_FOR_SCROLL = 3;//最小认定为滚动的尺度，否则认为是点击

    private View mHostView; //如RecyclerView或Viewpager等任何主View
    @NonNull
    private ILoadMore mILoadMore = new DefaultDragMoreView();
    private View mLoadMoreView;

    @DragState.EnumDragState
    private int mDragState = STATE_IDLE;
    private int mLoadMoreViewWidth = 0;

    private int mBreakPointX, mBreakPointY;//记录停顿位置
    private int mTempX, mTempY;//缓存上一次的位置

    private float mHostViewMoveDistance;//host view移动的距离
    private int mMoveMaxDistance;//可拉的最大距离

    @Nullable
    private ICallBack mCallBack;


    public HorizontalDragMoreView(@NonNull Context context) {
        this(context, null);
    }

    public HorizontalDragMoreView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HorizontalDragMoreView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);


    }


    /**
     * 设置自定义的load more View
     *
     * @param iLoadMore
     */
    @NonNull
    public HorizontalDragMoreView setLoadMoreView(@Nullable ILoadMore iLoadMore) {
        if (getChildCount() < 1) {
            throw new RuntimeException("HorizontalDragMoreView must has a child view,such as RecyclerView or Viewpager and so on");
        }
        mHostView = getChildAt(0);


        if (iLoadMore != null) {
            this.mILoadMore = iLoadMore;
        }

        this.mLoadMoreView = mILoadMore.getView(this);

        ((LayoutParams) mLoadMoreView.getLayoutParams()).gravity = Gravity.RIGHT;
        addView(mLoadMoreView);

//        if (getChildCount() > 2) {
//            throw new RuntimeException("HorizontalDragMoreView only permit to contain two child: hostView and loadMore View");
//        }

        return this;
    }

    /**
     * 设置加载更多的回调
     *
     * @param callBack
     */
    @NonNull
    public HorizontalDragMoreView setLoadMoreCallBack(@Nullable ICallBack callBack) {
        this.mCallBack = callBack;
        return this;
    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {

        if (mLoadMoreView == null) {
            return;
        }
        //获取loadMore view的宽度
        if (mLoadMoreViewWidth == 0) {
            mLoadMoreViewWidth = mLoadMoreView.getMeasuredWidth();
            mMoveMaxDistance = (int) (mLoadMoreViewWidth * 1.5);
        }

        if (mDragState == STATE_IDLE) {
            mLoadMoreView.setTranslationX(mLoadMoreViewWidth);
        }

        super.onLayout(changed, left, top, right, bottom);
    }


    /**
     * 当hostView左滑到尽头时需拦截触摸事件交由本View处理
     * 同时处理一下可能存在的滑动冲突，也就是检测到是水平滑动时才拦截
     *
     * @param ev
     * @return
     */
    @Override
    public boolean onInterceptTouchEvent(@NonNull MotionEvent ev) {
        int action = ev.getAction();
        int x = (int) ev.getX();
        int y = (int) ev.getY();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mTempX = x;
                mTempY = y;
                mBreakPointX = x;
                mBreakPointY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                //移动的距离
                int distanceX = x - mBreakPointX;
                int distanceY = y - mBreakPointY;

                mTempX = x;
                mTempY = y;
                mBreakPointX = x;
                mBreakPointY = y;

                //解决可能的滑动冲突
                boolean isInterceptTouchEvent = Math.abs(distanceX) > Math.abs(distanceY) && isLeftScrollToEnd(distanceX);
                if (isInterceptTouchEvent) {

                    getParent().requestDisallowInterceptTouchEvent(true);

                    mDragState = DragState.STATE_START_DRAG;
                    //drag start
                    mILoadMore.startDrag(mLoadMoreView);
                    return true;
                }
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                mBreakPointX = 0;
                mBreakPointY = 0;
                break;
        }

        return super.onInterceptTouchEvent(ev);
    }

    /**
     * hostView是否已经左滑到尽头
     *
     * @return
     */
    private boolean isLeftScrollToEnd(int scrollX) {
        if (mLoadMoreView == null) {
            return false;
        }
        //是否是往左滑
        boolean isLeftScroll = scrollX < -MIN_FOR_SCROLL;
        return !mHostView.canScrollHorizontally(Integer.MAX_VALUE) && isLeftScroll && mDragState != DragState.STATE_ON_LOADING;

    }


    @Override
    public boolean onTouchEvent(@NonNull MotionEvent event) {
        int action = event.getAction();
        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mTempX = x;
                mTempY = y;
                break;

            case MotionEvent.ACTION_MOVE:
                int distanceX = x - mTempX;
                mTempX = x;
                mTempY = y;

                float dragX = getDragMove(distanceX);
                mHostViewMoveDistance += dragX;

                processDrag(mHostViewMoveDistance);

                return true;

            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                mTempX = 0;
                mTempY = 0;
                mBreakPointX = 0;
                mBreakPointX = 0;

                processActionUp();
                return true;
        }
        return super.onTouchEvent(event);
    }


    /**
     * 处理拖动
     *
     * @param mHostViewMoveDistance
     */
    private void processDrag(float mHostViewMoveDistance) {
        //判断是否已拉超过设置的最大距离
        if (mHostViewMoveDistance <= -mMoveMaxDistance) {
            mHostViewMoveDistance = -mMoveMaxDistance;

        }
        //理论上不会大于0
        else if (mHostViewMoveDistance >= 0) {
            mHostViewMoveDistance = 0;
        }
        //正常情况
        else {
            mDragState = DragState.STATE_DRAGING;
            //拖拽中
            mILoadMore.onDraging(Math.abs(mHostViewMoveDistance / mLoadMoreViewWidth), mLoadMoreView);

        }
        //移动两块View
        if (mHostView != null && mLoadMoreView != null) {
            mHostView.setTranslationX(mHostViewMoveDistance);
            mLoadMoreView.setTranslationX(mLoadMoreViewWidth + mHostViewMoveDistance);
        }

    }

    /**
     * 处理手抬起时
     */
    private void processActionUp() {
        //拖拉距离未达到要求松手则反弹回去且不做任何操作
        if (mHostViewMoveDistance > -mLoadMoreViewWidth) {
            scrollBackToOrigin();

        } else {
            if (mHostView == null || mLoadMoreView == null) {
                return;
            }
            //开始加载更多
            mDragState = DragState.STATE_ON_LOADING;
            mILoadMore.onLoading(mLoadMoreView);

            //反弹到loadmore view宽度的位置
            animMove(-mHostViewMoveDistance - mLoadMoreViewWidth, -mLoadMoreViewWidth, 200, true, new AnimEndListener() {
                @Override
                public void animEnd() {
                    mHostViewMoveDistance = -mLoadMoreViewWidth;
                    if (mCallBack != null) {
                        mCallBack.loadMore();
                    }
                }
            });
        }
    }

    /**
     * 滚回原点
     */
    public void scrollBackToOrigin() {
        mILoadMore.startDrag(mLoadMoreView);
        //两块View均移回原位
        animMove(mLoadMoreViewWidth, 0, 200, false, new AnimEndListener() {
            @Override
            public void animEnd() {
                mDragState = STATE_IDLE;
                mHostViewMoveDistance = 0;
            }
        });
    }

    /**
     * 动画移动，主要是hostView和loadmore view配合一起滑动
     *
     * @param loadMoreViewMoveX loadMore view X轴移动到的位置
     * @param hostViewMoveX     hostView X轴移动到的位置
     * @param duration          动画时间
     * @param listener          动画结束回调
     */
    private void animMove(float loadMoreViewMoveX, int hostViewMoveX, int duration, boolean isLoadMoreMoveBy, @Nullable final AnimEndListener listener) {
        if (mHostView == null || mLoadMoreView == null) {
            return;
        }
        if (isLoadMoreMoveBy) {
            mLoadMoreView.animate().translationXBy(loadMoreViewMoveX).setDuration(duration).start();
        } else {
            mLoadMoreView.animate().translationX(loadMoreViewMoveX).setDuration(duration).start();
        }


        mHostView.animate().translationX(hostViewMoveX).setDuration(duration).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (listener != null) {
                    listener.animEnd();
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        }).start();
    }


    /**
     * 生成loadMoreView的移动距离（阻尼效果，拉的越大后阻尼越大）
     *
     * @return
     */
    public float getDragMove(int dragX) {

        return dragX * (1 - Math.abs(mHostViewMoveDistance / mMoveMaxDistance));
    }


    public interface AnimEndListener {
        void animEnd();
    }
}

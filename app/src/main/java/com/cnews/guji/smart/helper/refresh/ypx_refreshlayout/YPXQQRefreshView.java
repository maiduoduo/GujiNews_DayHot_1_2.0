package com.cnews.guji.smart.helper.refresh.ypx_refreshlayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.cnews.guji.smart.R;


/**
 * 功能: QQ下拉刷新
 * 博客主页：http://blog.csdn.net/qq_16674697?viewmode=list
 */
public class YPXQQRefreshView extends YPXRefreshBaseView {
    LinearLayout ll_ok;
    LinearLayout ll_refresh;
    ImageView iv_ok;
    TextView tv_ok;
    ProgressBar pb_refresh;
    YPXBezierHeaderView bezierView;

    private float topCircleRadius;//默认上面圆形半径
    private float topCircleX;//默认上面圆形x
    private float topCircleY;//默认上面圆形y

    private int refreshMaxHeight;//刷新小球可滑动的最大距离
    boolean bezierLock = false;//小球动画锁

    public YPXQQRefreshView(Context context) {
        super(context);
    }

    public YPXQQRefreshView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected View getRefreshHeaderView() {
        refreshView = LayoutInflater.from(mContext).inflate(R.layout.a_layout_qq_header, null);
        ll_ok = (LinearLayout) refreshView.findViewById(R.id.ll_ok);
        ll_refresh = (LinearLayout) refreshView.findViewById(R.id.ll_refresh);
        bezierView = (YPXBezierHeaderView) refreshView.findViewById(R.id.bview);
        iv_ok = (ImageView) refreshView.findViewById(R.id.iv_ok);
        tv_ok = (TextView) refreshView.findViewById(R.id.tv_ok);
        pb_refresh = (ProgressBar) refreshView.findViewById(R.id.pb_refresh);
        bezierView.setOnAnimResetListener(new YPXBezierHeaderView.OnAnimResetListener() {
            @Override
            public void onReset() {
                animRefreshView(200, TAKEBACK_REFRESH);
                if (refreshListener != null && refreshState == REFRESH_BY_RELEASE) {
                    refreshing();
                    refreshListener.onRefresh();
                    setRefreshState(REFRESHING);
                }
            }
        });
        resetData();
        return refreshView;
    }

    @Override
    protected void doMovement(LinearLayout.LayoutParams lp, int lastTop) {
        if (lastTop < 0) {//下拉刷新状态
            lp.topMargin = lastTop;
            lp.height = -refreshTargetTop;
            pullDownToRefresh();
        } else {//松开刷新状态改为下拉贝塞尔小球
            lp.topMargin = 0;
            lp.height = lastTop - refreshTargetTop;
            float offset = 1 - (lastTop * 1.0f) / refreshMaxHeight;//1~0
            if (offset >= 0.2) {//没有触发小球最大拉动距离
                bezierView.setBottomCircleY(bezierView.getTopCircleY() + (lastTop));
                bezierView.setBottomCircleRadius(bezierView.getDefaultRadius() * offset);
                bezierView.setOffset(offset);
                bezierView.setTopCircleRadius((float) (bezierView.getDefaultRadius() * (Math.pow(offset, 1 / 3.0))));
            } else {//小球爆炸收回,触发刷新
                if (!bezierLock && takeBackState != TAKEBACK_ALL) {
                    bezierView.animToReset(false);
                    refreshState = REFRESH_BY_RELEASE;//松开刷新状态
                    bezierLock = true;
                }
            }
            bezierView.postInvalidate();
        }
    }

    @Override
    protected void doMoveUp(LinearLayout.LayoutParams lp) {
        bezierView.setTopCircleRadius(topCircleRadius);
        bezierView.resetBottomCricle();
        animRefreshView(500, TAKEBACK_ALL);
    }

    private void resetData() {
        refreshMaxHeight = -refreshTargetTop;
        topCircleX = getScreenWidth() / 2;
        topCircleY = -refreshTargetTop / 2;
        topCircleRadius = -refreshTargetTop / 4;
        bezierView.setTopCircleX(topCircleX);
        bezierView.setTopCircleY(topCircleY);
        bezierView.setTopCircleRadius(topCircleRadius);
        bezierView.setMaxHeight(refreshMaxHeight);
        bezierView.resetBottomCricle();
    }


    @Override
    public void resetRefreshView() {
        super.resetRefreshView();
        bezierLock = false;
        bezierView.setTopCircleX(topCircleX);
        bezierView.setTopCircleY(topCircleY);
        bezierView.setTopCircleRadius(topCircleRadius);
        bezierView.setMaxHeight(refreshMaxHeight);
        bezierView.resetBottomCricle();
    }

    @Override
    public void pullDownToRefresh() {
        super.pullDownToRefresh();
        ll_refresh.setVisibility(View.VISIBLE);
        ll_ok.setVisibility(View.GONE);
        pb_refresh.setVisibility(View.GONE);
        bezierView.setVisibility(View.VISIBLE);
    }

    @Override
    public void pullUpToRefresh() {
        super.pullUpToRefresh();
    }

    @Override
    public void refreshing() {
        super.refreshing();
        ll_ok.setVisibility(View.GONE);
        bezierView.setVisibility(View.GONE);
        pb_refresh.setVisibility(View.VISIBLE);

    }

    @Override
    public void refreshFailed() {
        super.refreshFailed();
        ll_refresh.setVisibility(View.GONE);
        ll_ok.setVisibility(View.VISIBLE);
        tv_ok.setText("刷新失败");
        iv_ok.setImageDrawable(getResources().getDrawable(R.mipmap.pull_failure));
    }

    @Override
    public void refreshOK() {
        super.refreshOK();
        ll_refresh.setVisibility(View.GONE);
        ll_ok.setVisibility(View.VISIBLE);
        tv_ok.setText("刷新成功");
        iv_ok.setImageDrawable(getResources().getDrawable(R.mipmap.pull_ok));
    }

    public float getTopCircleY() {
        return topCircleY;
    }

    public void setTopCircleY(float topCircleY) {
        this.topCircleY = topCircleY;
        bezierView.setTopCircleY(topCircleY);
        bezierView.resetBottomCricle();
    }

    public float getTopCircleX() {
        return topCircleX;

    }

    public void setTopCircleX(float topCircleX) {
        this.topCircleX = topCircleX;
        bezierView.setTopCircleX(topCircleX);
        bezierView.resetBottomCricle();
    }

    public float getTopCircleRadius() {
        return topCircleRadius;
    }

    public void setTopCircleRadius(float topCircleRadius) {
        this.topCircleRadius = topCircleRadius;
        bezierView.setTopCircleRadius(topCircleRadius);
        bezierView.resetBottomCricle();
    }

    public int getRefreshMaxHeight() {
        return refreshMaxHeight;
    }

    public void setRefreshMaxHeight(int refreshMaxHeight) {
        this.refreshMaxHeight = refreshMaxHeight;
        bezierView.setMaxHeight(refreshMaxHeight);
    }

    public void setRefreshIcon(int drawableID) {
        bezierView.setDrawableID(drawableID);
    }

    public void setRefreshColor(int color) {
        bezierView.setColor(color);
    }

    public void setRefreshViewHeight(int height) {
        lastTop = -height;
        refreshTargetTop = -height;
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT, this.refreshTargetTop);
        refreshView.setLayoutParams(lp);
        resetData();
    }
}

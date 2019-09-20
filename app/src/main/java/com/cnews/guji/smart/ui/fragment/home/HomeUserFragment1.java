package com.cnews.guji.smart.ui.fragment.home;

import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.NestedScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cnews.guji.smart.R;
import com.cnews.guji.smart.base.BaseFragment;
import com.cnews.guji.smart.util.ILog;
import com.cnews.guji.smart.util.statusbar.StatusBarCompatUtils;

import butterknife.BindView;

/**
 * des:图片首页
 */
public class HomeUserFragment1 extends BaseFragment {
    private static int SIZE = 20;
    private int mStartPage = 1;
    private static final String TAG = HomeUserFragment1.class.getSimpleName();
    @BindView(R.id.topbar)
    LinearLayout topbar;
    @BindView(R.id.view2)
    TextView view2;
    @BindView(R.id.tl_tabs)
    TabLayout mtabs;
    @BindView(R.id.scrollView)
    NestedScrollView scrollView;
    @Override
    protected int getLayoutResource() {
        return R.layout.frag_home_index1;
    }

    @Override
    public void intBase() {
//        needTint();
    }

    @Override
    public void initPresenter() {
    }

    @Override
    public void initView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0+
            StatusBarCompatUtils.setTintStatusBg(getActivity(), topbar, scrollView);
        }else {
            StatusBarCompatUtils.setTintStatusBg(getActivity(), topbar, scrollView, view2);
        }

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            ILog.e(TAG,"setUserVisibleHint22:"+isVisibleToUser);
            // 相当于onResume()方法--获取焦点
        } else {
            ILog.e(TAG,"else setUserVisibleHint22:"+isVisibleToUser);
            // 相当于onpause()方法---失去焦点
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (hidden) {
            ILog.e(TAG,"hidden22:"+hidden);
            //相当于Fragment的onPause()
        } else {
            ILog.e(TAG,"else hidden22:"+hidden);
            // 相当于Fragment的onResume()
        }
    }

}

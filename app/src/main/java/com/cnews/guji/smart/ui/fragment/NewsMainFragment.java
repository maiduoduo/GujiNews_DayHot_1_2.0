package com.cnews.guji.smart.ui.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.cnews.guji.smart.R;
import com.cnews.guji.smart.app.BaseApplication;
import com.cnews.guji.smart.base.AppConstant;
import com.cnews.guji.smart.base.BaseFragment;
import com.cnews.guji.smart.ui.activity.NewsChannelManagerActivity;
import com.cnews.guji.smart.ui.adapter.FmPagerAdapter;
import com.cnews.guji.smart.ui.adapter.MyViewPageAdapter;
import com.cnews.guji.smart.ui.fragment.home.HomeHotTopFragment;
import com.cnews.guji.smart.ui.fragment.home.HomeOtherPublicFragment;
import com.cnews.guji.smart.ui.model.HomeNewsTabBean;
import com.cnews.guji.smart.ui.model.source.NewsSource;
import com.cnews.guji.smart.util.ILog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * des:咕唧首页
 */
public class NewsMainFragment extends BaseFragment implements ViewPager.OnPageChangeListener {
    private static final String TAG1 = "mainFragment";
    private static int SIZE = 20;
    private int mStartPage = 1;
    private static final String TAG = NewsMainFragment.class.getSimpleName();
    private FmPagerAdapter pagerAdapter;
    private List<String> tabList = new ArrayList<>();
    private ArrayList<Fragment> fragmentList = new ArrayList<Fragment>();
    @BindView(R.id.tablayout)
    TabLayout mTabLayout;
    @BindView(R.id.viewpager)
    ViewPager mViewpager;

    //    @BindView(R.id.fake_status_bar)
//    View mFakeStatusBar;
    public static NewsMainFragment getInstance(String title) {
        NewsMainFragment sf = new NewsMainFragment();
//        sf.mTitle = title;
        return sf;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.frag_home;
    }

    @Override
    public void intBase() {
    }

    @Override
    public void initPresenter() {
    }

    @Override
    public void initView() {
        init();
    }

    @Override
    protected void initData() {

    }


    private void init() {
//        mFakeStatusBar.setBackgroundColor(getResources().getColor(R.color.transparent));
        if (tabList != null) tabList.clear();
        if (fragmentList != null) fragmentList.clear();
        List<HomeNewsTabBean.Data.My> tabCurrentShowData = NewsSource.getTabCurrentShowData(0, getActivity());
        if (tabCurrentShowData != null && tabCurrentShowData.size() > 0) {
            for (int i = 0; i < tabCurrentShowData.size(); i++) {
//                if (0 == i)
//                    fragmentList.add(HomeHotTopFragment.getInstance(tabCurrentShowData.get(0).name,0));
//                else if (3==i)
//                    fragmentList.add(HomeYLFragment.getInstance(tabCurrentShowData.get(i).name,i));
//                else if (6==i)
//                    fragmentList.add(HomeGXFragment.getInstance(tabCurrentShowData.get(i).name,i));
//                else
//                    fragmentList.add(HomeOtherPublicFragment.getInstance(tabCurrentShowData.get(i).name,i));
                if (0 == i)
                    fragmentList.add(HomeHotTopFragment.getInstance(tabCurrentShowData.get(0).name, 0));
                else
                    fragmentList.add(HomeOtherPublicFragment.getInstance(tabCurrentShowData.get(i).name, i));
                //装载标签数据
                tabList.add(tabCurrentShowData.get(i).name);
            }
        }
        try {
            MyViewPageAdapter myViewPageAdapter = new MyViewPageAdapter(getChildFragmentManager(), tabList, fragmentList);
            mViewpager.setAdapter(myViewPageAdapter);
            mViewpager.setOffscreenPageLimit(3);//设置ViewPage缓存界面数
            mTabLayout.setupWithViewPager(mViewpager);
            mTabLayout.getTabAt(0).select(); //默认选中某项放在加载viewpager之后
            mTabLayout.setTabsFromPagerAdapter(myViewPageAdapter);
            for (int i = 0; i < myViewPageAdapter.getCount(); i++) {
                TabLayout.Tab tab = mTabLayout.getTabAt(i);//获得每一个tab
                tab.setCustomView(R.layout.view_tab);//给每一个tab设置view
                TextView textView = (TextView) tab.getCustomView().findViewById(R.id.tab_text);
                if (i == 0) {
                    textView.setSelected(true);//第一个tab被选中
                    textView.setTextSize(AppConstant.HOME_TAB_SELECTED_SIZE);
                    textView.setGravity(Gravity.TOP);
                } else {
                    textView.setSelected(false);
                    textView.setTextSize(AppConstant.HOME_TAB_UNSELECTED_SIZE);
                    textView.setGravity(Gravity.TOP);
                }
                textView.setText(tabList.get(i));//设置tab上的文字
                //设置字体
                textView.setTypeface(BaseApplication.getsInstance().getFangZhengSong3());
            }
            for (int i = 0; i < fragmentList.size(); i++) {
                swithOtherPages(i);
            }
            HomeHotTopFragment fragment = (HomeHotTopFragment) fragmentList.get(0);
            fragment.setCurrentPageType(0);
        }catch (Exception e){

        }
    }

    @OnClick({R.id.ibnAddChannel})
    public void bindViewClick(View view) {
        switch (view.getId()) {
            case R.id.ibnAddChannel://频道
                NewsChannelManagerActivity.startAction(getContext());
                break;
        }
    }


    @Override
    protected void initListener() {
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewpager.setCurrentItem(tab.getPosition());
                TextView viewById = (TextView) tab.getCustomView().findViewById(R.id.tab_text);
                viewById.setSelected(true);
                viewById.setTextSize(AppConstant.HOME_TAB_SELECTED_SIZE);
                if (tab.getPosition() == 0) {
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                TextView viewById = (TextView) tab.getCustomView().findViewById(R.id.tab_text);
                viewById.setSelected(false);
                viewById.setTextSize(AppConstant.HOME_TAB_UNSELECTED_SIZE);
                if (tab.getPosition() == 0) {
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        mViewpager.addOnPageChangeListener(this);
    }


    /**
     * 界面滑动
     *
     * @param position
     * @param positionOffset
     * @param positionOffsetPixels
     */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    /**
     * 界面滑动停止选中界面
     *
     * @param position
     */
    @Override
    public void onPageSelected(int position) {
        //页签选中
        mTabLayout.getTabAt(position).select();
        swithOtherPages(position);
    }

    private void swithOtherPages(int position) {
        if (0 == position) {
            HomeHotTopFragment fragment = (HomeHotTopFragment) fragmentList.get(0);
            fragment.setCurrentPageType(0);
        } else {
            HomeOtherPublicFragment fragment = (HomeOtherPublicFragment) fragmentList.get(position);
            fragment.setCurrentPageType(position);
        }


//        if (0 == position) {
//            HomeHotTopFragment fragment = (HomeHotTopFragment) fragmentList.get(0);
//            fragment.setCurrentPageType(0);
//        }else if (3==position) {
//            HomeYLFragment fragment = (HomeYLFragment) fragmentList.get(3);
//            fragment.setCurrentPageType(3);
//        }else if (6==position) {
//            HomeGXFragment fragment = (HomeGXFragment) fragmentList.get(6);
//            fragment.setCurrentPageType(6);
//        }else {
//            HomeOtherPublicFragment fragment = (HomeOtherPublicFragment) fragmentList.get(position);
//            fragment.setCurrentPageType(position);
//        }
    }

    /**
     * 界面滑动状态改变
     *
     * @param state
     */
    @Override
    public void onPageScrollStateChanged(int state) {

    }


    private View getTabView(int i) {
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.view_tab, null);
//        TextView viewById = (TextView) inflate.findViewById(R.id.ivTabBg);
//        viewById.setTextSize(30);
//        viewById.setTextColor(getResources().getColor(R.color.white));
        return inflate;
    }

    private View getTabViewUn(int i) {
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.view_tab, null);
//        TextView viewById = (TextView) inflate.findViewById(R.id.ivTabBg);
//        viewById.setTextSize(10);
//        viewById.setTextColor(getResources().getColor(R.color.white));
        return inflate;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden){//展示，可见
            ILog.e(TAG1,"onHiddenChanged newsmain:"+hidden);
        }else {
            ILog.e(TAG1,"[else]onHiddenChanged newsmain:"+hidden);
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser){//展示，可见
            ILog.e(TAG1,"isVisibleToUser newsmain:"+isVisibleToUser);
        }else {
            ILog.e(TAG1,"[else]isVisibleToUser newsmain:"+isVisibleToUser);
        }
    }

}

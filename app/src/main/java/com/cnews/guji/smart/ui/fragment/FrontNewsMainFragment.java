package com.cnews.guji.smart.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatImageView;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;

import com.cnews.guji.smart.R;
import com.cnews.guji.smart.app.BaseApplication;
import com.cnews.guji.smart.base.AppConstant;
import com.cnews.guji.smart.base.BaseFragment;
import com.cnews.guji.smart.common.bean.AppNettyData;
import com.cnews.guji.smart.common.bean.CategoryModel;
import com.cnews.guji.smart.common.bean.ShareBean;
import com.cnews.guji.smart.helper.righttopmenu.MenuItem;
import com.cnews.guji.smart.helper.righttopmenu.TopRightMenu;
import com.cnews.guji.smart.helper.textview.ColorFlipPagerTitleView;
import com.cnews.guji.smart.helper.textview.ScrollTextView;
import com.cnews.guji.smart.ui.activity.GJSettingActivity;
import com.cnews.guji.smart.ui.adapter.MyFragmentPagerAdapter;
import com.cnews.guji.smart.ui.model.source.NewsSource;
import com.cnews.guji.smart.util.ILog;
import com.cnews.guji.smart.util.SharePrefUtil;
import com.cnews.guji.smart.util.ToastUitl;
import com.cnews.guji.smart.util.statusbar.StatusBarCompatUtils;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * des:T
 * 要闻页
 */
public class FrontNewsMainFragment extends BaseFragment {
    private final String TAG1 = FrontNewsMainFragment.this.getClass().getSimpleName();
    private final String TAG2 = "mainFragment";
    TopRightMenu mTopRightMenu;
    private String paomadeng;
    @BindView(R.id.iv_menu)
    AppCompatImageView iv_menu;
    @BindView(R.id.fake_status_bar)
    View mFakeStatusBar;
    @BindView(R.id.iv_search)
    AppCompatImageView iv_search;
    @BindView(R.id.ll_magic_indicator)
    LinearLayout llMagicIndicator;
    @BindView(R.id.magic_indicator)
    MagicIndicator magicIndicator;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.scrollTextView)
    ScrollTextView scrollTextView;
    List<CategoryModel> modelList = new ArrayList<CategoryModel>();
    private List<Fragment> mFragmentList = new ArrayList<>();
    private ShareBean shareBean;
    private AppNettyData appData;

    public static FrontNewsMainFragment getInstance(String title) {
        FrontNewsMainFragment sf = new FrontNewsMainFragment();
//        sf.mTitle = title;
        return sf;
    }
    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_front_news;
    }

    @Override
    public void intBase() {
    }

    @Override
    public void initPresenter() {
    }

    @Override
    public void initView() {
        int color1 = getResources().getColor(R.color.white);
        setTvTitleBackgroundColor(color1);
        StatusBarCompatUtils.setLightMode(getActivity());
    }


    @Override
    protected void initData() {
        initJson();
        initPageView();
        initMagicIndicator7();
    }

    @Override
    protected void initListener() {
    }

    @OnClick({
            R.id.iv_menu
            , R.id.iv_search
    })
    public void bindViewClick(View view) {
        switch (view.getId()) {
            case R.id.iv_menu:
                initPopupWindow();
                break;
            case R.id.iv_search:
                ToastUitl.showShort("搜索");
//                startActivity(new Intent(getActivity(), MovieSearchActivity.class));
                break;
        }
    }

    private void initJson() {
        appData = SharePrefUtil.getObject(AppConstant.JSON_API_DATA, AppNettyData.class);
        if (appData != null) {
            shareBean = appData.share;
        }
        modelList.clear();
        modelList.addAll(NewsSource.getFrontNewsCategory(getActivity()));
    }

    private void initPageView() {
        if (!TextUtils.isEmpty(paomadeng)) {
            scrollTextView.setVisibility(View.VISIBLE);
            scrollTextView.setText(paomadeng);
        }

        List<String> channelNames = new ArrayList<>();

        for (CategoryModel model : modelList) {
            channelNames.add(model.title);
        }
        if (modelList != null) {
            setNewsList(modelList, channelNames);
            setViewPager(channelNames);
        }

    }

    /**
     * 设置数据列表
     *
     * @param modelList
     * @param channelNames
     */
    private void setNewsList(List<CategoryModel> modelList, List<String> channelNames) {
        mFragmentList.clear();
        for (int i = 0; i < modelList.size(); i++) {
            if (i == 0) {
                FrontNewsMixMainFragment frontNewsMixMainFragment = new FrontNewsMixMainFragment();
                Bundle bundle = new Bundle();
                frontNewsMixMainFragment.setArguments(bundle);
                mFragmentList.add(frontNewsMixMainFragment);
                channelNames.add(modelList.get(i).title);
            } else if (i == 1) {
                MineFrontNewsFragment mineCategoryFragment = new MineFrontNewsFragment();
                Bundle bundle = new Bundle();
                mineCategoryFragment.setArguments(bundle);
                mFragmentList.add(mineCategoryFragment);
                channelNames.add(modelList.get(i).title);
            }else {
                MineFrontNewsFragment movieCategoryFragment = new MineFrontNewsFragment();
                Bundle bundle = new Bundle();
                movieCategoryFragment.setArguments(bundle);
                mFragmentList.add(movieCategoryFragment);
                channelNames.add(modelList.get(i).title);
            }
        }

//        for (CategoryModel model : modelList) {
//            MineFrontNewsFragment newsListFragment = createListFragments(model);
//            mFragmentList.add(newsListFragment);
//            channelNames.add(model.title);
//        }
    }

    /**
     * 初始化ViewPager
     *
     * @param channelNames
     */
    private void setViewPager(List<String> channelNames) {
        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getChildFragmentManager(), channelNames, mFragmentList);
        mViewPager.setOffscreenPageLimit(mFragmentList.size());
        mViewPager.setAdapter(adapter);
    }


    /**
     * 初始化右上角弹窗
     */
    private void initPopupWindow() {

        mTopRightMenu = new TopRightMenu(getActivity());
        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(new MenuItem(R.drawable.ic_search, "分享app"));
        menuItems.add(new MenuItem(R.drawable.ic_search, "加入官方群"));
        menuItems.add(new MenuItem(R.drawable.ic_search, "设置"));

        mTopRightMenu
                .showIcon(false)
                .dimBackground(true)
                .needAnimationStyle(true)
                .setAnimationStyle(R.style.TRM_ANIM_STYLE)
                .addMenuList(menuItems)
                .setOnMenuItemClickListener(new TopRightMenu.OnMenuItemClickListener() {
                    @Override
                    public void onMenuItemClick(int position) {
                        switch (position) {
                            case 0:
                                shareApp();
                                break;
                            case 1:
                                if (!TextUtils.isEmpty(AppConstant.QQ_KEY)) {
                                    BaseApplication.joinQQGroup(getActivity(), AppConstant.QQ_KEY);//加入QQ群
                                }
                                break;
                            case 2:
                                startActivity(new Intent(mActivity, GJSettingActivity.class));
                                break;
                        }


                    }
                })
                .showAsDropDown(iv_menu, -iv_menu.getWidth() - 10, -10);

    }


    private void initMagicIndicator7() {
        magicIndicator.setBackgroundColor(getResources().getColor(R.color.white));
        CommonNavigator commonNavigator7 = new CommonNavigator(getActivity());
        commonNavigator7.setScrollPivotX(0.65f);
        commonNavigator7.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return modelList == null ? 0 : modelList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new ColorFlipPagerTitleView(context);
                simplePagerTitleView.setText(modelList.get(index).title);
                simplePagerTitleView.setNormalColor(R.color.red);
                simplePagerTitleView.setTypeface(BaseApplication.getsInstance().getFangZhengSong3());
                simplePagerTitleView.setSelectedColor(Color.parseColor("#D83D3A"));
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mViewPager.setCurrentItem(index);

                    }
                });
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setMode(LinePagerIndicator.MODE_EXACTLY);
                /**
                 * 指示器的高度
                 */
                indicator.setLineHeight(UIUtil.dip2px(context, 3));
                indicator.setLineWidth(UIUtil.dip2px(context, 10)); //  指示器的宽度
                indicator.setRoundRadius(UIUtil.dip2px(context, 3));//  指示器的圆角
                indicator.setStartInterpolator(new AccelerateInterpolator());
                indicator.setEndInterpolator(new DecelerateInterpolator(2.0f));
                indicator.setColors(Color.parseColor("#D83D3A"));
                return indicator;
            }
        });
        magicIndicator.setNavigator(commonNavigator7);
        ViewPagerHelper.bind(magicIndicator, mViewPager);
    }


    /**
     * 创建列表Fragment
     *
     * @param config
     * @return
     */
    private MineFrontNewsFragment createListFragments(CategoryModel config) {
        MineFrontNewsFragment fragment = new MineFrontNewsFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }


    private void shareApp() {
        ToastUitl.showShort("分享");
        if (shareBean == null) {
            shareBean = new ShareBean("咕唧新闻客户端", "咕唧新闻，给您最好的体验，最多最用心的新闻资讯", "https://www.lanzous.com/b311881/", "");
        }
    }


    public void setTvTitleBackgroundColor(@ColorInt int color) {
        try {
            llMagicIndicator.setBackgroundColor(color);
            mFakeStatusBar.setBackgroundColor(color);
        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden){//展示，可见  // 相当于Fragment的onResume()
            ILog.e(TAG2,"onHiddenChanged FrontNewsMainFragment:"+hidden);
            // 相当于Fragment的onResume()
            int color1 = getResources().getColor(R.color.white);
            StatusBarCompatUtils.setLightMode(getActivity());
            setTvTitleBackgroundColor(color1);
        }else {
            //相当于Fragment的onPause()
            ILog.e(TAG2,"[else]onHiddenChanged FrontNewsMainFragment:"+hidden);
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser){//展示，可见   // 相当于onResume()方法--获取焦点
            ILog.e(TAG2,"isVisibleToUser FrontNewsMainFragment:"+isVisibleToUser);
//            StatusBarCompatUtils.setDarkMode(getActivity());
        }else {
            // 相当于onpause()方法---失去焦点
            ILog.e(TAG2,"[else]isVisibleToUser FrontNewsMainFragment:"+isVisibleToUser);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        ILog.e(TAG1,"isVisibleToUser FrontNewsMainFragment  onResume---------------");
    }

}

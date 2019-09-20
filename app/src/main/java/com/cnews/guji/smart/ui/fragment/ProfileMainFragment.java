package com.cnews.guji.smart.ui.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.cnews.guji.smart.R;
import com.cnews.guji.smart.app.BaseApplication;
import com.cnews.guji.smart.base.AppConstant;
import com.cnews.guji.smart.base.BaseFragment;
import com.cnews.guji.smart.base.BaseRxFragment;
import com.cnews.guji.smart.base.baserx.ServerException;
import com.cnews.guji.smart.common.bean.ProfileCareBean;
import com.cnews.guji.smart.common.bean.ShareBean;
import com.cnews.guji.smart.helper.imageview.ExpandImageView;
import com.cnews.guji.smart.helper.righttopmenu.MenuItem;
import com.cnews.guji.smart.helper.righttopmenu.TopRightMenu;
import com.cnews.guji.smart.ui.activity.GJSettingActivity;
import com.cnews.guji.smart.ui.adapter.ProfileAdapter;
import com.cnews.guji.smart.ui.contract.ProfileCareContract;
import com.cnews.guji.smart.ui.model.ProfileCareModel;
import com.cnews.guji.smart.ui.presenter.ProfileCarePresenterimpl;
import com.cnews.guji.smart.util.ILog;
import com.cnews.guji.smart.util.NetWorkUtils;
import com.cnews.guji.smart.util.ToastUitl;
import com.cnews.guji.smart.util.statusbar.StatusBarCompatUtils;
import com.github.library.BaseQuickAdapter;
import com.github.library.BaseViewHolder;
import com.google.gson.Gson;
import com.uber.autodispose.AutoDisposeConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * des:关注
 */
public class ProfileMainFragment extends BaseRxFragment<ProfileCarePresenterimpl,ProfileCareModel> implements ProfileCareContract.View {
    private static final String TAG2 = "mainFragment";
    private static int SIZE = 20;
    private int mStartPage = 1;
    private TopRightMenu mTopRightMenu;
    private ShareBean shareBean;
//    private ProfileCarePresenterimpl _profileCarePresenterimpl;
    private  List<ProfileCareBean.Profile_data_list> profileList;
    private ProfileAdapter mAdapter;
    @BindView(R.id.comment_list)
    RecyclerView mRecyclerView;
    @BindView(R.id.article_img1)
    ExpandImageView mHeaderImg;
    @BindView(R.id.header_menu)
    AppCompatImageView mHeaderMenu;
    @BindView(R.id.collapse_menu)
    AppCompatImageView mCollapseMenu;
    @BindView(R.id.collapseHeaderIco)
    ExpandImageView mCollapseHeaderIco;
    @BindView(R.id.back_ima)
    ImageView mBackIma;

    public static ProfileMainFragment getInstance(String title) {
        ProfileMainFragment sf = new ProfileMainFragment();
        return sf;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.frag_care_profile;
    }

    @Override
    public void intBase() {
        if (profileList != null) profileList.clear();
        else profileList = new ArrayList<>();
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
//        String avatarUrl = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1516644385815&di=c0552674db9f07a5f889d7c0980e33db&imgtype=0&src=http%3A%2F%2Fimg.mp.itc.cn%2Fupload%2F20170529%2F83d3ce719e9d4c0a8f1cd033ecac3692_th.jpg";
//        Glide.with(this).load(avatarUrl).into(ivAvatar6);
//        Glide.with(this).load(avatarUrl).into(ivAvatar7);
//        Glide.with(this).load(avatarUrl).into(ivAvatar8);
//        Glide.with(this).load(avatarUrl).into(ivAvatar9);
//        Glide.with(this).load(avatarUrl).into(ivAvatar10);
    }

    @Override
    protected void initData() {
//        mRecyclerView.setNestedScrollingEnabled(false);
        mAdapter = new ProfileAdapter(getActivity(),profileList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mActivity);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mPresenter.getData(mActivity);

        //设置圆形本地头像
        mHeaderImg.setImageURI(Uri.parse(AppConstant.RES_SOURCE + R.mipmap.avtar_header_b));
        mCollapseHeaderIco.setImageURI(Uri.parse(AppConstant.RES_SOURCE + R.mipmap.avtar_header_b));
        mBackIma.setAlpha(150);
    }


    @OnClick({
            R.id.header_menu
            , R.id.collapse_menu
    })
    public void bindViewClick(View view) {
        switch (view.getId()) {
            case R.id.header_menu:
                initPopupWindow(0);
                break;
            case R.id.collapse_menu:
                initPopupWindow(1);
                break;
        }
    }

    @Override
    protected void initListener() {

    }

    public void setTvTitleBackgroundColor(@ColorInt int color) {
//        mTvTitle.setBackgroundColor(color);
//        mFakeStatusBar.setBackgroundColor(color);
    }

    /**
     * 个人导航数据
     * @param data
     */
    @Override
    public void setData(ProfileCareBean data) {
        if (data == null) {
            return;
        }
        stopProgressDialog();
        List<ProfileCareBean.Profile_data_list> profile_data_list = data.profile_data_list;
        ILog.e("profile"," getData:        "+ new Gson().toJson(data));
        mAdapter.getData().clear();
        if (profile_data_list != null) {
            mAdapter.setNewData(profile_data_list);
//            mRecyclerView.smoothScrollToPosition(0);
//            mRecyclerView.scrollToPosition(mAdapter.getItemCount() - 1);
        }else {
            ToastUitl.showShort("未查询到信息");
        }
    }

    @Override
    public void setDataWares(ProfileCareBean data) {

    }

    @Override
    public void setDataMoreWares(ProfileCareBean data) {

    }

    @Override
    public void showProgress(String content) {
        startProgressDialog(content);
    }

    @Override
    public void cancelProgress() {
        stopProgressDialog();
        ILog.e(TAG,"ProfileMainFragment-------[关闭进度框]------------------------------>");


    }

    @Override
    public void showError(Throwable throwable) {
        throwable.printStackTrace();
        //网络
        if (!NetWorkUtils.isNetConnected(BaseApplication.getAppContext())) {
            ToastUitl.showShort(BaseApplication.getAppContext().getString(R.string.no_net));
        }
        //服务器
        else if (throwable instanceof ServerException) {
            ToastUitl.showShort(throwable.getMessage());
        }
        //其它
        else {
            ToastUitl.showShort(BaseApplication.getAppContext().getString(R.string.net_error));
        }
    }

    @Override
    public void showError(String error) {

    }

    @Override
    public <T> AutoDisposeConverter<T> bindAutoDispose() {
        return null;
    }


    public class TypeAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
        private int[] c;

        public TypeAdapter(int layoutResId, List<String> data) {
            super(layoutResId, data);
            c = new int[]{Color.parseColor("#33FF0000"),
                    Color.parseColor("#3300FF00"),
                    Color.parseColor("#330000FF")};
        }

        @Override
        protected void convert(BaseViewHolder helper, String s) {
            int position = helper.getAdapterPosition();
//            TextView view = helper.getView(R.id.item_tv);
//            view.setBackgroundResource(c[position % 3]);
//            helper.setBgColor(R.id.item_tv, c[position % 3]);
            helper.setText(R.id.item_tv, "item" + s);
        }
    }


    /**
     * 当前界面是否隐藏
     * 这种方法适用于界面替换用到hide()和show()方法
     *
     * @param hidden
     */
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {//展示，可见  // 相当于Fragment的onResume()
            ILog.e(TAG2, "onHiddenChanged ProfileMainFragment:" + hidden);
            // 相当于Fragment的onResume()
            StatusBarCompatUtils.setDarkMode(getActivity());
        } else {
            //相当于Fragment的onPause()
            ILog.e(TAG2, "[else]onHiddenChanged ProfileMainFragment:" + hidden);
        }
    }

    /**
     * 当前界面是否对用户可见
     * 这种党法适用于FragmentTransaction的replace()
     *
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        try {
            if (getUserVisibleHint()) {//界面可见时
            } else {
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (isVisibleToUser) {//展示，可见   // 相当于onResume()方法--获取焦点
        } else {
            // 相当于onpause()方法---失去焦点
        }
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    /**
     * 初始化右上角弹窗
     */
    private void initPopupWindow(int posType) {
        mTopRightMenu = new TopRightMenu(getActivity());
        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(new MenuItem(R.mipmap.profile_ico_top_share, "分享app"));
        menuItems.add(new MenuItem(R.drawable.ic_search, "加入官方群"));
        menuItems.add(new MenuItem(R.drawable.ic_search, "设置"));

        mTopRightMenu
                .showIcon(false)
                .dimBackground(true)
                .needAnimationStyle(true)
                .showIcon(false)
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
                .showAsDropDown(posType == 0 ? mHeaderMenu : mCollapseMenu, posType == 0 ? -mHeaderMenu.getWidth() - 10 : mCollapseMenu.getWidth() - 10, -10);

    }

    private void shareApp() {
        ToastUitl.showShort("分享");
        if (shareBean == null) {
            shareBean = new ShareBean("咕唧新闻客户端", "咕唧新闻，给您最好的体验，最多最用心的新闻资讯", "https://www.lanzous.com/b311881/", "");
        }
    }

}

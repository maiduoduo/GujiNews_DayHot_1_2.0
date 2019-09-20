package com.cnews.guji.smart.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.cnews.guji.smart.R;
import com.cnews.guji.smart.base.BaseActivity;
import com.cnews.guji.smart.util.AppCommonUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 关于
 */
public class GJAboutActivity extends BaseActivity {

    @BindView(R.id.tv_version_name)
    TextView nameVersion;

    @OnClick(R.id.iv_back)
    public void back() {
        finish();
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        nameVersion.setText("v " + AppCommonUtils.getAppVersionName(mContext));
    }

    @Override
    public int getLayoutId() {
        return R.layout.gj_about_activity;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void initBase() {

    }

    @Override
    protected int setImmersiveStatusBarColor() {
        return 0;
    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected void getBundleExtras(Intent intent) {

    }
}

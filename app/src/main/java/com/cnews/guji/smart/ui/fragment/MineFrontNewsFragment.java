package com.cnews.guji.smart.ui.fragment;


import android.view.View;
import android.widget.Button;

import com.cnews.guji.smart.R;
import com.cnews.guji.smart.base.BaseFragment;
import com.cnews.guji.smart.util.ToastUitl;
import com.cnews.guji.smart.util.statusbar.StatusBarCompatUtils;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 视频分类
 */
public class MineFrontNewsFragment extends BaseFragment {
    private static final String TAG = "MineFrontNewsFragment";

    @BindView(R.id.btnLoginin)
    Button btnLoginin;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_mine_front_news;
    }

    @Override
    public void intBase() {

    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        StatusBarCompatUtils.setLightMode(getActivity());
    }

    @OnClick({R.id.btnLoginin})
    public void bindViewClick(View view){
        switch (view.getId()){
            case R.id.btnLoginin:
                ToastUitl.showShort("loginin");
                break;
        }
    }
}

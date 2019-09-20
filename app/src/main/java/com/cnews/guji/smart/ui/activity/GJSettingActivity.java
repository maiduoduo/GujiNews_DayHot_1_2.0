package com.cnews.guji.smart.ui.activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cnews.guji.smart.R;
import com.cnews.guji.smart.app.BaseApplication;
import com.cnews.guji.smart.base.AppConstant;
import com.cnews.guji.smart.base.BaseActivity;
import com.cnews.guji.smart.common.bean.AppNettyData;
import com.cnews.guji.smart.util.AppCommonUtils;
import com.cnews.guji.smart.util.ToastUitl;
import com.tencent.bugly.crashreport.CrashReport;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 设置
 * @author JSYL-DCL
 */
public class GJSettingActivity extends BaseActivity {

    private AppNettyData appData;
    private String download;
    @BindView(R.id.ll_version)
    RelativeLayout ll_version;
    @BindView(R.id.tv_curr_version)
    TextView tv_curr_version;

    @Override
    public int getLayoutId() {
        return R.layout.gj_setting_activity;
    }

    @OnClick({
            R.id.iv_back
            , R.id.tv_about
            , R.id.ll_version
            , R.id.tv_contact
            , R.id.tv_clear
            , R.id.tv_download
    })
    public void back(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finishThis();
                break;
            case R.id.tv_about:
                startActivity(new Intent(this, GJAboutActivity.class));
                break;
            case R.id.ll_version:
//                ToastUitl.showShort("已是最新版本");
                ToastUitl.showShort("已是最新版本");
//                CrashReport.testJavaCrash();
                break;
            //加入QQ群
            case R.id.tv_contact:
                if (!TextUtils.isEmpty(AppConstant.QQ_KEY)) {
                    BaseApplication.joinQQGroup(mContext, AppConstant.QQ_KEY);
                }
                break;
            case R.id.tv_clear:
                showShortToast("缓存清理成功");
                break;
            //复制下载地址
            case R.id.tv_download:
                download = AppConstant.DOWNLOAD_URL;
                if (!TextUtils.isEmpty(download)) {
                    ClipboardManager myClipboard;
                    myClipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                    ClipData myClip;
                    //text是内容
                    myClip = ClipData.newPlainText("text", download);
                    myClipboard.setPrimaryClip(myClip);
                    ToastUitl.showShort("下载链接已复制，去浏览器粘贴打开，或发送给好友！");
                }
                break;
            default:
                break;
        }
    }


    @Override
    protected void initView(Bundle savedInstanceState) {
        tv_curr_version.setText(" " + AppCommonUtils.getAppVersionName(mContext));
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

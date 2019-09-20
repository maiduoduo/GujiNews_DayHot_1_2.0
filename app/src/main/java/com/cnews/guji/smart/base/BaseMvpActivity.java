package com.cnews.guji.smart.base;

import android.arch.lifecycle.Lifecycle;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.cnews.guji.smart.ui.mvp.IBasePresenter;
import com.cnews.guji.smart.ui.mvp.IBaseView;
import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.AutoDisposeConverter;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;

/**
 * @package: BaseMvpActivity
 * @author： JSYL-DCL
 * @describe： MVPActivity基类
 * @email： 11442865
 */
public abstract class BaseMvpActivity<T extends IBasePresenter> extends BaseActivity implements IBaseView {
    protected T mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null){
            mPresenter.detachView();
        }
        super.onDestroy();
    }

    /**
     * 绑定生命周期，防止MVP内存泄漏
     * @param <T>
     * @return
     */
    @Override
    public <T> AutoDisposeConverter<T> bindAutoDispose() {
        return AutoDispose.autoDisposable(AndroidLifecycleScopeProvider
                .from(this, Lifecycle.Event.ON_DESTROY));
    }
}

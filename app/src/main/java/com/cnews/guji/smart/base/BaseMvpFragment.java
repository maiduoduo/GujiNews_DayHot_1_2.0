package com.cnews.guji.smart.base;

import android.arch.lifecycle.Lifecycle;

import com.cnews.guji.smart.ui.mvp.IBasePresenter;
import com.cnews.guji.smart.ui.mvp.IBaseView;
import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.AutoDisposeConverter;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;

/**
 * @package: BaseMvpFragment
 * @author： JSYL-DCL
 * @describe： Fragment基类
 * @email： 11442865
 */
public abstract class BaseMvpFragment<T extends IBasePresenter> extends BaseFragment implements IBaseView {
    protected T mPresenter;

    @Override
    public void onDestroyView() {
        if (mPresenter != null){
            mPresenter.detachView();
        }
        super.onDestroyView();
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

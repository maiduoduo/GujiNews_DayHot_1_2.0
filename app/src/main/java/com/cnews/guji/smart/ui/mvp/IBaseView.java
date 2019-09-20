package com.cnews.guji.smart.ui.mvp;

import com.uber.autodispose.AutoDisposeConverter;

/**
 * @author JSYL-DCL
 * @date 2018/5/24 14:18
 */
public interface IBaseView {
    /**
     * 显示进度
     */
    void showProgress(String content);

    /**
     * 取消显示进度
     */
    void cancelProgress();

    /**
     * 显示异常信息
     * @param throwable
     */
    void showError(Throwable throwable);
    void showError(String error);
    /**
     * 绑定Android生命周期 防止RxJava内存泄漏
     *
     * @param <T>
     * @return
     */
    <T> AutoDisposeConverter<T> bindAutoDispose();

}

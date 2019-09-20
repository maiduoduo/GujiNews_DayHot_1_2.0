package com.cnews.guji.smart.helper.horizontaldragmoreview;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

/**
 * 自定义loadMore View需承接ILoadMore，并实现这几个方法来控制每个阶段LoadMore View的呈现状态
 */
public interface ILoadMore {

    /**
     * 开始拖动
     *
     * @param view
     */
    void startDrag(View view);

    /**
     * 拖动中
     *
     * @param dragPercent
     * @param view
     */
    void onDraging(float dragPercent, View view);


    /**
     * 放开，加载中状态
     *
     * @param view
     */
    void onLoading(View view);

    /**
     * loadMore View
     *
     * @return
     */
    View getView(@NonNull ViewGroup parent);

}

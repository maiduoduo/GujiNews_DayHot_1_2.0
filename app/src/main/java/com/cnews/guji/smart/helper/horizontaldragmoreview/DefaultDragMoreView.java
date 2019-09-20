package com.cnews.guji.smart.helper.horizontaldragmoreview;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cnews.guji.smart.R;

/**
 * 拖拽布局
 */
public class DefaultDragMoreView implements ILoadMore {

    private TextView tvText;

    @Override
    public void startDrag(View view) {
        tvText.setText("滑\n动\n查\n看\n更\n多");
    }

    @Override
    public void onDraging(float dragPercent, View view) {

    }

    @Override
    public void onLoading(View view) {
        tvText.setText("加\n载\n中");
    }

    @Override
    public View getView(@NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_horizontal_default_more, parent, false);
        tvText = view.findViewById(R.id.tv_drag_text);
        return view;
    }
}

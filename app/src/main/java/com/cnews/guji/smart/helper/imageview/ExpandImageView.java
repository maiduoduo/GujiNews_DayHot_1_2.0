package com.cnews.guji.smart.helper.imageview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;

import com.cnews.guji.smart.R;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * @author：dingcl .
 * 此为在SimpleDraweeView基础上做一层包装，防止过度依赖
 */

public class ExpandImageView extends SimpleDraweeView {
    private Paint paint;
    private boolean isCenterImgShow;
    private Bitmap bitmap;
    public void setCenterImgShow(boolean centerImgShow) {
        isCenterImgShow = centerImgShow;
        if (isCenterImgShow) {
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_add_cc);
            invalidate();
        }
    }


    public ExpandImageView(Context context, GenericDraweeHierarchy hierarchy) {
        super(context, hierarchy);
    }

    public ExpandImageView(Context context) {
        super(context);
    }

    public ExpandImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ExpandImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public ExpandImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.drawColor(getResources().getColor(R.color.red_shadow));
    }
}

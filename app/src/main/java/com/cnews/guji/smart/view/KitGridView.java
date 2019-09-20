package com.cnews.guji.smart.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * author：JSYL-DCL on 2019/3/8
 * 自定义GridView
 *              解决在scrollview中只显示第一行数据的问题
 */
public class KitGridView extends GridView {
    public KitGridView(Context context) {
        super(context);
    }

    public KitGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public KitGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * 这两个参数指明控件可获得的空间以及关于这个空间描述的元数据.
     * @param widthMeasureSpec 此控件获得的宽度
     * @param heightMeasureSpec 此控件获得的高度
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        /**
         * >> 表示右移 ，如：int i=15; i>>2的结果是3，移出的部分将被抛弃。
         *转为二进制的形式可能更好理解，0000 1111(15)右移2位的结果是0000 0011(3)，0001 1010(18)右移3位的结果是0000 0011(3)。
         *
         * 最大模式（MeasureSpec.AT_MOST）
         这个也就是父组件，能够给出的最大的空间，当前组件的长或宽最大只能为这么大，当然也可以比这个小。

         MeasureSpec.AT_MOST是最大尺寸，当控件的layout_width或layout_height指定为WRAP_CONTENT时，
         控件大小一般随着控件的子空间或内容进行变化，此时控件尺寸只要不超过父控件允许的最大尺寸即可。
         因此，此时的mode是AT_MOST，size给出了父控件允许的最大尺寸。


         static int makeMeasureSpec(int size,int mode):根据提供的大小值和模式创建一个测量值(格式)
         */
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2 ,MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);

    }
}

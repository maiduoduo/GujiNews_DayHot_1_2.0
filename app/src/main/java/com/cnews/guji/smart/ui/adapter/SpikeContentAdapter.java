package com.cnews.guji.smart.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.cnews.guji.smart.R;
import com.cnews.guji.smart.common.bean.basebean.HomeTophotIndexBean;
import com.cnews.guji.smart.helper.imageview.ExpandImageView;
import com.cnews.guji.smart.ui.activity.HotNewsDetailActivity;
import com.github.library.BaseQuickAdapter;
import com.github.library.BaseViewHolder;

import java.util.List;

/**
 * @author：dingcl
 * 横向新闻列表
 * 热闻
 */

public class SpikeContentAdapter extends BaseQuickAdapter<HomeTophotIndexBean.Articles.HotSpecialNews, BaseViewHolder> {
    private Context context;
    public SpikeContentAdapter(Context context,int layoutResId, List<HomeTophotIndexBean.Articles.HotSpecialNews> data) {
        super(layoutResId, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeTophotIndexBean.Articles.HotSpecialNews hotSpecialNews) {
        int adapterPosition = helper.getAdapterPosition();
        if (hotSpecialNews != null) {
            ((ExpandImageView) helper.getView(R.id.article_img)).setImageURI(hotSpecialNews.pics.get(0).url);
            helper.setText(R.id.horiTitle, hotSpecialNews.title == null ? "" : hotSpecialNews.title);

            FrameLayout view = helper.getView(R.id.flHoriRoot);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    ToastUitl.showShort("横向："+adapterPosition+hotSpecialNews.title);
                    HotNewsDetailActivity.newInstance(context, hotSpecialNews.articleUrl == null ? "" : hotSpecialNews.articleUrl);
                }
            });
        }
    }

    @Override
    protected View getItemView(int layoutResId, ViewGroup parent) {
        View view = View.inflate(mContext, R.layout.item_index_horizontal_list_item,null);
//        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((int) (0.286 * DensityUtil.getScreenWidth(mContext)), LinearLayout.LayoutParams.WRAP_CONTENT);
//        view.setLayoutParams(params);
        return view;

    }

}

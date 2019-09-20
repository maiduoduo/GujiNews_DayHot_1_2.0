package com.cnews.guji.smart.ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cnews.guji.smart.R;
import com.cnews.guji.smart.base.AppConstant;
import com.cnews.guji.smart.common.bean.FrontNewsBean;
import com.cnews.guji.smart.helper.imageview.ExpandImageView;
import com.cnews.guji.smart.util.StringUtils;
import com.github.library.BaseQuickAdapter;
import com.github.library.BaseViewHolder;

import java.util.List;
/**
 * Created by dingcl.
 * 要闻数据装配
 */
public class FrontNewsAdapter extends BaseQuickAdapter<FrontNewsBean.Articles, BaseViewHolder> {
    private Context mcontext;

    public FrontNewsAdapter(int layoutResId, List<FrontNewsBean.Articles> data) {
        super(layoutResId, data);
    }

    public FrontNewsAdapter(Context context,int layoutResId, List<FrontNewsBean.Articles> data) {
        super(layoutResId, data);
        mcontext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, FrontNewsBean.Articles articles) {
        int adapterPosition = helper.getAdapterPosition();
        ExpandImageView imgTop = helper.getView(R.id.imgTop);
        TextView tvBottom = helper.getView(R.id.tvBottom);
        TextView tvStatus = helper.getView(R.id.tvStatus);
        LinearLayout llJoin =  helper.getView(R.id.llJoin);
        TextView tvGUBINum =  helper.getView(R.id.tvGUBINum);
        TextView tvFlagJoinNum =  helper.getView(R.id.tvFlagJoinNum);
        helper.setText(R.id.tvBottom, articles.title == null ? "" : articles.title);
        imgTop.setImageURI(articles.pics.get(0) == null ? "" : articles.pics.get(0).url);
//        ((ExpandImageView) helper.getView(R.id.imgTop3)).setImageURI(FrontNewsBean.url);
        if (AppConstant.FRONT_NEWS_RIGHT_BOTTOM_RIGHTNOW == articles.isjoinOrPublish){//马上参与
            imgTop.setAlpha(255);
            tvStatus.setVisibility(View.GONE);
            llJoin.setVisibility(View.VISIBLE);
            tvGUBINum.setVisibility(View.GONE);
            tvFlagJoinNum.setText(StringUtils.getDecimalWithW(articles.beanpool,1,false)+"万咕币");

        }else if (AppConstant.FRONT_NEWS_WAIT_PUBLISH == articles.isjoinOrPublish){//待揭晓
            imgTop.setAlpha(150);
            tvStatus.setVisibility(View.VISIBLE);
            llJoin.setVisibility(View.GONE);
            tvStatus.setText("待揭晓");
            tvGUBINum.setVisibility(View.VISIBLE);
            tvGUBINum.setText(StringUtils.getDecimalWithW(articles.beanpool,1,true)+"万咕币");

        }else if (AppConstant.FRONT_NEWS_ALRIGHT_PUBLISH == articles.isjoinOrPublish){//已揭晓
            imgTop.setAlpha(150);
            tvStatus.setVisibility(View.VISIBLE);
            llJoin.setVisibility(View.GONE);
            tvStatus.setText("已揭晓");
            tvGUBINum.setVisibility(View.VISIBLE);
            tvGUBINum.setText(StringUtils.getDecimalWithW(articles.beanpool,1,true)+"万咕币");
        }


//        if (adapterPosition % 3 == 0){
//            imgTop.setAlpha(150);
//            tvStatus.setVisibility(View.VISIBLE);
//            llJoin.setVisibility(View.VISIBLE);
//        }else {
//            imgTop.setAlpha(255);
//            tvStatus.setVisibility(View.GONE);
//            llJoin.setVisibility(View.GONE);
//        }

    }
}

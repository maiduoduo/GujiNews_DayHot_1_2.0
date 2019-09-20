package com.cnews.guji.smart.ui.adapter;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.cnews.guji.smart.R;
import com.cnews.guji.smart.base.AppConstant;
import com.cnews.guji.smart.common.bean.HotNewsBestBean;
import com.cnews.guji.smart.helper.glide.GlideImageLoader;
import com.cnews.guji.smart.helper.imageview.ExpandImageView;
import com.cnews.guji.smart.util.ILog;
import com.cnews.guji.smart.util.ToastUitl;
import com.github.library.BaseMultiItemQuickAdapter;
import com.github.library.BaseViewHolder;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.transformer.AccordionTransformer;
import com.youth.banner.transformer.BackgroundToForegroundTransformer;
import com.youth.banner.transformer.CubeInTransformer;
import com.youth.banner.transformer.CubeOutTransformer;
import com.youth.banner.transformer.DefaultTransformer;
import com.youth.banner.transformer.DepthPageTransformer;
import com.youth.banner.transformer.FlipHorizontalTransformer;
import com.youth.banner.transformer.FlipVerticalTransformer;
import com.youth.banner.transformer.ForegroundToBackgroundTransformer;
import com.youth.banner.transformer.RotateDownTransformer;
import com.youth.banner.transformer.RotateUpTransformer;
import com.youth.banner.transformer.ScaleInOutTransformer;
import com.youth.banner.transformer.StackTransformer;
import com.youth.banner.transformer.TabletTransformer;
import com.youth.banner.transformer.ZoomInTransformer;
import com.youth.banner.transformer.ZoomOutSlideTransformer;
import com.youth.banner.transformer.ZoomOutTranformer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dingcl
 * 热闻精选数据装配
 *  TODO:---------切记：1.对于控件映射绑定要进行判空处理，
 *  TODO:---------     2.对于绑定数据要对数据结果进行判空处理----------------------
 */
public class HotNewsBestMultipleRecycleAdapter extends BaseMultiItemQuickAdapter<HotNewsBestBean.Articles, BaseViewHolder> implements OnBannerListener {
    private String TAG = "HomeHottop";
    private Context context;

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public HotNewsBestMultipleRecycleAdapter(Context context, List<HotNewsBestBean.Articles> data) {
        super(data);
        this.context = context;
        addItemType(HotNewsBestBean.Articles.TYPE_HORIZAONTAL_IMAGE, R.layout.item_index_horizontal_list);
        addItemType(HotNewsBestBean.Articles.TYPE_EMPTY_IMAGE, R.layout.item_index_zeroempty_image);
        addItemType(HotNewsBestBean.Articles.TYPE_TYADMODE_IMAGE, R.layout.item_index_tyad_menu);
        addItemType(HotNewsBestBean.Articles.TYPE_TOP_BANNER, R.layout.item_index_banner_with_menu);
        addItemType(HotNewsBestBean.Articles.TYPE_SINGLE_IMAGE, R.layout.item_index_single_image);
        addItemType(HotNewsBestBean.Articles.TYPE_THREE_IMAGE, R.layout.item_index_three_image);
        addItemType(HotNewsBestBean.Articles.TYPE_SINGLE_BIG_IMAGE, R.layout.item_index_single_big_image);
    }

    @Override
    protected void convert(BaseViewHolder helper, HotNewsBestBean.Articles articles) {
        int adapterPosition = helper.getAdapterPosition();
        switch (articles.getItemType()) {
            case 0://banner
                bindTopBannerData(helper, articles, adapterPosition);
                break;
            case 1://single
                bindSingleNewsData(helper, articles, adapterPosition);
                break;
            case 2://ad-通用
                bindTYAdImageData(helper, articles, adapterPosition);
                break;
            case 3://multi
                bindMultiImgNewsData(helper, articles, adapterPosition);
            case 4://Empty
                bindEmptyImgNewsData(helper, articles, adapterPosition);
            case 5://Horizaontal
                ILog.e(TAG, "Horizaontal ===========11111");
                bindHorizaontalImgNewsData(helper, articles, adapterPosition);
            case 10://single BIG-IMAGE
                bindSingleBigNewsData(helper, articles, adapterPosition);
                break;
            default:
                break;

        }
    }

    /**
     * 轮播图
     *
     * @param position
     */
    @Override
    public void OnBannerClick(int position) {
        ToastUitl.showShort("轮播图，点击了：" + position);
    }

    /**
     * 绑定banner数据
     *
     * @param helper
     * @param item
     * @param position
     */
    private List<String> bannerList;

    private void bindTopBannerData(BaseViewHolder helper, final HotNewsBestBean.Articles item, int position) {
        Banner mBanner = helper.getView(R.id.banner1);
//        mBanner.setBannerStyle()
        List<String> titles = new ArrayList<>();
        if (titles != null && titles.size() > 0) titles.clear();
        if (bannerList != null) bannerList.clear();
        else bannerList = new ArrayList<>();
        initAnim();
        //数据
        for (HotNewsBestBean.Articles.Pics a : item.getPics()) {
            bannerList.add(a.url);
            titles.add("");
        }
        //默认是NUM_INDICATOR_TITLE
        mBanner.setImages(bannerList)
                //.setBannerTitles(titles)
                .setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
                .setIndicatorGravity(BannerConfig.CENTER)
                .setImageLoader(new GlideImageLoader())
                .setOnBannerListener(this)
                .setIndicatorGravity(Gravity.CENTER_HORIZONTAL)
                //设置指示器位置（当banner模式中有指示器时）
                .setIndicatorGravity(BannerConfig.CENTER)
                .isAutoPlay(true)
                .setBannerAnimation(transformers.get(1))
                .setDelayTime(3000)
                .start();

        ((ExpandImageView) helper.getView(R.id.menu_a)).setImageURI(AppConstant.HOME_MENU_URL_A);
        ((ExpandImageView) helper.getView(R.id.menu_b)).setImageURI(AppConstant.HOME_MENU_URL_B);
        ((ExpandImageView) helper.getView(R.id.menu_c)).setImageURI(AppConstant.HOME_MENU_URL_C);
        ((ExpandImageView) helper.getView(R.id.menu_d)).setImageURI(AppConstant.HOME_MENU_URL_D);
        ((ExpandImageView) helper.getView(R.id.menu_e)).setImageURI(AppConstant.HOME_MENU_URL_E);
    }

    /**
     * 单图
     *
     * @param helper
     * @param item
     * @param position
     */
    private void bindSingleNewsData(BaseViewHolder helper, HotNewsBestBean.Articles item, int position) {
        ((ExpandImageView) helper.getView(R.id.article_img)).setImageURI(item.getPics().get(0).url);
        helper.setText(R.id.tvSourceName, item.mediaName == null ? "" : item.mediaName);
        helper.setText(R.id.tvCommentnum, item.commentNum + "");
        helper.setText(R.id.tvArticletitle, item.title == null ? "" : item.title);

        if (item.flag != null){
            if (1 == item.flag.type || 3 == item.flag.type){//1：热点  3：精选
                helper.getView(R.id.hotFlag).setVisibility(View.VISIBLE);
                helper.getView(R.id.llZhuanTi).setVisibility(View.GONE);
                ((TextView)helper.getView(R.id.hotFlag)).setText(item.flag.text == null ? "" : item.flag.text);
            }else if (4 == item.flag.type){//专题
                helper.getView(R.id.hotFlag).setVisibility(View.GONE);
                helper.getView(R.id.llZhuanTi).setVisibility(View.VISIBLE);
//                ((TextView)helper.getView(R.id.tvZTText)).setText(item.flag.text == null ? "" : item.flag.text);
            }else {
                helper.getView(R.id.hotFlag).setVisibility(View.GONE);
                helper.getView(R.id.llZhuanTi).setVisibility(View.GONE);
            }
        }

    }

    /**
     * 单张大图
     *      TODO:----------这里对绑定控件及数据都做了空判断处理-------------------------
     * @param helper
     * @param item
     * @param adapterPosition
     */
    private void bindSingleBigNewsData(BaseViewHolder helper, HotNewsBestBean.Articles item, int adapterPosition) {
        TextView tvArticletitle_best = helper.getView(R.id.tvArticletitle_best);
        TextView tvSourceName_best = helper.getView(R.id.tvSourceName_best);
        TextView tvCommentnum_best = helper.getView(R.id.tvCommentnum_best);
        ExpandImageView img_single_big = helper.getView(R.id.img_single_big);
        if (tvArticletitle_best != null)tvArticletitle_best.setText(item.title == null ? "" : item.title);
        if (tvSourceName_best != null)tvSourceName_best.setText(item.mediaName == null ? "" : item.mediaName);
        if (tvCommentnum_best != null)tvCommentnum_best.setText(item.commentNum + "");
        if (img_single_big != null)img_single_big.setImageURI(item.getPics().get(0).url);
    }


    /**
     * 通用广告-三图拼接
     *
     * @param helper
     * @param item
     * @param adapterPosition
     */
    private void bindTYAdImageData(BaseViewHolder helper, HotNewsBestBean.Articles item, int adapterPosition) {
        if (item.getPics().size() >= 3) {
            ((ExpandImageView) helper.getView(R.id.tymenu_a)).setImageURI(item.getPics().get(0).url);
            ((ExpandImageView) helper.getView(R.id.tymenu_b)).setImageURI(item.getPics().get(1).url);
            ((ExpandImageView) helper.getView(R.id.tymenu_c)).setImageURI(item.getPics().get(2).url);
        }
    }

    /**
     * 三图
     * @param helper
     * @param item
     * @param position
     */
    private void bindMultiImgNewsData(BaseViewHolder helper, HotNewsBestBean.Articles item, int position) {
        if (item.getPics().size() >= 3) {
            ((ExpandImageView) helper.getView(R.id.article_img1)).setImageURI(item.getPics().get(0).url);
            ((ExpandImageView) helper.getView(R.id.article_img2)).setImageURI(item.getPics().get(1).url);
            ((ExpandImageView) helper.getView(R.id.article_img3)).setImageURI(item.getPics().get(2).url);
        }
        helper.setText(R.id.Tarticle_title, item.title == null ? "" : item.title);
        helper.setText(R.id.tvTSourceName, item.mediaName == null ? "" : item.mediaName);
        helper.setText(R.id.tvTcommetnum, item.commentNum + "");
        helper.setText(R.id.tvDate, item.virtualTime + "");

        if (item.flag != null){
            if (1 == item.flag.type || 3 == item.flag.type){//1：热点  3：精选
                helper.getView(R.id.hotFlag).setVisibility(View.VISIBLE);
                helper.getView(R.id.llZhuanTi).setVisibility(View.GONE);
                ((TextView)helper.getView(R.id.hotFlag)).setText(item.flag.text == null ? "" : item.flag.text);
            }else if (4 == item.flag.type){//专题
                helper.getView(R.id.hotFlag).setVisibility(View.GONE);
                helper.getView(R.id.llZhuanTi).setVisibility(View.VISIBLE);
//                ((TextView)helper.getView(R.id.tvZTText)).setText(item.flag.text == null ? "" : item.flag.text);
            }else {
                helper.getView(R.id.hotFlag).setVisibility(View.GONE);
                helper.getView(R.id.llZhuanTi).setVisibility(View.GONE);
            }
        }
    }

    /**
     * 无图
     *
     * @param helper
     * @param item
     * @param position
     */
    private void bindEmptyImgNewsData(BaseViewHolder helper, HotNewsBestBean.Articles item, int position) {
        helper.setText(R.id.Tarticle_title, item.title == null ? "" : item.title);
        helper.setText(R.id.tvTSourceName, item.mediaName == null ? "" : item.mediaName);
        helper.setText(R.id.tvTcommetnum, item.commentNum + "");
        helper.setText(R.id.tvDate, item.virtualTime + "");
    }

    /**
     * 横向图片新闻列表
     *
     * @param helper
     * @param item
     * @param adapterPosition
     */
    private void bindHorizaontalImgNewsData(BaseViewHolder helper, HotNewsBestBean.Articles item, int adapterPosition) {
//        RecyclerView recyclerView = helper.getView(R.id.spike_content_view);
//        HorizontalDragMoreView mHorizontalDragMoreView = helper.getView(R.id.horizontalDragMoreView);
//        //拖拽操作
//        initDragMoreLayout(mHorizontalDragMoreView);
//        List<HomeTophotIndexBean.Articles.HotSpecialNews> hotSpecialNews = item.getHotSpecialNews();
//        if (item.getHotSpecialNews() == null || item.getHotSpecialNews().size() <= 0) return;
//        ((ImageView) helper.getView(R.id.hotNewsTitleIco)).setImageResource(R.mipmap.icon_title_hotnews);
//        helper.setText(R.id.hotnewsMoreText, "查看更多");
//        helper.addOnClickListener(R.id.hotnewsMore);
//        helper.setOnClickListener(R.id.hotnewsMore, new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                HotNewsBestActivity.newInstance(context,null,AppConstant.HOT_NEWS_PARAMVALUE_TOP);
//            }
//        });
//        //Recyclerview
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
//        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//        recyclerView.setFocusable(false);
//        recyclerView.setFocusableInTouchMode(false);
//        recyclerView.setLayoutManager(linearLayoutManager);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setItemViewCacheSize(10);
//        SpikeContentAdapter adapter = new SpikeContentAdapter(R.layout.item_index_horizontal_list, hotSpecialNews);
//        recyclerView.setAdapter(adapter);
    }

    /**
     * banner动画
     */
    private List<Class<? extends ViewPager.PageTransformer>> transformers = new ArrayList<>();

    private void initAnim() {
        transformers.add(DefaultTransformer.class);
        transformers.add(AccordionTransformer.class);
        transformers.add(BackgroundToForegroundTransformer.class);
        transformers.add(ForegroundToBackgroundTransformer.class);
        transformers.add(CubeInTransformer.class);//兼容问题，慎用
        transformers.add(CubeOutTransformer.class);
        transformers.add(DepthPageTransformer.class);
        transformers.add(FlipHorizontalTransformer.class);//7
        transformers.add(FlipVerticalTransformer.class);
        transformers.add(RotateDownTransformer.class);
        transformers.add(RotateUpTransformer.class);
        transformers.add(ScaleInOutTransformer.class);
        transformers.add(StackTransformer.class);
        transformers.add(TabletTransformer.class);
        transformers.add(ZoomInTransformer.class);
        transformers.add(ZoomOutTranformer.class);
        transformers.add(ZoomOutSlideTransformer.class);
    }

}


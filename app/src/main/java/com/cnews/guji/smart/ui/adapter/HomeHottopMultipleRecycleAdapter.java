package com.cnews.guji.smart.ui.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cnews.guji.smart.R;
import com.cnews.guji.smart.base.AppConstant;
import com.cnews.guji.smart.common.bean.basebean.HomeTophotIndexBean;
import com.cnews.guji.smart.helper.UpDownViewSwitcher;
import com.cnews.guji.smart.helper.glide.GlideImageLoader;
import com.cnews.guji.smart.helper.horizontaldragmoreview.HorizontalScrollSlideView.PulToLeftViewGroup;
import com.cnews.guji.smart.helper.imageview.ExpandImageView;
import com.cnews.guji.smart.ui.activity.HotNewsBestActivity;
import com.cnews.guji.smart.ui.activity.HotNewsDetailActivity;
import com.cnews.guji.smart.ui.model.Menu9Model;
import com.cnews.guji.smart.util.DateTimeUtils;
import com.cnews.guji.smart.util.ILog;
import com.cnews.guji.smart.util.ImageLoaderUtils;
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
import java.util.LinkedHashMap;
import java.util.List;

import cn.jzvd.JZDataSource;
import cn.jzvd.JzvdStd;

/**
 * @author dingcl 数据绑定未进行详细的数据验证，再实际使用中不可取
 */
public class HomeHottopMultipleRecycleAdapter extends BaseMultiItemQuickAdapter<HomeTophotIndexBean.Articles, BaseViewHolder> implements OnBannerListener {
    private String TAG = "HomeHottop";
    private Context context;
    private List<View> mPagerList;
    private List<Menu9Model> mDatas;
    private LayoutInflater inflater;
    /**
     * 总的页数
     */
    private int pageCount;
    /**
     * 每一页显示的个数
     */
    private int pageSize = 10;
    /**
     * 当前显示的是第几页
     */
    private int curIndex = 0;


    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public HomeHottopMultipleRecycleAdapter(Context context, List<HomeTophotIndexBean.Articles> data) {
        super(data);
        this.context = context;
        ILog.e(TAG, "构造方法：==============================");
        addItemType(HomeTophotIndexBean.Articles.TYPE_HORIZAONTAL_IMAGE, R.layout.item_index_horizontal_list);
        addItemType(HomeTophotIndexBean.Articles.TYPE_EMPTY_IMAGE, R.layout.item_index_zeroempty_image);
        addItemType(HomeTophotIndexBean.Articles.TYPE_TYADMODE_IMAGE, R.layout.item_index_tyad_menu);
        addItemType(HomeTophotIndexBean.Articles.TYPE_TOP_BANNER, R.layout.item_index_banner_with_menu);
        addItemType(HomeTophotIndexBean.Articles.TYPE_SINGLE_IMAGE, R.layout.item_index_single_image);
        addItemType(HomeTophotIndexBean.Articles.TYPE_THREE_IMAGE, R.layout.item_index_three_image);
        addItemType(HomeTophotIndexBean.Articles.TYPE_SINGLE_BIG_IMAGE, R.layout.item_index_single_big_image);
        addItemType(HomeTophotIndexBean.Articles.TYPE_HORIZAONTAL_NINE_MENU, R.layout.item_index_horizontal_9menu);
        addItemType(HomeTophotIndexBean.Articles.TYPE_UP_DOWN_SWITCHER, R.layout.item_index_bullentin);
        addItemType(HomeTophotIndexBean.Articles.TYPE_UP_VIDEO_IMAGE, R.layout.item_index_video_image);
        addItemType(HomeTophotIndexBean.Articles.TYPE_VIDEO_CANPLAY, R.layout.item_video_home);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeTophotIndexBean.Articles articles) {
        int adapterPosition = helper.getAdapterPosition();
        switch (articles.getItemType()) {
            case HomeTophotIndexBean.Articles.TYPE_TOP_BANNER://banner
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
                bindHorizaontalImgNewsData(helper, articles, adapterPosition);
                break;
            case 7://视频封面
                ILog.e(TAG, "convert  视频封面：==============================");
                bindVideoImageData(helper, articles, adapterPosition);
                break;
            case 8://上下滚动广告条
                bindUpdownSwitcherData(helper, articles, adapterPosition);
                break;
            case 9://Horizaontal 9menu
                bindHorizaontal9MenuData(helper, articles, adapterPosition);
                break;
            case 10://single BIG-IMAGE
                bindSingleBigNewsData(helper, articles, adapterPosition);
            case HomeTophotIndexBean.Articles.TYPE_VIDEO_CANPLAY://videoplayer
                ILog.e(TAG, "构造方法：============videoplayer==================");
                bindVideoPlayerNewsData(helper, articles, adapterPosition);
                break;
            default:
                break;

        }
    }

    /**
     * 视频-支持直接播放
     * @param helper
     * @param articles
     * @param adapterPosition
     */
    private void bindVideoPlayerNewsData(BaseViewHolder helper, HomeTophotIndexBean.Articles articles, int adapterPosition) {
        int position = helper.getAdapterPosition();
        ILog.e(TAG, "构造方法：============videoplayer 11==================");
        JzvdStd mVideoPlayer = helper.getView(R.id.videoplayers);
        if (mVideoPlayer != null) {
            LinearLayout llVideoPlayer = helper.getView(R.id.llVideoPlayer);
            // 将列表中的每个视频设置为默认16:9的比例
            ViewGroup.LayoutParams params = mVideoPlayer.getLayoutParams();
            // 宽度为屏幕宽度
            params.width = llVideoPlayer.getResources().getDisplayMetrics().widthPixels;
            // 高度为宽度的9/16
            params.height = (int) (params.width * 9f / 16f);
            mVideoPlayer.setLayoutParams(params);
            bindData(mVideoPlayer, articles);
            helper.setText(R.id.videoChannel, articles.mediaName == null ? "" : articles.mediaName);
            helper.setText(R.id.tvVideoCommentNum, articles.commentNum +"");
        }
    }

    /**
     * 视频封面大图
     *
     * @param helper
     * @param item
     * @param adapterPosition
     */
    private void bindVideoImageData(BaseViewHolder helper, HomeTophotIndexBean.Articles item, int adapterPosition) {
        helper.setText(R.id.tvVideoTitle, item.title == null ? "" : item.title);
        helper.setText(R.id.tvSourceName_best, item.mediaName == null ? "" : item.mediaName);
        ((ExpandImageView) helper.getView(R.id.img_video)).setImageURI(item.getPics().get(0) == null ? "" : item.getPics().get(0).url);
        helper.addOnClickListener(R.id.flVideo);
        helper.setOnClickListener(R.id.flVideo, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUitl.showShort(item.title == null ? "" : item.title);
            }
        });
        helper.setText(R.id.tvDate, DateTimeUtils.getLongDateToString(item.virtualTime) + "");
        if (item.videos.size() > 0) {
            helper.setText(R.id.tvTotalText, DateTimeUtils.formatVideoTime(item.videos.get(0).duration) + "");
        }

        LinearLayout view = helper.getView(R.id.article_left_best);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUitl.showShort("视频："+adapterPosition+item.title);
            }
        });
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

    private void bindTopBannerData(BaseViewHolder helper, final HomeTophotIndexBean.Articles item, int position) {
        Banner mBanner = helper.getView(R.id.banner1);
//        mBanner.setBannerStyle()
        List<String> titles = new ArrayList<>();
        if (titles != null && titles.size() > 0) titles.clear();
        if (bannerList != null) bannerList.clear();
        else bannerList = new ArrayList<>();
        initAnim();
        //数据
        for (HomeTophotIndexBean.Articles.Pics a : item.getPics()) {
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
    private void bindSingleNewsData(BaseViewHolder helper, HomeTophotIndexBean.Articles item, int position) {
        LinearLayout view = helper.getView(R.id.llContent);
        ((ExpandImageView) helper.getView(R.id.article_img)).setImageURI(item.getPics().get(0).url);
        helper.setText(R.id.tvSourceName, item.mediaName == null ? "" : item.mediaName);
        helper.setText(R.id.tvCommentnum, item.commentNum + "");
        helper.setText(R.id.tvArticletitle, item.title == null ? "" : item.title);
        helper.setText(R.id.tvDate, DateTimeUtils.getLongDateToString(item.virtualTime) + "");
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ToastUitl.showShort("单图："+position+item.title);
                HotNewsDetailActivity.newInstance(context, item.articleUrl == null ? "" : item.articleUrl);
            }
        });

    }

    /**
     * 单张大图
     *
     * @param helper
     * @param item
     * @param adapterPosition
     */
    private void bindSingleBigNewsData(BaseViewHolder helper, HomeTophotIndexBean.Articles item, int adapterPosition) {
        LinearLayout view = helper.getView(R.id.article_left_best);
        helper.setText(R.id.tvSourceName_best, item.mediaName == null ? "" : item.mediaName);
        helper.setText(R.id.tvCommentnum_best, item.commentNum + "");
        helper.setText(R.id.tvArticletitle_best, item.title == null ? "" : item.title);
        ((ExpandImageView) helper.getView(R.id.img_single_big)).setImageURI(item.getPics().get(0).url);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ToastUitl.showShort("单张大图："+adapterPosition+item.title);
                HotNewsDetailActivity.newInstance(context, item.articleUrl == null ? "" : item.articleUrl);
            }
        });
    }

    /**
     * 通用广告-三图拼接
     *
     * @param helper
     * @param item
     * @param adapterPosition
     */
    private void bindTYAdImageData(BaseViewHolder helper, HomeTophotIndexBean.Articles item, int adapterPosition) {
        if (item.getPics().size() >= 3) {
            ((ExpandImageView) helper.getView(R.id.tymenu_a)).setImageURI(item.getPics().get(0).url);
            ((ExpandImageView) helper.getView(R.id.tymenu_b)).setImageURI(item.getPics().get(1).url);
            ((ExpandImageView) helper.getView(R.id.tymenu_c)).setImageURI(item.getPics().get(2).url);
        }
    }

    /**
     * 三图
     *
     * @param helper
     * @param item
     * @param position
     */
    private void bindMultiImgNewsData(BaseViewHolder helper, HomeTophotIndexBean.Articles item, int position) {
        LinearLayout view = helper.getView(R.id.llContent);
        if (item.getPics().size() >= 3) {
            ((ExpandImageView) helper.getView(R.id.article_img1)).setImageURI(item.getPics().get(0).url);
            ((ExpandImageView) helper.getView(R.id.article_img2)).setImageURI(item.getPics().get(1).url);
            ((ExpandImageView) helper.getView(R.id.article_img3)).setImageURI(item.getPics().get(2).url);
        }
        helper.setText(R.id.Tarticle_title, item.title == null ? "" : item.title);
        helper.setText(R.id.tvTSourceName, item.mediaName == null ? "" : item.mediaName);
        helper.setText(R.id.tvTcommetnum, item.commentNum + "");
        helper.setText(R.id.tvDate, DateTimeUtils.getLongDateToString(item.virtualTime) + "");
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ToastUitl.showShort("三图："+position+item.title);
                HotNewsDetailActivity.newInstance(context, item.articleUrl == null ? "" : item.articleUrl);
            }
        });
    }

    /**
     * 无图
     *
     * @param helper
     * @param item
     * @param position
     */
    private void bindEmptyImgNewsData(BaseViewHolder helper, HomeTophotIndexBean.Articles item, int position) {
        helper.setText(R.id.Tarticle_title, item.title == null ? "" : item.title);
        helper.setText(R.id.tvTSourceName, item.mediaName == null ? "" : item.mediaName);
        helper.setText(R.id.tvTcommetnum, item.commentNum + "");
        helper.setText(R.id.tvDate, DateTimeUtils.getLongDateToString(item.virtualTime) + "");
        LinearLayout view = helper.getView(R.id.llSingleRoot);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ToastUitl.showShort("无图："+position+item.title);
                HotNewsDetailActivity.newInstance(context, item.articleUrl == null ? "" : item.articleUrl);
            }
        });
    }

    /**
     * 横向图片新闻列表
     *
     * @param helper
     * @param item
     * @param adapterPosition
     */
    private void bindHorizaontalImgNewsData(BaseViewHolder helper, HomeTophotIndexBean.Articles item, int adapterPosition) {
        RecyclerView recyclerView = helper.getView(R.id.spike_content_view);
        RelativeLayout rlMore = helper.getView(R.id.rlMore);
        LinearLayout llHorizontalList = helper.getView(R.id.llHorizontalList);
        PulToLeftViewGroup mHorizontalPullGroup = helper.getView(R.id.pull_group);
        //拖拽操作
        initDragMoreLayout(mHorizontalPullGroup);
        List<HomeTophotIndexBean.Articles.HotSpecialNews> hotSpecialNews = item.getHotSpecialNews();
        if (item.getHotSpecialNews() == null || item.getHotSpecialNews().size() <= 0) return;
        ((ImageView) helper.getView(R.id.hotNewsTitleIco)).setImageResource(R.mipmap.icon_title_hotnews);
        helper.setText(R.id.hotnewsMoreText, "查看更多");
//        helper.addOnClickListener(R.id.rlMore);
        rlMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HotNewsBestActivity.newInstance(context, null, AppConstant.HOT_NEWS_PARAMVALUE_TOP);
            }
        });
        //Recyclerview
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setFocusable(false);
        recyclerView.setFocusableInTouchMode(false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(10);
        SpikeContentAdapter adapter = new SpikeContentAdapter(context,R.layout.item_index_horizontal_list, hotSpecialNews);
        adapter.setEnableLoadMore(true);
        recyclerView.setAdapter(adapter);

    }


    /**
     * 横向分页九宫格菜单
     *
     * @param helper
     * @param item
     * @param adapterPosition
     */
    private void bindHorizaontal9MenuData(BaseViewHolder helper, HomeTophotIndexBean.Articles item, int adapterPosition) {
        ViewPager mPager = helper.getView(R.id.viewpager);
        LinearLayout mLlDot = helper.getView(R.id.ll_dot);
        //填充数据
        addData();
        //设置数据
        setMixData(mPager, mLlDot);

    }

    /**
     * 上下滚动广告轮播
     *
     * @param helper
     * @param item
     * @param adapterPosition
     */
    private void bindUpdownSwitcherData(BaseViewHolder helper, HomeTophotIndexBean.Articles item, int adapterPosition) {
        UpDownViewSwitcher viewSwitcher = helper.getView(R.id.home_view_switcher);

        viewSwitcher.setSwitcheNextViewListener(new UpDownViewSwitcher.SwitchNextViewListener() {
            @Override
            public void switchTONextView(View nextView, int index) {
                if (nextView == null) return;
                final String tag = "热";
                final String tag1 = item.getHotSpecialNews().get(index % item.getHotSpecialNews().size()).title;
                ((TextView) nextView.findViewById(R.id.switch_title_status)).setText(tag);
                ((TextView) nextView.findViewById(R.id.switch_title)).setText(tag1);
                nextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(v.getContext().getApplicationContext(), tag1, Toast.LENGTH_SHORT).show();
                        HotNewsBestActivity.newInstance(context, null, AppConstant.HOT_NEWS_PARAMVALUE_TOP);
                    }
                });
            }
        });
        viewSwitcher.setContentLayout(R.layout.item_home_buttelin_switch_view);
    }


    private void addData() {
        mDatas = new ArrayList<Menu9Model>();
        String[] titles = context.getResources().getStringArray(R.array.menu9_title);
        for (int i = 0; i < titles.length; i++) {
            //动态获取资源ID，第一个参数是资源名，第二个参数是资源类型例如drawable，string等，第三个参数包名
            int imageId = context.getResources().getIdentifier("ic_category_" + i, "mipmap", context.getPackageName());
            mDatas.add(new Menu9Model(titles[i], imageId));
        }
    }

    /**
     * 设置横向滑动分页九宫格菜单数据
     *
     * @param mPager
     * @param mLlDot
     */
    private void setMixData(ViewPager mPager, LinearLayout mLlDot) {
        if (mPager != null && mLlDot != null) {
            inflater = LayoutInflater.from(context);
            //总的页数=总数/每页数量，并取整
            pageCount = (int) Math.ceil(mDatas.size() * 1.0 / pageSize);
            mPagerList = new ArrayList<View>();
            for (int i = 0; i < pageCount; i++) {
                //每个页面都是inflate出一个新实例
                GridView gridView = (GridView) inflater.inflate(R.layout.menu9_gridview, mPager, false);
                gridView.setFocusable(false);
                gridView.setFocusableInTouchMode(false);
                gridView.setAdapter(new Home9MenuGridViewAdapter(context, mDatas, i, pageSize));
                mPagerList.add(gridView);

                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        int pos = position + curIndex * pageSize;
                        ToastUitl.showShort(mDatas.get(pos).getName());
                    }
                });
            }
            //设置适配器
            mPager.setAdapter(new Menu9ViewPagerAdapter(mPagerList));
            //设置圆点
            setOvalLayout(mPager, mLlDot);
        }
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

    private void initDragMoreLayout(final PulToLeftViewGroup horizontalDragMoreView) {
        if (horizontalDragMoreView != null) {
            horizontalDragMoreView.setOnPullToLeftListener(new PulToLeftViewGroup.OnPullToLeftListener() {
                @Override
                public void onReleaseFingerToUpload() {
                    //回弹
                    horizontalDragMoreView.completeToUpload();
                    HotNewsBestActivity.newInstance(context, null, AppConstant.HOT_NEWS_PARAMVALUE_TOP);
                }

                @Override
                public void onStartToUpload() {

                }
            });
        }

//            horizontalDragMoreView.setLoadMoreView(new DefaultDragMoreView()).setLoadMoreCallBack(new ICallBack() {
//                @Override
//                public void loadMore() {
//                    //加载更多，或者跳转到其他页面等等操作
//                    //加载更多数据模拟耗时操作
//                    //非加载数据，如跳转则无需耗时线程操作
//                    horizontalDragMoreView.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            //回弹
//                            horizontalDragMoreView.scrollBackToOrigin();
//                            HotNewsBestActivity.newInstance(context, null, AppConstant.HOT_NEWS_PARAMVALUE_TOP);
//                        }
//                    }, 1000);
//
//                }
//            });
//        }
    }

    /**
     * 设置圆点
     *
     * @param mPager
     * @param mLlDot
     */
    public void setOvalLayout(ViewPager mPager, final LinearLayout mLlDot) {
        if (mLlDot != null)mLlDot.removeAllViews();
        for (int i = 0; i < pageCount; i++) {
            mLlDot.addView(inflater.inflate(R.layout.menu9_dot, null));
        }
        // 默认显示第一页
        mLlDot.getChildAt(0).findViewById(R.id.v_dot)
                .setBackgroundResource(R.drawable.dot_selected);
        mPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageSelected(int position) {
                // 取消圆点选中
                mLlDot.getChildAt(curIndex)
                        .findViewById(R.id.v_dot)
                        .setBackgroundResource(R.drawable.dot_normal);
                // 圆点选中
                mLlDot.getChildAt(position)
                        .findViewById(R.id.v_dot)
                        .setBackgroundResource(R.drawable.dot_selected);
                curIndex = position;
            }

            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            public void onPageScrollStateChanged(int arg0) {
            }
        });
    }



    void bindData(JzvdStd mVideoPlayer, HomeTophotIndexBean.Articles articles) {
        ILog.e(TAG, "构造方法：============videoplayer 22==================");
        if (mVideoPlayer != null && articles.videos.size() > 0) {
            ILog.e(TAG, "构造方法：============videoplayer 33==================");
            //播放带清晰度的视频
            LinkedHashMap map = new LinkedHashMap();
            map.put("高清", articles.videos.get(0).url);
            map.put("普清", articles.videos.get(0).definitionInfos.size() >= 2 ? articles.videos.get(0).definitionInfos.get(0).url : articles.videos.get(0).url);
            map.put("标清", articles.videos.get(0).definitionInfos.size() >= 2 ? articles.videos.get(0).definitionInfos.get(1).url : articles.videos.get(0).url);
            JZDataSource jzDataSource = new JZDataSource(map, articles.title == null ? "" : articles.title);
            jzDataSource.looping = true;
            jzDataSource.currentUrlIndex = 2;
            jzDataSource.headerMap.put("key", "value");//header
            mVideoPlayer.setUp(jzDataSource,JzvdStd.SCREEN_WINDOW_NORMAL);
//            mVideoPlayer.setUp(articles.videos.get(0).url, articles.title == null ? "" : articles.title,Jzvd.SCREEN_WINDOW_NORMAL);
            TextView titleTextView = mVideoPlayer.titleTextView;
            titleTextView.setTextSize(13f);
            titleTextView.setMaxLines(2);
            titleTextView.setEllipsize(TextUtils.TruncateAt.END);
            //粗体
            titleTextView .setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            titleTextView.setPadding(0,10,0,10);
//            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) titleTextView.getLayoutParams();
//            p.setMargins(10,50,10,10);
//            titleTextView.requestLayout();
//            titleTextView.setLayoutParams(p);
        }
        mVideoPlayer.thumbImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        // 将列表中的每个视频设置为默认16:9的比例
        ViewGroup.LayoutParams params = mVideoPlayer.getLayoutParams();
        // 宽度为屏幕宽度
        params.width = mContext.getResources().getDisplayMetrics().widthPixels;
        // 高度为宽度的9/16
        params.height = (int) (params.width * 9f / 16f);
        mVideoPlayer.setLayoutParams(params);
        ImageLoaderUtils.display(mContext,mVideoPlayer.thumbImageView,articles.getPics().get(0).url);


    }

}


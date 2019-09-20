package com.cnews.guji.smart.ui.model.source;

import android.content.Context;

import com.cnews.guji.smart.R;
import com.cnews.guji.smart.common.bean.CategoryModel;
import com.cnews.guji.smart.ui.constant.NewsConstant;
import com.cnews.guji.smart.ui.model.HomeNewsTabBean;
import com.cnews.guji.smart.util.FileUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 视频数据提取
 * @author JSYL-DCL
 */
public class NewsSource<S> {

    /**
     * 要闻分类
     * @param mContext
     * @return
     */
    public static List<CategoryModel> getFrontNewsCategory(Context mContext) {
        List<CategoryModel> list = new ArrayList<CategoryModel>();
        list.add(new CategoryModel(mContext.getString(R.string.txt_type0)));
        list.add(new CategoryModel(mContext.getString(R.string.txt_type1)));
        return list;
    }


    /**
     * 视频分类
     * @param mContext
     * @return
     */
    public static List<CategoryModel> getVideoCategory(Context mContext) {

        List<CategoryModel> list = new ArrayList<CategoryModel>();

        list.add(new CategoryModel(mContext.getString(R.string.txt_type2)));
        list.add(new CategoryModel(mContext.getString(R.string.txt_type3)));
        list.add(new CategoryModel(mContext.getString(R.string.txt_type4)));
        list.add(new CategoryModel(mContext.getString(R.string.txt_type5)));
        list.add(new CategoryModel(mContext.getString(R.string.txt_type6)));
        list.add(new CategoryModel(mContext.getString(R.string.txt_type7)));
        list.add(new CategoryModel(mContext.getString(R.string.txt_type8)));
        list.add(new CategoryModel(mContext.getString(R.string.txt_type9)));
        list.add(new CategoryModel(mContext.getString(R.string.txt_type10)));
        list.add(new CategoryModel(mContext.getString(R.string.txt_type11)));
        list.add(new CategoryModel(mContext.getString(R.string.txt_type12)));
        list.add(new CategoryModel(mContext.getString(R.string.txt_type13)));

        return list;
    }

    /**
     * 视频随机获取分类
     * @param mContext
     * @return
     */
    public static String[] getVideoCategoryStr(Context mContext) {
        List<String> list = new ArrayList<String>();
        list.add(mContext.getString(R.string.txt_type2));
        list.add(mContext.getString(R.string.txt_type3));
        list.add(mContext.getString(R.string.txt_type4));
        list.add(mContext.getString(R.string.txt_type5));
        list.add(mContext.getString(R.string.txt_type6));
        list.add(mContext.getString(R.string.txt_type7));
        list.add(mContext.getString(R.string.txt_type8));
        list.add(mContext.getString(R.string.txt_type9));
        list.add(mContext.getString(R.string.txt_type10));
        list.add(mContext.getString(R.string.txt_type11));
        list.add(mContext.getString(R.string.txt_type12));
        list.add(mContext.getString(R.string.txt_type13));
        String[] videoCategorysStr = list.toArray(new String[list.size()]);
        return videoCategorysStr;
    }


    /**
     * 获取当前页面初始刷新数据来源的标识文件名
     * @param currentLoadMorePos 当前页面位置
     * @return
     */
    public static String getVideoRefreshTypeSource(int currentLoadMorePos) {
        String jsonNameStr = "";
        switch (currentLoadMorePos) {
            case 0:
                jsonNameStr = NewsConstant.ASSET_VIDEO_NEWS_TUIJIAN;
                break;
            case 1:
                jsonNameStr = NewsConstant.ASSET_VIDEO_NEWS_GAOXIAO;
                break;
            case 2:
                jsonNameStr = NewsConstant.ASSET_VIDEO_NEWS_MENGPET;
                break;
            case 3:
                jsonNameStr = NewsConstant.ASSET_VIDEO_NEWS_FOOD;
                break;
            case 4:
                jsonNameStr = NewsConstant.ASSET_VIDEO_NEWS_JUNSHI;
                break;
            case 5:
                jsonNameStr = NewsConstant.ASSET_VIDEO_NEWS_MUSIC;
                break;
            case 6:
                jsonNameStr = NewsConstant.ASSET_VIDEO_NEWS_YULE;
                break;
            case 7:
                jsonNameStr = NewsConstant.ASSET_VIDEO_NEWS_YUER;
                break;
            case 8:
                jsonNameStr = NewsConstant.ASSET_VIDEO_NEWS_MOVIE;
                break;
            case 9:
                jsonNameStr = NewsConstant.ASSET_VIDEO_NEWS_KEJI;
                break;
            case 10:
                jsonNameStr = NewsConstant.ASSET_VIDEO_NEWS_XIAOPIN;
                break;
            case 11:
                jsonNameStr = NewsConstant.ASSET_VIDEO_NEWS_DANCES;
                break;
            default:
                jsonNameStr = NewsConstant.ASSET_VIDEO_NEWS_TUIJIAN;
                break;
        }
        return jsonNameStr;
    }

    /**
     * 获取当前页面加载更多数据来源的标识文件名
     * @param currentLoadMorePos 当前页面位置
     * @return
     */
    public static String getVideoLoadMoreTypeSource(int currentLoadMorePos) {
        String jsonNameStr = "";
        switch (currentLoadMorePos) {
            case 0:
                jsonNameStr = NewsConstant.ASSET_VIDEO_NEWS_TUIJIAN_MORE;
                break;
            case 1:
                jsonNameStr = NewsConstant.ASSET_VIDEO_NEWS_GAOXIAO_MORE;
                break;
            case 2:
                jsonNameStr = NewsConstant.ASSET_VIDEO_NEWS_MENGPET_MORE;
                break;
            case 3:
                jsonNameStr = NewsConstant.ASSET_VIDEO_NEWS_FOOD_MORE;
                break;
            case 4:
                jsonNameStr = NewsConstant.ASSET_VIDEO_NEWS_JUNSHI_MORE;
                break;
            case 5:
                jsonNameStr = NewsConstant.ASSET_VIDEO_NEWS_MUSIC_MORE;
                break;
            case 6:
                jsonNameStr = NewsConstant.ASSET_VIDEO_NEWS_YULE_MORE;
                break;
            case 7:
                jsonNameStr = NewsConstant.ASSET_VIDEO_NEWS_YUER_MORE;
                break;
            case 8:
                jsonNameStr = NewsConstant.ASSET_VIDEO_NEWS_MOVIE_MORE;
                break;
            case 9:
                jsonNameStr = NewsConstant.ASSET_VIDEO_NEWS_KEJI_MORE;
                break;
            case 10:
                jsonNameStr = NewsConstant.ASSET_VIDEO_NEWS_XIAOPIN_MORE;
                break;
            case 11:
                jsonNameStr = NewsConstant.ASSET_VIDEO_NEWS_DANCES_MORE;
                break;
            default:
                jsonNameStr = NewsConstant.ASSET_VIDEO_NEWS_TUIJIAN_MORE;
                break;
        }
        return jsonNameStr;
    }


    /**
     * 获取默认tab标签列表
     * @param tabType 0:我的  1:更多
     * @param context context
     * @return
     */
    public static List getTabCurrentShowData(int tabType,Context context) {
        HomeNewsTabBean multiIndexJsonData = (HomeNewsTabBean) getMultiIndexJsonData(context, NewsConstant.ASSET_HOME_NEWS_TABS);
        if (multiIndexJsonData != null){
            if (tabType == 0){
                List<HomeNewsTabBean.Data.My> my = multiIndexJsonData.data.my;
                return my;
            }else if (tabType == 1){
                List<HomeNewsTabBean.Data.More> more = multiIndexJsonData.data.more;
                return more;
            }else {
                String[] tabtitles = new String[]{"头条","体育","居家","精选","兴趣","科技","养生"};
                return  Arrays.asList(tabtitles);

            }
        }else {
            return null;
        }
    }


    /**
     * 首页
     *          获取当前页面初始刷新数据来源的标识文件名
     * @param currentLoadPos 当前页面位置
     * @return
     */
    public static String getHomeRefreshTypeSource(int currentLoadPos) {
        String jsonNameStr = "";
        switch (currentLoadPos) {
            case 0:
                jsonNameStr = NewsConstant.ASSET_HOME_HOT_TOP;
                break;
            case 1:
                jsonNameStr = NewsConstant.ASSET_HOME_IMPORTANT;
                break;
            case 2://视频
                jsonNameStr = NewsConstant.ASSET_HOME_VIDEO;
                break;
            case 3:
                jsonNameStr = NewsConstant.ASSET_HOME_IMPORTANT;
                break;
            case 4:
                jsonNameStr = NewsConstant.ASSET_HOME_IMPORTANT;
                break;
            case 5:
                jsonNameStr = NewsConstant.ASSET_HOME_IMPORTANT;
                break;
            case 6:
                jsonNameStr = NewsConstant.ASSET_HOME_IMPORTANT;
                break;
            case 7:
                jsonNameStr = NewsConstant.ASSET_HOME_IMPORTANT;
                break;
            case 8:
                jsonNameStr = NewsConstant.ASSET_HOME_IMPORTANT;
                break;
            default:
                jsonNameStr = NewsConstant.ASSET_HOME_IMPORTANT;
                break;
        }
        return jsonNameStr;
    }



    /**
     * 首页
     *      获取当前页面加载更多数据来源的标识文件名
     * @param currentLoadMorePos 当前页面位置
     * @return
     */
    public static String getHomeLoadMoreTypeSource(int currentLoadMorePos) {
        String jsonNameStr = "";
        switch (currentLoadMorePos) {
            case 0:
                jsonNameStr = NewsConstant.ASSET_HOME_HOT_TOP;
                break;
            case 1:
                jsonNameStr = NewsConstant.ASSET_HOME_IMPORTANT;
                break;
            case 2:
                jsonNameStr = NewsConstant.ASSET_HOME_VIDEO;
                break;
            case 3:
                jsonNameStr = NewsConstant.ASSET_HOME_IMPORTANT;
                break;
            case 4:
                jsonNameStr = NewsConstant.ASSET_HOME_IMPORTANT;
                break;
            case 5:
                jsonNameStr = NewsConstant.ASSET_HOME_IMPORTANT;
                break;
            case 6:
                jsonNameStr = NewsConstant.ASSET_HOME_IMPORTANT;
                break;
            case 7:
                jsonNameStr = NewsConstant.ASSET_HOME_IMPORTANT;
                break;
            case 8:
                jsonNameStr = NewsConstant.ASSET_HOME_IMPORTANT;
                break;
            default:
                jsonNameStr = NewsConstant.ASSET_HOME_IMPORTANT;
                break;
        }
        return jsonNameStr;
    }










    /**
     * 解析json
     */
    public static HomeNewsTabBean getMultiIndexJsonData(Context context, final String fileName) {
        String json = FileUtils.getJson(context, fileName);
        Gson gson = new Gson();
        Type type = new TypeToken<HomeNewsTabBean>() {
        }.getType();
        return gson.fromJson(json, type);
    }

}

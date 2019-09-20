package com.cnews.guji.smart.base;

import com.cnews.guji.smart.api.HostType;

/**
 * 全局配置
 * @author JSYL-DCL
 */
public class AppConstant {
    public static final String APP_NAME = "";
    public static final float HOME_TAB_SELECTED_SIZE = 20;
    public static final float HOME_TAB_UNSELECTED_SIZE = 12;
    public static final String RES_SOURCE = "res://com.cnews.cloudnews.smart/";
    public static String  QQ_KEY = "mselpzkwgLW06NJVpGG99jFtJjox9CJo";
    public static String DOWNLOAD_URL = "https://vip.d0.baidupan.com/file/?AmRVa11sUmMBCAszBTAGalFuUGhV7wO8BbNTsFykBJ9VsAHyWZ5SWVJyAWZWLAIxWi4AZAAvCmMDIlY2BjMGawJnVV1daVJiAWoLaQVnBjBRPVBtVTIDOQUkUzNccwQ4VWcBZlk3UjdSMQFmVjQCNlomAHUALwo1AzZWYAZrBj8CLVUyXThSKQFlC2UFfgZmUW1QN1U7AzAFZ1NiXDcEPFVkAWdZMlJkUmUBYFY7AjpaMQAyAG0KMQMwVmQGaQYyAjRVMV1mUmMBYgs8BWMGKVFqUCVVYQMnBXdTJlxlBHdVPAEyWTlSNVI8AW5WMAI6WjMAIwArCmEDaVY1BjwGOgIzVTddNVI2AWYLZAVkBjRRP1BjVSQDLw==";
    public static final String HOME_MENU_URL_A = "https://img1.epetbar.com/2018-10/19/19/9aa905ec1644bf2773a451aca9af06d3.png@!water";
    public static final String HOME_MENU_URL_B = "https://img1.epetbar.com/2018-10/19/18/ed8fcf7e8b283838a02a6a1604cb0237.png@!water";
    public static final String HOME_MENU_URL_C = "https://img1.epetbar.com/2018-10/19/18/5156506734c2d1c2a8fbbaa74fae5494.png@!water";
    public static final String HOME_MENU_URL_D = "https://img1.epetbar.com/2018-10/19/18/8faa333a45eb95384551b712866e91ff.png@!water";
    public static final String HOME_MENU_URL_E = "https://img1.epetbar.com/2018-10/18/20/806c70e41b42268892244372dfe393f8.png@!water";
    public static final String SPLASH_AD_A = "https://img1.epetbar.com/2017-11/03/14/8bdd96fdb55d98ccd33e380bb035cce4.jpg";
    /**
     * 要闻_TEMP
     */
    public static final String FRONT_TOP_IMAGE_URL = "http://p.cdn.sohu.com/570d99e4/f11c20f6088243216710891bb3fcb229.jpeg";
    public static final String FRONT_TOP_IMAGE_URL1 = "http://p.cdn.sohu.com/570d99e4/95db07f938470bb2aa70fc97bc88ca70.jpeg";
    /**
     * 热闻传值标记
     */
    public static final int HOT_NEWS_PARAMVALUE_TOP = 0x001024;
    public static final int HOT_NEWS_PARAMVALUE_TOP1 = 0x001025;
    public static final int HOT_NEWS_PARAMVALUE_TOP2 = 0x001025;
    public static final int HOT_NEWS_PARAMVALUE_TOP3 = 0x001026;
    /**
     * 要闻状态标记
     */
    public static final int FRONT_NEWS_RIGHT_BOTTOM_RIGHTNOW = 0;
    public static final int FRONT_NEWS_WAIT_PUBLISH = 1;
    public static final int FRONT_NEWS_ALRIGHT_PUBLISH = 2;

    public static final String JSON_API_DATA = "app_data";
    /**
     * BUGLY_APPID
     */
    public static String APPLICATION_BUGLY_APPID = "6859d0b061";

    /**
     * UMENG_APPKEY
     */
    public static  String APPLICATION_UMENG_APPKEY = "5c8b40a00cafb200d2001182";

    /**
     * 下拉刷新数据状态
     */
    public static  final int SUCCESS = 1;
    public static  final int FAILED = 0;
    /**
     * 启动页GIF 与 Ad静态图切换标记
     */
    public static  boolean START_GIF = true;
    /**
     * 倒计时状态控制
     */
    public static  boolean COUNTDOWN_TIMER = true;

    public static final String CHANNEL_SWAP = "CHANNEL_SWAP";
    public static final String NEWS_CHANNEL_CHANGED = "NEWS_CHANNEL_CHANGED";
    public static final String CHANNEL_MINE = "CHANNEL_MINE";
    public static final String CHANNEL_MORE = "CHANNEL_MORE";
    public static final String HOME_CURRENT_TAB_POSITION="HOME_CURRENT_TAB_POSITION";
    public static final String SP_NAME = "News_share";
    public static final String MENU_SHOW_HIDE="MENU_SHOW_HIDE";
    public static final String NETEAST_HOST = "http://c.m.163.com/";
    public static final String END_URL = "-20.html";
    public static final String ENDDETAIL_URL = "/full.html";
    public static final String PROFILE_MORE = "PROFILE_MORE";

    // 新闻详情
    public static final String NEWS_DETAIL = NETEAST_HOST + "nc/article/";

    // 头条TYPE
    public static final String HEADLINE_TYPE = "headline";
    // 房产TYPE
    public static final String HOUSE_TYPE = "house";
    // 其他TYPE
    public static final String OTHER_TYPE = "list";

    //    // 北京
    //    public static final String LOCAL_TYPE = "local";
    //    // 北京的Id
    //    public static final String BEIJING_ID = "5YyX5Lqs";
    //example：http://c.m.163.com/nc/article/headline/T1348647909107/0-20.html

    //服饰
    public static final String GOODS_TYPE_NAME = "裤子";
    public static final String GOODS_TYPE_CODE = "utf-8";
    // 头条id
    public static final String HEADLINE_ID = "T1348647909107";
    // 房产id
    public static final String HOUSE_ID = "5YyX5Lqs";
    // 足球
    public static final String FOOTBALL_ID = "T1399700447917";
    // 娱乐
    public static final String ENTERTAINMENT_ID = "T1348648517839";
    // 体育
    public static final String SPORTS_ID = "T1348649079062";
    // 财经
    public static final String FINANCE_ID = "T1348648756099";
    // 科技
    public static final String TECH_ID = "T1348649580692";
    // 电影
    public static final String MOVIE_ID = "T1348648650048";
    // 汽车
    public static final String CAR_ID = "T1348654060988";
    // 笑话
    public static final String JOKE_ID = "T1350383429665";
    // 游戏
    public static final String GAME_ID = "T1348654151579";
    // 时尚
    public static final String FASHION_ID = "T1348650593803";
    // 情感
    public static final String EMOTION_ID = "T1348650839000";
    // 精选
    public static final String CHOICE_ID = "T1370583240249";
    // 电台
    public static final String RADIO_ID = "T1379038288239";
    // nba
    public static final String NBA_ID = "T1348649145984";
    // 数码
    public static final String DIGITAL_ID = "T1348649776727";
    // 移动
    public static final String MOBILE_ID = "T1351233117091";
    // 彩票
    public static final String LOTTERY_ID = "T1356600029035";
    // 教育
    public static final String EDUCATION_ID = "T1348654225495";
    // 论坛
    public static final String FORUM_ID = "T1349837670307";
    // 旅游
    public static final String TOUR_ID = "T1348654204705";
    // 手机
    public static final String PHONE_ID = "T1348649654285";
    // 博客
    public static final String BLOG_ID = "T1349837698345";
    // 社会
    public static final String SOCIETY_ID = "T1348648037603";
    // 家居
    public static final String FURNISHING_ID = "T1348654105308";
    // 暴雪游戏
    public static final String BLIZZARD_ID = "T1397016069906";
    // 亲子
    public static final String PATERNITY_ID = "T1397116135282";
    // CBA
    public static final String CBA_ID = "T1348649475931";
    // 消息
    public static final String MSG_ID = "T1371543208049";
    // 军事
    public static final String MILITARY_ID = "T1348648141035";

    /**
     * 视频 http://c.3g.163.com/nc/video/list/V9LG4CHOR/n/10-10.html
     */
    public static final String Video = "nc/video/list/";
    public static final String VIDEO_CENTER = "/n/";
    public static final String VIDEO_END_URL = "-10.html";
    // 热点视频
    public static final String VIDEO_HOT_ID = "V9LG4B3A0";
    // 娱乐视频
    public static final String VIDEO_ENTERTAINMENT_ID = "V9LG4CHOR";
    // 搞笑视频
    public static final String VIDEO_FUN_ID = "V9LG4E6VR";
    // 精品视频
    public static final String VIDEO_CHOICE_ID = "00850FRB";

    /**
     * 天气预报url
     */
    public static final String WEATHER_HOST = "http://wthrcdn.etouch.cn/";

    /**
     * 新浪图片新闻
     * http://gank.io/api/data/福利/{size}/{page}
     */
    public static final String SINA_PHOTO_HOST = "http://gank.io/api/";

    // 精选列表
    public static final String SINA_PHOTO_CHOICE_ID = "hdpic_toutiao";
    // 趣图列表
    public static final String SINA_PHOTO_FUN_ID = "hdpic_funny";
    // 美图列表
    public static final String SINA_PHOTO_PRETTY_ID = "hdpic_pretty";
    // 故事列表
    public static final String SINA_PHOTO_STORY_ID = "hdpic_story";

    // 图片详情
    public static final String SINA_PHOTO_DETAIL_ID = "hdpic_hdpic_toutiao_4";

    /**
     * 新闻id获取类型
     *
     * @param id 新闻id
     * @return 新闻类型
     */
    public static String getType(String id) {
        switch (id) {
            case HEADLINE_ID:
                return HEADLINE_TYPE;
            case HOUSE_ID:
                return HOUSE_TYPE;
            default:
                break;
        }
        return OTHER_TYPE;
    }

    /**
     * 获取对应的host
     *
     * @param hostType host类型
     * @return host
     */
    public static String getHost(int hostType) {
        String host;
        switch (hostType) {
            case HostType.NETEASE_NEWS_VIDEO://新闻的host
                host = NETEAST_HOST;
                break;
            case HostType.GANK_GIRL_PHOTO://图片的host
                host = SINA_PHOTO_HOST;
                break;
            case HostType.NEWS_DETAIL_HTML_PHOTO:
                host = "http://kaku.com/";
                break;
            default:
                host = "";
                break;
    }
        return host;
    }
}

package com.cnews.guji.smart.common.bean.basebean;

import com.github.library.entity.MultiItemEntity;

import java.util.List;

/**
 * @author：dingcl. home
 * |- 头条
 */
public class HomeTophotIndexBean {
    private String adcode;
    private String ads;
    private int adsType;
    private String adsdebug;
    private List<Articles> articles;
    private int channelId;
    private String channelName;
    private String city;
    private String displayInfo;
    private int errorCode;
    private String exts;
    private String ip;
    private String loadid;
    private long loadtime;
    private String message;
    private int reqtype;
    private int supplement;
    private int totalNum;

    /**
     * Articles
     */
    public class Articles implements MultiItemEntity {
        //banner
        public static final int TYPE_TOP_BANNER = 0;
        //一图+文字
        public static final int TYPE_SINGLE_IMAGE = 1;
        //通用广告三拼
        public static final int TYPE_TYADMODE_IMAGE = 2;
        //三图
        public static final int TYPE_THREE_IMAGE = 3;
        //无图
        public static final int TYPE_EMPTY_IMAGE = 4;
        //横向展览列表
        public static final int TYPE_HORIZAONTAL_IMAGE = 5;
        //视频封面
        public static final int TYPE_UP_VIDEO_IMAGE = 7;
        //上下滚动广告条
        public static final int TYPE_UP_DOWN_SWITCHER = 8;
        //横向滑动九宫格菜单
        public static final int TYPE_HORIZAONTAL_NINE_MENU = 9;
        //单张大图
        public static final int TYPE_SINGLE_BIG_IMAGE = 10;
        //支持直接播放视频
        public static final int TYPE_VIDEO_CANPLAY = 11;

        public String articleUrl;
        private Category category;
        public int commentNum;
        private int contentType;
        private long createTime;
        private List<FilterWords> filterWords;
        private int isRec;
        private int isTop;
        private String keyword;
        private String mediaId;
        public String mediaName;
        private String newsId;
        private int picNum;
        private List<Pics> pics;
        private int playCount;
        private int recommendEvent;
        private int recpool;
        private int recreason;
        private double score;
        private int state;
        private int template;
        public String title;
        public long virtualTime;
        public List<Smenulist> smenulist;
        public List<Videos> videos;

        public class Videos {
            public List<DefinitionInfos> definitionInfos;
            public double duration;
            public int hd_id;
            public String rate;
            public long size;
            public int type;
            public String url;
            public void setDefinitionInfos(List<DefinitionInfos> definitionInfos) {
                this.definitionInfos = definitionInfos;
            }
            public List<DefinitionInfos> getDefinitionInfos() {
                return definitionInfos;
            }

            /**
             * DefinitionInfos
             */
            public class DefinitionInfos {
                public long size;
                public String url;
            }
        }

        /**
         * 类型
         * @return
         */
        @Override
        public int getItemType() {
            return template;
        }

        private List<HotSpecialNews> hotSpecialNews;

        public List<HotSpecialNews> getHotSpecialNews() {
            return hotSpecialNews;
        }

        public void setHotSpecialNews(List<HotSpecialNews> hotSpecialNews) {
            this.hotSpecialNews = hotSpecialNews;
        }

        public List<Videos> getVideos() {
            return videos;
        }

        public void setVideos(List<Videos> videos) {
            this.videos = videos;
        }

        /**
         *HotSpecialNews
         */
        public class HotSpecialNews {
            public String articleUrl;
            public Category category;
            public int commentNum;
            public int contentType;
            public long createTime;
            public List<FilterWords> filterWords;
            public int isRec;
            public int isTop;
            public String keyword;
            public String mediaId;
            public String mediaName;
            public String mediaUrl;
            public String newsId;
            public int picNum;
            public List<Pics> pics;
            public int playCount;
            public int recommendEvent;
            public int score;
            public int state;
            public String title;
            public Flag flag;


            /**
             * Flag
             */
            public class Flag {
                private String bgColor;
                private String borderColor;
                private String fillBgColor;
                private String fontColor;
                private String icon;
                private String text;
                private int type;
            }
        }

        /**
         * Category
         */
        public class Category {

            private int id;
            private String name;
        }

        /**
         * FilterWords
         */
        public class FilterWords {
            private String name;
            private String type;
        }

        /**
         * Smenulist
         */
        public class Smenulist {
            public String img_url;
            public String below_text;
        }

        public List<Smenulist> getSmenulist() {
            return smenulist;
        }

        public void setSmenulist(List<Smenulist> smenulist) {
            this.smenulist = smenulist;
        }

        public int getSpanSize() {
//            if("recommended_ware".equals(itemType)){
//                return 2;
//            }
            return 4;
        }

        /**
         * Pics
         */
        public class Pics {
            private int height;
            public String url;
            private int width;
        }

        public List<Pics> getPics() {
            return pics;
        }

        public void setPics(List<Pics> pics) {
            this.pics = pics;
        }
    }

    public List<Articles> getArticles() {
        return articles;
    }

    public void setArticles(List<Articles> articles) {
        this.articles = articles;
    }


}

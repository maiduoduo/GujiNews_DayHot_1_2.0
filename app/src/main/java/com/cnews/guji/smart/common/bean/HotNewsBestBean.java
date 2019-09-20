package com.cnews.guji.smart.common.bean;

import com.github.library.entity.MultiItemEntity;

import java.util.List;

/**
 * @author：dingcl. home
 * |- 热闻精选
 */
public class HotNewsBestBean {
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
        public static final int TYPE_TOP_BANNER = 0;
        public static final int TYPE_SINGLE_IMAGE = 1;
        public static final int TYPE_TYADMODE_IMAGE = 2;
        public static final int TYPE_THREE_IMAGE = 3;
        public static final int TYPE_EMPTY_IMAGE = 4;
        //横向列表
        public static final int TYPE_HORIZAONTAL_IMAGE = 5;
        public static final int TYPE_SINGLE_BIG_IMAGE = 10;

        private String articleUrl;
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
        public Flag flag;


        /**
         * 类型
         * @return
         */
        @Override
        public int getItemType() {
            return template;
        }


        public class Flag{
            public String bgColor;
            public String borderColor;
            public String fillBgColor;
            public String fontColor;
            public String icon;
            public String text;
            public int type;
        }

        private List<HotSpecialNews> hotSpecialNews;

        public List<HotSpecialNews> getHotSpecialNews() {
            return hotSpecialNews;
        }

        public void setHotSpecialNews(List<HotSpecialNews> hotSpecialNews) {
            this.hotSpecialNews = hotSpecialNews;
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

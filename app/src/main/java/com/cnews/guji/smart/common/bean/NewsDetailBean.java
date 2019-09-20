package com.cnews.guji.smart.common.bean;

import java.util.List;

/**
 * author：JSYL-DCL on 2019/3/11
 * 新闻详情数据
 */
public class NewsDetailBean {

    /**
     * article_id : 6510449782014609408
     * ban_comment : 1
     * channels : []
     * content : <p>10日下午，中共中央总书记、国家主席、中央军委主席习近平参加十三届全国人大二次会议福建代表团审议。</p><p>原标题：习近平参加福建代表团审议</p><p></p>
     * description :
     * error_code : 0
     * images : []
     * keywords : ["政法","中央军委"]
     * label : ["中央军委"]
     * media : {"authors":[],"avatar_url":"","id":"6183527926458220562","name":"新华社"}
     * message : success
     * monitor : []
     * publish_time : 1552208472
     * recommend_event : 6
     * source : 5
     * subtitle :
     * title : 习近平参加福建代表团审议
     * topic : []
     * type : 1
     * videos : []
     */

    public String article_id;
    public int ban_comment;
    public List<Channels> channels;
    public String content;
    public String description;
    public int error_code;
    public List<Images> images;
    public List<String> keywords;
    public List<String> label;
    public Media media;
    public String message;
    public List<?> monitor;
    public long publish_time;
    public int recommend_event;
    public int source;
    public String subtitle;
    public String title;
    public List<?> topic;
    public int type;
    public List<?> videos;
    public List<?> article_relate_topics;
    public List<String> jokes;
    public List<String> meteor_category;
    public List<String> relate_banner_articles;
    public List<String> relate_recommend_articles;
    public int special_type;
    public int subtype;
    public String url;
    public List<String> zones;
    public static class Channels {
        public int id;
        public String name;
    }
    public static class Media {
        /**
         * authors : []
         * avatar_url :
         * id : 6183527926458220562
         * name : 新华社
         */

        public String avatar_url;
        public String id;
        public String name;
        public List<?> authors;


    }
    public static class Images {
        public String height;
        public String note;
        public String size;
        public String type;
        public String url;
        public String width;


    }
}

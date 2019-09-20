package com.cnews.guji.smart.common.bean;


import com.github.library.entity.MultiItemEntity;

import java.io.Serializable;
import java.util.List;

/**
 * author：JSYL-DCL on 2019/3/4
 * 私人信息
 */
public class ProfileCareBean implements Serializable {

    /**
     * profile_data_list : [{"type":0,"left_image":{"img_size":"250x36","img_url":"https://img2.epetbar.com/nowater/2018-11/22/09/390880ba4e224747ebde28b56fe807da.png","target":{"mode":"login","param":""}},"right_image":{"img_size":"","img_url":"","target":{"mode":"login","param":""}},"type_name":"我的订单","id":"70","order":"80"},{"type":1,"item_list":[{"above_image":{"img_size":"38x38","img_url":"https://img2.epetbar.com/nowater/2018-11/22/09/fb45b8f54a662a0574c941ffb4de2d88.png","target":{"mode":"login","param":""}},"above_text":"","below_text":"待付款","id":"71","superscript":"","target":{"mode":"login","param":""}},{"above_image":{"img_size":"38x38","img_url":"https://img2.epetbar.com/nowater/2018-11/22/09/51435308ce6c089a450fd3ab77a5520b.png","target":{"mode":"login","param":""}},"above_text":"","below_text":"待收货","id":"72","superscript":"","target":{"mode":"login","param":""}},{"above_image":{"img_size":"38x38","img_url":"https://img2.epetbar.com/nowater/2018-11/22/09/e0aac3c323912454b6b0c4222d3c0edd.png","target":{"mode":"login","param":""}},"above_text":"","below_text":"待评价","id":"73","superscript":"","target":{"mode":"login","param":""}},{"above_image":{"img_size":"38x38","img_url":"https://img2.epetbar.com/nowater/2018-11/22/09/c3109afb1d05a016e31394cff7e6bb99.png","target":{"mode":"login","param":""}},"above_text":"","below_text":"退换记录","id":"74","superscript":"","target":{"mode":"login","param":""}},{"above_image":{"img_size":"38x38","img_url":"https://img2.epetbar.com/nowater/2018-11/22/09/77d6a175fa1a030b57826f7c5bcda252.png","target":{"mode":"login","param":""}},"above_text":"","below_text":"国际订单","id":"75","superscript":"","target":{"mode":"login","param":""}}],"items_per_row":"5"},{"type":0,"id":"76","order":"70","type_name":"我的钱包","left_image":{"img_size":"250x36","img_url":"https://img2.epetbar.com/nowater/2018-11/22/09/b5103c1385810f1a1f76891c8ee6d3ef.png","target":{"mode":"login","param":""}},"right_image":{"img_size":"","img_url":"","target":{"mode":"login","param":""}}},{"type":2,"item_list":[{"above_image":{"img_size":"","img_url":"","target":{"mode":"login","param":""}},"above_text":"0","below_text":"优惠券","id":"77","superscript":"","target":{"mode":"login","param":""}},{"above_image":{"img_size":"","img_url":"","target":{"mode":"login","param":""}},"above_text":"0","below_text":"E宠币","id":"78","superscript":"","target":{"mode":"login","param":""}},{"above_image":{"img_size":"","img_url":"","target":{"mode":"login","param":""}},"above_text":"0.00","below_text":"余额","id":"79","superscript":"","target":{"mode":"login","param":""}},{"above_image":{"img_size":"","img_url":"","target":{"mode":"login","param":""}},"above_text":"0.00","below_text":"红包","id":"80","superscript":"","target":{"mode":"login","param":""}}],"items_per_row":4},{"type":0,"left_image":{"img_size":"250x36","img_url":"https://img2.epetbar.com/nowater/2018-11/22/09/1cadc7c68de1db0b2ef68929b5a97bd6.png","target":{"mode":"login","param":""}},"right_image":{"img_size":"","img_url":"","target":{"mode":"login","param":""}},"id":"81","order":"50","type_name":"我的服务"},{"type":3,"item_list":[{"above_image":{"img_size":"38x38","img_url":"https://img2.epetbar.com/nowater/2018-11/28/16/4322689f4d28ec8ba8e4eff73a333803.png"},"above_text":"","below_text":"联系客服","id":"82","superscript":""},{"above_image":{"img_size":"38x38","img_url":"https://img2.epetbar.com/nowater/2018-11/28/17/d3813a6151551b28c2b34c358f4afd3c.png"},"above_text":"","below_text":"潮品预售","id":"83","superscript":""},{"above_image":{"img_size":"38x38","img_url":"https://img2.epetbar.com/nowater/2018-11/22/09/5e73c64777a9468146843c538aad2543.png"},"above_text":"","below_text":"我的团购","id":"89","superscript":""},{"above_image":{"img_size":"38x38","img_url":"https://img2.epetbar.com/nowater/2018-11/28/17/42ae00bbb4a33fa804b0d852e0233c19.png"},"above_text":"","below_text":"我的收藏","id":"84","superscript":""},{"above_image":{"img_size":"38x38","img_url":"https://img2.epetbar.com/nowater/2018-11/28/17/97a4b02ff70fe492684838bf022d6cc9.png"},"above_text":"","below_text":"随手送礼","id":"97","superscript":""},{"above_image":{"img_size":"38x38","img_url":"https://img2.epetbar.com/nowater/2018-11/22/09/8563e3584dd6756bdd0ad23e3ee808e7.png"},"above_text":"","below_text":"我的兑换","id":"91","superscript":""},{"above_image":{"img_size":"38x38","img_url":"https://img2.epetbar.com/nowater/2018-11/28/17/c2a952ea1e481e67f7f0223574b93afd.png"},"above_text":"","below_text":"我的关注","id":"88","superscript":""},{"above_image":{"img_size":"38x38","img_url":"https://img2.epetbar.com/nowater/2018-11/22/09/928fe6cbd944c114206ace04f9094489.png"},"above_text":"","below_text":"萌爪联盟","id":"90","superscript":""},{"above_image":{"img_size":"38x38","img_url":"https://img2.epetbar.com/nowater/2018-11/28/17/1574544034bb0653a49d69c337a2db3c.png"},"above_text":"","below_text":"我的捐赠","id":"93","superscript":""},{"above_image":{"img_size":"38x38","img_url":"https://img2.epetbar.com/nowater/2019-01/10/17/18bd0640beaff4eaa8ee6b1956ddeefa.png"},"above_text":"","below_text":"商务合作","id":"100","superscript":""}],"items_per_row":4},{"type":4,"login_status":0,"mall_uid":"epet_0","type_name":"联系我们","mall_user":"","msg":"","push_alias":"appmall_a06e9ea8406832004520c7872366f0bb","push_tags":"version426","service_info":{"content":[{"label":"热线时间","value":"09:00 - 18:30"}],"online_service":{"label":"在线客服时间","value":"09:00 - 24:00"},"phone":"4008889200","title":"400-888-9200"},"sign_pam":{"mode":"web","param":"https://wap.epet.com/user/sign/UserSign.html"},"sys_time":1551687056}]
     * vip : {"vip_data":{"vipitems":[{"above_image":{"img_size":"123x32","img_url":"https://img2.epetbar.com/nowater/2018-11/22/08/71ff7f9ee43b98e8c68cb150f3f03169.png"},"above_text":"","below_text":"0元转盘","id":"65","superscript":""},{"above_image":{"img_size":"123x32","img_url":"https://img2.epetbar.com/nowater/2018-11/22/08/d3d0bc1a081b6f0412b6f045863865be.png"},"above_text":"","below_text":"0元体验","id":"66","superscript":""},{"above_image":{"img_size":"131x30","img_url":"https://img2.epetbar.com/nowater/2018-11/22/08/e6f08976d0cf4a51d7eb209ba4c89df7.png"},"above_text":"","below_text":"医疗服务","id":"67","superscript":""},{"above_image":{"img_size":"141x23","img_url":"https://img2.epetbar.com/nowater/2019-01/31/14/de8b18448de149a49288d3a2f6be91fe.png"},"above_text":"","below_text":"vip俱乐部","id":"68","superscript":""}],"items_per_row":4},"id":"64","order":"100","type":5,"type_name":"VIP","vip_level_image":{"img_size":"60x28","img_url":"https://static.epetbar.com/static_www/my_dev/vip/V0.png"}}
     */

    public List<Profile_data_list> profile_data_list;
    public Vip vip;

    /**
     * Profile_data_list
     */
    public static class Profile_data_list implements MultiItemEntity {
        /**
         * type : 0
         * left_image : {"img_size":"250x36","img_url":"https://img2.epetbar.com/nowater/2018-11/22/09/390880ba4e224747ebde28b56fe807da.png","target":{"mode":"login","param":""}}
         * right_image : {"img_size":"","img_url":"","target":{"mode":"login","param":""}}
         * type_name : 我的订单
         * id : 70
         * order : 80
         * item_list : [{"above_image":{"img_size":"38x38","img_url":"https://img2.epetbar.com/nowater/2018-11/22/09/fb45b8f54a662a0574c941ffb4de2d88.png","target":{"mode":"login","param":""}},"above_text":"","below_text":"待付款","id":"71","superscript":"","target":{"mode":"login","param":""}},{"above_image":{"img_size":"38x38","img_url":"https://img2.epetbar.com/nowater/2018-11/22/09/51435308ce6c089a450fd3ab77a5520b.png","target":{"mode":"login","param":""}},"above_text":"","below_text":"待收货","id":"72","superscript":"","target":{"mode":"login","param":""}},{"above_image":{"img_size":"38x38","img_url":"https://img2.epetbar.com/nowater/2018-11/22/09/e0aac3c323912454b6b0c4222d3c0edd.png","target":{"mode":"login","param":""}},"above_text":"","below_text":"待评价","id":"73","superscript":"","target":{"mode":"login","param":""}},{"above_image":{"img_size":"38x38","img_url":"https://img2.epetbar.com/nowater/2018-11/22/09/c3109afb1d05a016e31394cff7e6bb99.png","target":{"mode":"login","param":""}},"above_text":"","below_text":"退换记录","id":"74","superscript":"","target":{"mode":"login","param":""}},{"above_image":{"img_size":"38x38","img_url":"https://img2.epetbar.com/nowater/2018-11/22/09/77d6a175fa1a030b57826f7c5bcda252.png","target":{"mode":"login","param":""}},"above_text":"","below_text":"国际订单","id":"75","superscript":"","target":{"mode":"login","param":""}}]
         * items_per_row : 5
         * login_status : 0
         * mall_uid : epet_0
         * mall_user :
         * msg :
         * push_alias : appmall_a06e9ea8406832004520c7872366f0bb
         * push_tags : version426
         * service_info : {"content":[{"label":"热线时间","value":"09:00 - 18:30"}],"online_service":{"label":"在线客服时间","value":"09:00 - 24:00"},"phone":"4008889200","title":"400-888-9200"}
         * sign_pam : {"mode":"web","param":"https://wap.epet.com/user/sign/UserSign.html"}
         * sys_time : 1551687056
         */
        //顶部导航
        public static final int PROFILE_MY_TOPBAR = 0;
        //我的订单
        public static final int PROFILE_MY_ORDER_MENU = 1;
        //我的钱包
        public static final int PROFILE_MY_WALLET = 2;
        //我的服务
        public static final int PROFILE_MY_SERVICE = 3;
        //联系我们
        public static final int PROFILE_CONTACT_US = 4;


        public int type;
        public Left_image left_image;
        public Right_image right_image;
        public String type_name;
        public String id;
        public String order;
        public List<Item_list> item_list;
        public String items_per_row;
        public int login_status;
        public String mall_uid;
        public String push_alias;
        public String push_tags;
        public Service_info service_info;
        public Sign_pam sign_pam;
        public long sys_time;
        public String title_name;
        public String title_url;


        @Override
        public int getItemType() {
            return type;
        }


        public static class Left_image {
            /**
             * img_size : 250x36
             * img_url : https://img2.epetbar.com/nowater/2018-11/22/09/390880ba4e224747ebde28b56fe807da.png
             * target : {"mode":"login","param":""}
             */
            public String img_size;
            public String img_url;
        }

        public static class Right_image {
            /**
             * img_size :
             * img_url :
             * target : {"mode":"login","param":""}
             */

            public String img_size;
            public String img_url;

        }


        public static class Sign_pam {
            /**
             * mode : web
             * param : https://wap.epet.com/user/sign/UserSign.html
             */

            public String mode;
            public String param;

        }

        public static class Item_list {
            /**
             * above_image : {"img_size":"38x38","img_url":"https://img2.epetbar.com/nowater/2018-11/22/09/fb45b8f54a662a0574c941ffb4de2d88.png","target":{"mode":"login","param":""}}
             * above_text :
             * below_text : 待付款
             * id : 71
             * superscript :
             * target : {"mode":"login","param":""}
             */
            public Above_image above_image;
            public String above_text;
            public String below_text;
            public String id;
            public String superscript;
            public String img_size;
            public String img_url;


            public static class Above_image {
                public String img_size;
                public String img_url;
            }

        }

        /**
         * Service_info
         */
        public class Service_info {
            public List<Content> content;
            public Online_service online_service;
            public String phone;
            public String title;

            /**
             * Content
             */
            public class Content {
                public String label;
                public String value;

            }

            /**
             * Online_service
             */
            public class Online_service {

                public String label;
                public String value;

            }


        }
    }

    /**
     * Vip
     */
    public static class Vip {
        /**
         * vip_data : {"vipitems":[{"above_image":{"img_size":"123x32","img_url":"https://img2.epetbar.com/nowater/2018-11/22/08/71ff7f9ee43b98e8c68cb150f3f03169.png"},"above_text":"","below_text":"0元转盘","id":"65","superscript":""},{"above_image":{"img_size":"123x32","img_url":"https://img2.epetbar.com/nowater/2018-11/22/08/d3d0bc1a081b6f0412b6f045863865be.png"},"above_text":"","below_text":"0元体验","id":"66","superscript":""},{"above_image":{"img_size":"131x30","img_url":"https://img2.epetbar.com/nowater/2018-11/22/08/e6f08976d0cf4a51d7eb209ba4c89df7.png"},"above_text":"","below_text":"医疗服务","id":"67","superscript":""},{"above_image":{"img_size":"141x23","img_url":"https://img2.epetbar.com/nowater/2019-01/31/14/de8b18448de149a49288d3a2f6be91fe.png"},"above_text":"","below_text":"vip俱乐部","id":"68","superscript":""}],"items_per_row":4}
         * id : 64
         * order : 100
         * type : 5
         * type_name : VIP
         * vip_level_image : {"img_size":"60x28","img_url":"https://static.epetbar.com/static_www/my_dev/vip/V0.png"}
         */
        public Vip_data vip_data;
        public String id;
        public String order;
        public int type;
        public String type_name;
        public Vip_level_image vip_level_image;


        public static class Vip_data {
            /**
             * vipitems : [{"above_image":{"img_size":"123x32","img_url":"https://img2.epetbar.com/nowater/2018-11/22/08/71ff7f9ee43b98e8c68cb150f3f03169.png"},"above_text":"","below_text":"0元转盘","id":"65","superscript":""},{"above_image":{"img_size":"123x32","img_url":"https://img2.epetbar.com/nowater/2018-11/22/08/d3d0bc1a081b6f0412b6f045863865be.png"},"above_text":"","below_text":"0元体验","id":"66","superscript":""},{"above_image":{"img_size":"131x30","img_url":"https://img2.epetbar.com/nowater/2018-11/22/08/e6f08976d0cf4a51d7eb209ba4c89df7.png"},"above_text":"","below_text":"医疗服务","id":"67","superscript":""},{"above_image":{"img_size":"141x23","img_url":"https://img2.epetbar.com/nowater/2019-01/31/14/de8b18448de149a49288d3a2f6be91fe.png"},"above_text":"","below_text":"vip俱乐部","id":"68","superscript":""}]
             * items_per_row : 4
             */

            public List<Vipitems> vipitems;
            public int items_per_row;

            public static class Vipitems {
                /**
                 * above_image : {"img_size":"123x32","img_url":"https://img2.epetbar.com/nowater/2018-11/22/08/71ff7f9ee43b98e8c68cb150f3f03169.png"}
                 * above_text :
                 * below_text : 0元转盘
                 * id : 65
                 * superscript :
                 */

                public Above_image above_image;
                public String above_text;
                public String below_text;
                public String id;
                public String superscript;


                public static class Above_image {
                    /**
                     * img_size : 123x32
                     * img_url : https://img2.epetbar.com/nowater/2018-11/22/08/71ff7f9ee43b98e8c68cb150f3f03169.png
                     */

                    public String img_size;
                    public String img_url;
                }
            }
        }

        public static class Vip_level_image {
            /**
             * img_size : 60x28
             * img_url : https://static.epetbar.com/static_www/my_dev/vip/V0.png
             */
            public String img_size;
            public String img_url;

        }
    }



}

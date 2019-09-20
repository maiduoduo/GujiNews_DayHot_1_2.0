package com.cnews.guji.smart.ui.model;

import java.util.List;

/**
 * author：JSYL-DCL on 2019/2/25
 * 首页标签数据资源
 */
public class HomeNewsTabBean {
    /**
     * data : {"isCMS":true,"more":[{"belongChannel":2,"id":153,"name":"运势","needMark":false,"sort":2,"type":1},{"belongChannel":2,"id":18,"name":"宠物","needMark":false,"sort":3,"type":1},{"belongChannel":2,"id":40,"name":"历史","needMark":false,"sort":4,"type":1},{"belongChannel":2,"id":24,"name":"育儿","needMark":false,"sort":5,"type":1},{"belongChannel":2,"id":16,"name":"数码","needMark":false,"sort":6,"type":1},{"belongChannel":2,"id":77,"name":"游戏","needMark":false,"sort":7,"type":1},{"belongChannel":2,"id":11,"name":"动漫","needMark":false,"sort":8,"type":1},{"belongChannel":2,"id":10,"name":"美食","needMark":false,"sort":9,"type":1},{"belongChannel":2,"id":17,"name":"健身","needMark":false,"sort":10,"type":1},{"belongChannel":2,"id":15,"name":"旅游","needMark":false,"sort":11,"type":1},{"belongChannel":2,"id":22,"name":"穿搭","needMark":false,"sort":12,"type":1},{"belongChannel":2,"id":27,"name":"文化","needMark":false,"sort":13,"type":1},{"belongChannel":2,"id":28,"name":"文玩","needMark":false,"sort":14,"type":1},{"belongChannel":2,"id":29,"name":"房产","needMark":false,"sort":15,"type":1},{"belongChannel":2,"id":31,"name":"篮球","needMark":false,"sort":16,"type":1},{"belongChannel":2,"id":32,"name":"足球","needMark":false,"sort":17,"type":1}],"my":[{"belongChannel":1,"id":1,"name":"推荐","needMark":false,"sort":1,"type":1},{"belongChannel":1,"id":148,"name":"要闻","needMark":false,"sort":2,"type":1},{"belongChannel":1,"id":2,"name":"视频","needMark":false,"sort":3,"type":1},{"belongChannel":1,"id":3,"name":"娱乐","needMark":false,"sort":4,"type":1},{"belongChannel":1,"id":9,"name":"体育","needMark":false,"sort":5,"type":1},{"belongChannel":1,"id":109,"name":"国际","needMark":false,"sort":6,"type":1},{"belongChannel":1,"id":13,"name":"搞笑","needMark":false,"sort":7,"type":1},{"belongChannel":1,"id":100,"name":"本地","needMark":false,"sort":8,"type":1},{"belongChannel":1,"id":144,"name":"养生","needMark":false,"sort":9,"type":1},{"belongChannel":1,"id":152,"name":"三农","needMark":false,"sort":10,"type":1},{"belongChannel":1,"id":26,"name":"情感","needMark":false,"sort":11,"type":1},{"belongChannel":1,"id":19,"name":"手机","needMark":false,"sort":12,"type":1},{"belongChannel":2,"id":25,"name":"军事","needMark":false,"sort":13,"type":1},{"belongChannel":2,"id":122,"name":"财经","needMark":false,"sort":14,"type":1},{"belongChannel":2,"id":113,"name":"科技","needMark":false,"sort":15,"type":1},{"belongChannel":2,"id":14,"name":"汽车","needMark":false,"sort":16,"type":1},{"belongChannel":2,"id":7,"name":"时尚","needMark":false,"sort":17,"type":1}]}
     * errorCode : 0
     * message : success
     */
    public Data data;
    public int errorCode;
    public String message;


    public static class Data {
        /**
         * isCMS : true
         * more : [{"belongChannel":2,"id":153,"name":"运势","needMark":false,"sort":2,"type":1},{"belongChannel":2,"id":18,"name":"宠物","needMark":false,"sort":3,"type":1},{"belongChannel":2,"id":40,"name":"历史","needMark":false,"sort":4,"type":1},{"belongChannel":2,"id":24,"name":"育儿","needMark":false,"sort":5,"type":1},{"belongChannel":2,"id":16,"name":"数码","needMark":false,"sort":6,"type":1},{"belongChannel":2,"id":77,"name":"游戏","needMark":false,"sort":7,"type":1},{"belongChannel":2,"id":11,"name":"动漫","needMark":false,"sort":8,"type":1},{"belongChannel":2,"id":10,"name":"美食","needMark":false,"sort":9,"type":1},{"belongChannel":2,"id":17,"name":"健身","needMark":false,"sort":10,"type":1},{"belongChannel":2,"id":15,"name":"旅游","needMark":false,"sort":11,"type":1},{"belongChannel":2,"id":22,"name":"穿搭","needMark":false,"sort":12,"type":1},{"belongChannel":2,"id":27,"name":"文化","needMark":false,"sort":13,"type":1},{"belongChannel":2,"id":28,"name":"文玩","needMark":false,"sort":14,"type":1},{"belongChannel":2,"id":29,"name":"房产","needMark":false,"sort":15,"type":1},{"belongChannel":2,"id":31,"name":"篮球","needMark":false,"sort":16,"type":1},{"belongChannel":2,"id":32,"name":"足球","needMark":false,"sort":17,"type":1}]
         * my : [{"belongChannel":1,"id":1,"name":"推荐","needMark":false,"sort":1,"type":1},{"belongChannel":1,"id":148,"name":"要闻","needMark":false,"sort":2,"type":1},{"belongChannel":1,"id":2,"name":"视频","needMark":false,"sort":3,"type":1},{"belongChannel":1,"id":3,"name":"娱乐","needMark":false,"sort":4,"type":1},{"belongChannel":1,"id":9,"name":"体育","needMark":false,"sort":5,"type":1},{"belongChannel":1,"id":109,"name":"国际","needMark":false,"sort":6,"type":1},{"belongChannel":1,"id":13,"name":"搞笑","needMark":false,"sort":7,"type":1},{"belongChannel":1,"id":100,"name":"本地","needMark":false,"sort":8,"type":1},{"belongChannel":1,"id":144,"name":"养生","needMark":false,"sort":9,"type":1},{"belongChannel":1,"id":152,"name":"三农","needMark":false,"sort":10,"type":1},{"belongChannel":1,"id":26,"name":"情感","needMark":false,"sort":11,"type":1},{"belongChannel":1,"id":19,"name":"手机","needMark":false,"sort":12,"type":1},{"belongChannel":2,"id":25,"name":"军事","needMark":false,"sort":13,"type":1},{"belongChannel":2,"id":122,"name":"财经","needMark":false,"sort":14,"type":1},{"belongChannel":2,"id":113,"name":"科技","needMark":false,"sort":15,"type":1},{"belongChannel":2,"id":14,"name":"汽车","needMark":false,"sort":16,"type":1},{"belongChannel":2,"id":7,"name":"时尚","needMark":false,"sort":17,"type":1}]
         */
        public boolean isCMS;
        public List<More> more;
        public List<My> my;


        public static class More {
            /**
             * belongChannel : 2
             * id : 153
             * name : 运势
             * needMark : false
             * sort : 2
             * type : 1
             */

            public int belongChannel;
            public int id;
            public String name;
            public boolean needMark;
            public int sort;
            public int type;
        }

        public static class My {
            /**
             * belongChannel : 1
             * id : 1
             * name : 推荐
             * needMark : false
             * sort : 1
             * type : 1
             */
            public int belongChannel;
            public int id;
            public String name;
            public boolean needMark;
            public int sort;
            public int type;
        }

        public List<More> getMore() {
            return more;
        }

        public void setMore(List<More> more) {
            this.more = more;
        }

        public List<My> getMy() {
            return my;
        }

        public void setMy(List<My> my) {
            this.my = my;
        }
    }


}

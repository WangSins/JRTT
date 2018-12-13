package jrtt.imust.com.jrtt.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018-6-28.
 */

public class OneChannelData {
    /**
     * countcommenturl : http://jrtt.qianlong.com/client/content/countComment/
     * more : 10007/list_2.json
     * news : [{"comment":true,"commentlist":"http://10.0.2.2:8080/jrtt/10007/comment_1.json","commenturl":"http://jrtt.qianlong.com/client/user/newComment/35319","id":35311,"listimage":"http://10.0.2.2:8080/jrtt/10007/1.jpg","pubdate":"2014-10-1113:18","title":"网上大讲堂第368期预告：义务环保人人有责","type":0,"url":"http://10.0.2.2:8080/jrtt/10007/724D6A55496A11726628.html"},{"comment":true,"commentlist":"http://10.0.2.2:8080/jrtt/10007/comment_1.json","commenturl":"http://jrtt.qianlong.com/client/user/newComment/35319","id":35312,"listimage":"http://10.0.2.2:8080/jrtt/10007/2.jpg","pubdate":"2014-10-1111:20","title":"马路改建为停车场车位年费高达3000元","type":0,"url":"http://10.0.2.2:8080/jrtt/10007/724D6A55496A11726628.html"},{"comment":true,"commentlist":"http://10.0.2.2:8080/jrtt/10007/comment_1.json","commenturl":"http://jrtt.qianlong.com/client/user/newComment/35319","id":35313,"listimage":"http://10.0.2.2:8080/jrtt/10007/3.jpg","listimage1":"http://10.0.2.2:8080/jrtt/10007/4.jpg","listimage2":"http://10.0.2.2:8080/jrtt/10007/5.jpg","pubdate":"2014-10-1110:34","title":"北京两年内将迁出1200家工业污染企业","type":1,"url":"http://10.0.2.2:8080/jrtt/10007/724D6A55496A11726628.html"},{"comment":true,"commentlist":"http://10.0.2.2:8080/jrtt/10007/comment_1.json","commenturl":"http://jrtt.qianlong.com/client/user/newComment/35319","id":35314,"listimage":"http://10.0.2.2:8080/jrtt/10007/6.jpg","pubdate":"2014-10-1110:08","title":"大雾再锁京城机场航班全部延误","type":0,"url":"http://10.0.2.2:8080/jrtt/10007/724D6A55496A11726628.html"},{"comment":true,"commentlist":"http://10.0.2.2:8080/jrtt/10007/comment_1.json","commenturl":"http://jrtt.qianlong.com/client/user/newComment/35319","id":35315,"listimage":"http://10.0.2.2:8080/jrtt/10007/7.jpg","listimage1":"http://10.0.2.2:8080/jrtt/10007/8.jpg","listimage2":"http://10.0.2.2:8080/jrtt/10007/9.jpg","pubdate":"2014-10-1110:03","title":"APEC会议期间调休企业员工盼同步放假","type":1,"url":"http://10.0.2.2:8080/jrtt/10007/724D6A55496A11726628.html"},{"comment":true,"commentlist":"http://10.0.2.2:8080/jrtt/10007/comment_1.json","commenturl":"http://jrtt.qianlong.com/client/user/newComment/35319","id":35316,"listimage":"http://10.0.2.2:8080/jrtt/10007/10.jpg","pubdate":"2014-10-1109:59","title":"机械\u201c龙马\u201d巡演17日亮相奥园","type":0,"url":"http://10.0.2.2:8080/jrtt/10007/724D6A55496A11726628.html"},{"comment":true,"commentlist":"http://10.0.2.2:8080/jrtt/10007/comment_1.json","commenturl":"http://jrtt.qianlong.com/client/user/newComment/35319","id":35310,"listimage":"http://10.0.2.2:8080/jrtt/10007/11.jpg","listimage1":"http://10.0.2.2:8080/jrtt/10007/12.jpg","listimage2":"http://10.0.2.2:8080/jrtt/10007/13.jpg","pubdate":"2014-10-1109:54","title":"门头沟获批100套限价房","type":1,"url":"http://10.0.2.2:8080/jrtt/10007/724D6A55496A11726628.html"},{"comment":true,"commentlist":"http://10.0.2.2:8080/jrtt/10007/comment_1.json","commenturl":"http://jrtt.qianlong.com/client/user/newComment/35319","id":35318,"listimage":"http://10.0.2.2:8080/jrtt/10007/14.jpg","pubdate":"2014-10-1109:52","title":"APEC期间净空区放带灯风筝可拘留","type":0,"url":"http://10.0.2.2:8080/jrtt/10007/724D6A55496A11726628.html"},{"comment":true,"commentlist":"http://10.0.2.2:8080/jrtt/10007/comment_1.json","commenturl":"http://jrtt.qianlong.com/client/user/newComment/35314","id":35314,"listimage":"http://10.0.2.2:8080/jrtt/10007/15.jpg","pubdate":"2014-10-1109:48","title":"今起两自住房摇号","type":0,"url":"http://10.0.2.2:8080/jrtt/10007/724D6A55496A11726628.html"},{"comment":true,"commentlist":"http://10.0.2.2:8080/jrtt/10007/comment_1.json","commenturl":"http://jrtt.qianlong.com/client/user/newComment/35117","id":35117,"listimage":"http://10.0.2.2:8080/jrtt/10007/16.jpg","listimage1":"http://10.0.2.2:8080/jrtt/10007/17.jpg","listimage2":"http://10.0.2.2:8080/jrtt/10007/18.jpg","pubdate":"2014-10-1109:45","title":"故宫神武门广场拟夜间开放停车","type":1,"url":"http://10.0.2.2:8080/jrtt/10007/724D6A55496A11726628.html"}]
     * title : 北京
     * topic : [{"description":"11111111","id":10101,"listimage":"http://10.0.2.2:8080/jrtt/10007/1452327318UU91.jpg","sort":1,"title":"北京","url":"http://10.0.2.2:8080/jrtt/10007/list_1.json"},{"description":"22222222","id":10100,"listimage":"http://10.0.2.2:8080/jrtt/10007/1452327318UU91.jpg","sort":2,"title":"222222222222","url":"http://10.0.2.2:8080/jrtt/10007/list_1.json"}]
     * topnews : [{"comment":true,"commentlist":"http://10.0.2.2:8080/jrtt/10007/comment_1.json","commenturl":"http://jrtt.qianlong.com/client/user/newComment/35301","id":35301,"pubdate":"2014-04-0814:24","title":"蜗居生活","topimage":"http://10.0.2.2:8080/jrtt/10007/1452327318UU91.jpg","type":"news","url":"http://10.0.2.2:8080/jrtt/10007/724D6A55496A11726628.html"},{"comment":true,"commentlist":"http://10.0.2.2:8080/jrtt/10007/comment_1.json","commenturl":"http://jrtt.qianlong.com/client/user/newComment/35125","id":35125,"pubdate":"2014-04-0809:09","title":"中秋赏月","topimage":"http://10.0.2.2:8080/jrtt/10007/1452327318UU92.jpg","type":"news","url":"http://10.0.2.2:8080/jrtt/10007/724D6A55496A11726628.html"},{"comment":true,"commentlist":"http://10.0.2.2:8080/jrtt/10007/comment_1.json","commenturl":"http://jrtt.qianlong.com/client/user/newComment/35125","id":35126,"pubdate":"2014-04-0809:09","title":"天空翱翔","topimage":"http://10.0.2.2:8080/jrtt/10007/1452327318UU93.jpg","type":"news","url":"http://10.0.2.2:8080/jrtt/10007/724D6A55496A11726628.html"},{"comment":true,"commentlist":"http://10.0.2.2:8080/jrtt/10007/comment_1.json","commenturl":"http://jrtt.qianlong.com/client/user/newComment/35125","id":35127,"pubdate":"2014-04-0809:09","title":"感官设计","topimage":"http://10.0.2.2:8080/jrtt/10007/1452327318UU94.png","type":"news","url":"http://10.0.2.2:8080/jrtt/10007/724D6A55496A11726628.html"}]
     */

    public String countcommenturl;
    public String more;
    public String title;
    public List<NewsBean> news;
    public List<TopicBean> topic;
    public List<TopnewsBean> topnews;

    //定义一个这则新闻在数据库的哪个表进行保存
    @DatabaseTable(tableName = "news_bean")
    public static class NewsBean implements Serializable{
        /**
         * comment : true
         * commentlist : http://10.0.2.2:8080/jrtt/10007/comment_1.json
         * commenturl : http://jrtt.qianlong.com/client/user/newComment/35319
         * id : 35311
         * listimage : http://10.0.2.2:8080/jrtt/10007/1.jpg
         * pubdate : 2014-10-1113:18
         * title : 网上大讲堂第368期预告：义务环保人人有责
         * type : 0
         * url : http://10.0.2.2:8080/jrtt/10007/724D6A55496A11726628.html
         * listimage1 : http://10.0.2.2:8080/jrtt/10007/4.jpg
         * listimage2 : http://10.0.2.2:8080/jrtt/10007/5.jpg
         */
        @DatabaseField(generatedId = true)
        public int id_;//数据库中每条记录都要有id
        @DatabaseField(columnName = "comment")
        public boolean comment;
        @DatabaseField(columnName = "commentlist")
        public String commentlist;
        @DatabaseField(columnName = "commenturl")
        public String commenturl;
        @DatabaseField(columnName = "id")
        public int id;
        @DatabaseField(columnName = "listimage")
        public String listimage;
        @DatabaseField(columnName = "pubdate")
        public String pubdate;
        @DatabaseField(columnName = "title")
        public String title;
        @DatabaseField(columnName = "type")
        public int type;
        @DatabaseField(columnName = "url")
        public String url;
        @DatabaseField(columnName = "listimage1")
        public String listimage1;
        @DatabaseField(columnName = "listimage2")
        public String listimage2;
    }

    public static class TopicBean {
        /**
         * description : 11111111
         * id : 10101
         * listimage : http://10.0.2.2:8080/jrtt/10007/1452327318UU91.jpg
         * sort : 1
         * title : 北京
         * url : http://10.0.2.2:8080/jrtt/10007/list_1.json
         */

        public String description;
        public int id;
        public String listimage;
        public int sort;
        public String title;
        public String url;
    }

    public static class TopnewsBean {
        /**
         * comment : true
         * commentlist : http://10.0.2.2:8080/jrtt/10007/comment_1.json
         * commenturl : http://jrtt.qianlong.com/client/user/newComment/35301
         * id : 35301
         * pubdate : 2014-04-0814:24
         * title : 蜗居生活
         * topimage : http://10.0.2.2:8080/jrtt/10007/1452327318UU91.jpg
         * type : news
         * url : http://10.0.2.2:8080/jrtt/10007/724D6A55496A11726628.html
         */

        public boolean comment;
        public String commentlist;
        public String commenturl;
        public int id;
        public String pubdate;
        public String title;
        public String topimage;
        public String type;
        public String url;
    }
    //json解析必须保存成员变量名字必须跟json中变量名一样


}

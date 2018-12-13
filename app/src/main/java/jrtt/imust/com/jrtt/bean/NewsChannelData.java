package jrtt.imust.com.jrtt.bean;

import java.util.List;

/**
 * Created by Administrator on 2018-6-28.
 */

public class NewsChannelData {


    public List<ListBean> list;

    public static class ListBean {
        /**
         * id : 10007
         * title : 北京
         * type : 1
         * url : 10007/list_1.json
         */

        public int id;
        public String title;
        public int type;
        public String url;
    }
}

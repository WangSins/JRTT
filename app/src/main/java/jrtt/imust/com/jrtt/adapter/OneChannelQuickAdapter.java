package jrtt.imust.com.jrtt.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.joanzapata.android.BaseAdapterHelper;
import com.joanzapata.android.QuickAdapter;

import java.util.List;

import jrtt.imust.com.jrtt.R;
import jrtt.imust.com.jrtt.bean.OneChannelData;

/**
 * Created by Administrator on 2018-6-28.
 */
//适配要提供给列表，几行，什么行视图，显示什么数据。 1，集合，2，视图 3，显示逻辑
public class OneChannelQuickAdapter extends QuickAdapter<OneChannelData.NewsBean> {
    public OneChannelQuickAdapter(Context context, int layoutResId, List<OneChannelData.NewsBean> data) {
        super(context, layoutResId, data);
    }
    @Override
    protected void convert(BaseAdapterHelper helper, OneChannelData.NewsBean item) {
      /*  {
            "comment": true,
                "commentlist": "http://10.0.2.2:8080/jrtt/10007/comment_1.json",
                "commenturl": "http://jrtt.qianlong.com/client/user/newComment/35319",
                "id": 35314,
                "listimage": "http://10.0.2.2:8080/jrtt/10007/6.jpg",
                "pubdate": "2014-10-1110:08",
                "title": "大雾再锁京城机场航班全部延误",
                "type": 0,
                "url": "http://10.0.2.2:8080/jrtt/10007/724D6A55496A11726628.html"
        }*/

        //显示标题
        helper.setText(R.id.one_channel_new_title,item.title);
        //显示日期
        helper.setText(R.id.one_channel_new_date,item.pubdate);
        //加载图片
       ImageView imageView =  helper.getView(R.id.one_channel_new_image);
        Glide.with(context).load(item.listimage).into(imageView);
    }
}

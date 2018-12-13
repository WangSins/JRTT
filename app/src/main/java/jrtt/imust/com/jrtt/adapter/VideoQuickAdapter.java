package jrtt.imust.com.jrtt.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.joanzapata.android.BaseAdapterHelper;
import com.joanzapata.android.QuickAdapter;

import java.util.List;

import jrtt.imust.com.jrtt.R;
import jrtt.imust.com.jrtt.bean.VideoData;

/**
 * 1,使用QuickAdapter必须继承
 * 2,通过QuickAdapter<泛型>明确知道元素
 * 3,生成一个构造方法，convert 显示逻辑
 * 4，适配器提供ListView显示的需要行数，视图
 */

public class VideoQuickAdapter extends QuickAdapter<VideoData.DataBean.VlistBean> {
    public VideoQuickAdapter(Context context, int layoutResId, List<VideoData.DataBean.VlistBean> data) {
        super(context, layoutResId, data);
    }
    @Override
    protected void convert(BaseAdapterHelper helper, VideoData.DataBean.VlistBean item) {
            helper.setText(R.id.text_title,item.shortTitle+" "+item.vt);//标题
            //加载截图 vpic
        ImageView image =  helper.getView(R.id.imge_video);
        Glide.with(context).load(item.vpic).into(image);
    }
}

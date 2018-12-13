package jrtt.imust.com.jrtt.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.joanzapata.android.BaseAdapterHelper;
import com.joanzapata.android.QuickAdapter;

import java.util.List;

import jrtt.imust.com.jrtt.R;
import jrtt.imust.com.jrtt.bean.PicData;

/**
 * 此处要给图片ImageView加载内容
 * 1,compile 'com.github.bumptech.glide:glide:3.7.0'
 * 2,编写加载代码
 *  Glide.with(上下文).load(url).into(image);
 */

public class PicQuickAdapter extends QuickAdapter<PicData.NewsBean> {
    public PicQuickAdapter(Context context, int layoutResId, List<PicData.NewsBean> data) {
        super(context, layoutResId, data);
    }
    @Override
    protected void convert(BaseAdapterHelper helper, PicData.NewsBean item) {
        //显示文字
        helper.setText(R.id.text,item.title);
       ImageView imageview =  helper.getView(R.id.image);
        //图片一会再来处理
        Glide.with(context).load(item.listimage).into(imageview);
    }
}

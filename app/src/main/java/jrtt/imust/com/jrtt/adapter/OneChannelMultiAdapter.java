package jrtt.imust.com.jrtt.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jrtt.imust.com.jrtt.R;
import jrtt.imust.com.jrtt.bean.OneChannelData;
import jrtt.imust.com.jrtt.db.JrttOrmHelper;
import jrtt.imust.com.jrtt.db.NewRecoder;

/**
 * 我们现在需要根据type= 0 显示 一张图片的视图，type=1显示三张图片的视图
 * 1，叫做复杂列表
 * 行的视图不止一种
 * 2,准备 多种视图
 * item_new.xml
 * item_new_three.xml
 * 3,继承 BaseAdapter
 * 4,可以把ButterKnife使用上，因为它可以自动生成ViewHolder
 */

public class OneChannelMultiAdapter extends BaseAdapter {
    private Context context;
    private List<OneChannelData.NewsBean> news;
    private  Dao<NewRecoder, Integer> dao;
    public OneChannelMultiAdapter(Context context, List<OneChannelData.NewsBean> news) {
        this.context = context;
        this.news = news;
        JrttOrmHelper jrttOrmHelper = new JrttOrmHelper(context);
        try {
            dao = jrttOrmHelper.getDao(NewRecoder.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //提供行数
    @Override
    public int getCount() {
        return news.size();
    }

    //每一行现在要进行一个判断,默认列表的行的种类都是1，只有一种，每次我们都布局一个item...xml
    @Override
    public int getViewTypeCount() {
        return 2;
    }

    //而是类型。 item_new.xml  0  item_new_three.xml  1
    @Override
    public int getItemViewType(int position) {
        //看服务端给我的数据
        int type = news.get(position).type;//0 一张 1三张
        if (type == 0) {
            return 0;//item_new.xml
        } else {
            return 1;//item_new_three.xml
        }
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        int itemType = getItemViewType(i);//getItemViewType用来判断当前应该使用哪种视图


        //getView在这里面打印 getView方法,调用次数过多，所以最好不在这里反复创建对象
        System.out.println("getView");
        OneChannelData.NewsBean item = news.get(i);
        boolean  isRead = false;
        try {
            List<NewRecoder> recoderList = dao.queryForEq("new_id", item.id + "");//where new_id = "199423"
            if(recoderList.size()==0){
                isRead =  false;
            }else{//能查找一个新闻的id
                isRead = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        if (itemType == 0) {//显示一张图片
            //1,优化写一个if判断
            ViewHolderOne  holderOne = null;
            if(view == null){
                view = View.inflate(context, R.layout.item_new, null);
                holderOne = new ViewHolderOne(view);//由butterknife生成的viewHolder可以马上使用
                //下次显示数据，需要使用缓存ViewHolder，将它们两个绑在一起
                view.setTag(holderOne);
            }else{// !=null
                //重用
                holderOne = (ViewHolderOne) view.getTag();
            }

            OneChannelData.NewsBean newsBean = news.get(i);
            //显示逻辑 一般就是设置值
            holderOne.oneChannelNewTitle.setText(newsBean.title);
            holderOne.oneChannelNewDate.setText(newsBean.pubdate);

            if(isRead){
                holderOne.oneChannelNewTitle.setTextColor(Color.GRAY);
                holderOne.oneChannelNewDate.setTextColor(Color.GRAY);
            }else{
                holderOne.oneChannelNewDate.setTextColor(Color.BLACK);//黑色
                holderOne.oneChannelNewDate.setTextColor(Color.BLACK);
            }

            Glide.with(context).load(newsBean.listimage).into(holderOne.oneChannelNewImage);
            return view;
//

        } else {//!= 0

            //此处是编写显示三张图片的赋值
            ViewHolderThree viewHolderThree =  null;
            if(view == null){
                view = View.inflate(context, R.layout.item_new_three, null);
                //第二步优化  ViewHolder优化掉findViewById
                viewHolderThree = new ViewHolderThree(view);//内部butterKinife生成findViewById代码
                view.setTag(viewHolderThree);
            }else{
                // view ! = null;
                viewHolderThree = (ViewHolderThree) view.getTag();
            }
            //赋值

            OneChannelData.NewsBean newsBean = news.get(i);
            //显示逻辑 一般就是设置值
            viewHolderThree.oneChannelNewTitle.setText(newsBean.title);
            viewHolderThree.oneChannelNewDate.setText(newsBean.pubdate);

            if(isRead){
                viewHolderThree.oneChannelNewTitle.setTextColor(Color.GRAY);
                viewHolderThree.oneChannelNewDate.setTextColor(Color.GRAY);
            }else{
                viewHolderThree.oneChannelNewTitle.setTextColor(Color.BLACK);//未读显示黑
                viewHolderThree.oneChannelNewDate.setTextColor(Color.BLACK);
            }


            Glide.with(context).load(newsBean.listimage).into(viewHolderThree.oneChannelNewImage1);
            Glide.with(context).load(newsBean.listimage1).into(viewHolderThree.oneChannelNewImage2);
            Glide.with(context).load(newsBean.listimage2).into(viewHolderThree.oneChannelNewImage3);
            return view;
        }

    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }


    /*
    一个图片视图的ViewHolder
     */
    static class ViewHolderOne {
        @BindView(R.id.one_channel_new_title)
        TextView oneChannelNewTitle;
        @BindView(R.id.one_channel_new_date)
        TextView oneChannelNewDate;
        @BindView(R.id.one_channel_new_image)
        ImageView oneChannelNewImage;

        ViewHolderOne(View view) {
            ButterKnife.bind(this, view);
        }
    }
    /*
    这是三张图片的ViewHolder
     */
    static class ViewHolderThree {
        @BindView(R.id.one_channel_new_title)
        TextView oneChannelNewTitle;
        @BindView(R.id.one_channel_new_image1)
        ImageView oneChannelNewImage1;
        @BindView(R.id.one_channel_new_image2)
        ImageView oneChannelNewImage2;
        @BindView(R.id.one_channel_new_image3)
        ImageView oneChannelNewImage3;
        @BindView(R.id.one_channel_new_date)
        TextView oneChannelNewDate;

        ViewHolderThree(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

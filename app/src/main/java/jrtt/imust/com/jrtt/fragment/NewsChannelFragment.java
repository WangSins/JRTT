package jrtt.imust.com.jrtt.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TableLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;

import jrtt.imust.com.jrtt.R;
import jrtt.imust.com.jrtt.bean.NewsChannelData;
import jrtt.imust.com.jrtt.fragment.sub.SubFagment;
import jrtt.imust.com.jrtt.fragment.sub.TabChannelFragment;
import jrtt.imust.com.jrtt.net.JrttApi;
import jrtt.imust.com.jrtt.net.OnDataLoadListener;
import jrtt.imust.com.jrtt.net.ResponseData;
import jrtt.imust.com.jrtt.net.RetrofitUtil;
import retrofit2.Call;

public class NewsChannelFragment  extends BaseFragment implements OnDataLoadListener{
   private View view;
   private  NewsChannelData data;
   private ViewPager viewPager;
   private TabLayout tabLayouts;
   private HashMap<String,TabChannelFragment> pages = new HashMap<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        initViews();
        requestNet();
        return  view;
    }

    @Override
    public void initViews() {
        view =  View.inflate(getContext(),R.layout.fragment_news_channel,null);
        //获取指示器
         tabLayouts = view.findViewById(R.id.tab_layout);
        //页面
         viewPager = view.findViewById(R.id.tab_viewpager);
    }

    @Override
    public void requestNet() {
        JrttApi api =  RetrofitUtil.get();
        Call<ResponseData> call = api.getDataNews();
        //调用RetrofitUtil工具
        RetrofitUtil.send(getContext(),call,NewsChannelFragment.this);
    }

    @Override
    public void parsejson(String json) {
        System.out.println(json);
        Gson gson = new Gson();
         //注意：如果服务端返回的josn数据是[]
        // 我们给.fromJson传参数 from(json,ArrayList<String>.class) 语法上不支持
        // {list:...}
        //一个class类对应一个json对象
        String json_ = "{list:"+json+"}";
         data =  gson.fromJson(json_,NewsChannelData.class);
         //遍历一下这个页面多少个标题，一个标题对应一个新闻列表页面(三级),有多少标题就会对应
        //多少个页面
         for(NewsChannelData.ListBean item: data.list){
             System.out.println(item.title+" "+item.url);
             TabChannelFragment tabChannelFragment = new TabChannelFragment();
             tabChannelFragment.setUrl(item.url);//每个页面都有访问地址
             pages.put(item.title,tabChannelFragment);
         }
        System.out.println(data.list.size());
    }

    @Override
    public void showData() {
      /*  for(NewsChannelData.ListBean  item: data.list){
            System.out.println(item.title);
        }*/
         //1,先展示出来一个ViewPager
       final  NewAdapter adapter =new NewAdapter(getFragmentManager());
        //只要给viewPager设置一个适配器，它就可以产生多个子页面，可以滑动切换页面
        viewPager.setAdapter(adapter);
        //后面没有数据，是没有缓存，造成数据被释放，所以，只要设置把所有页面缓存下就行了
        //tabLayout可以指示页面叫什么名字，同时也可以指定页面属于第几个。
        //用法非常简单,只要将有adapter的viewPager绑定给tabLayout
        tabLayouts.setupWithViewPager(viewPager);
    }
    class NewAdapter extends FragmentPagerAdapter{

        public NewAdapter(FragmentManager fm) {
            super(fm);
        }

        ///CharSequence字序列 ，把几个字符放在一个起、不就是字符串
        //'a' 'b' 'c '   "abc"
        @Nullable
        @Override
        public String getPageTitle(int position) {

            NewsChannelData.ListBean bean = data.list.get(position);//
         /* {
            "id": 10011,
                    "title": "财经",
                    "type": 1,
                    "url": "10011/list_1.json"
            }*/
         return bean.title;
        }

        @Override
        public Fragment getItem(int position) {
            //因为viewPager包含多个页面，每一个页面我们都保存在pages这个hashMap
            //viewpager的页面由它的adapter提供
            //getItem这个方法就是adpater，我们在这里机根据标题提供页面出来
            NewsChannelData.ListBean newBean = data.list.get(position);
            TabChannelFragment tabChannelFragment = pages.get(newBean.title);

            return tabChannelFragment;
        }

        @Override
        public int getCount() {
            return data.list.size();
        }
    }
}

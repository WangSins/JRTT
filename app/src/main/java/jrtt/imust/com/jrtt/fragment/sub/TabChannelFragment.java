package jrtt.imust.com.jrtt.fragment.sub;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jrtt.imust.com.jrtt.NewDetailActivity;
import jrtt.imust.com.jrtt.R;
import jrtt.imust.com.jrtt.adapter.OneChannelMultiAdapter;
import jrtt.imust.com.jrtt.adapter.OneChannelQuickAdapter;
import jrtt.imust.com.jrtt.bean.OneChannelData;
import jrtt.imust.com.jrtt.db.JrttOrmHelper;
import jrtt.imust.com.jrtt.db.NewRecoder;
import jrtt.imust.com.jrtt.net.JrttApi;
import jrtt.imust.com.jrtt.net.OnDataLoadListener;
import jrtt.imust.com.jrtt.net.ResponseData;
import jrtt.imust.com.jrtt.net.RetrofitUtil;
import retrofit2.Call;

/**
 * 1,一个可以下拉拖，也可以往上拖动的PullToRefreshListView
 *      1,1, 将开源控件库的代码引用进来成为个ModelLib
 *      1,2, 依赖    当前app项目，使用这个app去依赖控件库
 *      1,3, 在布局文件放一个PullToRefreshListView
 *      1,4,请求网络数据json
 *      1,5,解析json
 *      1,6, 使用Adapter，创建一个Adapter
 *      1,7, 将适配设置给PullToRefreshListView
 */
public class TabChannelFragment extends Fragment implements OnDataLoadListener{
    private String url;//表示该页面获取数据的地址
    //【3】定义一个下拉刷新滚动加载专用的集合
    private boolean isPull = false;
    private int fangXiang = -1;
    //如果往下拖动  fangXiang =1;
    //如果往上拖动  fangXiang = 11
    //拖动结束   fangXiang = -1;
    private List<OneChannelData.NewsBean> allList = new ArrayList<>();
    public void setUrl(String url) {
        this.url = url;
    }

    private  OneChannelData data;
    private View view;
    private PullToRefreshListView listView;
    public TabChannelFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
             System.out.println("执行oncreateView方法"+view);
             if(view == null){
                 initViews();
                 requestNet();
             }else{

                 ViewParent parent = view.getParent();
                 System.out.println("执行oncreateView方法view!=null"+parent);
                 if(parent!=null){
                    ViewGroup viewGroup = (ViewGroup) parent;
                     viewGroup.removeView(view);
                 }
             }
        return view;//
    }


    @Override
    public void initViews() {
       view =  View.inflate(getContext(),R.layout.fragment_tab_channel,null);
        listView = view.findViewById(R.id.list_view);
        //【1】给这个ListView设置，可以下拉，可以滚动
        listView.setMode(PullToRefreshBase.Mode.BOTH);
        //【2】添加一监听器
        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                //获取最新的数据，拿 到之后，是要清空集合
                fangXiang=1;
                requestNet();
            }
            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                //获多更多的数据.拿到之后，直接添加到原有记录的后面
                fangXiang=11;
                requestNet();
            }
        });
    }


    @Override
    public void requestNet() {
        JrttApi api = RetrofitUtil.get();

        String requestUrl ;
        if(fangXiang == 11 ){//取得下一页数据
            requestUrl = data.more;
            System.out.println(requestUrl);
        }else{
            requestUrl=url;
        }

        System.out.println("当前的请求地址："+requestUrl);

        //请求地址不能为 "" 如果是 空字符串就返回
        if(TextUtils.isEmpty(requestUrl)){
            Toast.makeText(getContext(), "没有更多", Toast.LENGTH_SHORT).show();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    //需要关闭等待效果
                    listView.onRefreshComplete();
                }
            }, 1000);
            return;
        }
        //10007/list_1.json  10007  list_1.json
        String[] args = requestUrl.split("\\/");
        Call<ResponseData> call = api.getDataOneChannelNew(args[0],args[1]);//只要去接口中修改就可以
        //调用send方法访问服务端
        RetrofitUtil.send(getContext(),call,TabChannelFragment.this);
    }

    @Override
    public void parsejson(String json) {
        System.out.println(json);
        //解析json
        Gson gson = new Gson();
        data = gson.fromJson(json,OneChannelData.class);

        //如果是进来页面，当时集合没有数据，直接添加到集合中
        if(isPull){
                if(fangXiang == 1){//往下拖
                    allList.clear();//清空
                    //放入最新的记录
                    allList.addAll(data.news);
                    //必须让适配器更新一下
                }else if( fangXiang == 11) {
                    //==1  往上滚动 说明用户要使用更多的新闻记录来查看，这时不要清空集合，直接加就可以
                    //显示的效果是越加越多
                    allList.addAll(data.news);
                }
        }else{
            //直接加数据到集合
            allList.addAll(data.news);//将获取服务端数据加到当前集合，以后适配器就显示这个集合的数据
            isPull=true;//后面的操作只能是拖动了
        }


    }

    private  OneChannelMultiAdapter adapter;
    @Override
    public void showData() {
      /*  for ( OneChannelData.NewsBean bean  :  data.news){
            System.out.println(bean.title);
        }*/
      if(adapter == null){
          //创建一个Adapter
          // OneChannelQuickAdapter adapter = new OneChannelQuickAdapter(getContext(),R.layout.item_new,data.news);
          //只是显示一种行视图的效果，我们现在需要根据type= 0 显示 一张图片的视图，type=1显示三张图片的视图
          adapter  = new  OneChannelMultiAdapter(getContext(),allList);
          //先使用再创建的思路
          listView.setAdapter(adapter);
          listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
              @Override
              public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                  //1,将这条新闻的id放到数据库中，表示记录了这条新闻已读
                  OneChannelData.NewsBean newsBean = data.news.get(i-1);//点第一行，给我的位置 1，不对。应该给0
                  Toast.makeText(getContext(), "id:"+newsBean.id+" "+newsBean.title, Toast.LENGTH_SHORT).show();
                  //改成往数据库中添加一个记录
                  JrttOrmHelper helper= new JrttOrmHelper(getContext());
                  //获取dao  Database Access  Object
                  try {
                      Dao<NewRecoder, Integer> dao = helper.getDao(NewRecoder.class);
                      //判断该新闻在数据库没有保存过,
                      List<NewRecoder> list = dao.queryForEq("new_id", newsBean.id+"");//where  new_id = 10000;
                      if(list.size() == 0){
                          //不存在，才进行保存
                          NewRecoder recoder = new NewRecoder(newsBean.id+"");
                          dao.create(recoder);//将这条记录插入到数据库新闻表中
                          //全部查询
                          //如何刷一个ListView,显示集合的一个View,如果集合内容发生改变，少了一个，多一个

                          adapter.notifyDataSetChanged();//将集合变化这个事情通知ListView
                      /*  List<NewRecoder> newRecoders = dao.queryForAll();
                        for (NewRecoder item: newRecoders){
                            System.out.println("已经保存在数据库中的新闻 id"+item.getNewId());
                        }*/
                      }
                  } catch (SQLException e) {
                      e.printStackTrace();
                  }
                  //2,为你打开一个详情
                  Intent intent = new Intent(getContext(), NewDetailActivity.class);
                  //把哪一条的新闻记录传过来,当你传入一个自定义类的对象，需要对该对象加上一个什么东西
                  //给类加上一个序列化接口，就可以使用intent的putExtra
                  intent.putExtra("oneNew",newsBean);
                  startActivity(intent);
              }
          });
      }else{
          //刷新
          adapter.notifyDataSetChanged();//获取数据后进行图片
          fangXiang = -1;
          handler.postDelayed(new Runnable() {
              @Override
              public void run() {
                  //需要关闭等待效果
                  listView.onRefreshComplete();
              }
          }, 1000);

      }
    }
    private  Handler handler = new Handler();
}

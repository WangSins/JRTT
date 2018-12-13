package jrtt.imust.com.jrtt.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import jrtt.imust.com.jrtt.R;
import jrtt.imust.com.jrtt.adapter.VideoQuickAdapter;
import jrtt.imust.com.jrtt.bean.VideoData;
import jrtt.imust.com.jrtt.net.JrttApi;
import jrtt.imust.com.jrtt.net.ResponseData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/*
当前我们在这里开发一个视频列表页面
    1,在fragment_video布局文件中，放一个<ListView...
    2.可以访问video.json
    3,写上一个Retrofit的请求代码
    4,解析这个json数据
    5.不能直接把集合设置给ListVieW，而要创建一个Adapter
    6,再设置给列表ListView
 */
public class VideoFragment未使用接口 extends BaseFragment{
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       // 1,在fragment_video布局文件中，放一个<ListView..
        View  v = View.inflate(getContext(), R.layout.fragment_video,null);
        //2.可以访问video.json
        final ListView listView = v.findViewById(R.id.listview);
        // 3,写上一个Retrofit的请求代码
        //http://ip:8080/jrtt/video.json
        String  baseUrl = "http://10.0.2.2:8080/jrtt/";
        //3.1 搞定retrofit对象
        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create())
                .build();
        //3.2 读请求需要的参数
        JrttApi api = retrofit.create(JrttApi.class);
        Call<ResponseData> call = api.getVideoDaata();
        //3.3.放入请求不想写线程，来一个包含线程的请求队列

         call.enqueue(new Callback<ResponseData>() {
             @Override
             public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                  //拿到数据的情况
                 ResponseData body = response.body();
                 String json = body.data ;
                 //json;
                  System.out.println("成功:"+json);
                // 4,解析这个json数据
                 Gson gson = new Gson();
                 VideoData  video= gson.fromJson(json,VideoData.class);
                 System.out.println("有多少集:"+video.data.vlist.size());//75
                 //5.不能直接把集合设置给ListVieW，而要创建一个Adapter
                 VideoQuickAdapter adapter = new VideoQuickAdapter(getContext(),R.layout.item_video,video.data.vlist);
                 //6,再设置给列表ListView
                 listView.setAdapter(adapter);
             }
             @Override
             public void onFailure(Call<ResponseData> call, Throwable t) {
                //如果衣求失败，提示
                 Toast.makeText(getContext(), "请求失败", Toast.LENGTH_SHORT).show();
             }
         });
        return v;
    }
}

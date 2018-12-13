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
import jrtt.imust.com.jrtt.adapter.PicQuickAdapter;
import jrtt.imust.com.jrtt.bean.PicData;
import jrtt.imust.com.jrtt.net.JrttApi;
import jrtt.imust.com.jrtt.net.ResponseData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/*
组图页面
    就是以一个列表的方式显示图片的集合
    使用 ListView
    1,到布局fragment_pic 里面添加一个 <ListView ...
    2,回到PicFragment查找View
    3,获取服务端的数据
    4，将json转成集合
    5, 创建一个adapter
    6, 设置给ListView
 */
public class PicFragment未使用接口 extends BaseFragment{
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //1,到布局fragment_pic 里面添加一个 <ListView ...
       View view = View.inflate(getContext(), R.layout.frgament_pic,null);
        //2,回到PicFragment查找View
        final ListView listview = view.findViewById(R.id.listview);
        // 3,获取服务端的数据
       //http://localhost:8080/jrtt/photos/photos_1.json
        String baseUrl = "http://10.0.2.2:8080/jrtt/";
        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create())
                .build();
        //读取接口中配置的参数
        JrttApi api =  retrofit.create(JrttApi.class);
        Call<ResponseData> call = api.getDataPic();
        //call可以放在线程队列里面等一个线程
        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                    ResponseData responseData =response.body();
                    System.out.println("获取数据成功:"+responseData.data);
               // 4，将json转成集合
                 //解析json数据使用gson库
                Gson gson = new Gson();
                PicData picData = gson.fromJson(responseData.data,PicData.class);

                //5, 创建一个adapter
                PicQuickAdapter adapter = new PicQuickAdapter(getContext(),R.layout.item_pic,picData.news);
                // 6, 设置给ListView
                listview.setAdapter(adapter);
                System.out.println(picData.news.size());
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                //获取数据失败
                Toast.makeText(getContext(), "onFailure", Toast.LENGTH_SHORT).show();
            }
        });//执行
        return view;
    }
}

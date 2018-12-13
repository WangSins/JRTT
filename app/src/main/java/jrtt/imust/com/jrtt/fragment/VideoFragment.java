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
import jrtt.imust.com.jrtt.net.OnDataLoadListener;
import jrtt.imust.com.jrtt.net.ResponseData;
import jrtt.imust.com.jrtt.net.RetrofitUtil;
import jrtt.imust.com.jrtt.test.inter.IPage;
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
public class VideoFragment extends BaseFragment implements OnDataLoadListener {
    private   View v;
    private   ListView listView;
    private  VideoData  video;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        initViews();
        requestNet();
        return v;
    }

    @Override
    public void initViews() {
        // 1,在fragment_video布局文件中，放一个<ListView..
         v = View.inflate(getContext(), R.layout.fragment_video,null);
        //2.可以访问video.json
          listView = v.findViewById(R.id.listview);
    }

    @Override
    public void requestNet() {
        // 3,写上一个Retrofit的请求代码
        JrttApi api = RetrofitUtil.get();
        Call<ResponseData> call = api.getVideoDaata();
        RetrofitUtil.send(getContext(),call,VideoFragment.this);
    }

    @Override
    public void parsejson(String json) {
        // 4,解析这个json数据
        Gson gson = new Gson();
        video= gson.fromJson(json,VideoData.class);
        System.out.println("有多少集:"+video.data.vlist.size());//75
    }

    @Override
    public void showData() {
        //5.不能直接把集合设置给ListVieW，而要创建一个Adapter
        VideoQuickAdapter adapter = new VideoQuickAdapter(getContext(),R.layout.item_video,video.data.vlist);
        //6,再设置给列表ListView
        listView.setAdapter(adapter);
    }
}

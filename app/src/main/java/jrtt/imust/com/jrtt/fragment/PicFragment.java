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

import java.io.IOException;

import jrtt.imust.com.jrtt.R;
import jrtt.imust.com.jrtt.adapter.PicQuickAdapter;
import jrtt.imust.com.jrtt.bean.PicData;
import jrtt.imust.com.jrtt.net.JrttApi;
import jrtt.imust.com.jrtt.net.OnDataLoadListener;
import jrtt.imust.com.jrtt.net.ResponseData;
import jrtt.imust.com.jrtt.net.RetrofitUtil;
import jrtt.imust.com.jrtt.test.inter.IPage;
import okhttp3.ResponseBody;
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
public class PicFragment extends BaseFragment implements OnDataLoadListener {
    private  ListView listview;
    private View view;
    private PicData picData;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        initViews();
        requestNet();
        return view;
    }

    @Override
    public void initViews() {
        //1,到布局fragment_pic 里面添加一个 <ListView ...
         view = View.inflate(getContext(), R.layout.frgament_pic,null);
        //2,回到PicFragment查找View
         listview = view.findViewById(R.id.listview);
    }

    @Override
    public void requestNet() {
        // 3,获取服务端的数据
        //http://localhost:8080/jrtt/photos/photos_1.json
        Call<ResponseData> call = api.getDataPic();
        RetrofitUtil.send(getContext(),call,PicFragment.this);
    }

    @Override
    public void parsejson(String json) {
        // 4，将json转成集合
        //解析json数据使用gson库
        Gson gson = new Gson();
         picData = gson.fromJson(json,PicData.class);
    }

    @Override
    public void showData() {
        //5, 创建一个adapter
        PicQuickAdapter adapter = new PicQuickAdapter(getContext(),R.layout.item_pic,picData.news);
        // 6, 设置给ListView
        listview.setAdapter(adapter);
        System.out.println(picData.news.size());
    }
}

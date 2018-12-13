package jrtt.imust.com.jrtt.net;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.Gson;

import jrtt.imust.com.jrtt.fragment.BaseFragment;
import jrtt.imust.com.jrtt.fragment.PicFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 1,工具类
 *      将重复的代码抽取成工具
 * 2,一般是静态的
 *      类名称.方法名称()
 */

public class RetrofitUtil {
    public static JrttApi get(){
        //1,设置baseUrl
        String  baseUrl = "http://10.0.2.2:8080/jrtt/";
        //2.创建一个Retorift核心对象
        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create())
                .build();
        //3.读的你的请求参数
        JrttApi api =  retrofit.create(JrttApi.class);
        return api;
    }
    public static void send(final Context context, Call<ResponseData> call, final OnDataLoadListener listener){

        //4.
        /* Call<ResponseData> call1 = api.getDataPic();
         Call<ResponseData> call2 = api.getVideoDaata();*/

        //5.放进队列进行请求
        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                    //当网络请求成功的时候，判断网络状态，来提示
                    ResponseData body = response.body();//里面就只有两个值 retcode  data 需要json解析
                if(200 ==  body.retcode){
                    //可以对json进行解析
                    Gson gson = new Gson();
                    //
                    String json = body.data;
                    System.out.println(json);
                    //解析json
                    listener.parsejson(json);//是不是组图页面就可以进行json解析
                    //显示数据
                    listener.showData();//是不是组图页面就可以进行数据显示
                }else{
                    Toast.makeText(context, "获取服务端数据异常", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                    //当网络请求失败的时候，我们来文字提示，获取服务端数据异常
                Toast.makeText(context, "获取服务端数据异常", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

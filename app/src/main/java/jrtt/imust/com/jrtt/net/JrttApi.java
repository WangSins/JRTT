package jrtt.imust.com.jrtt.net;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Administrator on 2018-6-26.
 */

public interface JrttApi {
        /*@GET 表示使用get请求
        括号内写的是后半段的路径
        http://localhost:8080/jrtt/photos/photos_1.json*/
        @GET("photos/photos_1.json")
        Call<ResponseData> getDataPic();

        //http://localhost:8080/jrtt/video.json
        @GET("video.json")
         Call<ResponseData> getVideoDaata();

        //http://localhost:8080/jrtt/home.json
        @GET("home.json")
       Call<ResponseData> getDataNews();
        //http://localhost:8080/jrtt/10007/list_1.json
        /* @GET("10007/list_1.json")
        Call<ResponseData> getDataOneChannelNew();*/
        @GET("{id}/{page}")//@GET里面的{}表示占位,将来由真实数据来替换
        Call<ResponseData> getDataOneChannelNew(@Path("id") String id, @Path("page") String page);//数据传过来
        //必须 指定替换   10007    {id}   list_1.json 替换  {page}  10007/list_1.json

}

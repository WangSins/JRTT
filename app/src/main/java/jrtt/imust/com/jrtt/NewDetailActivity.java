package jrtt.imust.com.jrtt;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jrtt.imust.com.jrtt.bean.OneChannelData;
import jrtt.imust.com.jrtt.db.JrttOrmHelper;

/*
1,页面上有一标题栏
    返回    关闭页面
    右侧
        收藏    将一条新闻记录保存在数据库中
        取消收藏   将这一条新闻记录从数据库中删除
        字体       将网页上的字调大调小
2,显示新闻详情
        网页      WebView
                    使用方法
 */
public class NewDetailActivity extends Activity {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.progress)
    TextView progress;
    @BindView(R.id.font)
    ImageView font;
    @BindView(R.id.fav)
    ImageView fav;
    @BindView(R.id.webview)
    WebView webview;

    private OneChannelData.NewsBean newsBean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_detail);
        ButterKnife.bind(this);



        //打开页面后，我能不能将上个页面带给我的新闻记录拿出来
        //先获取打开本页面的Intent
         newsBean = (OneChannelData.NewsBean) getIntent().getSerializableExtra("oneNew");

        JrttOrmHelper helper = new JrttOrmHelper(NewDetailActivity.this);
        try {
            Dao<OneChannelData.NewsBean, Integer> newDao = helper.getDao(OneChannelData.NewsBean.class);

            //应该显一个怎么样的收藏 图片，到底是选中的，还未选中的
            List<OneChannelData.NewsBean> l = newDao.queryForEq("id", newsBean.id + "");
            if(l.size() == 0){
                //显示未收藏的图标
                fav.setImageResource(R.mipmap.uncollect);
            }else{
                fav.setImageResource(R.mipmap.collect);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        //返回键，点击关闭
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        font.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    //显示单选对话框
                    showFontSizeDialog();
            }
        });

        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //1,完成了映射文件，对象在数据库中的保存关系就明确
                        //卸载一下应用，删除数据库，重新创建
                //2 ，来上一个dao,判断一个新闻记录是不是保存在数据库
                JrttOrmHelper jrttOrmHelper = new JrttOrmHelper(NewDetailActivity.this);
                try {
                    Dao<OneChannelData.NewsBean, Integer> dao = jrttOrmHelper.getDao(OneChannelData.NewsBean.class);
                    //3,如果保存了 取消收藏 ： 就这条记录从数据库删除
                      //  dao.queryForEq("id",?)  这个记录需要从列表界面传入一个新闻对象
                    List<OneChannelData.NewsBean> list = dao.queryForEq("id", newsBean.id);//where id = 32343;
                    if(list.size() == 0){
                        //表示当前这个新闻没有被收藏到数据库
                        dao.create(newsBean);
                        Toast.makeText(NewDetailActivity.this, "收藏成功", Toast.LENGTH_SHORT).show();
                        fav.setImageResource(R.mipmap.collect);
                    }else{//>0  == 1
                        OneChannelData.NewsBean savedNew = list.get(0);
                        dao.delete(savedNew);//删除一条被保存的新闻记录
                        Toast.makeText(NewDetailActivity.this, "取消收藏", Toast.LENGTH_SHORT).show();
                        fav.setImageResource(R.mipmap.uncollect);
                    }
                    
                    /*
                    测试
                     */
                    List<OneChannelData.NewsBean> newsBeans = dao.queryForAll();
                    System.out.println("===测试");
                    for(OneChannelData.NewsBean item: newsBeans){
                        System.out.println(item.title);
                    }
                    //4,反之，就把这条记录插入到数据库中
                } catch (SQLException e) {
                    e.printStackTrace();
                }


            }
        });


        // 网页      WebView
//        String url = "http://10.0.2.2:8080/";
        title.setText(newsBean.title);//测试这个新闻是不是传过来。因为服务端给的每个网页都是一样
        String url =newsBean.url;

        //WebView除了浏览器对象，谷歌浏览器对象，还有一个设置对象Settings
        WebSettings settings = webview.getSettings();
        settings.setUseWideViewPort(true);//ViewPort 可以支持 wap网的缩放
        settings.setBuiltInZoomControls(true);//显示出控制页面放大缩小的按钮
        //如果设置字体的大小，这字体的大小设置成 超大175  特大 150  正常100  特小75  超小 50
        //settings.setTextSize(WebSettings.TextSize.LARGEST);// 此方法经过升级现在不用这个方法
        settings.setTextZoom(50);//就是升级后的setTextSize
        //加载网页
        webview.loadUrl(url);


        //如果网页有链接，用户点一个链接，安卓系统自动给我打了一个浏览器，并且用这个浏览器将我这个地址打开
        webview.setWebViewClient(new WebViewClient());//设置自己的浏览器对象
        webview.setWebChromeClient(new WebChromeClient(){//WebChromeClient设置谷歌浏览器对象是为了显示加载进度
            //当加载进度改变的时候开始执行
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                System.out.println(newProgress);//可以看到进度变化
                progress.setTextColor(Color.RED);
                progress.setText("页面加载:"+newProgress+"%");
            }
        });

    }

    //
    String[] fontSizes = {"超大175",  "特大150", "正常100",  "特小75" , "超小50"};
    private void showFontSizeDialog() {
        //创建对话框最快捷的方式
        AlertDialog.Builder builder  = new AlertDialog.Builder(this);
        builder.setTitle("请选择网页的字号");
        builder.setSingleChoiceItems(fontSizes, 2, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {//int i表示你点了哪个选项
                switch (i){
                    case 0:
                        webview.getSettings().setTextZoom(200);
                        break;
                    case 1:
                        webview.getSettings().setTextZoom(150);
                        break;
                    case 2:
                        webview.getSettings().setTextZoom(100);
                        break;
                    case 3:
                        webview.getSettings().setTextZoom(75);
                        break;
                    case 4:
                        webview.getSettings().setTextZoom(50);
                        break;
                }

                dialogInterface.dismiss();//关闭

            }
        }).show();//参1 选项的数组 参2
    }
}

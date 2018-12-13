package jrtt.imust.com.jrtt.test;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import jrtt.imust.com.jrtt.R;
import jrtt.imust.com.jrtt.fragment.NewsChannelFragment;
import jrtt.imust.com.jrtt.fragment.PicFragment;
import jrtt.imust.com.jrtt.fragment.UserFragment;
import jrtt.imust.com.jrtt.fragment.VideoFragment;
import jrtt.imust.com.jrtt.fragment.sub.TabChannelFragment;
import jrtt.imust.com.jrtt.net.JrttApi;
import jrtt.imust.com.jrtt.net.ResponseData;
import jrtt.imust.com.jrtt.net.RetrofitUtil;
import jrtt.imust.com.jrtt.test.demo.StudentFragment;
import jrtt.imust.com.jrtt.test.tabs.DemoTabLayoutFragment;
import retrofit2.Call;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        //编写页面内容
        //如果我开发一个PicFragment,我就把组图页面放在这个activity显示
        Fragment picFragment = new UserFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame,picFragment).commit();//
        //参1，指定布局  参2，Fragment

        //这里测试

    }
}

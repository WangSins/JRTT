package jrtt.imust.com.jrtt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;

import java.util.HashMap;

import jrtt.imust.com.jrtt.fragment.BaseFragment;
import jrtt.imust.com.jrtt.fragment.NewsChannelFragment;
import jrtt.imust.com.jrtt.fragment.NewsChannelFragment;
import jrtt.imust.com.jrtt.fragment.PicFragment;
import jrtt.imust.com.jrtt.fragment.UserFragment;
import jrtt.imust.com.jrtt.fragment.VideoFragment;

public class MainActivity extends AppCompatActivity {
    //被包含在MainActivity里面的页面有四个
    //HomeFragment VideoFragment PicFragment UserFragment
    //点击不同的按钮，显示不同的页面
    HashMap<Integer,BaseFragment> pages =new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pages.put(R.id.radio1,new NewsChannelFragment());
        pages.put(R.id.radio2,new VideoFragment());
        pages.put(R.id.radio3,new PicFragment());
        pages.put(R.id.radio4,new UserFragment());
        //点击不同的按钮显示不同的页面
        RadioGroup radioGroup =  findViewById(R.id.radio_group);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                //由radioGroup可以获取被点击的按钮的id
               int id = radioGroup.getCheckedRadioButtonId();
               BaseFragment  page = pages.get(id);
               //获取FragmentManger用来添加Fragment到布局中
                getSupportFragmentManager().beginTransaction()
                            .replace(R.id.frame,page)
                            .commit();//只有commit完才能显示

            }
        });
        //在监听器配置好的情况下。我只要使用一个方法，模拟人的点击，被监听器发现。
        radioGroup.check(R.id.radio1);


    }
}

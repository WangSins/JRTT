package jrtt.imust.com.jrtt.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import jrtt.imust.com.jrtt.R;
import jrtt.imust.com.jrtt.net.JrttApi;
import jrtt.imust.com.jrtt.net.RetrofitUtil;

/**

 Fragment是一个可以被包含在 FragmentActivity里面的视图
    1，一般我们使用support-v4包里面
    2, 如果你创建一个Activity,配置layout文件，编写逻辑
            得到功能清单里面AndroidManifest.xml里面配置
            一般情况是ok
        如果一个Activity比较复杂，建议大家使用Frgament
            小Activity
            创建一个Fragment,配置layout文件，编写逻辑
            不需要到AndroidManifest.xml里面配置，
            只需要通过FragmentManger,可以添加一个布局
 */

public class BaseFragment extends Fragment{
    protected JrttApi api;

    public BaseFragment(){
        api = RetrofitUtil.get();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
                //将layout转换成view
               View view = View.inflate(getContext(), R.layout.fragment_news_channel,null);
               //参1，上下文  参2，配置文件  参3，null
                 TextView textView = (TextView) view;
                 textView.setBackgroundColor(Color.GRAY);
                 textView.setText(getClass().getSimpleName());
                 return view;
    }
}

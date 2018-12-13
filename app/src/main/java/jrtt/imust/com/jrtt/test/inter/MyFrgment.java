package jrtt.imust.com.jrtt.test.inter;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import jrtt.imust.com.jrtt.R;

/**
 * 因为我们组里已定义好每个页面编写的规范
 *  要求所有的程序都按照这个要求来写页面
 */
public class MyFrgment extends Fragment implements IPage {


    public MyFrgment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_frgment, container, false);
    }

    @Override
    public void initViews() {
        //findview
    }

    @Override
    public void requestNet() {
        //请求
    }

    @Override
    public void parsejson(String json) {
        //解析
    }

    @Override
    public void showData() {
        //显示
    }
}

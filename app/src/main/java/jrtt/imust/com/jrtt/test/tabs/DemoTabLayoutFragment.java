package jrtt.imust.com.jrtt.test.tabs;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import java.util.ArrayList;

import jrtt.imust.com.jrtt.R;
import jrtt.imust.com.jrtt.test.tabs.sub.TestFagment;

/**
 *1,TabLayout它是design包下面的，导design包
 *2,配置ViewPager使用，导 support-v4
 *3,在页面上使用标签 添加TableLayout,ViewPager
 * 先给ViewPager设置 PagerAdapter
 */

public class DemoTabLayoutFragment extends Fragment {
    private ArrayList<TestFagment> subPages;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragmet_demo_tablayout,null);
       ViewPager viewPager =  view.findViewById(R.id.tab_viewpager);
       TabLayout tableLayout =  view.findViewById(R.id.tab_layout);
       //需要多个次页面 一般都是Fragment
        subPages=new ArrayList<>();
        for (int i = 0; i <10 ; i++) {
            TestFagment fagment = new TestFagment();
            fagment.setTitle("页面"+i);
            subPages.add(fagment);//集合中保存多个页面
        }
        //创建一个ViewPager使用到Adapter
        MyAdapter adapter = new MyAdapter(getFragmentManager());
        viewPager.setAdapter(adapter);
        //只有ViewPager被赋值了adapter
        tableLayout.setupWithViewPager(viewPager);
        return view;
    }
    private class MyAdapter extends FragmentPagerAdapter{
        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {//给ViewPager返回一个指定页面的标题
            return  subPages.get(position).getTitle();
        }

        @Override
        public Fragment getItem(int position) {//给ViewPager返回一个指定页面
            return subPages.get(position);
        }

        @Override
        public int getCount() {//给ViewPager返回页面个数
            return subPages.size();
        }
    }
}

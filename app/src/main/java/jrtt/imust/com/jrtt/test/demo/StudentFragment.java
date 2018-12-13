package jrtt.imust.com.jrtt.test.demo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import jrtt.imust.com.jrtt.R;

/**
 * Created by Administrator on 2018-6-27.
 */

public class StudentFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //集合
        List<Student> list= new ArrayList<>();
   /*     Student stu1 = new Student();
        stu1.name="迪丽热巴";
        stu1.age=18;
        Student stu2 = new Student();
        stu2.name="古力娜扎";
        stu2.age= 16;
        list.add(stu1);
        list.add(stu2);*/
        for (int i = 0; i <100 ; i++) {
            Student stu1 = new Student();
            stu1.name="迪丽热巴"+i;
            stu1.age = 18+i;
            list.add(stu1);
        }

        //使用一个Listview
        ListView listView = new ListView(getContext());

        //我创建一个适配器 StudentAdapter,包含集合，视图，显示逻辑
//        StudentAdapter adapter = new StudentAdapter(getContext(),list, R.layout.item_student);
        StudentQuickAdapter adapter = new StudentQuickAdapter(getContext(), R.layout.item_student,list);
        //再设置给ListView
        listView.setAdapter(adapter);
        return listView;
    }
}

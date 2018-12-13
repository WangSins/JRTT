package jrtt.imust.com.jrtt.test.demo;

import android.content.Context;
import android.widget.BaseAdapter;

import com.joanzapata.android.BaseAdapterHelper;
import com.joanzapata.android.QuickAdapter;

import java.util.List;

import jrtt.imust.com.jrtt.R;

/**
 *BaseAdapter
 * 1,getCount
 * 2,getView
 * 3,优化
 * 现在， 使用QuickAdapter
 */

public class StudentQuickAdapter extends QuickAdapter<Student> {
    public StudentQuickAdapter(Context context, int layoutResId, List<Student> data) {
        super(context, layoutResId, data);
    }
    @Override
    protected void convert(BaseAdapterHelper helper, Student item) {
            //专用门来编写逻辑：将一个对象的数据设置给布局
            //参1,不需要写if优化，viewholder 参2,一个数据对象
        //R.id.text  往上面设置一个学生的姓名与年龄
        String s = "姓名:"+item.name+"  年龄:"+item.age;
        helper.setText(R.id.text,s);//参1,一个是view中的id，参2，数据
        //包含了优化
    }
}

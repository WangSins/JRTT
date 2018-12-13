package jrtt.imust.com.jrtt.test.demo;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import jrtt.imust.com.jrtt.R;

/**
 * 注意事项：
 * 1，ListView GridView RecylerView 都要使用适配器，不能直接设置集合
 * 2,如果以上View显成空白，有两种可能
 *      没有适配器
 *      没有行数    int getCount()  0
 * 3,适配器中，一般要有context,list,布局文件，逻辑
 */
public class StudentAdapter extends BaseAdapter{
    private Context context;
    private List<Student> list;
    private int layoutId;

    public StudentAdapter(Context context, List<Student> list, int layoutId) {
        this.context = context;
        this.list = list;
        this.layoutId = layoutId;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        StudentViewHolder holder = null;
        // view表示可以重用的行视图
        if(view == null){
            view =  View.inflate(context, layoutId, null);
            holder = new StudentViewHolder();
            TextView textView = view.findViewById(R.id.text);
            holder.text= textView;
            //还得把这个 holder放进view里面
            view.setTag(holder);
        }else{//view !=null
            //重用它
             holder = (StudentViewHolder) view.getTag();
        }

        //写上一段显示逻辑
        Student stu = list.get(i);
        //格式 ：   姓名:xxx  年龄:19
        String s = "姓名:"+stu.name+"  年龄:"+stu.age;
        holder.text.setText(s);
        //参1，上下文  参2 布局  参3null;
        return view;
    }
    //定义一个ViewHolder
    static class StudentViewHolder{
        //View的容器
        public TextView text;
    }
}


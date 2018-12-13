package jrtt.imust.com.jrtt;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jrtt.imust.com.jrtt.adapter.OneChannelMultiAdapter;
import jrtt.imust.com.jrtt.bean.OneChannelData;
import jrtt.imust.com.jrtt.db.JrttOrmHelper;

/*
在activity_collect中
    加上一个ListView
        使用dao给你查找一集合
            前面我们显示过新闻记录，有一个适配器（复杂列表的适配器）
                创建了一个适配器，将这个适配器设置给 ListView
 */
public class CollectActivity extends Activity {

    @BindView(R.id.list_view)
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect);
        ButterKnife.bind(this);
        //收藏的新闻
        JrttOrmHelper helper = new JrttOrmHelper(this);
        try {
            Dao<OneChannelData.NewsBean, Integer> dao = helper.getDao(OneChannelData.NewsBean.class);
            //查找所有的新闻记录
            List<OneChannelData.NewsBean> newsBeans = dao.queryForAll();
            //所有新闻是一个集合，需要一个适配器
            OneChannelMultiAdapter adapter = new OneChannelMultiAdapter(this, newsBeans);
            //设置给ListView
            listView.setAdapter(adapter);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

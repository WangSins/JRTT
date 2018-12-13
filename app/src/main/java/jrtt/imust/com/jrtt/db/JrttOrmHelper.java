package jrtt.imust.com.jrtt.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import jrtt.imust.com.jrtt.bean.OneChannelData;

/**
 * 1,数据库名字
 * 2,数据库版本号
 * 3，创建表
 */

public class JrttOrmHelper extends OrmLiteSqliteOpenHelper {

    public JrttOrmHelper(Context context) {
        super(context, "jrtt.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        //使用工具类，读取映射文件，由工具来创建表
        try {
            TableUtils.createTable(connectionSource,NewRecoder.class);
            TableUtils.createTable(connectionSource,OneChannelData.NewsBean.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

    }
}

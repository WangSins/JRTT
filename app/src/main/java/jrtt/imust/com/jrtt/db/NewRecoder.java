package jrtt.imust.com.jrtt.db;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * 先来一个映射文件
 * 本文件是一个java类
 */
@DatabaseTable(tableName = "new_recoder")
public class NewRecoder {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(columnName = "new_id")
    private String newId;

    public NewRecoder() {
    }

    public NewRecoder(String newId) {
        this.newId = newId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNewId() {
        return newId;
    }

    public void setNewId(String newId) {
        this.newId = newId;
    }

    //set/get
}

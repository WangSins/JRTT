package jrtt.imust.com.jrtt.net;

/**
 * 当前是一个关于页面编写的规范，就是一个接口
 */

public interface OnDataLoadListener {
    void initViews();
    void requestNet();
    void parsejson(String json);
    void showData();
}
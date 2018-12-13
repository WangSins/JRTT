package jrtt.imust.com.jrtt.test.inter;

/**
 * 当前是一个关于页面编写的规范，就是一个接口
 */

public interface IPage{
    void initViews();
    void requestNet();
    void parsejson(String json);
    void showData();
}
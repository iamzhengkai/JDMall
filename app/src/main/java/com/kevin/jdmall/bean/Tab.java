package com.kevin.jdmall.bean;

/**
 * Function:
 *
 * @FileName: com.kevin.zkshop.ui.bean.Tab.java
 * @author: zk
 * @date: 2016-10-03 04:42
 */
public class Tab {
    public String tag;
    public int resId;
    public String title;

    public Tab(String tag, int resId, String title) {
        this.tag = tag;
        this.resId = resId;
        this.title = title;
    }
}

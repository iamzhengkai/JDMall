package com.kevin.jdmall;

/**
 * Function:
 *
 * @FileName: com.kevin.jdmall.MyConstants.java
 * @author: zk
 * @date: 2017-03-16 00:37
 */

public interface MyConstants {
    String BASE_URL = "http://mall.520it.com";

    String PREF_USER_INFO = "user_info";
    interface DbConstants {
        String DB_NAME = "jdmall.db";
        int DB_VERSION = 1;
        String TBL_NAME = "user";
        String CLM_ID = "_id";
        String CLM_USERNAME = "username";
        String CLM_PWD = "pwd";
    }

}

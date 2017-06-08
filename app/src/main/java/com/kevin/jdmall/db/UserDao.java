package com.kevin.jdmall.db;

import android.database.sqlite.SQLiteOpenHelper;

import com.kevin.jdmall.MyConstants;

/**
 * Created by Administrator on 2017/6/8.
 */

public class UserDao {
    public static boolean insert(SQLiteOpenHelper helper,String username,String pwd){
        String sql = "insert into " + MyConstants.DbConstants.TBL_NAME +"(" +
                MyConstants.DbConstants.CLM_USERNAME + "," + MyConstants.DbConstants.CLM_PWD +")" +
                "values(" + username + "," + pwd + ");";
        helper.getWritableDatabase().execSQL(sql);
        return true;
    }
}

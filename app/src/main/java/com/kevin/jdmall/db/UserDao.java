package com.kevin.jdmall.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.kevin.jdmall.MyApplication;
import com.kevin.jdmall.MyConstants;
import com.kevin.jdmall.bean.User;

/**
 * Created by Administrator on 2017/6/8.
 */

public class UserDao {
    private SQLiteOpenHelper mHelper;
    public UserDao(){
        mHelper = new DbOpenHelper();
    }
    public  boolean addUser(String username,String pwd){
       /* String sql = "insert into " + MyConstants.DbConstants.TBL_NAME +"(" +
                MyConstants.DbConstants.CLM_USERNAME + "," + MyConstants.DbConstants.CLM_PWD +")" +
                "values(" + username + "," + pwd + ");";
        mHelper.getWritableDatabase().execSQL(sql);*/

        SQLiteDatabase database = mHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MyConstants.DbConstants.CLM_USERNAME,username);
        values.put(MyConstants.DbConstants.CLM_PWD,pwd);
        long resultCount = database.insert(MyConstants.DbConstants.TBL_NAME, null, values);
        database.close();
        return resultCount != -1;
    }

    public void clearUser(){
        SQLiteDatabase database = mHelper.getWritableDatabase();
        database.delete(MyConstants.DbConstants.TBL_NAME, null, null);
        database.close();
    }

    public User getUser(){
        SQLiteDatabase database = mHelper.getWritableDatabase();
        Cursor cursor = database.query(MyConstants.DbConstants.TBL_NAME, null, null, null, null,
                null, null);

        if (cursor.getCount() == 0){
            cursor.close();
            database.close();
            return null;
        }

        cursor.moveToFirst();
        User user = new User();
        user._id = cursor.getInt(cursor.getColumnIndex(MyConstants.DbConstants.CLM_ID));
        user.username = cursor.getString(cursor.getColumnIndex(MyConstants.DbConstants.CLM_USERNAME));
        user .pwd = cursor.getString(cursor.getColumnIndex(MyConstants.DbConstants.CLM_PWD));
        cursor.close();
        database.close();
        return user;
    }
}

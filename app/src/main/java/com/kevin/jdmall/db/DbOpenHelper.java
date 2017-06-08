package com.kevin.jdmall.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.kevin.jdmall.MyApplication;
import com.kevin.jdmall.MyConstants;

/**
 * Created by Administrator on 2017/6/8.
 */

public class DbOpenHelper extends SQLiteOpenHelper {

    public DbOpenHelper() {
        super(MyApplication.mContext, MyConstants.DbConstants.DB_NAME, null, MyConstants.DbConstants.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTbl = "create table " + MyConstants.DbConstants.TBL_NAME + "(" +
                MyConstants.DbConstants.CLM_ID + " integer primary key autoincrement," +
                MyConstants.DbConstants.CLM_USERNAME + "text," +
                MyConstants.DbConstants.CLM_PWD + "text);";
        sqLiteDatabase.execSQL(createTbl);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}

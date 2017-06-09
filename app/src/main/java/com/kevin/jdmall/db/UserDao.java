package com.kevin.jdmall.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.kevin.jdmall.MyConstants;
import com.kevin.jdmall.bean.User;

/**
 * Created by Administrator on 2017/6/8.
 */

public class UserDao {
    private SQLiteOpenHelper mHelper;
   /* private SqlBrite mSqlBrite;
    private BriteDatabase db;
    private Observable<SqlBrite.Query> mUserObservable;*/

    public UserDao() {
        mHelper = new DbOpenHelper();
      /*  mSqlBrite = new SqlBrite.Builder().build();
        db = mSqlBrite.wrapDatabaseHelper(mHelper, Schedulers.io());
        mUserObservable = db.createQuery(MyConstants.DbConstants.TBL_NAME, "SELECT * FROM " + MyConstants.DbConstants.TBL_NAME);*/
    }

    public boolean addUser(String username, String pwd) {

        SQLiteDatabase database = mHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(MyConstants.DbConstants.CLM_USERNAME,username);
        values.put(MyConstants.DbConstants.CLM_PWD,pwd);
        long resultCount = database.insert(MyConstants.DbConstants.TBL_NAME, null, values);
        database.close();

        return resultCount != -1;

       /* ContentValues values = new ContentValues();
        values.put(MyConstants.DbConstants.CLM_USERNAME, username);
        values.put(MyConstants.DbConstants.CLM_PWD, pwd);
        db.insert(MyConstants.DbConstants.TBL_NAME, values);*/
    }

    public void clearUser() {
        SQLiteDatabase database = mHelper.getWritableDatabase();
        database.delete(MyConstants.DbConstants.TBL_NAME, null, null);
        database.close();
    }

    public User getUser() {
        SQLiteDatabase database = mHelper.getWritableDatabase();
        Cursor cursor = database.query(MyConstants.DbConstants.TBL_NAME, null, null, null, null,
                null, null);
        User user = null;
        if(cursor.moveToFirst()){
            user = new User();
            user._id = cursor.getInt(cursor.getColumnIndex(MyConstants.DbConstants.CLM_ID));
            user.username = cursor.getString(cursor.getColumnIndex(MyConstants.DbConstants.CLM_USERNAME));
            user.pwd = cursor.getString(cursor.getColumnIndex(MyConstants.DbConstants.CLM_PWD));
            cursor.close();
            database.close();
        }

        return user;
    }
}

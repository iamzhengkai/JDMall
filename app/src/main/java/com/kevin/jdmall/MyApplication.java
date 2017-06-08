package com.kevin.jdmall;

import android.app.Application;
import android.content.Context;

import com.kevin.jdmall.api.RetrofitManager;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import retrofit2.Retrofit;

/**
 * Function:
 *
 * @FileName: com.kevin.jdmall.MyApplication.java
 * @author: zk
 * @date: 2017-03-15 21:30
 */

public class MyApplication extends Application {
    public static Context mContext;
    public static Retrofit mRetrofit;
    public static RefWatcher mRefWatcher;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        mRefWatcher = LeakCanary.install(this);
        // Normal app init code...
        mRetrofit = new RetrofitManager.Builder().build().getRetrofitClient(MyConstants.BASE_URL);
    }

}

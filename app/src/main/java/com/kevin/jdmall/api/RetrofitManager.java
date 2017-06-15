package com.kevin.jdmall.api;

import android.support.annotation.NonNull;


import com.kevin.jdmall.MyApplication;
import com.kevin.jdmall.utils.NetWorkUtil;
import com.orhanobut.logger.Logger;

import java.io.File;
import java.io.IOException;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zk on 2017/3/9 .
 */
public class RetrofitManager {

    private final int mMaxAge;
    private final int mMaxStale;
    private final long mCacheSize;
    private final String mCacheFileName;
    //缓存拦截器
    private Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR;
    //日志拦截器
    private HttpLoggingInterceptor logging;
    //自定义日志拦截器
    private LoggingInterceptor loggingInterceptor;
    //缓存文件
    private File httpCacheDirectory;
    //缓存
    private Cache cache;
    private OkHttpClient client;

    private RetrofitManager(Builder builder) {
        mMaxAge = builder.mMaxAge;
        mMaxStale = builder.mMaxStale;
        mCacheSize = builder.mCacheSize;
        mCacheFileName = builder.mCacheFileName;

        REWRITE_CACHE_CONTROL_INTERCEPTOR = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();

                //判断是否需要缓存
                if (request.cacheControl().noCache()){
                    Logger.d("No-Cache:============>" + request.cacheControl().noCache());
                    return chain.proceed(request);
                }

                //没网强制从缓存读取(必须得写，不然断网状态下，退出应用，或者等待一分钟后，就获取不到缓存）
                if (!NetWorkUtil.isNetWorkAvailable(MyApplication.mContext)) {
                    Logger.i("无网络，从缓存读取！");
                    request = request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                }

                String cacheHeaderValue = NetWorkUtil.isNetWorkAvailable(MyApplication.mContext) ?
                        "public, max-age=" : "public, only-if-cached, max-stale=";
                int cacheTime =  NetWorkUtil.isNetWorkAvailable(MyApplication.mContext) ?
                        mMaxAge : mMaxStale;

                Response originalResponse = chain.proceed(request);

                //int maxAge = 60; // 在线缓存在1分钟内可读取
                return originalResponse.newBuilder()
                        .removeHeader("Pragma")
                        .removeHeader("Cache-Control")
                        .header("Cache-Control", cacheHeaderValue + cacheTime)
                        .build();

            }
        };
        logging = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC);
        loggingInterceptor = new LoggingInterceptor();
        httpCacheDirectory = new File(MyApplication.mContext.getCacheDir(), mCacheFileName);
        cache = new Cache(httpCacheDirectory, mCacheSize);
        client = new OkHttpClient.Builder()
                .addNetworkInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
                .addInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
                .addInterceptor(loggingInterceptor)
                .addInterceptor(new RestfulInterceptor())
                .cache(cache)
                .build();

    }

    public OkHttpClient getOkHttpClient() {
        return client;
    }

    public Retrofit getRetrofitClient(@NonNull String baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static class Builder {
        private int mMaxAge = 60;
        private int mMaxStale = 60 * 60 * 24 * 28;
        private long mCacheSize = 10 * 1024 * 1024;
        private String mCacheFileName = "cacheFile";

        public Builder() {
        }

        public Builder setMaxAge(int maxAge) {
            mMaxAge = maxAge;
            return this;
        }

        public Builder setMaxStale(int maxStale) {
            mMaxStale = maxStale;
            return this;
        }

        public Builder setCacheSize(long cacheSize) {
            mCacheSize = cacheSize;
            return this;
        }

        public Builder setCacheFileName(String cacheFileName) {
            mCacheFileName = cacheFileName;
            return this;
        }

        public RetrofitManager build() {
            return new RetrofitManager(this);

        }
    }


}

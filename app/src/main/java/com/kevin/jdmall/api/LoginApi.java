package com.kevin.jdmall.api;

import com.kevin.jdmall.bean.LoginResult;

import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Function:
 *
 * @FileName: com.kevin.jdmall.api.Api.java
 * @author: zk
 * @date: 2017-03-16 00:34
 */

public interface LoginApi{
    @FormUrlEncoded
    @Headers("Cache-Control:no-cache")
    @POST("/login")
    Observable<LoginResult> login(
            @Field("username") String username,
            @Field("pwd") String pwd
    );

}

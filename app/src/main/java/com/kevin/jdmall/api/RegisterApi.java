package com.kevin.jdmall.api;

import com.kevin.jdmall.bean.RegisterResult;

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

public interface RegisterApi {
    @FormUrlEncoded
    @Headers("Cache-Control:no-cache")
    @POST("/regist")
    Observable<RegisterResult> login(
            @Field("username") String username,
            @Field("pwd") String pwd
    );

}

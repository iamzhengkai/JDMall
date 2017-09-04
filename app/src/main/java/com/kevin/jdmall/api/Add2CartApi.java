package com.kevin.jdmall.api;

import com.kevin.jdmall.bean.LoginResult;
import com.kevin.jdmall.bean.ToCartResult;

import java.util.HashMap;

import retrofit2.http.Field;
import retrofit2.http.FieldMap;
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

public interface Add2CartApi {
    @FormUrlEncoded
    @Headers("Cache-Control:no-cache")
    @POST("/toShopCar")
    Observable<ToCartResult> add2Cart(
            @FieldMap HashMap<String,Object> params
    );

}

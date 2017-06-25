package com.kevin.jdmall.api;

import com.kevin.jdmall.bean.BannerResult;
import com.kevin.jdmall.bean.LoginResult;
import com.kevin.jdmall.bean.RecommendResult;
import com.kevin.jdmall.bean.SecKillResult;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Function:
 *
 * @FileName: com.kevin.jdmall.api.Api.java
 * @author: zk
 * @date: 2017-03-16 00:34
 */

public interface HomeFragmentApi {
    //一定要记住，post请求是没有缓存的！！！！
   /* @FormUrlEncoded
    @POST("/banner")
    Observable<BannerResult> getBanner(
            @Field("adKind") int adKind
    );*/

    @GET("/banner")
    Observable<BannerResult> getBanner(@Query("adKind") int adKind);

    @GET("/seckill")
    Observable<SecKillResult> getSecKill();

    @GET("/getYourFav")
    Observable<RecommendResult> getYourFav();

}

package com.kevin.jdmall.api;

import com.kevin.jdmall.bean.CommentCountResult;
import com.kevin.jdmall.bean.CommentDetailResult;


import java.util.HashMap;
import java.util.Map;

import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Function:
 *
 * @FileName: com.kevin.jdmall.api.DetailCommentApi.java
 * @author: zk
 * @date: 2017-06-28 18:01
 */

public interface DetailCommentApi {
    @FormUrlEncoded
    @POST("/commentCount")
    Observable<CommentCountResult> getCommentCount(@Field("productId") int productId);

    @FormUrlEncoded
    @POST("/commentDetail")
    Observable<CommentDetailResult> getCommentDetail(
            @FieldMap Map<String,Object> params
    );
}

package com.kevin.jdmall.api;

import com.kevin.jdmall.bean.DetailProductResult;
import com.kevin.jdmall.bean.ProductCommentResult;
import com.kevin.jdmall.bean.SecondLevelCategoryResult;
import com.kevin.jdmall.bean.TopLevelCategoryResult;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Function:
 *
 * @FileName: com.kevin.jdmall.api.CategoryFragmentApi.java
 * @author: zk
 * @date: 2017-06-24 23:41
 */

public interface DetailProductApi {
    @GET("/productInfo")
    Observable<DetailProductResult> getProduct(@Query("id") int id);

    @FormUrlEncoded
    @POST("/productComment")
    Observable<ProductCommentResult> getProductComment(
            @Field("productId") int productId,@Field("type") int type);
}

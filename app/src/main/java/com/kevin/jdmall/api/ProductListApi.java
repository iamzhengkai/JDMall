package com.kevin.jdmall.api;

import com.kevin.jdmall.bean.BannerResult;
import com.kevin.jdmall.bean.BrandResult;
import com.kevin.jdmall.bean.ProductListResult;

import java.util.HashMap;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Function:
 *
 * @FileName: com.kevin.jdmall.api.ProductListApi.java
 * @author: zk
 * @date: 2017-06-25 21:06
 */

public interface ProductListApi {
    @GET("/brand")
    Observable<BrandResult> getBrandInfo(@Query("categoryId") int categoryId);

    @FormUrlEncoded
    @POST("/searchProduct")
    Observable<ProductListResult> getProductList(@FieldMap HashMap<String,Object> params);
}

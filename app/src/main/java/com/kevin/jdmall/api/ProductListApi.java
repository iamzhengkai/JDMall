package com.kevin.jdmall.api;

import com.kevin.jdmall.bean.BannerResult;
import com.kevin.jdmall.bean.BrandResult;

import retrofit2.http.GET;
import retrofit2.http.Query;
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
}

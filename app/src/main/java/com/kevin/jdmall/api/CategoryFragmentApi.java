package com.kevin.jdmall.api;

import com.kevin.jdmall.bean.SecondLevelCategoryResult;
import com.kevin.jdmall.bean.TopLevelCategoryResult;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Function:
 *
 * @FileName: com.kevin.jdmall.api.CategoryFragmentApi.java
 * @author: zk
 * @date: 2017-06-24 23:41
 */

public interface CategoryFragmentApi {
    @GET("/category")
    Observable<TopLevelCategoryResult> getTopLevelCategory();

    @GET("/category")
    Observable<SecondLevelCategoryResult> getSecondLevelCategory(@Query("parentId") int parentId);

}

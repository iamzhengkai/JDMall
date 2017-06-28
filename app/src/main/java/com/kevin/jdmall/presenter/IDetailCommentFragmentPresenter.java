package com.kevin.jdmall.presenter;

import java.util.Map;

import retrofit2.http.Field;

/**
 * Function:
 *
 * @FileName: com.kevin.jdmall.presenter.IDetailCommentFragmentPresenter.java
 * @author: zk
 * @date: 2017-06-28 17:53
 */

public interface IDetailCommentFragmentPresenter extends IBasePresenter{
    void getCommentCount(int productId);
    void getCommentDetail(Map<String,Object> params);
}

package com.kevin.jdmall.presenter;

import java.util.HashMap;
import java.util.Map;

/**
 * Function:
 *
 * @FileName: com.kevin.jdmall.presenter.IProductListPresenter.java
 * @author: zk
 * @date: 2017-06-25 17:44
 */

public interface IProductDetailPresenter extends IBasePresenter{
    boolean initParams(int userId,int productId,int buyCount,HashMap<String,Object> params) ;
}

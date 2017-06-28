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

public interface IProductListPresenter  extends IBasePresenter{
    void getBrandInfo(int categoryId);
    void getProductList(HashMap<String,Object> params);
    boolean vertifyPrice(String minPriceStr, String maxPriceStr, Map<String,Integer> pricesMap);
}

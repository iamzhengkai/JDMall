package com.kevin.jdmall.presenter.impl;

import com.kevin.jdmall.iview.IProductDetailView;
import com.kevin.jdmall.iview.IView;
import com.kevin.jdmall.presenter.IProductDetailPresenter;

/**
 * Function:
 *
 * @FileName: com.kevin.jdmall.presenter.impl.ProductDetailPresenterImpl.java
 * @author: zk
 * @date: 2017-06-27 22:30
 */

public class ProductDetailPresenterImpl extends BasePresenterImpl<IProductDetailView> implements IProductDetailPresenter {


    public ProductDetailPresenterImpl(IProductDetailView iView) {
        super(iView);
    }
}

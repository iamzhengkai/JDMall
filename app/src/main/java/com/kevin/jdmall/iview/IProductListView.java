package com.kevin.jdmall.iview;

import com.kevin.jdmall.bean.BrandResult;
import com.kevin.jdmall.bean.ProductListResult;

import java.util.List;

/**
 * Function:
 *
 * @FileName: com.kevin.jdmall.iview.IProductListView.java
 * @author: zk
 * @date: 2017-06-25 17:42
 */

public interface IProductListView extends IView {
    void initBrand(List<BrandResult.ResultBean> list);
    void initProductList(List<ProductListResult.ResultBean.RowsBean> list);
}

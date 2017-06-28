package com.kevin.jdmall.iview;

import com.kevin.jdmall.bean.DetailProductResult;
import com.kevin.jdmall.bean.ProductCommentResult;

import java.util.List;

/**
 * Function:
 *
 * @FileName: com.kevin.jdmall.iview.IDetailProductView.java
 * @author: zk
 * @date: 2017-06-28 00:36
 */

public interface IDetailProductView extends IView{
    void bindView(DetailProductResult result);
    void bindCommentList(ProductCommentResult result);
}

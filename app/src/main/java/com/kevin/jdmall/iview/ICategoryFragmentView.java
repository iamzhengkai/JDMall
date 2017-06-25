package com.kevin.jdmall.iview;

import com.kevin.jdmall.bean.SecondLevelCategoryResult;
import com.kevin.jdmall.bean.TopLevelCategoryResult;

import java.util.List;

/**
 * Function:
 *
 * @FileName: com.kevin.jdmall.iview.ICategoryFragmentView.java
 * @author: zk
 * @date: 2017-06-24 23:49
 */

public interface ICategoryFragmentView extends IView {
    void refreshTopLevelCategroyList(List<TopLevelCategoryResult.ResultBean> list);

    void refreshSecondLevelCategoryList(TopLevelCategoryResult.ResultBean topInfo,
                                        List<SecondLevelCategoryResult.ResultBean> list);

}

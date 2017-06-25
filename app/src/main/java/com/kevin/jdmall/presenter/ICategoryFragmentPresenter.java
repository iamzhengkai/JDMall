package com.kevin.jdmall.presenter;

import com.kevin.jdmall.bean.TopLevelCategoryResult;

public interface ICategoryFragmentPresenter extends IBasePresenter {
   void getSecondLevelCategory(TopLevelCategoryResult.ResultBean topInfo);
   void getTopLevelCategory();
}

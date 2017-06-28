package com.kevin.jdmall.presenter;

public interface IDetailProductFragmentPresenter extends IBasePresenter {
   void getProduct(int productId);
   void getProductComment(int productId,int type);
}

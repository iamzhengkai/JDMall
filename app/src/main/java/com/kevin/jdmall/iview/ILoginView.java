package com.kevin.jdmall.iview;

/**
 * Created by 蔡小木 on 2016/4/26 0026.
 */
public interface ILoginView extends IView{
    //Login界面操作
    void showProgressDialog();
    void hideProgressDialog();
    void showError(String errorMsg);
    void jumpToMainActivity();
    void setUserInfo(String username,String pwd);
}

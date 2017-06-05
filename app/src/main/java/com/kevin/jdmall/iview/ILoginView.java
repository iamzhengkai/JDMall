package com.kevin.jdmall.iview;

/**
 * Created by 蔡小木 on 2016/4/26 0026.
 */
public interface ILoginView {
    void showProgressDialog();
    void hideProgressDialog();
    void showError(String errorMsg);
    void jumpToMainActivity();
}

package com.kevin.jdmall.presenter;

public interface ILoginPresenter extends IBasePresenter {
    boolean vertifyLoginInfo(String username,String password);
    void login(String username,String password);
    void readUserFromDb();
}

package com.kevin.jdmall.presenter;

public interface ILoginPresenter extends BasePresenter{
    boolean vertifyLoginInfo(String username,String password);
    void login(String username,String password);

}

package com.kevin.jdmall.presenter;

public interface IRegisterPresenter extends BasePresenter{
    boolean vertifyRegisterInfo(String username, String password,String repassword);
    void register(String username, String password);

}

package com.kevin.jdmall.presenter;

public interface IResetPresenter extends BasePresenter{
    void reset(String username);
    boolean vertifyResetInfo(String username);
}

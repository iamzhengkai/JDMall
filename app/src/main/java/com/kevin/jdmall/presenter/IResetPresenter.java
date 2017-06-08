package com.kevin.jdmall.presenter;

public interface IResetPresenter extends IBasePresenter {
    void reset(String username);
    boolean vertifyResetInfo(String username);
}

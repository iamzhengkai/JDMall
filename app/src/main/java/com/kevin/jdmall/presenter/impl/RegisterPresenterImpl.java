package com.kevin.jdmall.presenter.impl;

import android.text.TextUtils;

import com.kevin.jdmall.BuildConfig;
import com.kevin.jdmall.MyApplication;
import com.kevin.jdmall.api.RegisterApi;
import com.kevin.jdmall.bean.RegisterResult;
import com.kevin.jdmall.iview.IRegisterView;
import com.kevin.jdmall.presenter.IRegisterPresenter;
import com.orhanobut.logger.Logger;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class RegisterPresenterImpl extends BasePresenterImpl implements IRegisterPresenter {

    private static final String TAG = "RegisterPresenterImpl";
    private IRegisterView mRegisterView;

    public RegisterPresenterImpl(IRegisterView registerView) {
        if (registerView==null)
            throw new IllegalArgumentException("IRegisterView must not be null");
        this.mRegisterView = registerView;
    }

    @Override
    public void register(String username,String password) {
        mRegisterView.showProgressDialog();

        Subscription s =  MyApplication.mRetrofit.create(RegisterApi.class)
                .login(username,password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RegisterResult>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        mRegisterView.hideProgressDialog();
                        e.printStackTrace();
                        mRegisterView.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(RegisterResult registerResult) {
                        mRegisterView.hideProgressDialog();
                        if (registerResult.isSuccess()){
                            mRegisterView.showError("注册成功!");
                            if (BuildConfig.DEBUG){
                                Logger.d(TAG,registerResult.toString());
                            }
                            //跳转到主界面
                            mRegisterView.jumpToLoginActivity();

                        }else{
                            mRegisterView.showError(registerResult.getErrorMsg());
                        }

                    }
                });
        addSubscription(s);
    }

    @Override
    public boolean vertifyRegisterInfo(String username,String password,String repassword) {
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)||TextUtils.isEmpty(repassword)){
            mRegisterView.showError("用户名或密码不能为空！");
            return false;
        }

        if (!password.equals(repassword)){
            mRegisterView.showError("两次输入的密码不相同，请重新输入！");
            return false;
        }

        return true;
    }
}

package com.kevin.jdmall.presenter.impl;

import android.text.TextUtils;

import com.kevin.jdmall.BuildConfig;
import com.kevin.jdmall.MyApplication;
import com.kevin.jdmall.api.LoginApi;
import com.kevin.jdmall.bean.LoginResult;
import com.kevin.jdmall.iview.ILoginView;
import com.kevin.jdmall.presenter.ILoginPresenter;
import com.orhanobut.logger.Logger;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by 蔡小木 on 2016/4/22 0022.
 */
public class LoginPresenterImpl extends BasePresenterImpl implements ILoginPresenter {

    private static final String TAG = "LoginPresenterImpl";
    private ILoginView mLoginView;

    public LoginPresenterImpl(ILoginView loginView) {
        if (loginView==null)
            throw new IllegalArgumentException("ILoginView must not be null");
        this.mLoginView = loginView;
    }

    @Override
    public void login(String username,String password) {
        mLoginView.showProgressDialog();

        Subscription s =  MyApplication.mRetrofit.create(LoginApi.class).login(username,password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginResult>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        mLoginView.hideProgressDialog();
                        e.printStackTrace();
                        mLoginView.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(LoginResult loginResult) {
                        mLoginView.hideProgressDialog();
                        if (loginResult.isSuccess()){
                            mLoginView.showError("登录成功!");
                            if (BuildConfig.DEBUG){
                                Logger.d(TAG,loginResult.toString());
                            }
                            //跳转到主界面
                            mLoginView.jumpToMainActivity();

                        }else{
                            mLoginView.showError(loginResult.getErrorMsg());
                        }

                    }
                });
        addSubscription(s);
    }

    @Override
    public boolean vertifyLoginInfo(String username,String password) {
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)){
            mLoginView.showError("用户名或密码不能为空！");
            return false;
        }
        return true;
    }
}

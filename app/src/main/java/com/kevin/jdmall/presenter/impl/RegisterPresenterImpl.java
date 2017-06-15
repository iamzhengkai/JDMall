package com.kevin.jdmall.presenter.impl;

import android.text.TextUtils;

import com.kevin.jdmall.BuildConfig;
import com.kevin.jdmall.MyApplication;
import com.kevin.jdmall.api.RegisterApi;
import com.kevin.jdmall.bean.RegisterResult;
import com.kevin.jdmall.iview.IRegisterView;
import com.kevin.jdmall.presenter.IRegisterPresenter;
import com.kevin.jdmall.subscriber.CheckedSubscriber;
import com.orhanobut.logger.Logger;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class RegisterPresenterImpl extends BasePresenterImpl<IRegisterView> implements IRegisterPresenter {

    private static final String TAG = "RegisterPresenterImpl";

    public RegisterPresenterImpl(IRegisterView iView) {
        super(iView);
    }

    @Override
    public void register(String username,String password) {
        mViewRef.get().showProgressDialog();

        Subscription s =  MyApplication.mRetrofit.create(RegisterApi.class)
                .login(username,password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CheckedSubscriber<RegisterResult,IRegisterView>(mViewRef.get()) {
                    @Override
                    public void onError(Throwable e) {
                        mView.hideProgressDialog();
                        e.printStackTrace();
                        mView.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(RegisterResult registerResult) {
                        mView.hideProgressDialog();
                        if (registerResult.isSuccess()){
                            mView.showError("注册成功!");
                            if (BuildConfig.DEBUG){
                                Logger.d(TAG,registerResult.toString());
                            }
                            //跳转到主界面
                            mView.jumpToLoginActivity();

                        }else{
                            mView.showError(registerResult.getErrorMsg());
                        }

                    }
                });
        addSubscription(s);
    }

    @Override
    public boolean vertifyRegisterInfo(String username,String password,String repassword) {
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)||TextUtils.isEmpty(repassword)){
            mViewRef.get().showError("用户名或密码不能为空！");
            return false;
        }

        if (!password.equals(repassword)){
            mViewRef.get().showError("两次输入的密码不相同，请重新输入！");
            return false;
        }

        return true;
    }
}

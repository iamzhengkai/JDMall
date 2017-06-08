package com.kevin.jdmall.presenter.impl;

import android.text.TextUtils;

import com.kevin.jdmall.BuildConfig;
import com.kevin.jdmall.MyApplication;
import com.kevin.jdmall.api.ResetApi;
import com.kevin.jdmall.bean.RestResult;
import com.kevin.jdmall.iview.IResetView;
import com.kevin.jdmall.presenter.IResetPresenter;
import com.orhanobut.logger.Logger;

import java.lang.ref.WeakReference;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by 蔡小木 on 2016/4/22 0022.
 */
public class ResetPresenterImpl extends BasePresenterImpl implements IResetPresenter {

    private static final String TAG = "ResetPresenterImpl";
//    private ILoginView mLoginView;
    private WeakReference<IResetView> mResetViewRef;

    public ResetPresenterImpl(IResetView resetView) {
        if (resetView==null)
            throw new IllegalArgumentException("IRestView must not be null");


        this.mResetViewRef = new WeakReference<IResetView>(resetView);
//        this.mLoginView = loginView;
    }

    @Override
    public void reset(String username) {
        if ((mResetViewRef.get()) != null){
            mResetViewRef.get().showProgressDialog();}
        else {
            Logger.e("ResetActivity已被回收");
            return;
        }
        Subscription s =  MyApplication.mRetrofit.create(ResetApi.class).reset(username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RestResult>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (mResetViewRef.get() != null){
                            mResetViewRef.get().hideProgressDialog();
                            mResetViewRef.get().showError(e.getMessage());
                        }else {
                            Logger.e("ResetActivity已被回收");
                        }
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(RestResult resetResult) {
                        if (mResetViewRef.get() != null) {
                            mResetViewRef.get().hideProgressDialog();
                            if (resetResult.isSuccess()) {
                                mResetViewRef.get().showError("密码重置成功!");
                                if (BuildConfig.DEBUG) {
                                    Logger.d(TAG, resetResult.toString());
                                }
                                //跳转到登录页面
                                mResetViewRef.get().returnToLoginActivity();

                            } else {
                                mResetViewRef.get().showError(resetResult.getErrorMsg());
                            }
                        }else {
                            Logger.e("ResetActivity已被回收");
                        }
                    }
                });
        addSubscription(s);
    }

    @Override
    public boolean vertifyResetInfo(String username) {
        if (TextUtils.isEmpty(username)){
            if (mResetViewRef.get() != null){
                mResetViewRef.get().showError("用户名不能为空！");
            }else {
                Logger.e("ResetActivity已被回收");
            }
            return false;
        }
        return true;
    }
}

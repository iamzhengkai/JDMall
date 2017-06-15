package com.kevin.jdmall.presenter.impl;

import android.text.TextUtils;

import com.kevin.jdmall.BuildConfig;
import com.kevin.jdmall.MyApplication;
import com.kevin.jdmall.api.ResetApi;
import com.kevin.jdmall.bean.ResetResult;
import com.kevin.jdmall.iview.IResetView;
import com.kevin.jdmall.presenter.IResetPresenter;
import com.kevin.jdmall.subscriber.CheckedSubscriber;
import com.orhanobut.logger.Logger;

import java.lang.ref.WeakReference;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by 蔡小木 on 2016/4/22 0022.
 */
public class ResetPresenterImpl extends BasePresenterImpl<IResetView> implements IResetPresenter {

    private static final String TAG = "ResetPresenterImpl";

    public ResetPresenterImpl(IResetView iView) {
        super(iView);
    }

    @Override
    public void reset(String username) {
        mViewRef.get().showProgressDialog();
        Subscription s = MyApplication.mRetrofit.create(ResetApi.class).reset(username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CheckedSubscriber<ResetResult, IResetView>(mViewRef.get()) {
                    @Override
                    public void onError(Throwable e) {
                        mView.hideProgressDialog();
                        mView.showError(e.getMessage());
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(ResetResult resetResult) {
                        mView.hideProgressDialog();
                        if (resetResult.isSuccess()) {
                            mView.showError("密码重置成功!");
                            if (BuildConfig.DEBUG) {
                                Logger.d(TAG, resetResult.toString());
                            }
                            //跳转到登录页面
                            mView.returnToLoginActivity();
                        } else {
                            mView.showError(resetResult.getErrorMsg());
                        }

                    }
                });
        addSubscription(s);
    }

    @Override
    public boolean vertifyResetInfo(String username) {
        if (TextUtils.isEmpty(username)) {
            if (mViewRef.get() != null) {
                mViewRef.get().showError("用户名不能为空！");
            } else {
                Logger.e("ResetActivity已被回收");
            }
            return false;
        }
        return true;
    }
}

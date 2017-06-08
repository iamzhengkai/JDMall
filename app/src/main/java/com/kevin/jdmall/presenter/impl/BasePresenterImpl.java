package com.kevin.jdmall.presenter.impl;

import com.kevin.jdmall.presenter.IBasePresenter;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public class BasePresenterImpl implements IBasePresenter {

    private CompositeSubscription mCompositeSubscription;

    //在使用Rxjava配合Retrofit进行网络操作时使用
    /*
    *  LoginPresenterImpl:
    *  Subscription s =  MyApplication.mRetrofit.create(LoginApi.class).login(username,password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer{...});

       //添加到订阅管理器
       addSubscription(s);
    * */
    protected void addSubscription(Subscription s) {
        if (this.mCompositeSubscription == null) {
            this.mCompositeSubscription = new CompositeSubscription();
        }
        this.mCompositeSubscription.add(s);
    }

    //Activity销毁onDestroy时，取消订阅
    @Override
    public void unsubcrible() {
        if (this.mCompositeSubscription != null) {
            this.mCompositeSubscription.unsubscribe();
        }
    }
}

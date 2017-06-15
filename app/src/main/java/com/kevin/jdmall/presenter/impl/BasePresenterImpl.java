package com.kevin.jdmall.presenter.impl;

import com.kevin.jdmall.iview.ILoginView;
import com.kevin.jdmall.iview.IView;
import com.kevin.jdmall.presenter.IBasePresenter;

import java.lang.ref.WeakReference;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public class BasePresenterImpl<T extends IView> implements IBasePresenter {

    private CompositeSubscription mCompositeSubscription;
    protected WeakReference<T> mViewRef;
    public BasePresenterImpl(T iView){
        if (iView == null)
            throw new IllegalArgumentException("ILoginView must not be null");
        mViewRef = new WeakReference<>(iView);
    }
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
    public boolean isViewDestoryed(){
        return mViewRef.get() == null;
    }
}

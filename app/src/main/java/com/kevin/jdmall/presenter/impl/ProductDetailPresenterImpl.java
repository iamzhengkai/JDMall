package com.kevin.jdmall.presenter.impl;

import com.kevin.jdmall.MyApplication;
import com.kevin.jdmall.api.Add2CartApi;
import com.kevin.jdmall.bean.ToCartResult;
import com.kevin.jdmall.iview.IProductDetailView;
import com.kevin.jdmall.presenter.IProductDetailPresenter;
import com.kevin.jdmall.subscriber.CheckedSubscriber;
import com.kevin.jdmall.utils.ToastUtil;

import java.util.HashMap;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Function:
 *
 * @FileName: com.kevin.jdmall.presenter.impl.ProductDetailPresenterImpl.java
 * @author: zk
 * @date: 2017-06-27 22:30
 */

public class ProductDetailPresenterImpl extends BasePresenterImpl<IProductDetailView> implements
        IProductDetailPresenter {

    private Add2CartApi mAdd2CartApi;

    public ProductDetailPresenterImpl(IProductDetailView iView) {
        super(iView);
        mAdd2CartApi = MyApplication.mRetrofit.create(Add2CartApi.class);

    }

    public boolean initParams(int userId, int productId, int buyCount, HashMap<String, Object>
            params) {
        if (userId == -1 || productId == -1 || buyCount == 0) {
            if (userId == -1)
                ToastUtil.showToast("please login first!");
            else if (productId == -1)
                ToastUtil.showToast("productId is -1!");
            else
                ToastUtil.showToast("buyCount must bigger than zero!");
            return false;
        } else {
            params.put("userId", userId);
            params.put("productId", productId);
            params.put("buyCount", buyCount);
            return true;
        }
    }

    public void add2Cart(HashMap<String, Object> params) {
        Subscription subscription = mAdd2CartApi.add2Cart(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CheckedSubscriber<ToCartResult, IProductDetailView>(mViewRef.get()) {

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(ToCartResult result) {
                        if (result.isSuccess()){
                            ToastUtil.showToast("add to cart succeed!");
                        }else {
                            ToastUtil.showToast("add to cart failed!," + result.getErrorMsg());
                        }
                    }
                });
        addSubscription(subscription);
    }
}

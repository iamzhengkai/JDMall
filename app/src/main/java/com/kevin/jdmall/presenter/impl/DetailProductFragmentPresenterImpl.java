package com.kevin.jdmall.presenter.impl;

import com.kevin.jdmall.MyApplication;
import com.kevin.jdmall.api.DetailProductApi;
import com.kevin.jdmall.bean.DetailProductResult;
import com.kevin.jdmall.bean.ProductCommentResult;
import com.kevin.jdmall.iview.IDetailProductView;
import com.kevin.jdmall.presenter.IDetailProductFragmentPresenter;
import com.kevin.jdmall.subscriber.CheckedSubscriber;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Function:
 *
 * @FileName: com.kevin.jdmall.presenter.impl.HomeFragmentPresenterImpl.java
 * @author: zk
 * @date: 2017-06-14 13:15
 */

public class DetailProductFragmentPresenterImpl extends BasePresenterImpl<IDetailProductView> implements IDetailProductFragmentPresenter {
    private DetailProductApi mDetailProductApi;
    public DetailProductFragmentPresenterImpl(IDetailProductView iView) {
        super(iView);
        mDetailProductApi = MyApplication.mRetrofit.create(DetailProductApi.class);
    }

    @Override
    public void getProduct(int productId) {
        mDetailProductApi.getProduct(productId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CheckedSubscriber<DetailProductResult,IDetailProductView>(mViewRef.get()) {
                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }
                    @Override
                    public void onNext(DetailProductResult result) {
                        mView.bindView(result);
                    }
                });
    }

    @Override
    public void getProductComment(int productId, int type) {
        mDetailProductApi.getProductComment(productId,type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CheckedSubscriber<ProductCommentResult,IDetailProductView>(mViewRef.get()) {
                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }
                    @Override
                    public void onNext(ProductCommentResult result) {
                        mView.bindCommentList(result);
                    }
                });
    }


}

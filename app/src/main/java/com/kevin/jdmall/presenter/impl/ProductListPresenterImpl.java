package com.kevin.jdmall.presenter.impl;

import com.kevin.jdmall.MyApplication;
import com.kevin.jdmall.adapter.BrandAdapter;
import com.kevin.jdmall.api.ProductListApi;
import com.kevin.jdmall.bean.BannerResult;
import com.kevin.jdmall.bean.BrandResult;
import com.kevin.jdmall.bean.SecondLevelCategoryResult;
import com.kevin.jdmall.iview.ICategoryFragmentView;
import com.kevin.jdmall.iview.IProductListView;
import com.kevin.jdmall.presenter.IProductListPresenter;
import com.kevin.jdmall.subscriber.CheckedSubscriber;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Function:
 *
 * @FileName: com.kevin.jdmall.presenter.impl.ProductListPresenterImpl.java
 * @author: zk
 * @date: 2017-06-25 17:45
 */

public class ProductListPresenterImpl extends BasePresenterImpl<IProductListView> implements IProductListPresenter {
    private ProductListApi mProductListApi;
    public ProductListPresenterImpl(IProductListView iView) {
        super(iView);
        mProductListApi = MyApplication.mRetrofit.create(ProductListApi.class);
    }


    @Override
    public void getBrandInfo(int categoryId) {
        Subscription subscription = mProductListApi.getBrandInfo(categoryId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CheckedSubscriber<BrandResult,IProductListView>(mViewRef.get()) {

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(BrandResult result) {
                        mView.initBrand(result.getResult());
                    }
                });
        addSubscription(subscription);
    }
}

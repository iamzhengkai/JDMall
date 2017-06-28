package com.kevin.jdmall.presenter.impl;

import android.text.TextUtils;
import android.widget.Toast;

import com.kevin.jdmall.MyApplication;
import com.kevin.jdmall.adapter.BrandAdapter;
import com.kevin.jdmall.api.ProductListApi;
import com.kevin.jdmall.bean.BannerResult;
import com.kevin.jdmall.bean.BrandResult;
import com.kevin.jdmall.bean.ProductListResult;
import com.kevin.jdmall.bean.SecondLevelCategoryResult;
import com.kevin.jdmall.iview.ICategoryFragmentView;
import com.kevin.jdmall.iview.IProductListView;
import com.kevin.jdmall.presenter.IProductListPresenter;
import com.kevin.jdmall.subscriber.CheckedSubscriber;
import com.kevin.jdmall.utils.ToastUtil;

import java.util.HashMap;
import java.util.Map;

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

public class ProductListPresenterImpl extends BasePresenterImpl<IProductListView> implements
        IProductListPresenter {
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
                .subscribe(new CheckedSubscriber<BrandResult, IProductListView>(mViewRef.get()) {

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

    @Override
    public void getProductList(HashMap<String, Object> params) {
        Subscription subscription = mProductListApi.getProductList(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CheckedSubscriber<ProductListResult, IProductListView>(mViewRef
                        .get()) {

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(ProductListResult result) {
                        mView.initProductList(result.getResult().getRows());
                    }
                });
        addSubscription(subscription);
    }

    @Override
    public boolean vertifyPrice(String minPriceStr, String maxPriceStr, Map<String,Integer> pricesMap) {
        int minPrice = 0;
        int maxPrice = 0;
        if (!TextUtils.isEmpty(minPriceStr)){
            minPrice = Integer.valueOf(minPriceStr);
            if (minPrice < 0 ){
                ToastUtil.showToast("价格必须大于等于0!");
                return false;
            }
            pricesMap.put("minPrice",minPrice);
        }
        if (!TextUtils.isEmpty(maxPriceStr)){
            maxPrice = Integer.valueOf(maxPriceStr);
            if (maxPrice < 0 ){
                ToastUtil.showToast("价格必须大于等于0!");
                return false;
            }
            pricesMap.put("maxPrice",maxPrice);
        }
        if (pricesMap.keySet().size() == 2){
            if (maxPrice < minPrice){
                ToastUtil.showToast("最高价必须大于等于最低价!");
                pricesMap.clear();
                return false;
            }
        }
        return true;
    }
}

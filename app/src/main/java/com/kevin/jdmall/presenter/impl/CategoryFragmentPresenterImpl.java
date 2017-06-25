package com.kevin.jdmall.presenter.impl;

import com.kevin.jdmall.MyApplication;
import com.kevin.jdmall.api.CategoryFragmentApi;
import com.kevin.jdmall.bean.SecondLevelCategoryResult;
import com.kevin.jdmall.bean.TopLevelCategoryResult;
import com.kevin.jdmall.iview.ICategoryFragmentView;
import com.kevin.jdmall.presenter.ICategoryFragmentPresenter;
import com.kevin.jdmall.subscriber.CheckedSubscriber;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Function:
 *
 * @FileName: com.kevin.jdmall.presenter.impl.CategoryFragmentPresenterImpl.java
 * @author: zk
 * @date: 2017-06-24 23:48
 *
 * public class HomeFragmentPresenterImpl extends BasePresenterImpl<IHomeFragmentView> implements IHomeFragmentPresenter {

 */

public class CategoryFragmentPresenterImpl extends BasePresenterImpl<ICategoryFragmentView> implements ICategoryFragmentPresenter{

    private CategoryFragmentApi mCategoryFragmentApi;
    public CategoryFragmentPresenterImpl(ICategoryFragmentView iView) {
        super(iView);
        mCategoryFragmentApi = MyApplication.mRetrofit.create(CategoryFragmentApi.class);
    }

    @Override
    public void getSecondLevelCategory(TopLevelCategoryResult.ResultBean topInfo) {
        Subscription secKillSubscription = mCategoryFragmentApi.getSecondLevelCategory(topInfo.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CheckedSubscriber<SecondLevelCategoryResult,ICategoryFragmentView>(mViewRef.get()) {

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(SecondLevelCategoryResult result) {
                        mView.refreshSecondLevelCategoryList(topInfo,result.getResult());
                    }
                });
        addSubscription(secKillSubscription);
    }

    @Override
    public void getTopLevelCategory() {
        Subscription secKillSubscription = mCategoryFragmentApi.getTopLevelCategory()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CheckedSubscriber<TopLevelCategoryResult,ICategoryFragmentView>(mViewRef.get()) {

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(TopLevelCategoryResult result) {
                        mView.refreshTopLevelCategroyList(result.getResult());
                    }
                });
        addSubscription(secKillSubscription);
    }
}

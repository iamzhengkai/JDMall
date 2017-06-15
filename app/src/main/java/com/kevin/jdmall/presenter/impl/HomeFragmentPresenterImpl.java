package com.kevin.jdmall.presenter.impl;

import com.kevin.jdmall.MyApplication;
import com.kevin.jdmall.api.HomeFragmentApi;
import com.kevin.jdmall.bean.BannerResult;
import com.kevin.jdmall.iview.IHomeFragmentView;
import com.kevin.jdmall.presenter.IHomeFragmentPresenter;
import com.kevin.jdmall.subscriber.CheckedSubscriber;
import com.kevin.jdmall.utils.ToastUtil;
import com.orhanobut.logger.Logger;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Function:
 *
 * @FileName: com.kevin.jdmall.presenter.impl.HomeFragmentPresenterImpl.java
 * @author: zk
 * @date: 2017-06-14 13:15
 */

public class HomeFragmentPresenterImpl extends BasePresenterImpl<IHomeFragmentView> implements IHomeFragmentPresenter {



    private HomeFragmentApi mHomeFragmentApi;

    public HomeFragmentPresenterImpl(IHomeFragmentView homeFragmentView) {
        super(homeFragmentView);
        mHomeFragmentApi = MyApplication.mRetrofit.create(HomeFragmentApi.class);
    }

    @Override
    public void getBanner(int type) {
        Subscription bannerSubscription = (Subscription) mHomeFragmentApi.getBanner(1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CheckedSubscriber<BannerResult,IHomeFragmentView>(mViewRef.get()) {
                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(BannerResult bannerResult) {
                        //更新banner
                        Logger.i(bannerResult.toString());
                        ToastUtil.showToast(bannerResult.toString());
                    }
                });
        addSubscription(bannerSubscription);
    }
}

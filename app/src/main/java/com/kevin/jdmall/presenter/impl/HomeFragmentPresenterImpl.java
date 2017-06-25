package com.kevin.jdmall.presenter.impl;

import android.util.Log;

import com.bumptech.glide.Glide;
import com.kevin.jdmall.MyApplication;
import com.kevin.jdmall.api.HomeFragmentApi;
import com.kevin.jdmall.bean.BannerResult;
import com.kevin.jdmall.bean.RecommendResult;
import com.kevin.jdmall.bean.SecKillResult;
import com.kevin.jdmall.iview.IHomeFragmentView;
import com.kevin.jdmall.presenter.IHomeFragmentPresenter;
import com.kevin.jdmall.subscriber.CheckedSubscriber;
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
        Subscription bannerSubscription = mHomeFragmentApi.getBanner(type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CheckedSubscriber<BannerResult,IHomeFragmentView>(mViewRef.get()) {
                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(BannerResult bannerResult) {
                        Logger.e("=====================BannerResult:" + bannerResult.toString());
                        //获取数据完毕
                        //设置适配器
                        switch (type){
                            case 1:
                                mView.startRun(bannerResult.getResult());
                                break;
                            case 2:
                                mView.showAd(bannerResult.getResult().get(0).getAdUrl());
                                break;
                        }
                        //初始化小点
                        //开始循环
                        //处理点击事件
                        //手指触摸暂停循环

                       /* //更新banner
                        Logger.i(bannerResult.toString());
                        ToastUtil.showToast(bannerResult.toString());
                        //设置ViewPager适配器，开始循环
                        Observable.interval(2000,2000, TimeUnit.MILLISECONDS)
                                .map(new Func1<Long, Long>() {
                                    @Override
                                    public Long call(Long aLong) {
                                        long i = aLong / 2000;
                                        return i % bannerResult.getResult().size();
                                    }
                                })
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(i -> toNextPage(i));*/

                    }
                });
        addSubscription(bannerSubscription);
    }

    @Override
    public void getSecKill() {
        Subscription secKillSubscription = mHomeFragmentApi.getSecKill()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CheckedSubscriber<SecKillResult,IHomeFragmentView>(mViewRef.get()) {

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(SecKillResult secKillResult) {

                        mView.initSecKill(secKillResult.getResult().getRows());
                    }
                });
        addSubscription(secKillSubscription);
    }

    @Override
    public void getYourFav() {
        Subscription secKillSubscription = mHomeFragmentApi.getYourFav()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CheckedSubscriber<RecommendResult,IHomeFragmentView>(mViewRef.get()) {

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(RecommendResult recommendResult) {
                        mView.initRecommend(recommendResult.getResult().getRows());
                    }
                });
        addSubscription(secKillSubscription);
    }


}

package com.kevin.jdmall.presenter.impl;

import com.kevin.jdmall.MyApplication;
import com.kevin.jdmall.api.DetailCommentApi;
import com.kevin.jdmall.bean.CommentCountResult;
import com.kevin.jdmall.bean.CommentDetailResult;
import com.kevin.jdmall.iview.IDetailCommentView;
import com.kevin.jdmall.presenter.IDetailCommentFragmentPresenter;
import com.kevin.jdmall.subscriber.CheckedSubscriber;

import java.util.Map;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Function:
 *
 * @FileName: com.kevin.jdmall.presenter.impl.DetailCommentFragmentPresenterImpl.java
 * @author: zk
 * @date: 2017-06-28 17:52
 */

public class DetailCommentFragmentPresenterImpl
        extends BasePresenterImpl<IDetailCommentView>
        implements IDetailCommentFragmentPresenter {
    private DetailCommentApi mDetailCommentApi;
    public DetailCommentFragmentPresenterImpl(IDetailCommentView iView) {
        super(iView);
        mDetailCommentApi = MyApplication.mRetrofit.create(DetailCommentApi.class);
    }


    @Override
    public void getCommentCount(int productId) {
        mDetailCommentApi.getCommentCount(productId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CheckedSubscriber<CommentCountResult,IDetailCommentView>(mViewRef.get()) {
                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }
                    @Override
                    public void onNext(CommentCountResult result) {
                        mView.setCommentCount(result);
                    }
                });
    }

    @Override
    public void getCommentDetail(Map<String,Object> params) {
        mDetailCommentApi.getCommentDetail(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CheckedSubscriber<CommentDetailResult,IDetailCommentView>(mViewRef.get()) {
                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }
                    @Override
                    public void onNext(CommentDetailResult result) {
                        mView.setComment(result);
                    }
                });
    }
}

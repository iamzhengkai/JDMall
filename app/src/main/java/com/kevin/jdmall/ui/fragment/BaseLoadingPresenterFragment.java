package com.kevin.jdmall.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.kevin.jdmall.presenter.impl.BasePresenterImpl;

/**
 * Created by Administrator on 2017/6/8.
 */

public abstract class BaseLoadingPresenterFragment<T extends BasePresenterImpl> extends BaseLoadingFragment {
    protected T mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //TODO 确定是否能够在此处进行初始化
//        mPresenter = initPresenter();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter = initPresenter();
    }

    protected abstract T initPresenter();

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null)
            mPresenter.unsubcrible();
    }
}

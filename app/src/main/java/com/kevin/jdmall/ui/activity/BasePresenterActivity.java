package com.kevin.jdmall.ui.activity;

import android.os.Bundle;

import com.kevin.jdmall.presenter.impl.BasePresenterImpl;

/**
 * Created by Administrator on 2017/6/8.
 */

public abstract class BasePresenterActivity<T extends BasePresenterImpl> extends BaseActivity {
    protected T mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = initPresenter();
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null)
            mPresenter.unsubcrible();
    }

    protected abstract T initPresenter();

    protected  void initData(){
    }
}

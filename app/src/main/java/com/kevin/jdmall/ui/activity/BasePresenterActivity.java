package com.kevin.jdmall.ui.activity;

import android.os.Bundle;

import com.kevin.jdmall.presenter.impl.BasePresenterImpl;

/**
 * Created by Administrator on 2017/6/8.
 */

public abstract class BasePresenterActivity extends BaseActivity {
    protected BasePresenterImpl mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = initPresenter();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null)
            mPresenter.unsubcrible();
    }

    protected abstract BasePresenterImpl initPresenter();
}

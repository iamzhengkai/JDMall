package com.kevin.jdmall.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kevin.jdmall.presenter.impl.BasePresenterImpl;

/**
 * Created by Administrator on 2017/6/8.
 */

public abstract class BasePresenterFragment<T extends BasePresenterImpl> extends BaseFragment {
    protected T mPresenter;
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        mPresenter = initPresenter();
        super.onActivityCreated(savedInstanceState);
    }

    protected abstract T initPresenter();

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null)
            mPresenter.unsubcrible();
    }
}

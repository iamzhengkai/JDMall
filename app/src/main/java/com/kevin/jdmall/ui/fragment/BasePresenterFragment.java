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

public abstract class BasePresenterFragment extends Fragment {
    protected BasePresenterImpl mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //TODO 确定是否能够在此处进行初始化
        mPresenter = initPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        return initView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        mPresenter = initPresenter();
    }

    protected abstract View initView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState);

    protected abstract BasePresenterImpl initPresenter();

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null)
            mPresenter.unsubcrible();
    }
}

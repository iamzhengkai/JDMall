package com.kevin.jdmall.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kevin.jdmall.R;
import com.kevin.jdmall.presenter.impl.BasePresenterImpl;

/**
 * Created by Administrator on 2017/6/8.
 */

public class CartFragment extends BasePresenterFragment {
    @Override
    protected View initView() {
       View view = View.inflate(mActivity, R.layout.fragment_shopcar,null);
        return view;
    }

    @Override
    protected BasePresenterImpl initPresenter() {
        return null;
    }

    @Override
    protected void bindView() {


    }
}

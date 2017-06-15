package com.kevin.jdmall.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kevin.jdmall.iview.IHomeFragmentView;
import com.kevin.jdmall.presenter.impl.BasePresenterImpl;
import com.kevin.jdmall.presenter.impl.HomeFragmentPresenterImpl;

/**
 * Created by Administrator on 2017/6/8.
 */

public class HomeFragment extends BasePresenterFragment<HomeFragmentPresenterImpl> implements IHomeFragmentView{
    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        TextView view = new TextView(getActivity());
        view.setText("首页");
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.getBanner(1);
            }
        });
        return view;
    }

    @Override
    protected HomeFragmentPresenterImpl initPresenter() {
        return new HomeFragmentPresenterImpl(this);
    }


}

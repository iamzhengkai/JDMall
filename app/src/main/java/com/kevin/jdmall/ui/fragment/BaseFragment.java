package com.kevin.jdmall.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 基类Fragment, 所有Fragment继承此类
 * 
 * 1. 定义Activity常量,方便子类使用 2. 定义抽象方法initViews,初始化布局,必须实现 3.
 * 定义方法initData,初始化数据,可以不实现
 * 
 * @author Kevin
 * 
 */
public abstract class BaseFragment extends Fragment {

	public Activity mActivity;
	private Unbinder mUnbinder;

	// Fragment创建
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mActivity = getActivity();
	}

	// Fragment填充布局
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = initView();
		mUnbinder = ButterKnife.bind(this, view);
		return view;
	}

	/**
	 * 初始化布局
	 * 
	 * @return
	 */
	public abstract View initView();

	// Fragment所依赖的Activity创建完成
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		initData();
	}

	/**
	 * 初始化数据
	 */
	public void initData() {
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		mUnbinder.unbind();
	}
}

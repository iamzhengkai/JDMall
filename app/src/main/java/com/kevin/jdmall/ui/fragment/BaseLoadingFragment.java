package com.kevin.jdmall.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kevin.jdmall.ui.view.LoadingPage;
import com.kevin.jdmall.utils.ViewUtil;

public abstract class BaseLoadingFragment extends Fragment {
	protected LoadingPage mLoadingPage;
	protected Activity mActivity;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mActivity = getActivity();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
		if(mLoadingPage ==null){
			mLoadingPage = new LoadingPage(mActivity) {
				@Override
				public void loadDataAndRefreshView() {
					BaseLoadingFragment.this.loadDataAndRefreshView();
				}

				@Override
				protected View createSuccessView() {
					return BaseLoadingFragment.this.createSuccessView();
				}
			};
		}else {
			ViewUtil.removeSelfFromParent(mLoadingPage);
		}
		return mLoadingPage;
	}

	protected abstract void loadDataAndRefreshView();

	protected abstract View createSuccessView();
	protected Object loadData(){
		return null;
	}
}

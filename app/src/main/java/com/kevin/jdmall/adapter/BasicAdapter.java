package com.kevin.jdmall.adapter;

import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class BasicAdapter<T,V extends BaseHolder<T>> extends BaseAdapter {
	protected List<T> list;
	protected int mPosition;

	BasicAdapter(List<T> list) {
		super();
		this.list = list;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public T getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// 1.初始化holder
		V holder = null;
		if (convertView == null) {
			holder = getHolder();//需要一个不固定的holder
		} else {
			holder = (V) convertView.getTag();
		}
		// 3.绑定数据
		holder.bindData(list.get(position),position,mPosition);

		return holder.getHolderView();
	}

	protected abstract V getHolder();

	public int getPosition() {
		return mPosition;
	}

	public void setPosition(int position) {
		mPosition = position;
	}
}

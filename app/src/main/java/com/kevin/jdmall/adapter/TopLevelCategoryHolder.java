package com.kevin.jdmall.adapter;

import android.view.View;
import android.widget.TextView;

import com.kevin.jdmall.MyApplication;
import com.kevin.jdmall.R;
import com.kevin.jdmall.bean.TopLevelCategoryResult;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Function:
 *
 * @FileName: com.kevin.jdmall.adapter.TopLevelCategoryHolder.java
 * @author: zk
 * @date: 2017-06-25 10:02
 */

public class TopLevelCategoryHolder extends BaseHolder<TopLevelCategoryResult.ResultBean> {
    @BindView(R.id.tv)
    TextView mTv;
    @BindView(R.id.divider)
    View mDivider;

    @Override
    public View initHolderView() {
        View view = View.inflate(MyApplication.mContext, R.layout.item_top_category, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void bindData(TopLevelCategoryResult.ResultBean data,int position,int currentPosition) {
        mTv.setText(data.getName());
        //设置左边的红色线条和背景颜色
        if (currentPosition == position) {
            mTv.setBackgroundResource(R.drawable.tongcheng_all_bg01);
            mDivider.setVisibility(View.INVISIBLE);
        }else {
            mTv.setBackgroundColor(0xf4f4f4);
            mDivider.setVisibility(View.VISIBLE);
        }
        //文字的颜色是一个Selector 设置文字的颜色
        mTv.setSelected(currentPosition == position);

    }
}

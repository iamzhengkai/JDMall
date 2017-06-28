package com.kevin.jdmall.ui.fragment;

import android.view.View;
import android.widget.TextView;

import com.kevin.jdmall.MyConstants;

/**
 * Function:
 *
 * @FileName: com.kevin.jdmall.ui.fragment.ProductDetailDetailFragment.java
 * @author: zk
 * @date: 2017-06-27 23:40
 */

public class ProductDetailCommentFragment extends BaseFragment {
    @Override
    protected View initView() {
        TextView textView = new TextView(mActivity);
        textView.setText(MyConstants.FragmentTags.COMMENT);
        return textView;
    }
}

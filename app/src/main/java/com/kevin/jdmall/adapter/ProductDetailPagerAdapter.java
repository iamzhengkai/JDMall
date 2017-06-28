package com.kevin.jdmall.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


import com.kevin.jdmall.MyConstants;
import com.kevin.jdmall.ui.fragment.BaseFragment;
import com.kevin.jdmall.ui.fragment.factory.ProductDetailFragmentFactory;


/**
 * Function:
 *
 * @FileName: com.kevin.zkmusic.adapter.ProductDetailPagerAdapter.java
 * @author: zk
 * @date: 2017-04-18 19:47
 */

public class ProductDetailPagerAdapter extends FragmentPagerAdapter {
    private String[] mTags = {
            MyConstants.FragmentTags.PRODUCT,
            MyConstants.FragmentTags.DETIAL,
            MyConstants.FragmentTags.COMMENT,
    };
    public ProductDetailPagerAdapter(FragmentManager fm) {
        super(fm);
    }
    @Override
    public Fragment getItem(int position) {
        BaseFragment fragment = ProductDetailFragmentFactory.obtainFragment(mTags[position]);
        return fragment;
    }

    @Override
    public int getCount() {
        return mTags.length;
    }
}
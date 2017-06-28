package com.kevin.jdmall.ui.fragment.factory;


import com.kevin.jdmall.MyConstants;
import com.kevin.jdmall.ui.fragment.BaseFragment;
import com.kevin.jdmall.ui.fragment.ProductDetailCommentFragment;
import com.kevin.jdmall.ui.fragment.ProductDetailDetailFragment;
import com.kevin.jdmall.ui.fragment.ProductDetailProductFragment;

import java.util.HashMap;

public class ProductDetailFragmentFactory {
    private static HashMap<String, BaseFragment> fragmentCache =
            new HashMap<>();

    public static BaseFragment obtainFragment(String tag) {
        BaseFragment fragment = fragmentCache.get(tag);
        if (fragment == null) {
            switch (tag) {
                case MyConstants.FragmentTags.PRODUCT:
                    fragment = new ProductDetailProductFragment();
                    break;
                case MyConstants.FragmentTags.DETIAL:
                    fragment = new ProductDetailDetailFragment();
                    break;
                case MyConstants.FragmentTags.COMMENT:
                    fragment = new ProductDetailCommentFragment();
                    break;

            }
            fragmentCache.put(tag, fragment);
        }
        return fragment;
    }

    public static void removeFragment(String tag){
        fragmentCache.remove(tag);
    }

}

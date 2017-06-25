package com.kevin.jdmall.adapter;

import com.kevin.jdmall.bean.TopLevelCategoryResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Function:
 *
 * @FileName: com.kevin.jdmall.adapter.TopLevelCategoryAdapter.java
 * @author: zk
 * @date: 2017-06-25 09:55
 */

public class TopLevelCategoryAdapter extends BasicAdapter<TopLevelCategoryResult.ResultBean,TopLevelCategoryHolder> {

    public TopLevelCategoryAdapter(List<TopLevelCategoryResult.ResultBean> list) {
        super(list);
    }

    @Override
    protected TopLevelCategoryHolder getHolder() {
        return new TopLevelCategoryHolder();
    }
}

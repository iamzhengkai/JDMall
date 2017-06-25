package com.kevin.jdmall.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.kevin.jdmall.MyConstants;
import com.kevin.jdmall.R;
import com.kevin.jdmall.bean.BannerResult;
import com.kevin.jdmall.ui.view.RatioImageView;

import java.util.List;

/**
 * Function:
 *
 * @FileName: com.kevin.jdmall.adapter.HomeBannerAdapter.java
 * @author: zk
 * @date: 2017-06-23 20:52
 */

public class HomeBannerAdapter extends BasePagerAdapter<BannerResult.ResultBean> {
    private Context mContext;

    public HomeBannerAdapter(List<BannerResult.ResultBean> list, Context context) {
        super(list);
        mContext = context;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        RatioImageView ratioImageView = (RatioImageView) View.inflate(mContext, R.layout
                .item_ratio_iamge_view, null);
        //if (mList.size() > 0) {
            int realPosition = position % mList.size();
            String imageUrl = MyConstants.BASE_URL + mList.get(realPosition).getAdUrl();
            Glide.with(mContext).load(imageUrl).into(ratioImageView);
        //}
        container.addView(ratioImageView);
        return ratioImageView;
    }
}

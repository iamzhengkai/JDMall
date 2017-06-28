package com.kevin.jdmall.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.kevin.jdmall.MyConstants;
import com.kevin.jdmall.R;
import com.kevin.jdmall.bean.BannerResult;
import com.kevin.jdmall.ui.view.RatioImageView;
import com.orhanobut.logger.Logger;

import java.util.List;

/**
 * Function:
 *
 * @FileName: com.kevin.jdmall.adapter.HomeBannerAdapter.java
 * @author: zk
 * @date: 2017-06-23 20:52
 */

public class ProductDetailBannerPagerAdapter extends BasePagerAdapter<String> {
    private Context mContext;

    public ProductDetailBannerPagerAdapter(List<String> list, Context context) {
        super(list);
        mContext = context;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        RatioImageView ratioImageView = (RatioImageView) View.inflate(mContext, R.layout
                .item_img_product_detail_banner, null);
        String imageUrl = MyConstants.BASE_URL + mList.get(position);
        Logger.e("imageUrl====>" + imageUrl);
        Glide.with(mContext).load(imageUrl).into(ratioImageView);
        container.addView(ratioImageView);
        return ratioImageView;
    }
}

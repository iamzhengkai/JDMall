package com.kevin.jdmall.ui.fragment;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.kevin.jdmall.R;
import com.kevin.jdmall.adapter.DetailProductBrandAdapter;
import com.kevin.jdmall.adapter.DetailProductCommentAdapter;
import com.kevin.jdmall.adapter.ProductDetailBannerPagerAdapter;
import com.kevin.jdmall.bean.DetailProductResult;
import com.kevin.jdmall.bean.ProductCommentResult;
import com.kevin.jdmall.iview.IDetailProductView;
import com.kevin.jdmall.presenter.impl.DetailProductFragmentPresenterImpl;
import com.kevin.jdmall.ui.activity.ProductDetailActivity;
import com.orhanobut.logger.Logger;

import java.util.List;

import butterknife.BindView;

/**
 * Function:
 *
 * @FileName: com.kevin.jdmall.ui.fragment.ProductDetailDetailFragment.java
 * @author: zk
 * @date: 2017-06-27 23:40
 */

public class ProductDetailProductFragment extends
        BasePresenterFragment<DetailProductFragmentPresenterImpl> implements IDetailProductView {
    @BindView(R.id.vp_ad)
    ViewPager mVpAd;
    @BindView(R.id.vp_indic_tv)
    TextView mTvIndicator;
    @BindView(R.id.name_tv)
    TextView mNameTv;
    @BindView(R.id.self_sale_tv)
    TextView mSelfSaleTv;
    @BindView(R.id.desc_tv)
    TextView mDescTv;
    @BindView(R.id.recommend_buy_tv)
    TextView mRecommendBuyTv;
    @BindView(R.id.price_tv)
    TextView mPriceTv;
    @BindView(R.id.tip_tv)
    TextView mTipTv;
    @BindView(R.id.product_versions_gv)
    RecyclerView mProductVersionsGv;
    @BindView(R.id.good_rate_tip)
    TextView mGoodRateTip;
    @BindView(R.id.good_rate_tv)
    TextView mGoodRateTv;
    @BindView(R.id.good_comment_tv)
    TextView mGoodCommentTv;
    @BindView(R.id.rv_good_comment)
    RecyclerView mRvGoodComment;
    @BindView(R.id.scrollview)
    ScrollView mScrollview;
    @BindView(R.id.scroll_to_top_indic)
    TextView mScrollToTopIndic;
    private ProductDetailBannerPagerAdapter mMainAdapter;
    private DetailProductCommentAdapter mCommentAdapter;

    @Override
    protected View initView() {
        View view = View.inflate(mActivity, R.layout.product_introduce_view, null);
        return view;
    }

    @Override
    protected void bindView() {
        FlexboxLayoutManager flexboxLayoutManager = new FlexboxLayoutManager(mActivity);
        flexboxLayoutManager.setFlexDirection(FlexDirection.ROW);
        flexboxLayoutManager.setFlexWrap(FlexWrap.WRAP);
        mProductVersionsGv.setLayoutManager(flexboxLayoutManager);
        mRvGoodComment.setLayoutManager(new LinearLayoutManager(mActivity));
    }

    @Override
    public void initData() {
        int productId = ((ProductDetailActivity) mActivity).mProductId;
        mPresenter.getProduct(productId);
        mPresenter.getProductComment(productId,1);
    }

    @Override
    protected DetailProductFragmentPresenterImpl initPresenter() {
        return new DetailProductFragmentPresenterImpl(this);
    }

    @Override
    public void bindView(DetailProductResult result) {
        List<String> urls = result.getResult().getImgUrls();
        mMainAdapter = new ProductDetailBannerPagerAdapter(urls, mActivity);
        Logger.e(urls.toString());
        mVpAd.setAdapter(mMainAdapter);
        mTvIndicator.setText(1 + "/" + urls.size());
        mVpAd.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int
                    positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mTvIndicator.setText(++position + "/" + urls.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mNameTv.setText(result.getResult().getName());

        if (result.getResult().isIfSaleOneself()){
            mSelfSaleTv.setVisibility(View.VISIBLE);
        }else {
            mSelfSaleTv.setVisibility(View.INVISIBLE);
        }
        mDescTv.setText(result.getResult().getRecomProduct());
        mPriceTv.setText("" + result.getResult().getPrice());
        mGoodRateTv.setText(result.getResult().getFavcomRate() + "%好评");
        mGoodCommentTv.setText(result.getResult().getCommentCount() + "人评价");
        mProductVersionsGv.setAdapter(new DetailProductBrandAdapter(mActivity,result.getResult().getTypeList()));

    }

    @Override
    public void bindCommentList(ProductCommentResult result) {
        mCommentAdapter = new DetailProductCommentAdapter(mActivity, result.getResult());
        mRvGoodComment.setAdapter(mCommentAdapter);
    }

}

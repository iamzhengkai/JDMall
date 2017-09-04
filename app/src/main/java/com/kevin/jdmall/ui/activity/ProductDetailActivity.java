package com.kevin.jdmall.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.kevin.jdmall.MyApplication;
import com.kevin.jdmall.MyConstants;
import com.kevin.jdmall.R;
import com.kevin.jdmall.adapter.ProductDetailPagerAdapter;
import com.kevin.jdmall.iview.IProductDetailView;
import com.kevin.jdmall.presenter.impl.ProductDetailPresenterImpl;
import com.kevin.jdmall.ui.fragment.ProductDetailProductFragment;
import com.kevin.jdmall.ui.fragment.factory.ProductDetailFragmentFactory;
import com.kevin.jdmall.utils.ToastUtil;
import com.orhanobut.logger.Logger;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProductDetailActivity extends
        BasePresenterActivity<ProductDetailPresenterImpl> implements IProductDetailView {

    @BindView(R.id.details_view)
    View mDetailsView;
    @BindView(R.id.introduce_view)
    View mIntroduceView;
    @BindView(R.id.comment_view)
    View mCommentView;
    @BindView(R.id.vp_container)
    ViewPager mVpContainer;
    private ProductDetailPagerAdapter mContainerPagarAdpter;
    public int mProductId;


    @Override
    protected ProductDetailPresenterImpl initPresenter() {
        return new ProductDetailPresenterImpl(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_productdetails;
    }

    @Override
    protected void initData() {
        mProductId = getIntent().getIntExtra(MyConstants.EXTRA_PRODUCT_DETAIL_ID, 0);
        if (mProductId == 0) {
            Logger.e("获取产品id失败");
            ToastUtil.showToast("获取产品id失败");
            throw new IllegalStateException("Failed to get product id!");
        }
        mContainerPagarAdpter = new ProductDetailPagerAdapter(getSupportFragmentManager());
        mVpContainer.setAdapter(mContainerPagarAdpter);
        mVpContainer.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int
                    positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                hideAllIndicators();
                switch (position) {
                    case 0:
                        mIntroduceView.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        mDetailsView.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        mCommentView.setVisibility(View.VISIBLE);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    @OnClick({R.id.details_ll, R.id.introduce_ll, R.id.comment_ll})
    public void onViewClicked(View view) {
        hideAllIndicators();
        switch (view.getId()) {
            case R.id.details_ll:
                mDetailsView.setVisibility(View.VISIBLE);
                mVpContainer.setCurrentItem(1);
                break;
            case R.id.introduce_ll:
                mIntroduceView.setVisibility(View.VISIBLE);
                mVpContainer.setCurrentItem(0);
                break;
            case R.id.comment_ll:
                mCommentView.setVisibility(View.VISIBLE);
                mVpContainer.setCurrentItem(2);
                break;
        }
    }

    private void hideAllIndicators() {
        mCommentView.setVisibility(View.INVISIBLE);
        mDetailsView.setVisibility(View.INVISIBLE);
        mIntroduceView.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //避免内存泄漏
        ProductDetailFragmentFactory.clearFragment();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.add2shopcar)
    public void onViewClicked() {
        //1.get params
        HashMap<String,Object> params = new HashMap<>();
        ProductDetailProductFragment detailFragment =
                (ProductDetailProductFragment) mContainerPagarAdpter.getItem(0);
        mPresenter.initParams(MyApplication.userId,mProductId,
                detailFragment.getProductCount(),params);
        //2.send request
        mPresenter.add2Cart(params);
        //3.show toast
    }
}

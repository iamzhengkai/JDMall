package com.kevin.jdmall.ui.activity;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.TextView;

import com.kevin.jdmall.MyConstants;
import com.kevin.jdmall.R;
import com.kevin.jdmall.adapter.BrandAdapter;
import com.kevin.jdmall.bean.BrandResult;
import com.kevin.jdmall.iview.IProductListView;
import com.kevin.jdmall.presenter.impl.ProductListPresenterImpl;
import com.kevin.jdmall.ui.view.pop.PopupWindowBuilder;
import com.orhanobut.logger.Logger;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Function:
 *
 * @FileName: com.kevin.jdmall.ui.activity.ProductListActivity.java
 * @author: zk
 * @date: 2017-06-25 17:42
 */

public class ProductListActivity extends BasePresenterActivity<ProductListPresenterImpl>
        implements IProductListView{

    @BindView(R.id.rv_brand)
    RecyclerView mRvBrand;
    @BindView(R.id.all_indicator)
    TextView mAllIndicator;
    @BindView(R.id.sale_indicator)
    TextView mSaleIndicator;
    @BindView(R.id.price_indicator)
    TextView mPriceIndicator;
    @BindView(R.id.choose_indicator)
    TextView mChooseIndicator;
    @BindView(R.id.rv_product)
    RecyclerView mRvProduct;
    @BindView(R.id.content_view)
    LinearLayout mContentView;
    @BindView(R.id.jd_take_tv)
    TextView mJdTakeTv;
    @BindView(R.id.paywhenreceive_tv)
    TextView mPaywhenreceiveTv;
    @BindView(R.id.justhasstock_tv)
    TextView mJusthasstockTv;
    @BindView(R.id.minPrice_et)
    EditText mMinPriceEt;
    @BindView(R.id.maxPrice_et)
    EditText mMaxPriceEt;
    @BindView(R.id.slide_view)
    ScrollView mSlideView;
    @BindView(R.id.drawerlayout)
    DrawerLayout mDrawerlayout;

    private int mCategoryId;
    private int mTopCategoryId;
    private boolean mIsSelected;
    //TODO 构建请求参数
    private HashMap<String,String> mParams;
    @Override
    protected ProductListPresenterImpl initPresenter() {
        return new ProductListPresenterImpl(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_product_list;
    }

    @Override
    protected void initData() {
        mCategoryId = getIntent().getIntExtra(MyConstants.EXTRA_PRODUCT_LIST_THIRD_ID, -1);
        mTopCategoryId = getIntent().getIntExtra(MyConstants.EXTRA_PRODUCT_LIST_TOP_ID, -1);
        Logger.e("分类id:%d", mCategoryId);
        Logger.e("顶级分类id:%d", mTopCategoryId);

        mRvBrand.setLayoutManager(new GridLayoutManager(this, 3));
        mPresenter.getBrandInfo(mTopCategoryId);
    }

    public void initBrand(List<BrandResult.ResultBean> list) {
        BrandAdapter adapter = new BrandAdapter(this, list);
        mRvBrand.setAdapter(adapter);
    }

    @OnClick({R.id.all_indicator, R.id.sale_indicator, R.id.price_indicator, R.id.choose_indicator})
    public void onViewClicked(View view) {
        PopupWindow popupWindow;
        switch (view.getId()) {
            case R.id.all_indicator:
                View popView = View.inflate(this,R.layout.product_sort_pop_view,null);
                popupWindow = new PopupWindowBuilder(this).setView(popView).Build();
                popupWindow.showAsDropDown(mAllIndicator);

                popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        mAllIndicator.setSelected(false);
                    }
                });

                if (popupWindow.isShowing()){
                    mAllIndicator.setSelected(true);
                }
                break;
            case R.id.sale_indicator:
                break;
            case R.id.price_indicator:
                mPriceIndicator.setSelected(!mIsSelected);
                mIsSelected = !mIsSelected;
                break;
            case R.id.choose_indicator:
                break;
        }
    }
}

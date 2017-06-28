package com.kevin.jdmall.ui.activity;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.TextView;

import com.kevin.jdmall.MyConstants;
import com.kevin.jdmall.R;
import com.kevin.jdmall.adapter.BrandAdapter;
import com.kevin.jdmall.adapter.ProductListAdapter;
import com.kevin.jdmall.bean.BrandResult;
import com.kevin.jdmall.bean.ProductListResult;
import com.kevin.jdmall.iview.IProductListView;
import com.kevin.jdmall.presenter.impl.ProductListPresenterImpl;
import com.kevin.jdmall.ui.view.pop.PopupWindowBuilder;
import com.orhanobut.logger.Logger;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Function:
 *
 * @FileName: com.kevin.jdmall.ui.activity.ProductListActivity.java
 * @author: zk
 * @date: 2017-06-25 17:42
 */

public class ProductListActivity extends BasePresenterActivity<ProductListPresenterImpl>
        implements IProductListView, View.OnClickListener {

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

    //TODO 构建请求参数
    private HashMap<String, Object> mParams;
    private PopupWindow mPopupWindow;
    private int mDeliverChoose;
    private ProductListAdapter mProductListAdapter;
    private List<ProductListResult.ResultBean.RowsBean> mProductList;

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
        initPop();
        mProductList = new ArrayList<>();
        mProductListAdapter = new ProductListAdapter(this, mProductList);
        mRvProduct.setAdapter(mProductListAdapter);

        int categoryId = getIntent().getIntExtra(MyConstants.EXTRA_PRODUCT_LIST_THIRD_ID, -1);
        int topCategoryId = getIntent().getIntExtra(MyConstants.EXTRA_PRODUCT_LIST_TOP_ID, -1);
        Logger.e("分类id:%d", categoryId);
        Logger.e("顶级分类id:%d", topCategoryId);
        //初始请求参数
        mParams = new HashMap<>();
        mParams.put("categoryId", categoryId);
        mParams.put("filterType", "1");
        mParams.put("deliverChoose",0);

        mRvBrand.setLayoutManager(new GridLayoutManager(this, 3));
        mRvProduct.setLayoutManager(new LinearLayoutManager(this));
        mRvProduct.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this).build());

        mPresenter.getBrandInfo(topCategoryId);
        mPresenter.getProductList(mParams);
    }

    public void initBrand(List<BrandResult.ResultBean> list) {
        BrandAdapter adapter = new BrandAdapter(this, list);
        mRvBrand.setAdapter(adapter);
        adapter.setOnItemClickListener(new BrandAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, BrandResult.ResultBean item) {
                //请求参数
                if (view.isSelected()) {
                    mParams.remove("brandId");
                } else {
                    mParams.put("brandId", item.getId());
                }
              /*  mDrawerlayout.closeDrawer(Gravity.END);
                mPresenter.getProductList(mParams);*/
            }
        });
    }

    @Override
    public void initProductList(List<ProductListResult.ResultBean.RowsBean> list) {
        mProductList.clear();
        mProductList.addAll(list);
        mProductListAdapter.notifyDataSetChanged();
    }

    @OnClick({
            R.id.all_indicator, R.id.sale_indicator, R.id.price_indicator, R.id.choose_indicator,
            R.id.confirm_choose, R.id.reset_choose,R.id.jd_take_tv,R.id.justhasstock_tv,
            R.id.paywhenreceive_tv
    })
    public void onViewClicked(View view) {
        if (mPopupWindow.isShowing()) {
            mPopupWindow.dismiss();
        }
        switch (view.getId()) {
            case R.id.all_indicator:
                showPop();
                break;
            case R.id.sale_indicator:
                mSaleIndicator.setSelected(!mSaleIndicator.isSelected());
                //请求参数
                if (mSaleIndicator.isSelected()){
                    mParams.put("sortType", "1");
                    mPriceIndicator.setSelected(false);
                }else {
                    mParams.remove("sortType");
                }

                mPresenter.getProductList(mParams);
                break;
            case R.id.price_indicator:
                mPriceIndicator.setSelected(!mPriceIndicator.isSelected());
                Logger.e("mPriceIndicator isSelected" + mPriceIndicator.isSelected());
                //请求参数
                if (mPriceIndicator.isSelected()) mSaleIndicator.setSelected(false);
                mParams.put("sortType", mPriceIndicator.isSelected() ? "2" : "3");
                mPresenter.getProductList(mParams);
                break;
            case R.id.choose_indicator:
                mDrawerlayout.openDrawer(Gravity.END);
                break;
            case R.id.confirm_choose:
                //获取最高低价格
                HashMap<String,Integer> priceMap = new HashMap<>();
                String maxPriceStr = mMaxPriceEt.getText().toString();
                String minPriceStr = mMinPriceEt.getText().toString();
                if(mPresenter.vertifyPrice(minPriceStr,maxPriceStr,priceMap)){
                    setPrice(priceMap);
                    mPresenter.getProductList(mParams);
                    mDrawerlayout.closeDrawer(Gravity.END);
                }
                Logger.e(mParams.toString());
                break;
            case R.id.reset_choose:
                mDeliverChoose = 0;
                mParams.put("deliverChoose",0);
                mParams.remove("minPrice");
                mParams.remove("maxPrice");
                mParams.remove("brandId");
                //重置ui
                mJdTakeTv.setSelected(false);
                mPaywhenreceiveTv.setSelected(false);
                mJusthasstockTv.setSelected(false);
                mMinPriceEt.setText("");
                mMaxPriceEt.setText("");
                //重新请求网络
                mDrawerlayout.closeDrawer(Gravity.END);
                mPresenter.getProductList(mParams);
                break;
            case R.id.jd_take_tv:
            case R.id.justhasstock_tv:
            case R.id.paywhenreceive_tv:
                view.setSelected(!view.isSelected());
                mDeliverChoose += mJdTakeTv.isSelected() ? 1 : -1;
                mDeliverChoose += mPaywhenreceiveTv.isSelected() ? 2 : -2;
                mDeliverChoose += mJusthasstockTv.isSelected() ? 4 : -4;
                //服务端api错误
                if (mDeliverChoose > 6){
                    mDeliverChoose = 6;
                }
                Logger.e("mDeliverChoose:===========>%d",mDeliverChoose);
                break;
        }
    }

    private void setPrice(HashMap<String, Integer> priceMap) {
        if (priceMap.containsKey("minPrice"))
            mParams.put("minPrice",priceMap.get("minPrice"));
        else{
            mParams.remove("minPrice");
        }
        if (priceMap.containsKey("maxPrice"))
            mParams.put("maxPrice",priceMap.get("maxPrice"));
        else{
            mParams.remove("maxPrice");
        }
    }

    private void showPop() {

        mPopupWindow.showAsDropDown(mAllIndicator);

        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                mAllIndicator.setSelected(false);
            }
        });

        if (mPopupWindow.isShowing()) {
            mAllIndicator.setSelected(true);
        }
    }

    private void initPop() {
        View popView = View.inflate(this, R.layout.product_sort_pop_view,
                null);

        TextView tvAll = (TextView) popView.findViewById(R.id.all_sort);
        TextView tvNew = (TextView) popView.findViewById(R.id.new_sort);
        TextView tvComment = (TextView) popView.findViewById(R.id.comment_sort);
        View vLeft = (View) popView.findViewById(R.id.left_v);

        tvAll.setOnClickListener(this);
        tvNew.setOnClickListener(this);
        tvComment.setOnClickListener(this);
        vLeft.setOnClickListener(this);


        mPopupWindow = new PopupWindowBuilder(this).setView(popView).Build();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.all_sort:
                mParams.put("filterType", "1");
                break;
            case R.id.new_sort:
                mParams.put("filterType", "2");
                break;
            case R.id.comment_sort:
                mParams.put("filterType", "3");
                break;
        }

        mPriceIndicator.setSelected(false);
        mSaleIndicator.setSelected(false);
        mParams.remove("sortType");

        mPopupWindow.dismiss();
        //请求网络
        mPresenter.getProductList(mParams);
    }

}

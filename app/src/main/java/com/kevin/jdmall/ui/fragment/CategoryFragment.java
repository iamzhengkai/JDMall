package com.kevin.jdmall.ui.fragment;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.flexbox.AlignSelf;
import com.google.android.flexbox.FlexboxLayout;
import com.kevin.jdmall.MyConstants;
import com.kevin.jdmall.R;
import com.kevin.jdmall.adapter.TopLevelCategoryAdapter;
import com.kevin.jdmall.bean.SecondLevelCategoryResult;
import com.kevin.jdmall.bean.TopLevelCategoryResult;
import com.kevin.jdmall.iview.ICategoryFragmentView;
import com.kevin.jdmall.presenter.impl.CategoryFragmentPresenterImpl;
import com.kevin.jdmall.ui.activity.ProductListActivity;
import com.kevin.jdmall.ui.view.RatioImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/6/8.
 * HomeFragment extends BasePresenterFragment<HomeFragmentPresenterImpl> implements
 * IHomeFragmentView
 */

public class CategoryFragment extends BasePresenterFragment<CategoryFragmentPresenterImpl>
        implements ICategoryFragmentView {

    @BindView(R.id.search_et)
    EditText mSearchEt;
    @BindView(R.id.top_lv)
    ListView mTopLv;
    @BindView(R.id.fl_container)
    FlexboxLayout mFlContainer;

    private List<TopLevelCategoryResult.ResultBean> mTopLevelList;
    private TopLevelCategoryAdapter mTopLevelAdapter;
    private List<SecondLevelCategoryResult.ResultBean> mSecondLevelList;

    @Override
    protected CategoryFragmentPresenterImpl initPresenter() {
        return new CategoryFragmentPresenterImpl(this);
    }

    @Override
    protected View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_category, null);
        mTopLevelList = new ArrayList<>();
        mSecondLevelList = new ArrayList<>();
        mTopLevelAdapter = new TopLevelCategoryAdapter(mTopLevelList);
        return view;
    }

    @Override
    public void initData() {
        mTopLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mTopLevelAdapter.setPosition(i);
                mTopLevelAdapter.notifyDataSetChanged();
                //清空已有数据
                mFlContainer.removeAllViews();
                //请求对应的二级分类
                mPresenter.getSecondLevelCategory(mTopLevelList.get(i));
            }
        });
        mTopLv.setAdapter(mTopLevelAdapter);
        mPresenter.getTopLevelCategory();
    }

    @Override
    public void refreshTopLevelCategroyList(List<TopLevelCategoryResult.ResultBean> list) {
        mTopLevelList.addAll(list);
        mTopLevelAdapter.notifyDataSetChanged();
        //一级分类结果返回后，请求二级分类
        mPresenter.getSecondLevelCategory(mTopLevelList.get(0));
    }

    public void refreshSecondLevelCategoryList(TopLevelCategoryResult.ResultBean topInfo,
                                               List<SecondLevelCategoryResult.ResultBean> list) {
        FlexboxLayout.LayoutParams lp = new FlexboxLayout.LayoutParams(
                FlexboxLayout.LayoutParams.MATCH_PARENT, FlexboxLayout
                .LayoutParams.WRAP_CONTENT);
        if (!TextUtils.isEmpty(topInfo.getBannerUrl())){
            RatioImageView bannerImg = (RatioImageView) View.inflate(mActivity,R.layout.img_category_banner,null);
            Glide.with(mActivity).load(MyConstants.BASE_URL + topInfo.getBannerUrl()).into(bannerImg);
//            lp.flexGrow = -1;
            lp.setFlexGrow(-1);
            mFlContainer.addView(bannerImg,lp);
        }
        for (int i = 0; i < list.size(); i++) {
            TextView title = (TextView) View.inflate(mActivity,R.layout.item_second_category_title,null);
//            TextView title = new TextView(mActivity);
            title.setText(list.get(i).getName());
          /*  lp.width = FlexboxLayout.LayoutParams.MATCH_PARENT;
            lp.height = FlexboxLayout.LayoutParams.WRAP_CONTENT;*/
//            lp.flexGrow = -1;
            lp.setFlexGrow(-1);
//                            lp.flexBasisPercent = 1f;
            mFlContainer.addView(title, lp);
            List<SecondLevelCategoryResult.ResultBean.ThirdCategoryBean> itemList = list.get
                    (i).getThirdCategory();
            for (int j = 0; j < itemList.size(); j++) {
                FlexboxLayout.LayoutParams lpin = new FlexboxLayout.LayoutParams(
                        FlexboxLayout.LayoutParams.MATCH_PARENT, FlexboxLayout
                        .LayoutParams.WRAP_CONTENT);
               /* if (j == 0){
                    lpin.flexGrow = 0;
                }else{
                }*/
//                lpin.alignSelf = FlexboxLayout.LayoutParams.ALIGN_SELF_FLEX_START;
//                                lpin.flexGrow = 1;
                lpin.setAlignSelf(AlignSelf.FLEX_START);
//                lpin.flexBasisPercent = 0.33f;
                lpin.setFlexBasisPercent(0.33f);
               /* lp.flexGrow = 1;
                lp.flexBasisPercent = 0.33f;*/
                View view = View.inflate(mActivity, R.layout
                        .item_third_category, null);
                ImageView logo = (ImageView) view.findViewById(R.id.logo);
                TextView name = (TextView) view.findViewById(R.id.name);
                Glide.with(mActivity).load(MyConstants.BASE_URL + itemList.get(j)
                        .getBannerUrl()).into(logo);
                name.setText(itemList.get(j).getName());

                int finalJ = j;
                view.setOnClickListener(v ->{
                    Intent intent = new Intent(mActivity, ProductListActivity.class);
                    intent.putExtra(MyConstants.EXTRA_PRODUCT_LIST_THIRD_ID,itemList.get(finalJ).getId());
                    intent.putExtra(MyConstants.EXTRA_PRODUCT_LIST_TOP_ID,topInfo.getId());
                    mActivity.startActivity(intent);
                });

                mFlContainer.addView(view, lpin);
            }
        }
        mFlContainer.invalidate();
    }



}

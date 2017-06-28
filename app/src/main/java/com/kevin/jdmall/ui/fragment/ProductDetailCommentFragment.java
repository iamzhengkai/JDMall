package com.kevin.jdmall.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxItemDecoration;
import com.kevin.jdmall.R;
import com.kevin.jdmall.adapter.DetailCommentAdapter;
import com.kevin.jdmall.bean.CommentCountResult;
import com.kevin.jdmall.bean.CommentDetailResult;
import com.kevin.jdmall.iview.IDetailCommentView;
import com.kevin.jdmall.presenter.impl.DetailCommentFragmentPresenterImpl;
import com.kevin.jdmall.ui.activity.ProductDetailActivity;
import com.orhanobut.logger.Logger;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Function:
 *
 * @FileName: com.kevin.jdmall.ui.fragment.ProductDetailDetailFragment.java
 * @author: zk
 * @date: 2017-06-27 23:40
 */

public class ProductDetailCommentFragment extends
        BasePresenterFragment<DetailCommentFragmentPresenterImpl> implements IDetailCommentView {
    @BindView(R.id.all_comment_tip)
    TextView mAllCommentTip;
    @BindView(R.id.all_comment_tv)
    TextView mAllCommentTv;
    @BindView(R.id.all_comment_ll)
    LinearLayout mAllCommentLl;
    @BindView(R.id.positive_comment_tip)
    TextView mPositiveCommentTip;
    @BindView(R.id.positive_comment_tv)
    TextView mPositiveCommentTv;
    @BindView(R.id.positive_comment_ll)
    LinearLayout mPositiveCommentLl;
    @BindView(R.id.center_comment_tip)
    TextView mCenterCommentTip;
    @BindView(R.id.center_comment_tv)
    TextView mCenterCommentTv;
    @BindView(R.id.center_comment_ll)
    LinearLayout mCenterCommentLl;
    @BindView(R.id.nagetive_comment_tip)
    TextView mNagetiveCommentTip;
    @BindView(R.id.nagetive_comment_tv)
    TextView mNagetiveCommentTv;
    @BindView(R.id.nagetive_comment_ll)
    LinearLayout mNagetiveCommentLl;
    @BindView(R.id.has_image_comment_tip)
    TextView mHasImageCommentTip;
    @BindView(R.id.has_image_comment_tv)
    TextView mHasImageCommentTv;
    @BindView(R.id.has_image_comment_ll)
    LinearLayout mHasImageCommentLl;
    @BindView(R.id.rv_comment)
    RecyclerView mRvComment;

    private int mProductId;
    private DetailCommentAdapter mCommentAdapter;
    private Map<String,Object> mParams;

    @Override
    protected View initView() {
        View view = View.inflate(mActivity, R.layout.product_comment_view, null);
        return view;
    }

    @Override
    protected void bindView() {
        mRvComment.setLayoutManager(new LinearLayoutManager(mActivity));
        mRvComment.addItemDecoration(new HorizontalDividerItemDecoration.Builder(mActivity).build());
        mAllCommentLl.setSelected(true);
    }

    @Override
    public void initData() {
        mParams = new HashMap<>();
        mProductId = ((ProductDetailActivity) mActivity).mProductId;
        mPresenter.getCommentCount(mProductId);
        mParams.put("productId",mProductId);
        mPresenter.getCommentDetail(mParams);
    }

    @Override
    protected DetailCommentFragmentPresenterImpl initPresenter() {
        return new DetailCommentFragmentPresenterImpl(this);
    }

    @OnClick({R.id.all_comment_ll, R.id.positive_comment_ll, R.id.center_comment_ll, R.id
            .nagetive_comment_ll, R.id.has_image_comment_ll})
    public void onViewClicked(View view) {
        resetSelection();
        switch (view.getId()) {
            case R.id.all_comment_ll:
                mAllCommentLl.setSelected(true);
                break;
            case R.id.positive_comment_ll:
                mPositiveCommentLl.setSelected(true);
                mParams.put("type",1);
                break;
            case R.id.center_comment_ll:
                mCenterCommentLl.setSelected(true);
                mParams.put("type",2);
                break;
            case R.id.nagetive_comment_ll:
                mNagetiveCommentLl.setSelected(true);
                mParams.put("type",3);
                break;
            case R.id.has_image_comment_ll:
                mHasImageCommentLl.setSelected(true);
                mParams.put("hasImgCom",true);
                break;
        }
        Logger.e("请求参数: =========>" + mParams);
        mPresenter.getCommentDetail(mParams);
    }

    private void resetSelection() {
        mAllCommentLl.setSelected(false);
        mPositiveCommentLl.setSelected(false);
        mCenterCommentLl.setSelected(false);
        mNagetiveCommentLl.setSelected(false);
        mHasImageCommentLl.setSelected(false);
        mParams.clear();
        mParams.put("productId",mProductId);
    }

    @Override
    public void setCommentCount(CommentCountResult result) {
        mAllCommentTv.setText("" + result.getResult().getAllComment());
        mPositiveCommentTv.setText("" + result.getResult().getPositiveCom());
        mCenterCommentTv.setText("" + result.getResult().getModerateCom());
        mNagetiveCommentTv.setText("" + result.getResult().getNegativeCom());
        mHasImageCommentTv.setText("" + result.getResult().getHasImgCom());
    }

    @Override
    public void setComment(CommentDetailResult result) {
        mCommentAdapter = new DetailCommentAdapter(mActivity, result.getResult());
        mRvComment.setAdapter(mCommentAdapter);
    }
}

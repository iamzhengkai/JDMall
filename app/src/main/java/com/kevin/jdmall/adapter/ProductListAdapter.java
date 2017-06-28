package com.kevin.jdmall.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kevin.jdmall.ActivityManager;
import com.kevin.jdmall.MyApplication;
import com.kevin.jdmall.MyConstants;
import com.kevin.jdmall.R;
import com.kevin.jdmall.bean.BrandResult;
import com.kevin.jdmall.bean.ProductListResult;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Function:
 *
 * @FileName: com.kevin.jdmall.adapter.SecKillAdapter.java
 * @author: zk
 * @date: 2017-06-24 21:02
 */

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.VH> {
    private int mPosition = -1;
    private List<ProductListResult.ResultBean.RowsBean> mList;
    private Context mContext;
    private OnItemClickListener mOnItemClickListener;

    public ProductListAdapter(Context context, List<ProductListResult.ResultBean.RowsBean> list) {
        mList = list;
        mContext = context;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new VH(View.inflate(mContext, R.layout.item_product_list, null));
    }

    @Override
    public void onBindViewHolder(VH vh, int i) {
        ProductListResult.ResultBean.RowsBean item = mList.get(i);
        vh.mView.setOnClickListener(view -> ActivityManager.toProductDetail(mContext,item.getId()));
        Glide.with(mContext).load(MyConstants.BASE_URL + item.getIconUrl()).into(vh.mProductIv);
        vh.mNameTv.setText(item.getName());
        vh.mCommrateTv.setText(item.getCommentCount() + " 条评论，好评率: " + item.getFavcomRate());
        vh.mPriceTv.setText("¥ " + item.getPrice());
        vh.mAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                    mOnItemClickListener.onItemClick(i, mList.get(i));
                }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    static class VH extends RecyclerView.ViewHolder {
        @BindView(R.id.product_iv)
        ImageView mProductIv;
        @BindView(R.id.name_tv)
        TextView mNameTv;
        @BindView(R.id.commrate_tv)
        TextView mCommrateTv;
        @BindView(R.id.price_tv)
        TextView mPriceTv;
        @BindView(R.id.add_to_cart)
        ImageView mAddToCart;
        View mView;

        public VH(View view) {
            super(view);
            mView = view;
            ButterKnife.bind(this, view);
        }

    }

    public interface OnItemClickListener {
        void onItemClick(View view,int position, BrandResult.ResultBean item);
    }

}

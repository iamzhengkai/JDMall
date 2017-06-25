package com.kevin.jdmall.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kevin.jdmall.MyConstants;
import com.kevin.jdmall.R;
import com.kevin.jdmall.bean.RecommendResult;

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

public class RecommendAdapter extends RecyclerView.Adapter<RecommendAdapter.VH> {

    private List<RecommendResult.ResultBean.RowsBean> mList;
    private Context mContext;

    public RecommendAdapter(Context context, List<RecommendResult.ResultBean.RowsBean> list) {
        mList = list;
        mContext = context;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new VH(View.inflate(mContext, R.layout.item_recommend, null));
    }

    @Override
    public void onBindViewHolder(VH vh, int i) {
        RecommendResult.ResultBean.RowsBean item = mList.get(i);
        Glide.with(mContext).load(MyConstants.BASE_URL + item.getIconUrl()).into(vh.mImageIv);
        vh.mNameTv.setText(item.getName());
        vh.mPriceTv.setText("" + item.getPrice());

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class VH extends RecyclerView.ViewHolder {
        @BindView(R.id.image_iv)
        ImageView mImageIv;
        @BindView(R.id.name_tv)
        TextView mNameTv;
        @BindView(R.id.price_tv)
        TextView mPriceTv;

        public VH(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

    }
}

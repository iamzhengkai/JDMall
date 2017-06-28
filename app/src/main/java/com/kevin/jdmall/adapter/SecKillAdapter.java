package com.kevin.jdmall.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kevin.jdmall.ActivityManager;
import com.kevin.jdmall.MyConstants;
import com.kevin.jdmall.R;
import com.kevin.jdmall.bean.SecKillResult;

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

public class SecKillAdapter extends RecyclerView.Adapter<SecKillAdapter.VH> {
    private List<SecKillResult.ResultBean.RowsBean> mList;
    private Context mContext;

    public SecKillAdapter(Context context, List<SecKillResult.ResultBean.RowsBean> list) {
        mList = list;
        mContext = context;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new VH(View.inflate(mContext, R.layout.item_seckill, null));
    }

    @Override
    public void onBindViewHolder(VH vh, int i) {
        SecKillResult.ResultBean.RowsBean item = mList.get(i);
        vh.mView.setOnClickListener(v-> ActivityManager.toProductDetail(mContext,item.getProductId()));
        Glide.with(mContext).load(MyConstants.BASE_URL + item.getIconUrl()).into(vh.mImageIv);
        vh.mNowpriceTv.setText("现价:" + item.getPointPrice());
        vh.mNormalpriceTv.setText("原价:" + item.getAllPrice());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class VH extends RecyclerView.ViewHolder {
        @BindView(R.id.image_iv)
        ImageView mImageIv;
        @BindView(R.id.nowprice_tv)
        TextView mNowpriceTv;
        @BindView(R.id.normalprice_tv)
        TextView mNormalpriceTv;
        View mView;
        public VH(View view) {
            super(view);
            mView = view;
            ButterKnife.bind(this, view);
        }

    }
}

package com.kevin.jdmall.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kevin.jdmall.R;
import com.kevin.jdmall.bean.BrandResult;

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

public class BrandAdapter extends RecyclerView.Adapter<BrandAdapter.VH> {

    private int mPosition = -1;
    private List<BrandResult.ResultBean> mList;
    private Context mContext;

    public BrandAdapter(Context context, List<BrandResult.ResultBean> list) {
        mList = list;
        mContext = context;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new VH(View.inflate(mContext, R.layout.item_brand, null));
    }

    @Override
    public void onBindViewHolder(VH vh, int i) {
        BrandResult.ResultBean item = mList.get(i);
        vh.mBrandTv.setText(item.getName());
        vh.mBrandTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPosition = i;
                notifyDataSetChanged();
            }
        });
        if (i == mPosition){
            vh.mBrandTv.setSelected(true);
        }else{
            vh.mBrandTv.setSelected(false);
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class VH extends RecyclerView.ViewHolder {
        @BindView(R.id.brand_tv)
        TextView mBrandTv;
        public VH(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

    }
}

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

public class DetailProductBrandAdapter extends RecyclerView.Adapter<DetailProductBrandAdapter.VH> {

    private int mPosition = -1;
    private List<String> mList;
    private Context mContext;
    private OnItemClickListener mOnItemClickListener;

    public DetailProductBrandAdapter(Context context, List<String> list) {
        mList = list;
        mContext = context;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new VH(View.inflate(mContext, R.layout.item_brand, null));
    }

    @Override
    public void onBindViewHolder(VH vh, int i) {
        vh.mBrandTv.setText(mList.get(i));
//        vh.mBrandTv.setTag(true);
        vh.mBrandTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vh.mBrandTv.isSelected()){
                    mPosition = i;
                }
                else {
                    mPosition = -1;
                }
                notifyDataSetChanged();
                /*if (mOnItemClickListener != null){
                    mOnItemClickListener.onItemClick(vh.mBrandTv,i,mList.get(i));
                }*/
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

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    static class VH extends RecyclerView.ViewHolder {
        @BindView(R.id.brand_tv)
        TextView mBrandTv;
        public VH(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

    }

    public interface OnItemClickListener{
        void onItemClick(View view, int position, BrandResult.ResultBean item);
    }

    public String getSelectedBrand(){
        if (mPosition == -1){
            return null;
        }else{
            return mList.get(mPosition);
        }
    }

}

package com.kevin.jdmall.adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatRatingBar;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kevin.jdmall.MyConstants;
import com.kevin.jdmall.R;
import com.kevin.jdmall.bean.BrandResult;
import com.kevin.jdmall.bean.ProductCommentResult;
import com.kevin.jdmall.utils.DensityUtil;

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

public class DetailProductCommentAdapter extends
        RecyclerView.Adapter<DetailProductCommentAdapter.VH> {

    private List<ProductCommentResult.ResultBean> mList;
    private Context mContext;
    private OnItemClickListener mOnItemClickListener;

    public DetailProductCommentAdapter(Context context, List<ProductCommentResult.ResultBean>
            list) {
        mList = list;
        mContext = context;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new VH(View.inflate(mContext, R.layout.recommend_comment_item_view, null));
    }

    @Override
    public void onBindViewHolder(VH vh, int i) {
        ProductCommentResult.ResultBean item = mList.get(i);
        vh.mRatingBar.setRating(item.getRate());
        vh.mNameTv.setText(item.getUserName());
        vh.mContentTv.setText(item.getComment());
        if (item.getImgUrls().size() > 0){
            LinearLayout.LayoutParams params =
                    new LinearLayout.LayoutParams(DensityUtil.dp2px(mContext,60),DensityUtil.dp2px(mContext,80));
            vh.mIamgesContainer.setVisibility(View.VISIBLE);
            for (int j = 0; j < item.getImgUrls().size(); j++) {
                ImageView imageView = new ImageView(mContext);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                if (j > 0){
                    params.leftMargin = DensityUtil.dp2px(mContext,5);
                }
                Glide.with(mContext).load(MyConstants.BASE_URL + item.getImgUrls().get(j)).into(imageView);
                vh.mIamgesContainer.addView(imageView,params);
            }
        }else{
            vh.mIamgesContainer.setVisibility(View.INVISIBLE);
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
        @BindView(R.id.name_tv)
        TextView mNameTv;
        @BindView(R.id.content_tv)
        TextView mContentTv;
        @BindView(R.id.iamges_container)
        LinearLayout mIamgesContainer;
        @BindView(R.id.rating_bar)
        AppCompatRatingBar mRatingBar;

        public VH(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position, BrandResult.ResultBean item);
    }

}

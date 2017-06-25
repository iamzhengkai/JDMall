package com.kevin.jdmall.ui.fragment;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.kevin.jdmall.MyConstants;
import com.kevin.jdmall.R;
import com.kevin.jdmall.adapter.HomeBannerAdapter;
import com.kevin.jdmall.adapter.RecommendAdapter;
import com.kevin.jdmall.adapter.SecKillAdapter;
import com.kevin.jdmall.bean.BannerResult;
import com.kevin.jdmall.bean.RecommendResult;
import com.kevin.jdmall.bean.SecKillResult;
import com.kevin.jdmall.iview.IHomeFragmentView;
import com.kevin.jdmall.presenter.impl.HomeFragmentPresenterImpl;
import com.kevin.jdmall.ui.view.RatioImageView;
import com.kevin.jdmall.utils.DensityUtil;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/6/8.
 */

public class HomeFragment extends BasePresenterFragment<HomeFragmentPresenterImpl> implements
        IHomeFragmentView {
    @BindView(R.id.ad_vp)
    ViewPager mAdVp;
    @BindView(R.id.ad_indicator)
    LinearLayout mAdIndicator;
    @BindView(R.id.ad2_iv)
    RatioImageView mAd2Iv;
    @BindView(R.id.rv_seckill)
    RecyclerView mRvSeckKill;
    @BindView(R.id.rv_recommand)
    RecyclerView mRvRecommand;

    private HomeBannerAdapter mBannerAdapter;
    private List<BannerResult.ResultBean> mList;
    private List<ImageView> mDots;
    private Subscription mAutoRunSub;

    @Override
    protected HomeFragmentPresenterImpl initPresenter() {
        return new HomeFragmentPresenterImpl(this);
    }

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_home, null);
        return view;
    }

    public void initDots(int num) {
        ImageView dot;
        mDots = new ArrayList<>();
        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(LinearLayout
                        .LayoutParams.WRAP_CONTENT, LinearLayout
                        .LayoutParams.WRAP_CONTENT);
        for (int i = 0; i < num; i++) {
            dot = (ImageView) View.inflate(mActivity, R.layout.item_dot, null);
            if (i == 0) {
                dot.setSelected(true);
            } else {
                params.leftMargin = (int) DensityUtil.dp2px(mActivity, 8f);
                dot.setSelected(false);
            }
            mAdIndicator.addView(dot, params);
            int finalI = i;
           /* dot.setOnClickListener(view -> {
                mAdVp.setCurrentItem(finalI);
            });*/
            mDots.add(dot);
        }
    }

    @Override
    public void initData() {
        mList = new ArrayList<>();
        mBannerAdapter = new HomeBannerAdapter(mList, mActivity);
//        mAdVp.setAdapter(mBannerAdapter);

        /*mAdVp.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        stopAutoRun();
                        break;
                    case MotionEvent.ACTION_UP: {
                        autoRun();
                        break;
                    }
                }
                return false;
            }
        });*/

        mAdVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int
                    positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int realPosition = position % mList.size();
                for (int i = 0; i < mDots.size(); i++) {
                    if (i == realPosition) {
                        mDots.get(i).setSelected(true);
                    } else {
                        mDots.get(i).setSelected(false);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mRvSeckKill.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.HORIZONTAL,false));
        mRvRecommand.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
//        initDots(5);
        //获取数据
        mPresenter.getBanner(1);
        mPresenter.getBanner(2);
        mPresenter.getSecKill();
        mPresenter.getYourFav();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mAutoRunSub.isUnsubscribed())
            mAutoRunSub.unsubscribe();
    }

    @Override
    public void startRun(List<BannerResult.ResultBean> list) {
        initDots(list.size());
        mList.addAll(list);
        mAdVp.setAdapter(mBannerAdapter);
//        mBannerAdapter.notifyDataSetChanged();
        //开始轮播
        autoRun();
    }

    @Override
    public void showAd(String url) {
        Glide.with(mActivity).load(MyConstants.BASE_URL + url).into(mAd2Iv);
        mAd2Iv.setVisibility(View.VISIBLE);
    }

    @Override
    public void initSecKill(List<SecKillResult.ResultBean.RowsBean> list) {
        SecKillAdapter adapter = new SecKillAdapter(mActivity,list);
        mRvSeckKill.setAdapter(adapter);
    }

    @Override
    public void initRecommend(List<RecommendResult.ResultBean.RowsBean> list) {
        RecommendAdapter adapter = new RecommendAdapter(mActivity,list);
        mRvRecommand.setAdapter(adapter);
    }

    public void autoRun() {
        mAutoRunSub = Observable.interval(4000, 2000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Long>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.e("Error:CurrentItem=====>%d", mAdVp.getCurrentItem());
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(Long aLong) {
//                        int i = (mAdVp.getCurrentItem() + 1) % mList.size();
                        int i = (mAdVp.getCurrentItem() + 1);
                        Logger.e("==========>i:%d", i);
                        mAdVp.setCurrentItem(i);
                    }
                });
    }

    public void stopAutoRun() {
        if (!mAutoRunSub.isUnsubscribed()) {
            mAutoRunSub.unsubscribe();
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden){
            Logger.e("hiden!");
            stopAutoRun();
        }else{
            Logger.e("not hiden!");
            autoRun();
        }
    }


}

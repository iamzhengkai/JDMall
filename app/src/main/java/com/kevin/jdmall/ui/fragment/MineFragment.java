package com.kevin.jdmall.ui.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kevin.jdmall.MyConstants;
import com.kevin.jdmall.R;
import com.kevin.jdmall.bean.LoginResult;
import com.kevin.jdmall.db.UserDao;
import com.kevin.jdmall.utils.JsonUtil;
import com.kevin.jdmall.utils.PrefUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/6/8.
 */

public class MineFragment extends BaseFragment {

    @BindView(R.id.user_icon_iv)
    ImageView mUserIconIv;
    @BindView(R.id.user_name_tv)
    TextView mUserNameTv;
    @BindView(R.id.user_level_tv)
    TextView mUserLevelTv;
    @BindView(R.id.wait_pay_tv)
    TextView mWaitPayTv;
    @BindView(R.id.wait_pay_ll)
    LinearLayout mWaitPayLl;
    @BindView(R.id.wait_receive_tv)
    TextView mWaitReceiveTv;
    @BindView(R.id.wait_receive_ll)
    LinearLayout mWaitReceiveLl;
    @BindView(R.id.mime_order)
    LinearLayout mMimeOrder;
    @BindView(R.id.logout_btn)
    Button mLogoutBtn;
    Unbinder unbinder;
    private Subscription mSubscription;

    @Override
    protected View initView() {
        return View.inflate(mActivity, R.layout.fragment_mine, null);
    }

    @Override
    public void initData() {
        mSubscription = Observable.just(PrefUtils.getString(MyConstants.PREF_USER_INFO,
                null))
                .map(s -> JsonUtil.parseJsonToBean(s, LoginResult
                        .ResultBean.class))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResult)
                /*.subscribe(new Action1<LoginResult.ResultBean>() {
                    @Override
                    public void call(LoginResult.ResultBean resultBean) {
                        handleResult(resultBean);
                    }
                })*/;

       /* Observable.create(new Observable.OnSubscribe<LoginResult.ResultBean>() {
            @Override
            public void call(Subscriber<? super LoginResult.ResultBean> subscriber) {
                String result = PrefUtils.getString(MyConstants.PREF_USER_INFO, null);
                LoginResult.ResultBean resultBean = JsonUtil.parseJsonToBean(result, LoginResult
                        .ResultBean.class);
                subscriber.onNext(resultBean);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<LoginResult.ResultBean>() {
                    @Override
                    public void call(LoginResult.ResultBean resultBean) {
                        handleResult(resultBean);

                    }
                });*/

    }

    private void handleResult(LoginResult.ResultBean resultBean) {
        if (resultBean != null) {
            if (!TextUtils.isEmpty(resultBean.getUserLevel())) {
                mUserLevelTv.setText(resultBean.getUserLevel());
            }
            if (!TextUtils.isEmpty(resultBean.getUserName())) {
                mUserNameTv.setText(resultBean.getUserName());
            }
            if (!TextUtils.isEmpty(resultBean.getWaitPayCount())) {
                mWaitPayTv.setText(resultBean.getWaitPayCount());
            }
            if (!TextUtils.isEmpty(resultBean.getWaitReceiveCount())) {
                mWaitReceiveTv.setText(resultBean.getWaitReceiveCount());
            }

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (!mSubscription.isUnsubscribed())
            mSubscription.unsubscribe();
    }

    @OnClick(R.id.logout_btn)
    public void onViewClicked(View view) {
        //1.删除数据库用户信息
        //2.删除sharedPreference
        //3.都成功后，更新当前界面
        UserDao userDao = new UserDao();
        userDao.clearUser();
        PrefUtils.setString(MyConstants.PREF_USER_INFO,null);
        mUserNameTv.setText(getResources().getString(R.string.user_name_default));
        mWaitReceiveTv.setText("0");
        mWaitPayTv.setText("0");
        mUserLevelTv.setText(getResources().getString(R.string.user_level_default));
    }
}

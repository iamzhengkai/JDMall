package com.kevin.jdmall.ui.fragment;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kevin.jdmall.R;

import butterknife.BindView;

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

    @Override
    public View initView() {
        return View.inflate(mActivity, R.layout.fragment_mine, null);
    }
}

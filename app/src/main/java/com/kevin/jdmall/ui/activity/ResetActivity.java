package com.kevin.jdmall.ui.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.kevin.jdmall.R;
import com.kevin.jdmall.iview.IResetView;
import com.kevin.jdmall.presenter.impl.ResetPresenterImpl;
import com.kevin.jdmall.utils.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class ResetActivity extends BaseActivity implements IResetView {

    @BindView(R.id.username_et)
    EditText mUsernameEt;
    @BindView(R.id.bt_reset)
    Button mBtReset;

    private ProgressDialog mProgressDialog;
    private ResetPresenterImpl mResetPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mResetPresenter = new ResetPresenterImpl(this);
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setTitle("密码重置");
        mProgressDialog.setMessage("密码重置中...");

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_reset;
    }

    @Override
    public void showProgressDialog() {
        mProgressDialog.show();
    }

    @Override
    public void hideProgressDialog() {
        mProgressDialog.dismiss();
    }

    @Override
    public void showError(String errorMsg) {
        ToastUtil.showToast(errorMsg);
    }

    @Override
    public void returnToLoginActivity() {
        finish();
    }

    @OnClick(R.id.bt_reset)
    public void onViewClicked(View view) {
        String username = mUsernameEt.getText().toString();
        if(mResetPresenter.vertifyResetInfo(username)){
            mResetPresenter.reset(username);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mResetPresenter.unsubcrible();
    }
}

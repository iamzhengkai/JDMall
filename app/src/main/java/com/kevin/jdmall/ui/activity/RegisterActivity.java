package com.kevin.jdmall.ui.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.kevin.jdmall.R;
import com.kevin.jdmall.iview.IRegisterView;
import com.kevin.jdmall.presenter.impl.RegisterPresenterImpl;
import com.kevin.jdmall.utils.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity implements IRegisterView {
    ProgressDialog mProgressDialog;
    RegisterPresenterImpl mPresenter;
    @BindView(R.id.title_v)
    TextView mTitleV;
    @BindView(R.id.username_et)
    EditText mUsernameEt;
    @BindView(R.id.pwd_et)
    EditText mPwdEt;
    @BindView(R.id.surepwd_et)
    EditText mSurepwdEt;
    @BindView(R.id.cb_agree)
    CheckBox mCbAgree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setTitle("注册");
        mProgressDialog.setMessage("注册中...");

        mPresenter = new RegisterPresenterImpl(this);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_regist;
    }

    @Override
    public void showProgressDialog() {
        mProgressDialog.show();
    }

    @Override
    public void hideProgressDialog() {
        mProgressDialog.hide();
    }

    @Override
    public void showError(String errorMsg) {
        ToastUtil.showToast(errorMsg);
    }

    @Override
    public void jumpToLoginActivity() {
        finish();
    }

    @OnClick(R.id.bt_next)
    public void onClick() {
        String password = mPwdEt.getText().toString();
        String repassword = mSurepwdEt.getText().toString();
        String username = mUsernameEt.getText().toString();

        if (!mCbAgree.isChecked()){
            ToastUtil.showToast("请同意用户协议！");
            return;
        }
        if(mPresenter.vertifyRegisterInfo(username,password,repassword)){
            mPresenter.register(username,password);
        }

    }
}

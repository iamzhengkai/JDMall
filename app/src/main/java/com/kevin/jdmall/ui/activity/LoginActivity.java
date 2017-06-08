package com.kevin.jdmall.ui.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.kevin.jdmall.ActivityManager;
import com.kevin.jdmall.R;
import com.kevin.jdmall.iview.ILoginView;
import com.kevin.jdmall.presenter.ILoginPresenter;
import com.kevin.jdmall.presenter.impl.LoginPresenterImpl;
import com.kevin.jdmall.utils.ToastUtil;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements ILoginView {

    @BindView(R.id.title_v)
    TextView mTitleV;
    @BindView(R.id.name_et)
    EditText mNameEt;
    @BindView(R.id.pwd_et)
    EditText mPwdEt;
    @BindView(R.id.login_btn)
    Button mLoginBtn;

    private ProgressDialog mProgressDialog;
    ILoginPresenter mPresenter;
    private String mUsername;
    private String mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //初始化Presenter
        mPresenter = new LoginPresenterImpl(this);
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setTitle("登录");
        mProgressDialog.setMessage("登陆中...");
    }

    @OnClick(R.id.login_btn)
    public void onLoginBtnClick(){
        mUsername = mNameEt.getText().toString();
        mPassword = mPwdEt.getText().toString();
        Logger.d(TAG,"username"+ mUsername);
        Logger.d(TAG,"pwd"+ mPassword);
        //调用Presenter进行业务处理
        if (mPresenter.vertifyLoginInfo(mUsername,mPassword)){
            mPresenter.login(mUsername,mPassword);
        }
    }

    @OnClick(R.id.bt_register)
    public void onRegisterBtnClick(){
        ToastUtil.showToast("注册");
        ActivityManager.startActivity(this,RegisterActivity.class,false);
    }
    @OnClick(R.id.bt_reset_pwd)
    public void onResetPwdBtnClick(){
        ToastUtil.showToast("重置");
        ActivityManager.startActivity(this,ResetActivity.class,false);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    //实现View中对应的接口
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
    public void jumpToMainActivity() {
        ActivityManager.startActivity(this,MainActivity.class,false);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.unsubcrible();
    }
}

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
import com.kevin.jdmall.presenter.impl.BasePresenterImpl;
import com.kevin.jdmall.presenter.impl.LoginPresenterImpl;
import com.kevin.jdmall.utils.ToastUtil;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BasePresenterActivity implements ILoginView {

    @BindView(R.id.title_v)
    TextView mTitleV;
    @BindView(R.id.name_et)
    EditText mNameEt;
    @BindView(R.id.pwd_et)
    EditText mPwdEt;
    @BindView(R.id.login_btn)
    Button mLoginBtn;

    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setTitle("登录");
        mProgressDialog.setMessage("登陆中...");

        ((LoginPresenterImpl)mPresenter).readUserFromDb();
    }

    @OnClick(R.id.login_btn)
    public void onLoginBtnClick(){
        String username = mNameEt.getText().toString();
        String password = mPwdEt.getText().toString();
        Logger.d(TAG,"username"+ username);
        Logger.d(TAG,"pwd"+ password);
        //调用Presenter进行业务处理
        if (((LoginPresenterImpl)mPresenter).vertifyLoginInfo(username, password)){
            ((LoginPresenterImpl)mPresenter).login(username, password);
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
        ActivityManager.startActivity(this,MainActivity.class,true);
    }

    @Override
    public void setUserInfo(String username, String pwd) {
        mNameEt.setText(username);
        mPwdEt.setText(pwd);
    }


    @Override
    protected BasePresenterImpl initPresenter() {
        return new LoginPresenterImpl(this);
    }
}

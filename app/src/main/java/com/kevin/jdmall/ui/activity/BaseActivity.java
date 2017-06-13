package com.kevin.jdmall.ui.activity;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.jaeger.library.StatusBarUtil;
import com.kevin.jdmall.ActivityManager;
import com.kevin.jdmall.R;
import com.orhanobut.logger.Logger;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity {

    protected final String TAG = this.getClass().getSimpleName();
    @LayoutRes
    protected int mLayoutId;
    private Unbinder mUnbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.d(TAG, "onCreate: " + TAG);
        ActivityManager.addActivity(this);
        //去掉标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //隐藏ActionBar
        ActionBar actionBar= getSupportActionBar();
        if (null != actionBar)
            actionBar.hide();
        //设置布局
        mLayoutId = getLayoutId();
        setContentView(mLayoutId);
        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorPrimaryDark));
        mUnbinder = ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManager.removeActivity(this);
        mUnbinder.unbind();
    }
    protected abstract int getLayoutId();
}

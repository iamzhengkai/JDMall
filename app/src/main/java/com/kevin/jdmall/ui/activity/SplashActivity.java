package com.kevin.jdmall.ui.activity;

import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.kevin.jdmall.ActivityManager;
import com.kevin.jdmall.R;

import butterknife.BindView;

public class SplashActivity extends BaseActivity {

    @BindView(R.id.logo_iv)
    ImageView mLogoIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.2f,1.0f);
        alphaAnimation.setDuration(2000);
        alphaAnimation.setFillAfter(true);
        //添加监听
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                ActivityManager.startActivity(SplashActivity.this,LoginActivity.class,true);
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        mLogoIv.startAnimation(alphaAnimation);
    }

    @Override
    protected void setLayoutRes() {
        mLayoutId = R.layout.activity_splash;
    }
}

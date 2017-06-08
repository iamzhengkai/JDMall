package com.kevin.jdmall.presenter.impl;

import android.text.TextUtils;

import com.kevin.jdmall.BuildConfig;
import com.kevin.jdmall.MyApplication;
import com.kevin.jdmall.api.LoginApi;
import com.kevin.jdmall.bean.LoginResult;
import com.kevin.jdmall.bean.User;
import com.kevin.jdmall.db.UserDao;
import com.kevin.jdmall.iview.ILoginView;
import com.kevin.jdmall.presenter.ILoginPresenter;
import com.orhanobut.logger.Logger;

import java.lang.ref.WeakReference;

import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by 蔡小木 on 2016/4/22 0022.
 */
public class LoginPresenterImpl extends BasePresenterImpl implements ILoginPresenter {

    private static final String TAG = "LoginPresenterImpl";
    //    private ILoginView mLoginView;
    private WeakReference<ILoginView> mLoginViewRef;

    public LoginPresenterImpl(ILoginView loginView) {
        if (loginView == null)
            throw new IllegalArgumentException("ILoginView must not be null");


        this.mLoginViewRef = new WeakReference<ILoginView>(loginView);
//        this.mLoginView = loginView;
    }

    @Override
    public void login(final String username, final String password) {
        if ((mLoginViewRef.get()) != null) {
            mLoginViewRef.get().showProgressDialog();
        } else {
            Logger.e("LoginActivity已被回收");
            return;
        }
        Subscription s = MyApplication.mRetrofit.create(LoginApi.class).login(username, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginResult>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (mLoginViewRef.get() != null) {
                            mLoginViewRef.get().hideProgressDialog();
                            mLoginViewRef.get().showError(e.getMessage());
                        } else {
                            Logger.e("LoginActivity已被回收");
                        }
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(LoginResult loginResult) {
                        if (mLoginViewRef.get() != null) {
                            mLoginViewRef.get().hideProgressDialog();
                            if (loginResult.isSuccess()) {
//                                mLoginViewRef.get().showError("登录成功!");
                                if (BuildConfig.DEBUG) {
                                    Logger.d(TAG, loginResult.toString());
                                }

                                //保存用户信息
                                Subscription sSaveUser = Observable.create(new Observable
                                        .OnSubscribe<Boolean>() {
                                    @Override
                                    public void call(Subscriber<? super Boolean> subscriber) {
                                        UserDao userDao = new UserDao();
                                        userDao.clearUser();
                                        boolean result = userDao.addUser(username, password);
                                        subscriber.onNext(result);
                                    }
                                }).subscribeOn(Schedulers.io())
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .subscribe(new Subscriber<Boolean>() {
                                            @Override
                                            public void onCompleted() {

                                            }

                                            @Override
                                            public void onError(Throwable e) {
                                                if (mLoginViewRef.get() != null) {
                                                    mLoginViewRef.get().showError(e.getMessage());
                                                }
                                            }

                                            @Override
                                            public void onNext(Boolean isSuccess) {
                                                if (mLoginViewRef.get() != null) {
                                                    if (isSuccess) {
                                                        //跳转到主界面
                                                        mLoginViewRef.get().showError("登录成功!");
                                                        mLoginViewRef.get().jumpToMainActivity();
                                                    } else {
                                                        mLoginViewRef.get().showError
                                                                ("保存用户信息失败:数据库错误!");
                                                    }
                                                }

                                            }
                                        });

                                addSubscription(sSaveUser);

                            } else {
                                mLoginViewRef.get().showError(loginResult.getErrorMsg());
                            }
                        } else {
                            Logger.e("LoginActivity已被回收");
                        }
                    }
                });
        addSubscription(s);
    }

    @Override
    public void readUserFromDb() {
        Subscription s = Observable.create(new Observable.OnSubscribe<User>() {
            @Override
            public void call(Subscriber<? super User> subscriber) {
                UserDao userDao = new UserDao();
                User user = userDao.getUser();
                subscriber.onNext(user);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<User>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(User user) {
                        if (user != null) {
                            if (mLoginViewRef.get() != null){
                                mLoginViewRef.get().setUserInfo(user.username,user.pwd);
                            }
                        }
                    }
                });
        addSubscription(s);
    }

    @Override
    public boolean vertifyLoginInfo(String username, String password) {
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            if (mLoginViewRef.get() != null) {
                mLoginViewRef.get().showError("用户名或密码不能为空！");
            } else {
                Logger.e("LoginActivity已被回收");
            }
            return false;
        }
        return true;
    }

}

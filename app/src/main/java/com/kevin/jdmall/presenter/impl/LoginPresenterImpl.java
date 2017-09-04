package com.kevin.jdmall.presenter.impl;

import android.text.TextUtils;

import com.kevin.jdmall.MyApplication;
import com.kevin.jdmall.MyConstants;
import com.kevin.jdmall.api.LoginApi;
import com.kevin.jdmall.bean.LoginResult;
import com.kevin.jdmall.bean.User;
import com.kevin.jdmall.bean.UserInfo;
import com.kevin.jdmall.db.UserDao;
import com.kevin.jdmall.iview.ILoginView;
import com.kevin.jdmall.presenter.ILoginPresenter;
import com.kevin.jdmall.subscriber.CheckedSubscriber;
import com.kevin.jdmall.utils.JsonUtil;
import com.kevin.jdmall.utils.PrefUtils;
import com.orhanobut.logger.Logger;

import java.lang.ref.WeakReference;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class LoginPresenterImpl extends BasePresenterImpl<ILoginView> implements ILoginPresenter {

    private static final String TAG = "LoginPresenterImpl";

    public LoginPresenterImpl(ILoginView loginView) {
      super(loginView);
    }

    @Override
    public void login(final String username, final String password) {
        mViewRef.get().showProgressDialog();
        Subscription sLogin = MyApplication.mRetrofit.create(LoginApi.class).login(username,
                password)
                .map(new Func1<LoginResult, Integer>() {
                    @Override
                    public Integer call(LoginResult loginResult) {
                        //将用户信息保存到数据库，并将登录结果保存到sharedPreference，同时将结果转成数字
                        String s = JsonUtil.parseObjToJson(loginResult.getResult());
                        UserDao userDao = new UserDao();
                        userDao.clearUser();
                        boolean dbResult = userDao.addUser(username, password);
                        boolean prfResult = PrefUtils.setString(MyConstants.PREF_USER_INFO, s);
                        if (dbResult && prfResult) {
                            MyApplication.userId = loginResult.getResult().getId();
                            return 0;
                        } else {
                            //数据库错误
                            if (!dbResult)
                                return -1;
                            //preference错误
                            return -2;
                        }
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CheckedSubscriber<Integer,ILoginView>(mViewRef.get()) {
                    @Override
                    public void onError(Throwable e) {
                        mView.showError(e.getMessage());
                        mView.hideProgressDialog();
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(Integer type) {
                        //处理最终结果
                        switch (type){
                            case 0:
                                //跳转到主界面
                                mView.showError("登录成功!");
                                mView.hideProgressDialog();
                                mView.jumpToMainActivity();
                                break;
                            case -1:
                                mView.showError("保存用户信息失败:数据库错误!");
                                break;
                            case -2:
                                mView.showError("保存用户信息失败:文件错误!");
                                break;
                        }
                    }

                });
        addSubscription(sLogin);
    }

   /* private void saveUserInfo(final String username, final String password, final LoginResult
            loginResult) {

        //保存用户信息
        Subscription sSaveUser = Observable.create(new Observable
                .OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                //1.将用户信息保存到数据库
                UserDao userDao = new UserDao();
                userDao.clearUser();
                boolean dbResult = userDao.addUser(username, password);
                boolean prfResult = PrefUtils.setString(MyConstants.PREF_USER_INFO, JsonUtil
                        .parseObjToJson(loginResult.getResult()));
                if (dbResult && prfResult) {
                    //成功
                    subscriber.onNext(0);
                } else {
                    //数据库错误
                    if (!dbResult) {
                        subscriber.onNext(-1);
                        return;
                    }
                    //preference错误
                    subscriber.onNext(-2);
                }
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CheckedSubscriber<Integer,ILoginView>(mViewRef.get()) {
                    @Override
                    public void onError(Throwable e) {
                        mView.showError(e.getMessage());
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(Integer isSuccess) {
                        switch (isSuccess){
                            case 0:
                                //跳转到主界面
                                mView.showError("登录成功!");
                                mView.jumpToMainActivity();
                                break;
                            case -1:
                                mView.showError("保存用户信息失败:数据库错误!");
                                break;
                            case -2:
                                mView.showError("保存用户信息失败:文件错误!");
                                break;
                        }
                    }

                });

        addSubscription(sSaveUser);
    }
*/
    @Override
    public void readUserFromDb() {
       /* Subscription sReadUser = Observable.create(new Observable.OnSubscribe<User>() {
            @Override
            public void call(Subscriber<? super User> subscriber) {
                UserDao userDao = new UserDao();
                User user = userDao.getUser();
                subscriber.onNext(user);
            }
        })*/
        Subscription sReadUser = Observable.just(new UserDao().getUser())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CheckedSubscriber<User,ILoginView>(mViewRef.get()){
                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(User user) {
                        if (user != null) {
                            mView.setUserInfo(user.username, user.pwd);
                        }
                    }
                });
        addSubscription(sReadUser);
    }

    @Override
    public boolean vertifyLoginInfo(String username, String password) {
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            if (mViewRef.get() != null) {
                mViewRef.get().showError("用户名或密码不能为空！");
            } else {
                Logger.e("LoginActivity已被回收");
            }
            return false;
        }
        return true;
    }

}

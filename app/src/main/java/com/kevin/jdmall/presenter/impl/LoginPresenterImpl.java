package com.kevin.jdmall.presenter.impl;

import android.text.TextUtils;

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
        this.mLoginViewRef = new WeakReference<>(loginView);
    }

    @Override
    public void login(final String username, final String password) {
        mLoginViewRef.get().showProgressDialog();
        Subscription sLogin = MyApplication.mRetrofit.create(LoginApi.class).login(username, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<LoginResult>() {
                    ILoginView loginView = mLoginViewRef.get();

                    @Override
                    public void onStart() {
                        if (loginView == null) {
                            unsubcrible();
                            Logger.e("视图已经被回收！");
                        }
                    }

                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        loginView.hideProgressDialog();
                        loginView.showError(e.getMessage());
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(LoginResult loginResult) {
                        loginView.hideProgressDialog();
                        if (loginResult.isSuccess()) {
//                          mLoginViewRef.get().showError("登录成功!");
                            Logger.d(TAG, loginResult.toString());
                            //保存用户信息
                            saveUserInfo(username, password);

                        } else {
                            loginView.showError(loginResult.getErrorMsg());
                        }

                    }
                });
        addSubscription(sLogin);
    }

    private void saveUserInfo(final String username, final String password) {
        /*DbOpenHelper mHelper = new DbOpenHelper();
        SqlBrite mSqlBrite = new SqlBrite.Builder().build();
        BriteDatabase db = mSqlBrite.wrapDatabaseHelper(mHelper, Schedulers.io());
        Observable<SqlBrite.Query> userObservable =
                db.createQuery(MyConstants.DbConstants.TBL_NAME,
                        "SELECT * FROM " + MyConstants.DbConstants.TBL_NAME);

        Subscription s = userObservable.subscribe(new Action1<SqlBrite.Query>() {
            @Override
            public void call(SqlBrite.Query query) {
                Cursor cursor = query.run();

                if (cursor.getCount() > 0) {
                    //跳转到主界面
                    mLoginViewRef.get().showError("登录成功!");
                    mLoginViewRef.get().jumpToMainActivity();
                } else {
                    mLoginViewRef.get().showError
                            ("保存用户信息失败:数据库错误!");
                }


            }
        });

        addSubscription(s);*/


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
                    ILoginView loginView = mLoginViewRef.get();

                    @Override
                    public void onStart() {
                        if (loginView == null) {
                            unsubcrible();
                            Logger.e("视图已经被回收！");
                        }
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        loginView.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(Boolean isSuccess) {
                        if (isSuccess) {
                            //跳转到主界面
                            loginView.showError("登录成功!");
                            loginView.jumpToMainActivity();
                        } else {
                            loginView.showError("保存用户信息失败:数据库错误!");
                        }
                    }

                });

        addSubscription(sSaveUser);
    }

    @Override
    public void readUserFromDb() {
        Subscription sReadUser = Observable.create(new Observable.OnSubscribe<User>() {
            @Override
            public void call(Subscriber<? super User> subscriber) {
                UserDao userDao = new UserDao();
                User user = userDao.getUser();
                subscriber.onNext(user);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<User>() {
                    ILoginView loginView = mLoginViewRef.get();

                    @Override
                    public void onStart() {
                        if (loginView == null) {
                            unsubcrible();
                            Logger.e("视图已经被回收！");
                        }
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(User user) {
                        if (user != null) {
                            loginView.setUserInfo(user.username, user.pwd);
                        }
                    }
                });
        addSubscription(sReadUser);
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

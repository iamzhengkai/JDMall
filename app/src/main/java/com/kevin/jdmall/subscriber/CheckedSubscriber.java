package com.kevin.jdmall.subscriber;

import com.kevin.jdmall.iview.ILoginView;
import com.kevin.jdmall.iview.IView;
import com.orhanobut.logger.Logger;

import rx.Subscriber;

/**
 * Function:
 *
 * @FileName: com.kevin.jdmall.subscriber.CheckedSubscriber.java
 * @author: zk
 * @date: 2017-06-14 11:16
 */

public abstract class CheckedSubscriber<T,V> extends Subscriber<T> {
    protected V mView;
    protected CheckedSubscriber(V iView){
        mView = iView;
    }

    @Override
    public void onStart() {
        if (mView == null){
            this.unsubscribe();
            Logger.e("视图已经被回收！");
        }
    }

    @Override
    public void onCompleted() {

    }
}

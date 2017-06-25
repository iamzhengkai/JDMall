package com.kevin.jdmall.ui.view.pop;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.kevin.jdmall.MyApplication;
import com.kevin.jdmall.R;

/**
 * Function:
 *
 * @FileName: com.kevin.jdmall.ui.view.pop.PopupWindowBuilder.java
 * @author: zk
 * @date: 2017-06-25 23:07
 */

public class PopupWindowBuilder {

    private View mView;
    private boolean mFocusable;
    private boolean mOutSideTouchable;
    private int mWidthMode;
    private int mHeightMode;
    private Context mContext;

    public PopupWindowBuilder(Context context) {
        mFocusable = true;
        mOutSideTouchable = true;
        mWidthMode = ViewGroup.LayoutParams.MATCH_PARENT;
        mHeightMode = ViewGroup.LayoutParams.MATCH_PARENT;
        mContext = context;
    }

    public PopupWindowBuilder setView(View view) {
        mView = view;
        return this;
    }

    public PopupWindowBuilder setView(@LayoutRes int layoutId) {
        mView = View.inflate(mContext, layoutId, null);
        return this;
    }

    public PopupWindowBuilder setWidthMode(int widthMode) {
        mWidthMode = widthMode;
        return this;
    }

    public PopupWindowBuilder setHeightMode(int heightMode) {
        mHeightMode = heightMode;
        return this;
    }

    public PopupWindowBuilder setFocusable(boolean focusable) {
        mFocusable = focusable;
        return this;
    }

    public PopupWindowBuilder setOutSideTouchable(boolean outSideTouchable) {
        mOutSideTouchable = outSideTouchable;
        return this;
    }

    public PopupWindow Build() {
        if (mView == null)
            throw new IllegalStateException("The setView() method must be invoked before build!");
        PopupWindow popupWindow = new PopupWindow(mView, mWidthMode, mHeightMode);
        popupWindow.setFocusable(mFocusable);
        popupWindow.setOutsideTouchable(mOutSideTouchable);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.update();
        return popupWindow;
    }
}

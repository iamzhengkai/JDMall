package com.kevin.jdmall.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.kevin.jdmall.R;

public class RatioViewPager extends ViewPager {
    private float mRatio;
    private OnFingerUpDownListener mUpDownListener;
    public RatioViewPager(Context context) {
        super(context);
    }

    public RatioViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.RatioViewPager);
        mRatio = ta.getFloat(R.styleable.RatioViewPager_pagerRatio, -1);
        ta.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int height;
        int width = MeasureSpec.getSize(widthMeasureSpec);
        if (mRatio > 0) {
            height = (int) (width / mRatio);
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    //Override dispatchTouchEvent to do sth before dispatch any touch event
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        //do sth before dispatch event
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (mUpDownListener != null) {
                    mUpDownListener.onFingerDown();
                }
                break;
            case MotionEvent.ACTION_UP:
                if (mUpDownListener != null) {
                    mUpDownListener.onFingerUp();
                }
                break;
            default:
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    public void setOnFingerUpDownListener(OnFingerUpDownListener l){
        mUpDownListener = l;
    }

    public interface OnFingerUpDownListener{
        void onFingerDown();
        void onFingerUp();
    }

}

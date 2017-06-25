package com.kevin.jdmall.ui.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

public class WrapContentViewPager extends ViewPager {
    public WrapContentViewPager(Context context) {
        super(context);
    }

    public WrapContentViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int height = 0;
        int childHeight = 0;
        View child;
        for (int i=0;i<getChildCount();i++){
            child = getChildAt(i);
            //child.measure(0,0);
           /* child.measure(widthMeasureSpec,
                    MeasureSpec.makeMeasureSpec(0,MeasureSpec.EXACTLY)
                    );*/
            childHeight = child.getMeasuredHeight();
            height = Math.max(childHeight,height);
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(height,MeasureSpec.EXACTLY);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}

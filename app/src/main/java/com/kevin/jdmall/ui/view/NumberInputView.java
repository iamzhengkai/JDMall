package com.kevin.jdmall.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.kevin.jdmall.R;
import com.kevin.jdmall.utils.DensityUtil;
import com.kevin.jdmall.utils.ToastUtil;

/**
 * Function:
 *
 * @FileName: com.kevin.jdmall.ui.view.NumberInputView.java
 * @author: zk
 * @date: 2017-06-28 09:30
 */

public class NumberInputView extends FrameLayout implements View.OnClickListener {
    EditText mEtNum;
    private View mView;
    private onPlusClickListener mOnPlusClickListener;
    private onMinusClickListener mOnMinusClickListener;
    private onNumberChangeListener mOnNumberChangeListener;
    private long mMax = Integer.MAX_VALUE;
    private int mValue;
    private TextWatcher mWatcher;
    private TextView mTvJian;
    private TextView mTvJia;

    public NumberInputView(@NonNull Context context) {
        super(context);
        initView(null);
    }

    public NumberInputView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(attrs);
    }


    public NumberInputView(@NonNull Context context, @Nullable AttributeSet attrs, int
            defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(attrs);
    }


    private void initView(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.NumberInputView);
            mMax = ta.getInteger(R.styleable.NumberInputView_maxValue, Integer.MAX_VALUE);
            mValue = ta.getInteger(R.styleable.NumberInputView_value, 0);
            ta.recycle();
        }

        mView = View.inflate(getContext(), R.layout.view_number_input_old, null);
        mEtNum = (EditText) mView.findViewById(R.id.et_num);
        String strValue = "" + mValue;
        mEtNum.setText(strValue);
        mEtNum.setSelection(strValue.toString().length());
        mWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                long num = 0;
                if (!TextUtils.isEmpty(s.toString())) {
                    num = Long.valueOf(s.toString());
                }else {
                    ToastUtil.showToast("数量不能为空！");
                    return;
                }
               /* if (num <= 0) {
                    ToastUtil.showToast(getContext(), "数量不能小于0");
                    mEtNum.setText("" + 0);
                    return;
                }*/

                if (num > mMax) {
                    ToastUtil.showToast("已达最大值，最大值为:" + mMax);
                    mEtNum.setText("" + mMax);
                    return;
                }
                if (mOnNumberChangeListener != null)
                    mOnNumberChangeListener.onNumberChanged(NumberInputView.this, getResult());
//                invalidate();
                mEtNum.setSelection(s.toString().length());
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        };
        mEtNum.addTextChangedListener(mWatcher);
        mTvJian = (TextView) mView.findViewById(R.id.tv_jian);
        mTvJia = (TextView) mView.findViewById(R.id.tv_jia);
        mTvJian.setOnClickListener(this);
        mTvJia.setOnClickListener(this);
        addView(mView);

    }

    @Override
    public void onClick(View v) {
        long num;
        String strNum="";
        String inputText = mEtNum.getText().toString();
        if (!TextUtils.isEmpty(inputText)) {
            num = Long.valueOf(inputText);
        } else {
            ToastUtil.showToast("数量不能为空！");
            return;
        }
        switch (v.getId()) {
            case R.id.tv_jian:
                if (mOnMinusClickListener != null) {
                    mOnMinusClickListener.onMinusClick(v);
                } else {
                    if (num <= 0){
                        ToastUtil.showToast("数量不能小于0");
                        return;
                    }
                    mEtNum.removeTextChangedListener(mWatcher);
                    strNum = --num + "";
                    mEtNum.setText(strNum);
                    mEtNum.setSelection(strNum.length());
                    mEtNum.addTextChangedListener(mWatcher);
                }
                break;
            case R.id.tv_jia:
                if (mOnPlusClickListener != null) {
                    mOnPlusClickListener.onPushClick(v);
                } else {
                    mEtNum.setText(++num + "");
                }
                break;
        }
    }

    public interface onPlusClickListener {
        void onPushClick(View view);
    }

    public interface onMinusClickListener {
        void onMinusClick(View view);
    }

    public interface onNumberChangeListener {
        void onNumberChanged(View view, int num);
    }

    public void setOnPlusClickListener(onPlusClickListener onPlusClickListener) {
        mOnPlusClickListener = onPlusClickListener;
    }

    public void setOnMinusClickListener(onMinusClickListener onMinusClickListener) {
        mOnMinusClickListener = onMinusClickListener;
    }

    public void setOnNumberChangeListener(onNumberChangeListener onNumberChangeListener) {
        mOnNumberChangeListener = onNumberChangeListener;
    }

    public int getResult() {
        return Integer.valueOf(mEtNum.getText().toString());
    }

    public void setMax(int max) {
        mMax = max;
    }

    //when the child view has minWdith,then you don't need to override onMeasure to support
    // wrap_content
    //Or you can also override onMeasure,but take the max value of the measured width and the min
    // width.
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int minWidthSize = DensityUtil.dp2px(getContext(), 100);
        if (widthMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(Math.max(minWidthSize, widthSize), heightSize);
        }
    }
}

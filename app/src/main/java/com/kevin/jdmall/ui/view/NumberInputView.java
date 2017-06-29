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

import com.kevin.jdmall.R;
import com.kevin.jdmall.utils.DensityUtil;
import com.kevin.jdmall.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Function:
 *
 * @FileName: com.kevin.jdmall.ui.view.NumberInputView.java
 * @author: zk
 * @date: 2017-06-28 09:30
 */

public class NumberInputView extends FrameLayout {
    @BindView(R.id.et_num)
    EditText mEtNum;
    private View mView;
    private onPlusClickListener mOnPlusClickListener;
    private onMinusClickListener mOnMinusClickListener;
    private onNumberChangeListener mOnNumberChangeListener;
    private int mMax = Integer.MAX_VALUE;
    private TextWatcher mWatcher;

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
        if (attrs != null){
            TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.NumberInputView);
            mMax = ta.getInteger(R.styleable.NumberInputView_maxValue, Integer.MAX_VALUE);
            ta.recycle();
        }

        mView = View.inflate(getContext(), R.layout.view_number_input, null);
        ButterKnife.bind(this, mView);
        addView(mView);
        mWatcher = new TextWatcher() {
            String oldValue;
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                oldValue = s.toString();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(s.toString())){
                    return;
                }
                if (Integer.valueOf(s.toString()) > mMax){
                    ToastUtil.showToast("已达最大值，最大值为:" + mMax);
                    mEtNum.setText("" + mMax);
                    return;
                }
                mOnNumberChangeListener.onNumberChanged(NumberInputView.this,getResult());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
    }

    @OnClick({R.id.tv_jian, R.id.tv_jia})
    public void onViewClicked(View view) {
        int num;
        String inputText = mEtNum.getText().toString();
        if (!TextUtils.isEmpty(inputText)){
            num = Integer.valueOf(inputText);
        }else{
            ToastUtil.showToast("数量不能为空！");
            return;
        }
        switch (view.getId()) {
            case R.id.tv_jian:
                if (mOnMinusClickListener != null){
                    mOnMinusClickListener.onMinusClick(view);
                } else {
                    if (num <= 0){
                        ToastUtil.showToast("数量不能小于0");
                        return;
                    }else{
                        mEtNum.removeTextChangedListener(mWatcher);
                        mEtNum.setText(--num + "");
                        mEtNum.addTextChangedListener(mWatcher);
                    }
                }
                break;
            case R.id.tv_jia:
                if (mOnPlusClickListener != null){
                    mOnPlusClickListener.onPushClick(view);
                } else {
                    if (num >= mMax){
                        ToastUtil.showToast("已达最大值！");
                        mEtNum.removeTextChangedListener(mWatcher);
                        mEtNum.setText("" + mMax);
                        mEtNum.addTextChangedListener(mWatcher);
                        return;
                    }else {
                        mEtNum.setText(++num + "");
                    }
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

    public interface onNumberChangeListener{
        void onNumberChanged(View view,int num);
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

    public int getResult(){
        return Integer.valueOf(mEtNum.getText().toString());
    }

    public void setMax(int max) {
        mMax = max;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int minWidthSize = DensityUtil.dp2px(getContext(),100);
        if (widthMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(minWidthSize,heightSize);
        }



    }
}

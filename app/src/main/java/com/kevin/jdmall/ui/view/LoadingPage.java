package com.kevin.jdmall.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.kevin.jdmall.R;


public abstract class LoadingPage extends FrameLayout {
    //页面状态枚举
    public static final int STATE_LOADING = 1;
    public static final int STATE_SUCCESS = 2;
    public static final int STATE_EMPTY = 3;
    public static final int STATE_ERROR = 4;
    //页面状态，默认为加载中
    private int mState = STATE_LOADING;


    private View loadingView;//加载状态的view
    private View emptyView;//空数据的view
    private View errorView;//加载错误的view
    private View successView;//加载成功的view
    private LayoutParams params;


    public LoadingPage(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initPage();
    }

    public LoadingPage(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPage();
    }

    public LoadingPage(Context context) {
        super(context);
        initPage();
    }

    /**
     * 初始化page的状态
     */
    private void initPage() {
        params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        //1.先把所有的view加入到ContentPage中
        if (loadingView == null) {
            loadingView = View.inflate(getContext(), R.layout.page_loading, null);
            addView(loadingView, params);
        }
        if (emptyView == null) {
            emptyView = View.inflate(getContext(), R.layout.page_empty, null);
            addView(emptyView, params);
        }
        if (errorView == null) {
            errorView = View.inflate(getContext(), R.layout.page_error, null);
            Button button = (Button) errorView.findViewById(R.id.btn_reload);
            button.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    mState = STATE_LOADING;
                    showPage();
                    loadDataAndRefreshView();
                }
            });
            addView(errorView, params);
        }
        if (successView == null) {
            successView = createSuccessView();
            if (successView != null) {
                addView(successView, params);
            } else {
                throw new IllegalArgumentException("The method createSuccessView() can not return" +
                        " null!");
            }
        }
        //2.根据state来控制显示哪个view
        showPage();
        //3.加载数据，并且根据加载后的数据去刷新view
        loadDataAndRefreshView();
    }

    /**
     * 根据state来控制显示哪个view
     */
    private void showPage() {
        loadingView.setVisibility(mState == STATE_LOADING ? View.VISIBLE : View.INVISIBLE);
        emptyView.setVisibility(mState == STATE_EMPTY ? View.VISIBLE : View.INVISIBLE);
        errorView.setVisibility(mState == STATE_ERROR ? View.VISIBLE : View.INVISIBLE);
        successView.setVisibility(mState == STATE_SUCCESS ? View.VISIBLE : View.INVISIBLE);
    }

    private void showPage(int state) {
        mState = state;
        loadingView.setVisibility(mState == STATE_LOADING ? View.VISIBLE : View.INVISIBLE);
        emptyView.setVisibility(mState == STATE_EMPTY ? View.VISIBLE : View.INVISIBLE);
        errorView.setVisibility(mState == STATE_ERROR ? View.VISIBLE : View.INVISIBLE);
        successView.setVisibility(mState == STATE_SUCCESS ? View.VISIBLE : View.INVISIBLE);
    }

    /**
     * 加载数据，并根据加载后的数据去刷新view
     */
    public abstract void loadDataAndRefreshView();

    /**
     * 由于成功view不确定，所以是抽象方法
     *
     * @return
     */
    protected abstract View createSuccessView();

    /**
     * 加载数据的方法，我们不确定怎么加载，所以是抽象方法，不过该方法要返回一个PageState，用于刷新View
     *
     * @return
     */
    protected Object loadData() {
        return null;
    }
}

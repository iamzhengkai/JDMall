package com.kevin.jdmall.ui.fragment;

import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.kevin.jdmall.MyConstants;
import com.kevin.jdmall.R;
import com.kevin.jdmall.ui.activity.ProductDetailActivity;
import com.orhanobut.logger.Logger;

import butterknife.BindView;

/**
 * Function:
 *
 * @FileName: com.kevin.jdmall.ui.fragment.ProductDetailDetailFragment.java
 * @author: zk
 * @date: 2017-06-27 23:40
 */

public class ProductDetailDetailFragment extends BaseFragment {

    @BindView(R.id.webview)
    WebView mWebview;

    @Override
    protected View initView() {
        return View.inflate(mActivity, R.layout.product_details_view, null);
    }

    @Override
    protected void bindView() {
        int productId = ((ProductDetailActivity)mActivity).mProductId;

        /*mWebview.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        mWebview.getSettings().setJavaScriptEnabled(true);*/
        String url = MyConstants.BASE_URL + "/productDetail" + "?productId=" + productId;
        Logger.e("url====>" + url);
        mWebview.loadUrl(url);

    }
}

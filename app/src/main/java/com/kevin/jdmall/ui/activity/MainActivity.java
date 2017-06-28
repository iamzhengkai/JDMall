package com.kevin.jdmall.ui.activity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.kevin.jdmall.R;
import com.kevin.jdmall.bean.Tab;
import com.kevin.jdmall.ui.fragment.CartFragment;
import com.kevin.jdmall.ui.fragment.CategoryFragment;
import com.kevin.jdmall.ui.fragment.HomeFragment;
import com.kevin.jdmall.ui.fragment.MineFragment;
import com.kevin.jdmall.ui.view.FragmentTabHost;

import java.util.ArrayList;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(android.R.id.tabcontent)
    FrameLayout mTabcontent;
    @BindView(android.R.id.tabhost)
    FragmentTabHost mTabhost;



    private ArrayList<Tab> mTabs = new ArrayList<>();
    private Class[] fragments = new Class[]{
            HomeFragment.class,
            CategoryFragment.class,
            CartFragment.class,
            MineFragment.class
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initTabs();
        initTabHost();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    private void initTabHost() {
        FragmentTabHost tabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);

        tabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);
        tabHost.getTabWidget().setDividerDrawable(null);
        for (int i = 0; i < mTabs.size(); i++) {
            TabHost.TabSpec tabSpec = tabHost.newTabSpec(mTabs.get(i).tag);
            tabSpec.setIndicator(createIndicator(mTabs.get(i)));
            tabHost.addTab(tabSpec, fragments[i], null);
        }
    }

    private void initTabs() {
        mTabs.add(new Tab("Home", R.drawable.selector_icon_home, "主页"));
        mTabs.add(new Tab("CategoryBean", R.drawable.selector_icon_category, "分类"));
        mTabs.add(new Tab("Cart", R.drawable.selector_icon_cart, "购物车"));
        mTabs.add(new Tab("Mine", R.drawable.selector_icon_mine, "我的"));
    }

    private View createIndicator(Tab tab) {
        View view = View.inflate(this, R.layout.indicator_view, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.indictor_iv);
        TextView textView = (TextView) view.findViewById(R.id.indictor_tv);

        imageView.setImageResource(tab.resId);
        textView.setText(tab.title);
        return view;
    }

    @Override
    public void onBackPressed() {
        //处理返回键
        String tag = mTabhost.getCurrentTabTag();
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(tag);
        if (fragment instanceof HomeFragment){
            super.onBackPressed();
        }else{
            mTabhost.setCurrentTab(0);
        }
    }
}

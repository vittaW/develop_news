package com.vitta.develop_news.base.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.gyf.barlibrary.ImmersionBar;
import com.vitta.develop_news.App;
import com.vitta.develop_news.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportActivity;

/**
 * 作者：王文婷 邮箱：WVitta@126.com
 * 创建时间：2017/11/21 10:11
 * 描述：RootActivity
 *
 * 非mvp 的activty
 */

public abstract class RootActivity extends SupportActivity {

    //ButterKnife 的绑定与解绑
    private Unbinder mUnBinder;
    protected Activity mContext;
    //状态栏 沉浸式 包含setToolbar
    protected ImmersionBar mImmersionBar;
    //finish 时 关闭软键盘
    private InputMethodManager imm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        setStatusBar();
        mUnBinder = ButterKnife.bind(this);
        mContext = this;
        onViewCreated();
        //添加到activity 管理栈中
        App.getInstance().addActivity(this);
        initView();
    }

    /**
     * 设置页面状态栏
     */
    protected void setStatusBar() {
        mImmersionBar = ImmersionBar.with(this)
//                .statusBarColor(R.color.status_bar_color_white)     //状态栏颜色，不写默认透明色
//                .statusBarDarkFont(true, 1f) //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
//                .fitsSystemWindows(true)    //解决状态栏和布局重叠问题，任选其一，默认为false，当为true时一定要指定statusBarColor()，不然状态栏为透明色，还有一些重载方法
                .keyboardEnable(true);
//                .navigationBarColor(R.color.nav_bar_color_white);//支持側滑返回
        mImmersionBar
                .init();  //必须调用方可沉浸式
    }

    protected void setToolBar(Toolbar toolbar, String title) {
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressedSupport();
            }
        });
        if (mImmersionBar != null){
            mImmersionBar.titleBar(toolbar).init();
        }
    }

    protected void onViewCreated() {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        App.getInstance().removeActivity(this);
        this.imm = null;
        if (mUnBinder != null){
            mUnBinder.unbind();
        }
        if (mImmersionBar != null) {
            mImmersionBar.destroy();
        }
    }

    @Override
    public void finish() {
        super.finish();
        hideSoftKeyBoard();
    }

    public void hideSoftKeyBoard() {
        View localView = getCurrentFocus();
        if (this.imm == null) {
            this.imm = ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE));
        }
        if ((localView != null) && (this.imm != null)) {
            this.imm.hideSoftInputFromWindow(localView.getWindowToken(), 2);
        }
    }

    protected abstract int getLayout();
    protected abstract void initView();
}
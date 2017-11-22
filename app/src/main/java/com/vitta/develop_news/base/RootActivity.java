package com.vitta.develop_news.base;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.SupportActivity;
import com.vitta.develop_news.App;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 作者：王文婷 邮箱：WVitta@126.com
 * 创建时间：2017/11/21 10:11
 * 描述：RootActivity
 */

public abstract class RootActivity extends SupportActivity {

    //ButterKnife 的绑定与解绑
    private Unbinder mUnBinder;
    protected Activity mContext;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(getLayout());
        mUnBinder = ButterKnife.bind(this);
        mContext = this;
        onViewCreated();
        //添加到activity 管理栈中
        App.getInstance().addActivity(this);
        initView();
    }

    protected void onViewCreated() {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        App.getInstance().removeActivity(this);
        mUnBinder.unbind();
    }
    protected abstract int getLayout();
    protected abstract void initView();
}
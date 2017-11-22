package com.vitta.develop_news.base;

import android.support.v7.app.AppCompatDelegate;
import android.view.ViewGroup;

import com.vitta.develop_news.App;
import com.vitta.develop_news.dagger.component.ActivityComponent;
import com.vitta.develop_news.dagger.component.DaggerActivityComponent;
import com.vitta.develop_news.dagger.module.ActivityModule;
import com.vitta.develop_news.util.SnackbarUtil;

import javax.inject.Inject;

/**
 * 作者：王文婷 邮箱：WVitta@126.com
 * 创建时间：2017/11/21 10:37
 * 描述：BaseActivity
 */

public abstract class BaseActivity<P extends BasePresenter> extends RootActivity implements BaseView {


    @Inject
    protected P mPresenter;


    protected ActivityComponent getActivityComponent(){
        return DaggerActivityComponent.builder()
                .appComponent(App.getAppComponent())
                .activityModule(getActivityModule())
                .build();
    }

    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

    //-------------------管理presenter 和 component 的生命周期----------------------------------------
    @Override
    protected void onViewCreated() {
        super.onViewCreated();
        //在onCreate 方法里注入 ，注入this对象，那么component 的生命周期跟activity 的生命周期同步
        initInject();
        if (mPresenter != null){
            mPresenter.attachView(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null){
            mPresenter.detachView();
        }
    }
    //-------------------管理presenter 和 component 的生命周期----------------------------------------

    @Override
    public void showErrorMsg(String msg) {
        SnackbarUtil.show(((ViewGroup) findViewById(android.R.id.content)).getChildAt(0), msg);
    }

    @Override
    public void userNightMode(boolean isNight) {
        if (isNight) {
            AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_NO);
        }
        recreate();
    }

    @Override
    public void stateError() {

    }

    @Override
    public void stateEmpty() {

    }

    @Override
    public void stateLoading() {

    }

    @Override
    public void stateMain() {

    }

    protected abstract void initInject();
}

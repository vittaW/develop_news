package com.vitta.develop_news.base;

import javax.inject.Inject;

/**
 * 作者：王文婷 邮箱：WVitta@126.com
 * 创建时间：2017/11/21 10:37
 * 描述：BaseActivity
 */

public abstract class BaseActivity<P extends BasePresenter> extends RootActivity implements BaseView {


    @Inject
    protected P mPresenter;


    @Override
    public void showErrorMsg(String msg) {

    }

    @Override
    public void userNightMode(boolean isNight) {

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

    @Override
    protected int getLayout() {
        return 0;
    }

    @Override
    protected void initEventAndData() {

    }
}

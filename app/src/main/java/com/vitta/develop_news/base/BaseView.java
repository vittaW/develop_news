package com.vitta.develop_news.base;

/**
 * 作者：王文婷 邮箱：WVitta@126.com
 * 创建时间：2017/11/21 10:39
 * 描述：BaseView
 */

public interface BaseView {

    void showErrorMsg(String msg);
    void userNightMode(boolean isNight);

    //state
    void stateError();
    void stateEmpty();
    void stateLoading();
    void stateMain();
}

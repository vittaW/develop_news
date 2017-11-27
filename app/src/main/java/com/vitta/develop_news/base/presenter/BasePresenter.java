package com.vitta.develop_news.base.presenter;

import com.vitta.develop_news.base.BaseView;

/**
 * 作者：王文婷 邮箱：WVitta@126.com
 * 创建时间：2017/11/21 10:43
 * 描述：BasePresenter
 */

public interface BasePresenter<T extends BaseView> {

    void attachView(T view);
    void detachView();

}

package com.vitta.develop_news.dagger.component;

import android.app.Activity;

import com.vitta.develop_news.dagger.module.FragmentModule;
import com.vitta.develop_news.dagger.scope.FragmentScope;
import com.vitta.develop_news.ui.zhihu.ZhiHuMainFragment;

import dagger.Component;

/**
 * 作者：王文婷 邮箱：WVitta@126.com
 * 创建时间：2017/11/21 12:05
 * 描述：FragmentComponent
 */

@FragmentScope
@Component(modules = {FragmentModule.class},dependencies = AppComponent.class)
public interface FragmentComponent {

    Activity getActivity();

    void inject(ZhiHuMainFragment zhiHuMainFragment);

}

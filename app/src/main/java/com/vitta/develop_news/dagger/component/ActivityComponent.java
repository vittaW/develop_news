package com.vitta.develop_news.dagger.component;

import android.app.Activity;

import com.vitta.develop_news.MainActivity;
import com.vitta.develop_news.dagger.module.ActivityModule;
import com.vitta.develop_news.dagger.scope.ActivityScope;

import dagger.Component;

/**
 * 作者：王文婷 邮箱：WVitta@126.com
 * 创建时间：2017/11/21 12:04
 * 描述：ActivityComponent
 */
@ActivityScope
@Component(modules = {ActivityModule.class} ,dependencies = AppComponent.class)
public interface ActivityComponent {

    Activity getActivity();

    void inject(MainActivity mainActivity);

}

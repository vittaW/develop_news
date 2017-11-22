package com.vitta.develop_news.dagger.component;

import com.vitta.develop_news.App;
import com.vitta.develop_news.dagger.module.AppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * 作者：王文婷 邮箱：WVitta@126.com
 * 创建时间：2017/11/21 12:05
 * 描述：AppComponent
 */
@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    App getContext();//提供App的Context

}

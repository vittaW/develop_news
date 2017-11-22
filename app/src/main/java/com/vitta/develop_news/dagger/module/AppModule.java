package com.vitta.develop_news.dagger.module;

import com.vitta.develop_news.App;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * 作者：王文婷 邮箱：WVitta@126.com
 * 创建时间：2017/11/21 12:07
 * 描述：AppModule
 */
@Module
public class AppModule {

    private final App application;

    public AppModule(App application) {
        this.application = application;
    }

    @Provides
    @Singleton
    App provideApplicationContext(){
        return application;
    }
}

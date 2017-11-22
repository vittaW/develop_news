package com.vitta.develop_news.dagger.module;

import android.app.Activity;

import com.vitta.develop_news.dagger.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * 作者：王文婷 邮箱：WVitta@126.com
 * 创建时间：2017/11/22 10:33
 * 描述：ActivityModule
 */
@Module
public class ActivityModule {

    private Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityScope
    public Activity provideActivity(){
        return activity;
    }
}

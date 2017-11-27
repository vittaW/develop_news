package com.vitta.develop_news.dagger.module;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.vitta.develop_news.dagger.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;

/**
 * 作者：王文婷 邮箱：WVitta@126.com
 * 创建时间：2017/11/23 14:15
 * 描述：FragmentModule
 */

@Module
public class FragmentModule {

    private Fragment fragment;

    public FragmentModule(Fragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @FragmentScope
    Activity provideActivity(){
        return fragment.getActivity();
    }

}

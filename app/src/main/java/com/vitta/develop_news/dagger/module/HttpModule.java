package com.vitta.develop_news.dagger.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 作者：王文婷 邮箱：WVitta@126.com
 * 创建时间：2017/11/22 15:38
 * 描述：HttpModule
 */
@Module
public class HttpModule {

    @Provides
    @Singleton
    public Retrofit.Builder provideRetrofit(){
        return new Retrofit.Builder();
    }

    private Retrofit createRetrofit(String baseUrl, OkHttpClient client){
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

}

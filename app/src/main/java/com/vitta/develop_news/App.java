package com.vitta.develop_news;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.v7.app.AppCompatDelegate;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import com.vitta.develop_news.di.AppComponent;
import com.vitta.develop_news.di.DaggerAppComponent;
import com.vitta.develop_news.di.module.AppModule;

import java.util.HashSet;
import java.util.Set;

/**
 * 作者：王文婷 邮箱：WVitta@126.com
 * 创建时间：2017/11/21 10:20
 * 描述：App
 */

public class App extends Application {

    private static App instance;
    private static AppComponent appComponent;
    //activity 的管理栈
    private Set<Activity> allActivities;
    public static int SCREEN_WIDTH = -1;
    public static int SCREEN_HEIGHT = -1;
    public static float DIMEN_RATE = -1.0F;
    public static int DIMEN_DPI = -1;

    //静态的App 实例，也就是单例的
    public static synchronized App getInstance() {
        return instance;
    }

    static {
        //静态语句块，在构造前执行，且只执行一次
        //设置了非夜间模式
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        //初始化屏幕宽高
        getScreenSize();
        //在子线程中完成其他初始化
        InitializeService.start(this);
    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    //--------------------------------activity管理---------------------------------------
    public void addActivity(Activity act) {
        if (allActivities == null) {
            allActivities = new HashSet<>();
        }
        allActivities.add(act);
    }

    public void removeActivity(Activity act) {
        if (allActivities != null) {
            allActivities.remove(act);
        }
    }

    public void exitApp() {
        if (allActivities != null) {
            synchronized (allActivities) {
                for (Activity act : allActivities) {
                    act.finish();
                }
            }
        }
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }
    //--------------------------------activity管理---------------------------------------

    //---------------------------------component----------------------------------------
    public static AppComponent getAppComponent() {
        if (appComponent == null){
            appComponent = DaggerAppComponent.builder()//定义了AppComponent ，自动生成DaggerAppComponent！
                    .appModule(new AppModule(instance))
                    .build();
        }
        return appComponent;
    }
    //---------------------------------component----------------------------------------

    public void getScreenSize() {
        WindowManager windowManager = (WindowManager)this.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        Display display = windowManager.getDefaultDisplay();
        display.getMetrics(dm);
        DIMEN_RATE = dm.density / 1.0F;
        DIMEN_DPI = dm.densityDpi;
        SCREEN_WIDTH = dm.widthPixels;
        SCREEN_HEIGHT = dm.heightPixels;
        if(SCREEN_WIDTH > SCREEN_HEIGHT) {
            int t = SCREEN_HEIGHT;
            SCREEN_HEIGHT = SCREEN_WIDTH;
            SCREEN_WIDTH = t;
        }
    }
}

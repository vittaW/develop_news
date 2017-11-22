package com.vitta.develop_news.component;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import com.github.moduth.blockcanary.BlockCanary;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.squareup.leakcanary.LeakCanary;
import com.vitta.develop_news.App;
import com.vitta.develop_news.widget.AppBlockCanaryContext;

/**
 * Service 服务，相当于activity 的组件，执行在子线程
 * IntentService 前台服务，系统不会杀死的服务
 *
 * 为什么？
 * 编写耗时操作又不得不放于后台管理的逻辑实现。
 * 这些初始化是耗时操作， 加载native的lib, 用ZipFile操作等。优化app 的启动速度，避免处理阻塞主线程。
 */

public class InitializeService extends IntentService {

    private static final String ACTION_INIT = "initApplication";

    public InitializeService() {
        super("InitializeService");
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, InitializeService.class);
        intent.setAction(ACTION_INIT);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_INIT.equals(action)) {
                initApplication();
            }
        }
    }

    private void initApplication() {
        //初始化日志
        Logger.addLogAdapter(new AndroidLogAdapter());

        //初始化错误收集
//        CrashHandler.init(new CrashHandler(getApplicationContext()));
        initBugly();

        //初始化内存泄漏检测
        LeakCanary.install(App.getInstance());

        //初始化过度绘制检测
        BlockCanary.install(getApplicationContext(), new AppBlockCanaryContext()).start();

//        //初始化tbs x5 webview
//        QbSdk.allowThirdPartyAppDownload(true);
//        QbSdk.initX5Environment(getApplicationContext(), QbSdk.WebviewInitType.FIRSTUSE_AND_PRELOAD, new QbSdk.PreInitCallback() {
//            @Override
//            public void onCoreInitFinished() {
//            }
//
//            @Override
//            public void onViewInitFinished(boolean b) {
//            }
//        });
    }

    private void initBugly() {
//        Context context = getApplicationContext();
//        String packageName = context.getPackageName();
//        String processName = SystemUtil.getProcessName(android.os.Process.myPid());
//        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(context);
//        strategy.setUploadProcess(processName == null || processName.equals(packageName));
//        CrashReport.initCrashReport(context, Constants.BUGLY_ID, isDebug, strategy);
    }
}

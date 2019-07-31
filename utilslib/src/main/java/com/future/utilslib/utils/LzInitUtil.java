package com.future.utilslib.utils;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import com.future.utilslib.BuildConfig;
import com.future.utilslib.net.LoggerInterceptor;
import com.lz.httplib.RxRequestUtils;
import com.lz.httplib.http.ConfigModule;
import com.lz.httplib.http.GlobalConfigBuild;
import com.noober.background.BackgroundLibrary;
import com.orhanobut.logger.*;
import com.tencent.bugly.crashreport.CrashReport;

/**
 * -----------作者----------日期----------变更内容-----
 * -          刘泽      2019-01-22       创建class
 */
public class LzInitUtil {

    //app环境切换
    public static final String APPBASEYRL = "APPBASEYRL";

    private LzInitUtil() {
    }

    private static Context sContext;

    public static void init(Application context) {
        sContext = context;
        //网络请求
        initRequest();

        //buglu配置
        CrashReport.setIsDevelopmentDevice(context, BuildConfig.DEBUG);
        CrashReport.initCrashReport(context, BuildConfig.BUGGLY_APPID, BuildConfig.DEBUG);
        CrashReport.putUserData(context, "VersionName", BuildConfig.VERSION_NAME);
        CrashReport.setUserId("");

        //页面生命周期
        context.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                LzActivityTool.addActivity(activity);
            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                LzActivityTool.finishActivity(activity);

            }
        });

        //日志打印初始化
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)
                .methodCount(1)
                .methodOffset(1)
                .logStrategy(new LogStrategy() {
                    @Override
                    public void log(int priority, @Nullable String tag, @NonNull String message) {
                        Log.println(priority, randomKey() + tag, message);
                    }

                    private int last;

                    private String randomKey() {
                        int random = (int) (10 * Math.random());
                        if (random == last) {
                            random = (random + 1) % 10;
                        }
                        last = random;
                        return String.valueOf(random);
                    }
                })
                .tag("FELOG")
                .build();

        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy) {
            @Override
            public boolean isLoggable(int priority, @Nullable String tag) {
                return BuildConfig.DEBUG;
            }
        });

    }

    public static void initRequest() {
        RxRequestUtils.initConfig(new ConfigModule() {
            @Override
            public void applyOptions(GlobalConfigBuild.Builder builder) {
                if (BuildConfig.DEBUG) {
                    builder.addInterceptor(new LoggerInterceptor());
                    boolean appbaseyrl = LzSPUtils.getBoolean(APPBASEYRL);
                    if (!appbaseyrl) {
                        builder.baseurl(BuildConfig.API_URL);
                    } else {
                        builder.baseurl(BuildConfig.API_ONLINE_URL);
                    }
                } else {
                    builder.baseurl(BuildConfig.API_URL);
                }
            }
        });
    }

    public static Context getApp() {
        return sContext;
    }
}

package com.future.utilslib.utils;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import com.future.httplib.http.HttpLoadDialog;
import com.future.utilslib.BuildConfig;
import com.future.utilslib.R;
import com.future.utilslib.dialog.LzDialogUtils;
import com.future.utilslib.net.LoggerInterceptor;
import com.future.utilslib.view.floating.FloatingView;
import com.future.utilslib.view.floating.FloatingViewConfig;
import com.lz.httplib.RxRequestUtils;
import com.lz.httplib.http.ConfigModule;
import com.lz.httplib.http.GlobalConfigBuild;
import com.lzx.starrysky.manager.MusicManager;
import com.orhanobut.logger.*;
import com.tencent.bugly.crashreport.CrashReport;

/**
 * -----------作者----------日期----------变更内容-----
 * -          刘泽      2019-01-22       创建class
 */
public class LzInitUtil {

    //app环境切换
    public static final String APPBASEURL = "APPBASEURL";
    private static FloatingView floatingView;
    private static int count = 0;

    private LzInitUtil() {
    }

    private static Context sContext;

    public static void init(final Application context) {
        sContext = context;
        //网络请求
        initRequest();
        //音乐
        MusicManager.initMusicManager(context);

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
                if (count == 0) {
                    if (floatingView != null) {
                        floatingView.showOverlaySystem();
                    }

                }
                count++;
            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {
                count--;
                if (count == 0) {
                    if (floatingView != null) {
                        floatingView.hide();
                    }
                }
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


        if (floatingView == null) {
            FloatingViewConfig config = new FloatingViewConfig.Builder()
                    .setGravity(FloatingViewConfig.GRAVITY.LEFT_CENTER)
                    .setDisplayWidth(LzDp2Px.dp2px(30))
                    .setDisplayHeight(LzDp2Px.dp2px(30))
                    .build();

            floatingView = new FloatingView(context, R.layout.view_floating, config);
            floatingView.showOverlaySystem();
            floatingView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LzSPUtils.putBoolean(
                            LzInitUtil.APPBASEURL, !LzSPUtils.getBoolean(
                                    LzInitUtil.APPBASEURL
                            )
                    );
                    LzDialogUtils.alertConfirmDialog(context, "提示信息", "切换之后必须重启APP", "", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {


                            Intent intent = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
                            PendingIntent restartIntent =
                                    PendingIntent.getActivity(context, 0, intent, Intent.FLAG_ACTIVITY_NEW_TASK);
                            // 退出程序
                            AlarmManager mgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
                            mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 500, restartIntent);
                            android.os.Process.killProcess(android.os.Process.myPid());

                        }
                    }).

                            setCancelable(false);
                }
            });

        }
    }

    public static void initRequest() {
        RxRequestUtils.initConfig(new ConfigModule() {
            @Override
            public void applyOptions(GlobalConfigBuild.Builder builder) {
                if (BuildConfig.DEBUG) {
                    builder.addInterceptor(new LoggerInterceptor());
                    boolean appbaseyrl = LzSPUtils.getBoolean(APPBASEURL);
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

package com.future.utilslib.dialog;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.app.Dialog;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import java.util.List;

/**
 * @author : liuze
 * @e-mail : 835052259@qq.com
 * @date : 2019/9/23-12:43
 * @desc : 修改内容
 * @version: 1.0
 */
public class LoadDialog {


    private Dialog mDialog;

    public LoadDialog(final Context context) {

        if (!isContextExisted(context)) {
            return;
        }

        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            initView(context);
        } else {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    initView(context);
                }
            });
        }
    }

    public Dialog getDialog() {
        return mDialog;
    }

    private void initView(Context context) {
        mDialog = new Dialog(context);
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = mDialog.getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.alpha = 1f;
        window.setAttributes(layoutParams);
        layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        layoutParams.gravity = Gravity.CENTER;

        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        ProgressBar progressBar = new ProgressBar(context);
        LinearLayout.LayoutParams rootLp = new LinearLayout.LayoutParams(dp2px(70), dp2px(70));
        mDialog.setContentView(progressBar, rootLp);


    }

    private static int dp2px(final float dpValue) {
        final float scale = Resources.getSystem().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    private int showCount = 0;

    public synchronized void show() {
        if (mDialog != null) {
            if (!mDialog.isShowing()) {
                mDialog.show();
            }
            showCount = showCount + 1;
        }
    }

    public synchronized void dismiss() {
        if (mDialog != null) {
            showCount = showCount - 1;
            if (showCount <= 0) {
                showCount = 0;
                mDialog.dismiss();

            }
        }
    }


    public static boolean isServiceExisted(Context context, String className) {
        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> serviceList = activityManager
                .getRunningServices(Integer.MAX_VALUE);

        if (!(serviceList.size() > 0)) {
            return false;
        }

        for (int i = 0; i < serviceList.size(); i++) {
            ActivityManager.RunningServiceInfo serviceInfo = serviceList.get(i);
            ComponentName serviceName = serviceInfo.service;

            if (serviceName.getClassName().equals(className)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isContextExisted(Context context) {
        if (context != null) {
            if (context instanceof Activity) {
                if (!((Activity) context).isFinishing()) {
                    return true;
                }
            } else if (context instanceof Service) {
                if (isServiceExisted(context, context.getClass().getName())) {
                    return true;
                }
            } else if (context instanceof Application) {
                return true;
            }
        }
        return false;
    }
}
package com.future.utilslib.base;



import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.future.utilslib.R;
import com.gyf.immersionbar.ImmersionBar;
import com.lz.fram.base.FramActivity;
import com.noober.background.BackgroundLibrary;

import lz.com.status.StatusUiImpl;
import lz.com.status.StatusView;
import lz.com.status.view.ConfigViewModle;


public abstract class BaseActivity extends FramActivity {


    protected ImmersionBar mImmersionBar;
    protected StatusView mStatusView;


    @Override
    protected void initConfig() {
        super.initConfig();
        //状态栏标题栏
        mImmersionBar = ImmersionBar.with(this)
                .statusBarColor("#ffffff")
                .statusBarDarkFont(true, 0.5f)
                .flymeOSStatusBarFontColorInt(Color.BLACK)
                .navigationBarColor("#ffffff")
                .autoNavigationBarDarkModeEnable(true, 0.2f)
                .autoStatusBarDarkModeEnable(true, 0.2f);
        mImmersionBar
                .init();
        //控件背景设置
        BackgroundLibrary.inject2(this);
        ViewGroup contentView = findViewById(R.id.container);
        if (contentView != null) {
            if (contentView.getChildCount() == 1) {
                mStatusView = StatusView.init(contentView.getChildAt(0));
            } else if (contentView.getChildCount() == 2) {
                mStatusView = StatusView.init(contentView.getChildAt(1));
            } else {
                mStatusView = StatusView.init(this);
            }
            mStatusView.setStatusUI(new StatusUiImpl() {
                @Override
                public void setStatusData(ConfigViewModle statusModle) {


                }
            });
        }
        mSwipePanel.setLeftEnabled(false);
    }

}

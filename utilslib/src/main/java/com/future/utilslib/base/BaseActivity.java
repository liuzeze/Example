package com.future.utilslib.base;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import com.future.utilslib.R;
import com.future.utilslib.view.SwipePanel;
import com.future.utilslib.view.statu.StatusConfigBuild;
import com.future.utilslib.view.statu.StatusView;
import com.future.utilslib.view.toolbar.TitleToolbar;
import com.gyf.immersionbar.ImmersionBar;
import com.lz.fram.base.BaseView;
import com.lz.fram.inject.PresenterDispatch;
import com.lz.fram.inject.PresenterProviders;
import com.noober.background.BackgroundLibrary;
import io.reactivex.annotations.Nullable;

/**
 * Activity 基类
 * Created by 刘泽 on 2017/7/10 18:50.
 */

public abstract class BaseActivity extends FragmentActivity implements BaseView {

    protected Activity mActivity;
    private TitleToolbar titleToolbar;
    private SwipePanel swipePanel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BackgroundLibrary.inject(this);
        initConfig();
        initBar();
        initData();
        initLisenter();

    }

    /**
     * 初始化公用的参数
     */
    protected void initConfig() {
        mActivity = this;
        InjectManager.getLayoutId(this);
        PresenterDispatch presenterDispatch = PresenterProviders.inject(this).presenterCreate();
        presenterDispatch.attachView(this, getLifecycle());
    }


    protected void initBar() {
        ImmersionBar
                .with(this)
                .fitsSystemWindows(true)
                .statusBarColor(R.color.colorPrimary)
                .autoStatusBarDarkModeEnable(true, 0.2f)
                .init();
    }

    protected TitleToolbar getTitleToolbar() {
        if (titleToolbar == null) {
            titleToolbar = findViewById(R.id.common_toolbar);
        }

        if (titleToolbar == null) {
            titleToolbar = new TitleToolbar(this);
        }
        return titleToolbar;
    }

    protected SwipePanel getSwipePanel() {
        if (swipePanel == null) {
            swipePanel = findViewById(R.id.sp_root);
        }

        if (swipePanel == null) {
            swipePanel = new SwipePanel(this);
        }
        return swipePanel;
    }

    @Override
    public Context getContext() {
        return mActivity;
    }


    /**
     * 初始化数据
     */
    protected abstract void initData();

    protected void initLisenter() {
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ActivityCompat.finishAfterTransition(this);
    }

    @Override
    public void showErrorMsg(String msg) {

    }


}

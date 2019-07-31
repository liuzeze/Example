package com.future.utilslib.base;


import android.graphics.Color;
import com.future.utilslib.R;
import com.gyf.immersionbar.ImmersionBar;
import com.lz.fram.base.FramBaseActivity;
import com.noober.background.BackgroundLibrary;


public abstract class BaseActivity extends FramBaseActivity {

    @Override
    protected void initConfig() {
        BackgroundLibrary.inject(this);
        super.initConfig();
        initBar();
        getSwipePanel().setLeftSwipeColor(Color.RED);
    }

    protected void initBar() {
        ImmersionBar
                .with(this)
                .fitsSystemWindows(true)
                .statusBarColor(R.color.colorPrimary)
                .autoStatusBarDarkModeEnable(true, 0.2f)
                .init();
    }


}

package com.future.utilslib.base;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.future.utilslib.R;
import com.future.utilslib.view.SwipePanel;
import com.future.utilslib.view.toolbar.TitleToolbar;
import com.lz.fram.base.BaseView;
import com.lz.fram.inject.PresenterDispatch;
import com.lz.fram.inject.PresenterProviders;
import io.reactivex.annotations.Nullable;



public abstract class BaseFragment extends BaseVisibleFragment implements BaseView {

    protected Context mContext;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View inflate = getRootView(inflater, container);
        return inflate;
    }

    private View getRootView(LayoutInflater inflater, @Nullable ViewGroup container) {
        View inflate = InjectManager.getView(getClass(), inflater, container);
        PresenterDispatch presenterDispatch = PresenterProviders.inject(this).presenterCreate();
        presenterDispatch.attachView(this, getLifecycle());

        return inflate;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
        initLisenter();
    }

    protected abstract void initData();

    protected void initLisenter() {
    }

    @Nullable
    @Override
    public Context getContext() {
        return mContext;
    }

    @Override
    public void showErrorMsg(String msg) {

    }

}

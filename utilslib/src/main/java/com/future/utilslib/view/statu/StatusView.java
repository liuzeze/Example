package com.future.utilslib.view.statu;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.future.utilslib.R;

/**
 * -----------作者----------日期----------变更内容-----
 * -          刘泽      2018-10-22       创建class
 */
public class StatusView extends FrameLayout {

    private @LayoutRes
    int mLoadingResourceId = R.layout.layout_loading;
    private @LayoutRes
    int mErrorResourceId = R.layout.layout_error;
    private @LayoutRes
    int mEmptyResourceId = R.layout.layout_empty;
    private Context mContext;

    private SparseArray<View> views = new SparseArray<>();
    private View mContentView;
    private View mCurrentView;
    private StatusConfigBuild mStatusBuild;

    public StatusView(@NonNull Context context) {
        this(context, null);
    }

    public StatusView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StatusView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context, attrs, defStyleAttr, 0);
        setBackgroundColor(Color.WHITE);
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public StatusView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initAttrs(context, attrs, defStyleAttr, defStyleRes);
    }

    private void initAttrs(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        if (mStatusBuild == null) {
            mStatusBuild = new StatusConfigBuild();
        }
        mContext = context;
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.StatusView, defStyleAttr, defStyleRes);
        for (int i = 0; i < typedArray.getIndexCount(); i++) {
            int index = typedArray.getIndex(i);
            if (index == R.styleable.StatusView_loading_view) {
                mLoadingResourceId = typedArray.getResourceId(i, index);
            } else if (index == R.styleable.StatusView_error_view) {
                mErrorResourceId = typedArray.getResourceId(i, index);
            } else if (index == R.styleable.StatusView_empty_view) {
                mEmptyResourceId = typedArray.getResourceId(i, index);
            }
        }
        typedArray.recycle();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (getChildCount() == 1) {
            View contentView = getChildAt(0);
            setContentView(contentView);
        }
    }

    public static StatusView init(Activity activity) {
        View contentView = ((ViewGroup) activity.findViewById(android.R.id.content)).getChildAt(0);
        return bindView(contentView);
    }

    public static StatusView init(Activity activity, @IdRes int resId) {
        View rootView = ((ViewGroup) activity.findViewById(android.R.id.content)).getChildAt(0);
        View contentView = rootView.findViewById(resId);
        return bindView(contentView);
    }

    public static StatusView init(View contentView) {
        return bindView(contentView);
    }

    public static StatusView init(Fragment fragment) {
        View view = fragment.getView();
        return bindView(view);
    }

    public static StatusView init(Fragment fragment, @IdRes int resourceId) {
        View rootView = fragment.getView();
        View contentView = null;
        if (rootView != null) {
            contentView = rootView.findViewById(resourceId);
        }
        return bindView(contentView);
    }

    private static StatusView bindView(View contentView) {

        if (contentView == null) {
            return null;
//            new Throwable("contentView can not be null");
        }
        ViewGroup parent = (ViewGroup) contentView.getParent();
        ViewGroup.LayoutParams layoutParams = contentView.getLayoutParams();
        int index = parent.indexOfChild(contentView);

        parent.removeView(contentView);
        StatusView statusView = new StatusView(contentView.getContext());
        statusView.addView(contentView);
        statusView.setContentView(contentView);
        if (contentView.getVisibility() == View.GONE) {
            statusView.setVisibility(GONE);
        }
        parent.addView(statusView, index, layoutParams);
        //兼容linearLayout的权重属性
        if (layoutParams instanceof LinearLayout.LayoutParams) {
            if (((LinearLayout.LayoutParams) layoutParams).weight == 1
                    && layoutParams.width == 0) {
                ViewGroup.LayoutParams childParams = contentView.getLayoutParams();
                childParams.width = LinearLayout.LayoutParams.MATCH_PARENT;
                contentView.setLayoutParams(childParams);
            }
        }

        return statusView;
    }

    private void setContentView(View contentView) {
        mContentView = mCurrentView = contentView;
    }


    private synchronized void switchView(View statuView) {
        //当前已显示
        if (mCurrentView == statuView) {
            return;
        }
        //当前显示的是UI View  要显示其他View  不显示UI View
        if (mCurrentView == mContentView) {
            mContentView.setVisibility(GONE);
            mCurrentView = statuView;
            addView(mCurrentView);
        } else {
            //当前显示的不是UI View
            removeView(mCurrentView);
            mCurrentView = statuView;
            if (statuView == mContentView) {
                if (mContentView.getVisibility() == View.GONE) {
                    mContentView.setVisibility(VISIBLE);
                }
            } else {
                addView(mCurrentView);

            }
        }
    }

    public void showLoadingView() {
        View contentView = getView(mLoadingResourceId);
        switchView(contentView);
    }


    public void showContentView() {
        switchView(mContentView);
    }


    public void showErrorView() {
        switchView(getView(mErrorResourceId));
    }


    public void showEmptyView() {
        switchView(getView(mEmptyResourceId));

    }

    public void showLoadingView(String loadText) {
        mStatusBuild.setLoadTitle(loadText);
        View contentView = getView(mLoadingResourceId);
        switchView(contentView);
    }

    private void setShowTitle(View contentView, int resourceId) {
        //当前已显示
        if (mCurrentView == contentView) {
            return;
        }
        if (mContentView == contentView) {
            return;
        }
        //当前显示的是UI View  要显示其他View  不显示UI View
        if (mCurrentView == mContentView) {
            Object tag = contentView.getTag();
            if (tag != null) {
                if (tag instanceof StatusViewHolder) {
                    StatusViewHolder viewHolder = (StatusViewHolder) tag;
                    if (resourceId == R.layout.layout_loading) {
                        viewHolder.setText(R.id.tv_load_msg, mStatusBuild.getLoadTitle());
                    } else if (resourceId == R.layout.layout_error) {
                        viewHolder.setText(R.id.tv_error_msg, mStatusBuild.getErrorTitle());
                    } else if (resourceId == R.layout.layout_empty) {
                        viewHolder.setText(R.id.tv_empty_msg, mStatusBuild.getEmptyTitle());
                        viewHolder.setText(R.id.tv_retry, mStatusBuild.getRetryTitle());
                    }
                }
            }
        }
    }

    public void showEmptyView(String emptyText) {
        mStatusBuild.setEmptyTitle(emptyText);
        switchView(getView(mEmptyResourceId));

    }

    public void showErrorView(String errorText) {
        mStatusBuild.setErrorTitle(errorText);
        switchView(getView(mErrorResourceId));
    }

    private View getView(@LayoutRes int resourceId) {
        View view = views.get(resourceId);
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(resourceId, null);
            views.put(resourceId, view);

            StatusViewHolder statusViewHolder = StatusViewHolder.create(view);
            updataView(resourceId, statusViewHolder);
            view.setTag(statusViewHolder);
        }

        setShowTitle(view, resourceId);

        return view;
    }

    private void updataView(final int resourceId, StatusViewHolder viewHolder) {
        if (mStatusBuild == null) {
            return;
        }
        if (resourceId == R.layout.layout_loading) {

            viewHolder.setVisibility(R.id.tv_load_msg, mStatusBuild.isShowloadTitle());
            viewHolder.setText(R.id.tv_load_msg, mStatusBuild.getLoadTitle());
            viewHolder.setTextSize(R.id.tv_load_msg, mStatusBuild.getTitleSize());
            if (mStatusBuild.getTitleColor() > 0) {
                viewHolder.setTextColor(R.id.tv_load_msg, mContext.getResources().getColor(mStatusBuild.getTitleColor()));
            }
            viewHolder.setVisibility(R.id.sv_load, mStatusBuild.isShowloadImage());
            if (mStatusBuild.getAnimView() != null) {
                viewHolder.setLoadanimStyle(R.id.sv_load, mStatusBuild.getAnimView());
            }
        } else if (resourceId == R.layout.layout_error) {
            viewHolder.setVisibility(R.id.iv_error, mStatusBuild.isShowErrorImage());
            viewHolder.setImageResource(R.id.iv_error, mStatusBuild.getErrorImage());
            viewHolder.setVisibility(R.id.tv_error_msg, mStatusBuild.isShowErrorTitle());
            viewHolder.setText(R.id.tv_error_msg, mStatusBuild.getErrorTitle());
            viewHolder.setTextSize(R.id.tv_error_msg, mStatusBuild.getTitleSize());
            if (mStatusBuild.getTitleColor() > 0) {
                viewHolder.setTextColor(R.id.tv_error_msg, mContext.getResources().getColor(mStatusBuild.getTitleColor()));
            }

            View view = viewHolder.getView(R.id.tv_error_msg);
            if (view != null) {
                view.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showLoadingView();
                        if (mStatusBuild.getErrorRetryListener() != null) {
                            mStatusBuild.getErrorRetryListener().onClick(v);
                        }
                    }
                });
            }

        } else if (resourceId == R.layout.layout_empty) {
            viewHolder.setVisibility(R.id.iv_empty, mStatusBuild.isShowEmptyImage());
            viewHolder.setImageResource(R.id.iv_empty, mStatusBuild.getEmptyImage());
            viewHolder.setVisibility(R.id.tv_empty_msg, mStatusBuild.isShowEmptyTitle());
            viewHolder.setText(R.id.tv_empty_msg, mStatusBuild.getEmptyTitle());

            viewHolder.setVisibility(R.id.tv_retry, mStatusBuild.isShowRetryButton());
            viewHolder.setText(R.id.tv_retry, mStatusBuild.getRetryTitle());

            viewHolder.setTextSize(R.id.tv_empty_msg, mStatusBuild.getTitleSize());
            if (mStatusBuild.getTitleColor() > 0) {
                viewHolder.setTextColor(R.id.tv_empty_msg, mContext.getResources().getColor(mStatusBuild.getTitleColor()));
            }
            viewHolder.setTextSize(R.id.tv_retry, mStatusBuild.getRetrySize());
            if (mStatusBuild.getRetryColor() > 0) {
                viewHolder.setTextColor(R.id.tv_retry, mContext.getResources().getColor(mStatusBuild.getRetryColor()));
            }
            viewHolder.setBackgroundResource(R.id.tv_retry, mStatusBuild.getRetryBackGround());

            View view = viewHolder.getView(R.id.tv_retry);
            if (view != null) {
                view.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showLoadingView();
                        if (mStatusBuild.getEmptyRetryListener() != null) {
                            mStatusBuild.getEmptyRetryListener().onClick(v);
                        }
                    }
                });
            }

        }

    }

    public StatusView config(StatusConfigBuild build) {
        if (build != null) {
            mStatusBuild = build;
        }
        return this;
    }

    public StatusView setLoadingView(View loadingView) {
        if (loadingView == null) {
            views.remove(mLoadingResourceId);
            return this;
        }
        views.put(mLoadingResourceId, loadingView);

        return this;
    }

    public StatusView setErrorView(View errorView) {
        if (errorView == null) {
            views.remove(mErrorResourceId);
            return this;
        }
        views.put(mErrorResourceId, errorView);
        return this;
    }

    public StatusView setEmptyView(View emptyView) {
        if (emptyView == null) {
            views.remove(mEmptyResourceId);
            return this;
        }
        views.put(mEmptyResourceId, emptyView);
        return this;
    }

    public StatusConfigBuild getStatusBuild() {
        return mStatusBuild;
    }
}

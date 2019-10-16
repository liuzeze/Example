package com.future.utilslib.http;


import com.lz.fram.base.BaseView;
import com.lz.httplib.observer.BaseObserver;

/**
 * -------- 日期 ---------- 维护人 ------------ 变更内容 --------
 * 2017/12/26	9:24	     刘泽			  公用的订阅处理 ResourceObserver
 *
 * @author liuze
 */
public abstract class CommonObserver<T> extends BaseObserver<T> {
    private BaseView mView;

    public CommonObserver(BaseView baseView) {
        super(baseView);
        mView = baseView;
    }


    @Override
    protected void onError(int code, String mes) {
        if (mView != null) {
            mView.showErrorMsg(code + ":" + mes);
        }
    }

}
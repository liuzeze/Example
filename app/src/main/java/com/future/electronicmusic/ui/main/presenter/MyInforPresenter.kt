package com.future.electronicmusic.ui.main.presenter

import com.future.electronicmusic.api.RequestApi
import com.lz.fram.base.RxPresenter
import com.lz.fram.observer.CommonSubscriber


/**
 * -----------作�??----------日期----------变更内容-----
 * -          刘泽      2019-04-26       创建class
 */
class MyInforPresenter : RxPresenter<MyInforContract.View>(), MyInforContract.Presenter {

    private var mRequestApi: RequestApi

    init {
        mRequestApi = RequestApi()
    }

    override fun getNewLists(type: String) {
        val commonSubscriber = mRequestApi
            .getNewLists(type, "")
            .subscribeWith(object : CommonSubscriber<String>() {
                override fun onNext(s: String) {
                    mBaseView.getNewsListSuccess(s)
                }
            })
    }
}

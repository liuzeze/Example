package com.future.electronicmusic.ui.main.fragment.presenter

import com.future.electronicmusic.api.RequestApi
import com.lz.fram.base.RxPresenter
import com.lz.fram.observer.CommonSubscriber

/**
 * -----------作者----------日期----------变更内容-----
 * -          刘泽      2019-04-26       创建class
 */
class HomePresenter : RxPresenter<HomeContract.View>(), HomeContract.Presenter {

    private val mRequestApi: RequestApi

    init {
        mRequestApi = RequestApi()
    }

    override fun getNewLists(type: String) {
        val commonSubscriber = mRequestApi
            .getNewLists(type, "")
            .`as`(bindLifecycle())
            .subscribeWith(object : CommonSubscriber<String>() {

                override fun onNext(s: String) {
                    mBaseView.getNewsListSuccess(s)
                }
            })
        addSubscribe("getNewLists", commonSubscriber)
    }
}

package com.future.electronicmusic.ui.main.fragment.presenter

import com.future.electronicmusic.api.RequestApi
import com.future.utilslib.http.CommonObserver
import com.lz.fram.base.RxPresenter

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
            .subscribeWith(object : CommonObserver<String>(mBaseView) {

                override fun onNext(s: String) {
                    mBaseView.getNewsListSuccess(s)
                }
            })
    }

}

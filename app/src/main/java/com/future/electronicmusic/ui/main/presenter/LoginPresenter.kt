package com.future.electronicmusic.ui.main.fragment.presenter

import com.future.electronicmusic.api.RequestApi
import com.lz.fram.base.RxPresenter
import com.lz.fram.observer.CommonSubscriber

/**
 * -----------作者----------日期----------变更内容-----
 * -          刘泽      2019-04-26       创建class
 */
class LoginPresenter : RxPresenter<LoginContract.View>(), LoginContract.Presenter {

    private val mRequestApi: RequestApi

    init {
        mRequestApi = RequestApi()
    }

    override fun login(userName: String, password: String) {
        mBaseView.loginSuccess()
        val commonSubscriber = mRequestApi
            .login(userName, password)
            .`as`(bindLifecycle())
            .subscribeWith(object : CommonSubscriber<String>() {

                override fun onNext(s: String) {
                    mBaseView.loginSuccess()
                }
            })
        addSubscribe("login", commonSubscriber)
    }

}

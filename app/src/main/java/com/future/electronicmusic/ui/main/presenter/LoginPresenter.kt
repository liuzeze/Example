package com.future.electronicmusic.ui.main.presenter

import com.future.electronicmusic.api.RequestApi
import com.future.electronicmusic.ui.main.fragment.presenter.LoginContract
import com.future.utilslib.http.CommonObserver
import com.lz.fram.base.RxPresenter

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
        val commonSubscriber = mRequestApi
            .login(userName, password)
            .subscribeWith(object : CommonObserver<String>(mBaseView) {
                override fun onNext(s: String) {
                    mBaseView.loginSuccess()
                }
            })
    }

}

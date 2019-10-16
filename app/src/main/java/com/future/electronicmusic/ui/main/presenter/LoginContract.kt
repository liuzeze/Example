package com.future.electronicmusic.ui.main.fragment.presenter

import com.lz.fram.base.BaseView

/**
 * -----------作者----------日期----------变更内容-----
 * -          刘泽      2019-04-26       创建class
 */
class LoginContract {

    interface View : BaseView {
        fun loginSuccess()
    }

    internal interface Presenter  {
        fun login(userName: String,password: String)
    }
}

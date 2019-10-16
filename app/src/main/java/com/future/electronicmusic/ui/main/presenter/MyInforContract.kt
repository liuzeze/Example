package com.future.electronicmusic.ui.main.presenter

import com.lz.fram.base.BaseView

/**
 * -----------作�??----------日期----------变更内容-----
 * -          刘泽      2019-04-26       创建class
 */
class MyInforContract {

    interface View : BaseView {
        fun getNewsListSuccess(s: String)
    }

    interface Presenter  {
        fun getNewLists(type: String)
    }
}

package com.future.electronicmusic.ui.main.fragment.presenter

import com.lz.fram.base.BasePresenter
import com.lz.fram.base.BaseView

/**
 * -----------作者----------日期----------变更内容-----
 * -          刘泽      2019-04-26       创建class
 */
class HomeContract {

    interface View : BaseView {
        fun getNewsListSuccess(s: String)
    }

    internal interface Presenter : BasePresenter {
        fun getNewLists(type: String)
    }
}

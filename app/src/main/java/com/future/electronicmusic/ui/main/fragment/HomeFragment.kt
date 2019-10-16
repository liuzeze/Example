package com.future.electronicmusic.ui.main.fragment

import com.future.electronicmusic.R
import com.future.electronicmusic.ui.main.fragment.presenter.HomeContract
import com.future.electronicmusic.ui.main.fragment.presenter.HomePresenter
import com.future.utilslib.base.BaseFragment
import com.lz.fram.base.GraphConfig
import com.lz.fram.scope.AttachPresenter
import kotlinx.android.synthetic.main.fragment_home.*

/**
 *-----------作者----------日期----------变更内容-----
 *-          刘泽      2019-07-12       创建class
 */
class HomeFragment : BaseFragment(), HomeContract.View {

    override fun initLayout(graphLayout: GraphConfig) {
        graphLayout.setLayoutId(R.layout.fragment_home)
    }
    override fun initViewData() {
        mPresenter?.getNewLists("推荐")
    }
    @AttachPresenter
    var mPresenter: HomePresenter? = null


    override fun getNewsListSuccess(s: String) {
        tv_center.text = s

    }
}
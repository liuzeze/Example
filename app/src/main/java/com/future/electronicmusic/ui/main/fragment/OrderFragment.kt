package com.future.electronicmusic.ui.main.fragment;

import com.future.electronicmusic.R
import com.future.electronicmusic.ui.main.fragment.presenter.OrderContract
import com.future.electronicmusic.ui.main.fragment.presenter.OrderPresenter
import com.future.utilslib.base.BaseFragment
import com.lz.fram.base.GraphConfig
import com.lz.fram.scope.AttachPresenter


/**
 * @author Administrator
 */

class OrderFragment : BaseFragment(), OrderContract.View {

    override fun initLayout(graphLayout: GraphConfig) {
        graphLayout.setLayoutId(R.layout.fragment_order).setTitleName("订单").setShowTitle(true)
    }

    @AttachPresenter
    var mPresenter: OrderPresenter? = null

    override fun getNewsListSuccess(s: String) {

    }
    override fun initViewData() {
        mPresenter?.getNewLists("推荐")
    }

}
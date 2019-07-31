package com.future.electronicmusic.ui.main.fragment;

import com.future.electronicmusic.R
import com.future.electronicmusic.ui.main.fragment.presenter.OrderContract
import com.future.electronicmusic.ui.main.fragment.presenter.OrderPresenter
import com.future.utilslib.base.BaseFragment
import com.lz.fram.base.InjectLayout
import com.lz.fram.scope.AttachPresenter


/**
 * @author Administrator
 */

@InjectLayout(layoutId = R.layout.fragment_order,isShowFragTitle = true,titleName = "订单")
class OrderFragment : BaseFragment(), OrderContract.View {

    @AttachPresenter
    var mPresenter: OrderPresenter? = null

    override fun initData() {
        mPresenter?.getNewLists("推荐")
    }

    override fun getNewsListSuccess(s: String) {

    }
}
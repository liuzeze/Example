package com.future.electronicmusic.ui.main;

import com.future.electronicmusic.R
import com.future.electronicmusic.ui.main.presenter.MyInforContract
import com.future.electronicmusic.ui.main.presenter.MyInforPresenter
import com.future.utilslib.base.BaseActivity
import com.lz.fram.base.InjectLayout
import com.lz.fram.scope.AttachPresenter
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


/**
 *-----------作者----------日期----------变更内容-----
 *-          刘泽      2019-07-12       创建class
 */

@InjectLayout(layoutId = R.layout.activity_my_infor, titleName = "标题")
class MyInforActivity : BaseActivity(), MyInforContract.View {

    @AttachPresenter
    var mPresenter: MyInforPresenter? = null

    override fun initData() {
        runBlocking {
            val launch = GlobalScope.launch {
                mPresenter?.getNewLists("推荐")

            }
            launch.join()
        }
    }


    override fun getNewsListSuccess(s: String) {

    }
}
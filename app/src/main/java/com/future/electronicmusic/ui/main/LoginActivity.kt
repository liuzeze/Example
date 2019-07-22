package com.future.electronicmusic.ui.main

import android.app.Activity
import com.future.electronicmusic.R
import com.future.electronicmusic.ui.main.fragment.presenter.LoginContract
import com.future.electronicmusic.ui.main.fragment.presenter.LoginPresenter
import com.future.utilslib.base.BaseActivity
import com.future.utilslib.base.InjectLayout
import com.future.utilslib.utils.FeToast
import com.future.utilslib.view.statu.StatusView
import com.lz.fram.scope.AttachPresenter
import kotlinx.android.synthetic.main.activity_login.*

@InjectLayout(layoutId = R.layout.activity_login, titleName = "登录")
class LoginActivity : BaseActivity(), LoginContract.View {


    @AttachPresenter
    lateinit var mPresenter: LoginPresenter


    override fun initData() {
        val statusView = StatusView.init(ll_login_root)
        statusView.statusBuild?.run {
            setEmptyRetryListener {
                statusView?.showContentView()
            }

        }
        statusView?.showEmptyView("没有数据哦了sdfaf ")

    }

    override fun initLisenter() {
        super.initLisenter()
        bt_login.setOnClickListener {
            val userName = cet_username.textStrign
            val password = cet_password.textStrign
            mPresenter!!.login(userName, password)
        }
        tv_regist.setOnClickListener { FeToast.showToast("注册") }
        swipePanel.setOnFullSwipeListener {
            finish()
            swipePanel.close()
        }
    }

    override fun loginSuccess() {
        setResult(Activity.RESULT_OK)
        onBackPressed()
    }

}

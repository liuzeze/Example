package com.future.electronicmusic.ui.main

import android.content.Intent
import com.future.electronicmusic.R
import com.future.electronicmusic.ui.main.fragment.presenter.LoginContract
import com.future.electronicmusic.ui.main.presenter.LoginPresenter
import com.future.utilslib.base.BaseActivity
import com.future.utilslib.utils.LzToast
import com.lz.fram.base.GraphConfig
import com.lz.fram.scope.AttachPresenter
import kotlinx.android.synthetic.main.activity_login.*
import lz.com.status.StatusView

class LoginActivity : BaseActivity(), LoginContract.View {
    override fun initLayout(graphLayout: GraphConfig) {
        graphLayout.setLayoutId(R.layout.activity_login).setTitleName("登录")
    }


    @AttachPresenter
    lateinit var mPresenter: LoginPresenter


    override fun initViewData() {

        val statusView = StatusView.init(ll_login_root)
        statusView.setStatusUI {
            it?.run {

                setLoadText("急躁记载")
                setNetText("没有网络了aaaaaaaa")
                setEmptyTextClick {
                    statusView.showNetErrorView()
                }

                setNetRetryClick {
                    statusView.showErrorView("错误数据")

                }

                setErrorRetryClick {
                    statusView.showContentView()
                }

            }
        }
        statusView?.showLoadingView()
        statusView.postDelayed(Runnable {
            statusView?.showEmptyView("没有数据哦了sdfaf ")

        }, 2000)
    }

    override fun initLisenter() {
        super.initLisenter()
        bt_login.setOnClickListener {
            val userName = cet_username.textStrig
            val password = cet_password.textStrig
            mPresenter!!.login(userName, password)
        }
        tv_regist.setOnClickListener { LzToast.showToast("注册") }

    }

    override fun loginSuccess() {
//        setResult(Activity.RESULT_OK)
//        onBackPressed()
        startActivity(Intent(mActivity, MyInforActivity::class.java))

    }

}

package com.future.electronicmusic.ui.main.fragment;

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.future.electronicmusic.R
import com.future.electronicmusic.ui.main.LoginActivity
import com.future.utilslib.base.BaseFragment
import com.future.utilslib.dialog.LzDialogUtils
import com.future.utilslib.utils.LzInitUtil
import com.future.utilslib.utils.LzSPUtils
import com.future.utilslib.utils.LzToast
import com.lz.fram.base.InjectLayout
import kotlinx.android.synthetic.main.fragment_my.*


/**
 * @author Administrator
 */

@InjectLayout(
    layoutId = R.layout.fragment_my,
    isShowFragTitle = true,
    titleName = "消息"
)
class MessageFragment : BaseFragment() {
    override fun initData() {


    }

    @SuppressLint("WrongConstant")
    override fun initLisenter() {
        super.initLisenter()
        tv_login.setOnClickListener {
            startActivityForResult(Intent(mContext, LoginActivity::class.java), 100)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        LzToast.showToast("${resultCode}=====${requestCode}")
        tv_login.text = "登录成功"
    }
}
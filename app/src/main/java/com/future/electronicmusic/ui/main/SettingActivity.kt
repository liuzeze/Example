package com.future.electronicmusic.ui.main;

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.future.electronicmusic.R
import com.future.electronicmusic.logic.Constants
import com.future.utilslib.base.BaseActivity
import com.future.utilslib.dialog.LzDialogUtils
import com.future.utilslib.utils.LzInitUtil
import com.future.utilslib.utils.LzSPUtils
import com.lz.fram.base.GraphConfig
import kotlinx.android.synthetic.main.activity_setting.*
import me.ele.uetool.UETool


/**
 * @author Administrator
 */

class SettingActivity : BaseActivity() {
    override fun initLayout(graphLayout: GraphConfig?) {
        graphLayout!!.setLayoutId(R.layout.activity_setting).setTitleName("设置")

    }


    override fun initViewData() {

        tv_show_uetool.switchIsChecked = LzSPUtils.getBoolean(Constants.UETOOL_Check)
        if (!tv_show_uetool.switchIsChecked) {
            UETool.dismissUETMenu()
        } else {
            UETool.showUETMenu()
        }

    }

    @SuppressLint("WrongConstant")
    override fun initLisenter() {
        super.initLisenter()
        tv_change_url.setOnSuperTextViewClickListener {
            LzSPUtils.putBoolean(
                LzInitUtil.APPBASEURL, !LzSPUtils.getBoolean(
                    LzInitUtil.APPBASEURL
                )
            )
            LzDialogUtils.alertConfirmDialog(context, "提示信息", "切换之后必须重启APP", "") {
                val intent = context.packageManager.getLaunchIntentForPackage(context.packageName)
                val restartIntent =
                    PendingIntent.getActivity(context, 0, intent, Intent.FLAG_ACTIVITY_NEW_TASK)
                // 退出程序
                val mgr = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
                mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 500, restartIntent)
                android.os.Process.killProcess(android.os.Process.myPid())
            }.setCancelable(false)
        }
        tv_show_uetool.setSwitchCheckedChangeListener { buttonView, isChecked ->
            if (!isChecked) {
                UETool.dismissUETMenu()
            } else {
                UETool.showUETMenu()
            }
            LzSPUtils.putBoolean(Constants.UETOOL_Check, isChecked)
        }
    }
}
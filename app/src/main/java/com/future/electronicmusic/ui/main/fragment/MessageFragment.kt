package com.future.electronicmusic.ui.main.fragment;

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.future.electronicmusic.ui.main.LoginActivity
import com.future.utilslib.base.BaseFragment
import com.future.utilslib.base.InjectLayout
import com.future.utilslib.dialog.FeDialogUtils
import com.future.utilslib.utils.FeInitUtil
import com.future.utilslib.utils.FeSPUtils
import com.future.utilslib.utils.FeToast
import kotlinx.android.synthetic.main.fragment_my.*


/**
 * @author Administrator
 */

@InjectLayout(
    layoutId = com.future.electronicmusic.R.layout.fragment_my,
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
        tv_change_url.setOnClickListener {
            FeSPUtils.putBoolean(FeInitUtil.APPBASEYRL, !FeSPUtils.getBoolean(FeInitUtil.APPBASEYRL))
            FeDialogUtils.alertConfirmDialog(mContext, "提示信息", "切换之后必须重启APP", "", {

                val intent = mContext.getPackageManager().getLaunchIntentForPackage(mContext.packageName);
                val restartIntent =
                    PendingIntent.getActivity(mContext!!.applicationContext, 0, intent, Intent.FLAG_ACTIVITY_NEW_TASK)
                // 退出程序
                val mgr = mContext!!.getSystemService(Context.ALARM_SERVICE) as AlarmManager
                mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 500, restartIntent)
                android.os.Process.killProcess(android.os.Process.myPid())

            }).setCancelable(false)

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        FeToast.showToast("${resultCode}=====${requestCode}")
        tv_login.text = "登录成功"
    }
}
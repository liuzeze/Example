package com.future.electronicmusic.ui.main.fragment

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.AudioManager
import android.media.MediaPlayer
import android.net.Uri
import com.future.electronicmusic.IMusicService
import com.future.electronicmusic.R
import com.future.utilslib.base.BaseFragment
import com.future.utilslib.base.InjectLayout
import com.future.utilslib.dialog.FeDialogUtils
import com.future.utilslib.utils.FeInitUtil
import com.future.utilslib.utils.FeSPUtils
import com.future.utilslib.utils.FeToast
import com.lzx.starrysky.manager.MediaSessionConnection
import com.lzx.starrysky.manager.MusicManager
import com.lzx.starrysky.model.SongInfo
import kotlinx.android.synthetic.main.fragment_my.*
import tv.danmaku.ijk.media.player.IjkMediaPlayer


/**
 *-----------作者----------日期----------变更内容-----
 *-          刘泽      2019-07-12       创建class
 */
@InjectLayout(layoutId = R.layout.fragment_my, isShowFragTitle = true, titleName = "我的")
class MyFragment : BaseFragment() {


    override fun initData() {
        MediaSessionConnection.getInstance().connect();

    }

    override fun onDestroy() {
        super.onDestroy()
        MediaSessionConnection.getInstance().disconnect()
    }

    @SuppressLint("WrongConstant")
    override fun initLisenter() {
        super.initLisenter()
        tv_login.setOnClickListener {

            //            /storage/emulated/0/Music/cc.mid
            //            /storage/emulated/0/Music/1.3gp
            //            /storage/emulated/0/Music/4.amr
            //            /storage/emulated/0/Music/2.mp3
            //            /storage/emulated/0/Music/3.wav
            //            /storage/emulated/0/Music/5.m4a
            //            /storage/emulated/0/Music/6.wma
            //            /storage/emulated/0/Music/1.ape
//            var player = MediaPlayer()
//            player.setOnPreparedListener { it.start() }
//            player.setDataSource("/storage/emulated/0/Music/cc.mid")
//            player.prepareAsync()
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
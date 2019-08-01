package com.future.electronicmusic.ui.main.fragment

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.future.electronicmusic.R
import com.future.electronicmusic.ui.main.MyInforActivity
import com.future.utilslib.base.BaseFragment
import com.future.utilslib.dialog.LzDialogUtils
import com.future.utilslib.utils.LzInitUtil
import com.future.utilslib.utils.LzSPUtils
import com.future.utilslib.utils.LzToast
import com.future.utilslib.view.floating.FloatingView
import com.future.utilslib.view.floating.FloatingViewConfig
import com.lz.fram.base.InjectLayout
import com.lzx.starrysky.manager.MediaSessionConnection
import kotlinx.android.synthetic.main.fragment_my.*


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


//            http://192.168.60.57:8080/examples/Music/2.mp3

//            var player = MediaPlayer()
//            player.setOnPreparedListener { it.start() }
//            player.setDataSource("http://192.168.60.57:8080/examples/Music/2.mp3")
//            player.prepareAsync()

            startActivity(Intent(mContext,MyInforActivity::class.java))
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        LzToast.showToast("${resultCode}=====${requestCode}")
        tv_login.text = "登录成功"
    }
}
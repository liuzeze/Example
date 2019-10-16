package com.future.electronicmusic.ui.main.fragment

import android.annotation.SuppressLint
import android.content.Intent
import com.future.electronicmusic.R
import com.future.electronicmusic.ui.main.MyInforActivity
import com.future.electronicmusic.ui.main.SettingActivity
import com.future.utilslib.base.BaseFragment
import com.lz.fram.base.GraphConfig
import kotlinx.android.synthetic.main.fragment_my.*


/**
 *-----------作者----------日期----------变更内容-----
 *-          刘泽      2019-07-12       创建class
 */
class MyFragment : BaseFragment() {

    override fun initLayout(graphLayout: GraphConfig) {
        graphLayout.setLayoutId(R.layout.fragment_my).setTitleName("我的").setShowTitle(true)
    }

    override fun initViewData() {

    }

    override fun onDestroy() {
        super.onDestroy()
    }

    @SuppressLint("WrongConstant")
    override fun initLisenter() {
        super.initLisenter()
        tv_my_infor.setOnClickListener {

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

            startActivity(Intent(mContext, MyInforActivity::class.java))

        }

        tv_setting.setOnClickListener {

            startActivity(Intent(mContext, SettingActivity::class.java))
        }

    }


}
package com.future.electronicmusic

import android.app.Application
import com.future.utilslib.utils.LzInitUtil
import com.lzx.starrysky.manager.MusicManager


/**
 *-----------作者----------日期----------变更内容-----
 *-          刘泽      2019-07-10       创建class
 */
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        LzInitUtil.init(this)
        MusicManager.initMusicManager(this);
    }
}
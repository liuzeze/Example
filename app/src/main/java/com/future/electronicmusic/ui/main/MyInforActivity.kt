package com.future.electronicmusic.ui.main;

import android.os.Trace
import com.future.electronicmusic.db.DBInstance
import com.future.electronicmusic.db.bean.PhoneBean
import com.future.electronicmusic.db.db.AppDatabase
import com.future.electronicmusic.ui.main.presenter.MyInforContract
import com.future.electronicmusic.ui.main.presenter.MyInforPresenter
import com.future.utilslib.base.BaseActivity
import com.google.android.exoplayer2.util.TraceUtil
import com.lz.fram.scope.AttachPresenter
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.*


/**
 *-----------作者----------日期----------变更内容-----
 *-          刘泽      2019-07-12       创建class
 */

class MyInforActivity : BaseActivity(), MyInforContract.View {

    @AttachPresenter
    var mPresenter: MyInforPresenter? = null

    override fun initData() {
        Thread(Runnable {
            val mPhones = ArrayList<PhoneBean>()
            DBInstance.getInstance().getPhoneDao().insertAll(mPhones)
            val phoneCount = DBInstance.getInstance().getPhoneDao().phoneCount

        }).start()

        runBlocking {
            val launch = GlobalScope.launch {
              print("dklsajdgjkhsjkdgh")

            }
            launch.join()
        }

    }


    override fun getNewsListSuccess(s: String) {

    }
}
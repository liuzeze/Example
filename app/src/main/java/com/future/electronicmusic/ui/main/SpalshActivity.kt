package com.future.electronicmusic.ui.main


import android.content.Intent
import android.os.CountDownTimer
import android.view.View
import com.future.electronicmusic.R
import com.future.utilslib.base.BaseActivity
import com.gyf.immersionbar.BarHide
import com.lz.fram.base.GraphConfig
import kotlinx.android.synthetic.main.activity_spalsh.*

/**
 *-----------作者----------日期----------变更内容-----
 *-          刘泽      2019-07-12       创建class
 */
class SpalshActivity : BaseActivity() {
    var timerSikp: CountDownTimer? = null
    override fun initLayout(graphLayout: GraphConfig?) {
        graphLayout!!.setLayoutId(R.layout.activity_spalsh).setShowTitle(false)

    }


    override fun initViewData() {
        mImmersionBar
            .hideBar(BarHide.FLAG_HIDE_STATUS_BAR)
            .init()
        startTime(5)
    }

    override fun initLisenter() {
        super.initLisenter()
        tv_skip.setOnClickListener(View.OnClickListener {
            toMainActivity()
        })
    }

    /**
     * 开始定时
     */
    private fun startTime(time: Int) {
        timerSikp = object : CountDownTimer((time * 1000).toLong(), 1000) {
            override fun onTick(millisUntilFinished: Long) {
                tv_skip.setText("${millisUntilFinished / 1000}跳过")

            }

            override fun onFinish() {
                tv_skip.setText("1跳过")
                toMainActivity()

            }
        }

        timerSikp?.start()
    }

    @Synchronized
    private fun toMainActivity() {
        timerSikp?.cancel()
        timerSikp = null
        startActivity(Intent(mActivity, MainActivity::class.java))
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        timerSikp?.cancel()
        timerSikp = null

    }
}

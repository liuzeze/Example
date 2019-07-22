package com.future.electronicmusic.ui.main


import android.content.Intent
import android.os.CountDownTimer
import android.view.View
import com.future.electronicmusic.R
import com.future.utilslib.base.BaseActivity
import com.future.utilslib.base.InjectLayout
import com.gyf.immersionbar.BarHide
import com.gyf.immersionbar.ImmersionBar
import kotlinx.android.synthetic.main.activity_spalsh.*

/**
 *-----------作者----------日期----------变更内容-----
 *-          刘泽      2019-07-12       创建class
 */
@InjectLayout(layoutId = R.layout.activity_spalsh, isShowActTitle = false)
class SpalshActivity : BaseActivity() {
    var timerSikp: CountDownTimer? = null

    override fun initBar() {
        ImmersionBar
            .with(this)
            .hideBar(BarHide.FLAG_HIDE_STATUS_BAR)
            .init()
    }

    override fun initData() {
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

package com.future.electronicmusic.ui.main

import android.graphics.Color
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import com.flyco.tablayout.listener.CustomTabEntity
import com.flyco.tablayout.listener.OnTabSelectListener
import com.future.electronicmusic.R
import com.future.electronicmusic.bean.MainTabBean
import com.future.electronicmusic.ui.main.fragment.HomeFragment
import com.future.electronicmusic.ui.main.fragment.MessageFragment
import com.future.electronicmusic.ui.main.fragment.MyFragment
import com.future.electronicmusic.ui.main.fragment.OrderFragment
import com.future.utilslib.base.BaseActivity
import com.future.utilslib.utils.LzFragmentUtils
import com.future.utilslib.utils.LzToast
import com.gyf.immersionbar.ImmersionBar
import com.lz.fram.base.GraphConfig
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

/**
 *-----------作者----------日期----------变更内容-----
 *-          刘泽      2019-07-12       创建class
 */
class MainActivity : BaseActivity() {
    val mFragmentList = arrayListOf<Fragment>()
    private val mTitles = arrayOf("首页", "消息", "订单", "更多")
    private val mIconUnselectIds = intArrayOf(
        R.mipmap.ic_launcher,
        R.mipmap.ic_launcher,
        R.mipmap.ic_launcher,
        R.mipmap.ic_launcher
    )
    private val mIconSelectIds = intArrayOf(
        R.mipmap.ic_launcher,
        R.mipmap.ic_launcher,
        R.mipmap.ic_launcher,
        R.mipmap.ic_launcher
    )
    private val mTabEntities = ArrayList<CustomTabEntity>()
    override fun initLayout(graphLayout: GraphConfig) {
        graphLayout.setLayoutId(R.layout.activity_main).setShowTitle(false)

    }


    override fun initViewData() {
        mSwipePanel.setLeftEnabled(false)
        mTabEntities.clear()
        for (i in mTitles.indices) {
            mTabEntities.add(MainTabBean(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]))
        }
        mFragmentList.clear()
        mFragmentList.add(HomeFragment())
        mFragmentList.add(MessageFragment())
        mFragmentList.add(OrderFragment())
        mFragmentList.add(MyFragment())

        main_tab.setTabData(mTabEntities)
        main_tab.showDot(2)
        main_tab.showMsg(1, 22)
        main_tab.setOnTabSelectListener(object : OnTabSelectListener {
            override fun onTabSelect(position: Int) {
                LzFragmentUtils.showHide(position, mFragmentList)
                if (position == 0) {
                    mImmersionBar
                        .fitsSystemWindows(true)
                        .statusBarColor(R.color.common_app_transparency_b95)
                } else {
                    mImmersionBar
                        .fitsSystemWindows(false)
                        .statusBarColor(R.color.colorPrimary)
                }
                mImmersionBar.init()
            }

            override fun onTabReselect(position: Int) {

            }
        })

        LzFragmentUtils.add(supportFragmentManager, mFragmentList, R.id.main_pager, 0)
    }

    /**
     * 拦截返回事件
     */
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return LzToast.doubleClickExit() && super.onKeyDown(keyCode, event)
        }
        return super.onKeyDown(keyCode, event)
    }

}

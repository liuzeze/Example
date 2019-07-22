package com.future.electronicmusic.bean

import com.flyco.tablayout.listener.CustomTabEntity

/**
 *-----------作者----------日期----------变更内容-----
 *-          刘泽      2019-07-12       创建class
 */
class MainTabBean : CustomTabEntity {
    var title: String = ""
    var selectedIcon: Int = 0
    var unSelectedIcon: Int = 0

    constructor(title: String, selectedIcon: Int, unSelectedIcon: Int) {
        this.title = title
        this.selectedIcon = selectedIcon
        this.unSelectedIcon = unSelectedIcon
    }

    override fun getTabUnselectedIcon(): Int {

        return unSelectedIcon
    }

    override fun getTabSelectedIcon(): Int {
        return selectedIcon
    }

    override fun getTabTitle(): String {
        return title
    }

}
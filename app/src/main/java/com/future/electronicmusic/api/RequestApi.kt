package com.future.electronicmusic.api


import com.future.utilslib.utils.FeInitUtil
import com.lz.fram.base.LpLoadDialog
import com.lz.fram.net.RxRequestUtils
import com.lz.fram.observer.Transformer
import io.reactivex.Observable

/**
 * 网络请求
 */
class RequestApi {

    private val mLpLoadDialog: LpLoadDialog

    init {
        mLpLoadDialog = LpLoadDialog(FeInitUtil.getApp())
    }

    fun getNewLists(category: String, time: String): Observable<String> {


        return RxRequestUtils
            .create(ApiService::class.java)
            .getNewsArticle2(category, time)
            .compose(Transformer.switchSchedulersObser(mLpLoadDialog))


    }

    fun login(userName: String, password: String): Observable<String> {

        return RxRequestUtils
            .create(ApiService::class.java)
            .login(userName, password)
            .compose(Transformer.switchSchedulersObser(mLpLoadDialog))


    }


}

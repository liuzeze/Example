package com.future.electronicmusic.api


import com.future.httplib.http.HttpLoadDialog
import com.future.utilslib.utils.LzInitUtil
import com.lz.httplib.RxRequestUtils
import com.lz.httplib.transformer.Transformer
import io.reactivex.Observable

/**
 * 网络请求
 */
class RequestApi {

    private val mLpLoadDialog: HttpLoadDialog

    init {
        mLpLoadDialog = HttpLoadDialog(LzInitUtil.getApp())
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
            .compose(Transformer.switchSchedulersObser())



    }


}

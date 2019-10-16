package com.future.electronicmusic.api


import com.future.utilslib.http.Transformer
import com.lz.httplib.RxHttp
import io.reactivex.Observable

/**
 * 网络请求
 */
class RequestApi {


    fun getNewLists(category: String, time: String): Observable<String> {
        return RxHttp
            .create(ApiService::class.java)
            .getNewsArticle2(category, time)
            .compose(Transformer.switchSchedulersStr())
    }

    fun login(userName: String, password: String): Observable<String> {
        return RxHttp
            .create(ApiService::class.java)
            .login(userName, password)
            .compose(Transformer.switchSchedulersStr())



    }


}

package com.future.electronicmusic.ui.main.fragment.presenter

import com.future.electronicmusic.api.RequestApi
import com.future.utilslib.http.CommonObserver
import com.future.utilslib.http.Transformer
import com.lz.fram.base.RxPresenter
import com.lz.httplib.RxHttp

/**
 * -----------作者----------日期----------变更内容-----
 * -          刘泽      2019-04-26       创建class
 */
class HomePresenter : RxPresenter<HomeContract.View>(), HomeContract.Presenter {

    private val mRequestApi: RequestApi

    init {
        mRequestApi = RequestApi()
    }

    override fun getNewLists(type: String) {
//        RxHttp
//            .create()
//            .get("url")
//            .compose(Transformer.switchSchedulersArray(DataBean::class.java))
//            .subscribe(object : CommonObserver<List<DataBean>>(mBaseView) {
//                fun onNext(dataBeans: List<DataBean>) {
//                    System.out.println(dataBeans[0].getDesc())
//
//                }
//
//            })
        val commonSubscriber = mRequestApi
            .getNewLists(type, "")
            .subscribeWith(object : CommonObserver<String>(mBaseView) {

                override fun onNext(s: String) {
                    mBaseView.getNewsListSuccess(s)
                }
            })
    }

}

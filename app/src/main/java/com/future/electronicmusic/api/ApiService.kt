package com.future.electronicmusic.api


import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * API 管理器服务
 */
interface ApiService {


    @GET("news/feed/v62/?iid=12507202490&device_id=37487219424&refer=1&count=20&aid=13")
    fun getNewsArticle2(
        @Query("category") category: String,
        @Query("max_behot_time") maxBehotTime: String
    ): Observable<String>


    @GET("http://lf.snssdk.com/api/news/feed/v62/?iid=12507202490&device_id=37487219424&refer=1&count=20&aid=13")
    fun login(
        @Query("userName") userName: String,
        @Query("password") password: String
    ): Observable<String>


}


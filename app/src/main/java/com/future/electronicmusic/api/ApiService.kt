package com.future.electronicmusic.api


import com.future.electronicmusic.logic.Constants
import com.future.electronicmusic.logic.FeUrl
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 *接口管理
 */
interface ApiService {


    @GET(FeUrl.GETNEWSARTICLE2)
    fun getNewsArticle2(
        @Query("category") category: String,
        @Query("max_behot_time") maxBehotTime: String
    ): Observable<String>


    @GET(FeUrl.GETNEWSARTICLE2)
    fun login(
        @Query("userName") userName: String,
        @Query("password") password: String
    ): Observable<String>


}


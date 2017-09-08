package com.lucasgomes.gitrepositories

import retrofit2.http.GET
import rx.Observable
import javax.security.auth.callback.Callback

/**
 * Created by Lucas Gomes on 08/09/2017.
 */
interface GitApiDef{
    @GET("users/lucasgomes-eti/repos")
    fun listRepositories() : Observable<List<Repository>>
}
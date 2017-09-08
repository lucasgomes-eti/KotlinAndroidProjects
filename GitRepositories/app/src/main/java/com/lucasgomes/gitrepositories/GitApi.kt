package com.lucasgomes.gitrepositories

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.Observable

/**
 * Created by Lucas Gomes on 08/09/2017.
 */
class GitApi{
    val service : GitApiDef

    init {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)

        val gson = GsonBuilder().setLenient().create()

        val retrofit = Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient.build())
                .build()

        service = retrofit.create<GitApiDef>(GitApiDef::class.java)
    }

    fun loadRepositories() : Observable<RepositoryModel>?{
        return service.listRepositories()
                .flatMap { repositoriesResults -> Observable.from(repositoriesResults.toList()) }
                .map { repository -> RepositoryModel(repository.id, repository.name, repository.description, repository.language) }
    }
}
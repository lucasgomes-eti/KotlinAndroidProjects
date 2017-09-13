package com.lucasgomes.personagens.services

import com.google.gson.GsonBuilder
import com.lucasgomes.personagens.models.Personagem
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Lucas Gomes on 13/09/2017.
 */
class RestService{

    val restService : RestDefinition

    init {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)

        var gson = GsonBuilder().setLenient().create()

        val retrofit = Retrofit.Builder()
                .baseUrl("http://52.67.109.13:3003/api/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient.build())
                .build()

        restService = retrofit.create<RestDefinition>(RestDefinition::class.java)
    }

    fun loadPersonagens() : Observable<Personagem> {
        return restService.getListPersonagens()
                .flatMap { pResult -> Observable.fromIterable(pResult.toList()) }
                .map { p ->  Personagem(p.FotoUrl, p.Nome) }
    }
}
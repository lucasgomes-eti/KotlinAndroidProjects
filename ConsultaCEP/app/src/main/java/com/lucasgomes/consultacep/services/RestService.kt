package com.lucasgomes.consultacep.services

import com.google.gson.GsonBuilder
import com.lucasgomes.consultacep.models.Endereco
import io.reactivex.Observable
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Lucas Gomes on 11/09/2017.
 */
class RestService{
    val service : RestDefinition

    init {
        val httpClient = OkHttpClient.Builder()

        val gson = GsonBuilder().setLenient().create()

        val retrofit = Retrofit.Builder()
                .baseUrl("https://viacep.com.br/ws/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient.build())
                .build()

        service = retrofit.create<RestDefinition>(RestDefinition::class.java)
    }

    fun loadEndereco(cep: String): Observable<Endereco> {
        return service.GetCep(cep)
    }
}
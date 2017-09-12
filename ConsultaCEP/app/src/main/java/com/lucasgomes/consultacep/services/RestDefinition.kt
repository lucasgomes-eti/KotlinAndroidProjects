package com.lucasgomes.consultacep.services

import com.lucasgomes.consultacep.models.Endereco
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Lucas Gomes on 11/09/2017.
 */
interface RestDefinition{
    @GET("{cep}/json")
    fun GetCep(@Path("cep") cep : String) : Observable<Endereco>
}
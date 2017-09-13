package com.lucasgomes.personagens.services

import com.lucasgomes.personagens.models.Personagem
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * Created by Lucas Gomes on 13/09/2017.
 */
interface RestDefinition{
    @GET("Personagens")
    fun getListPersonagens() : Observable<List<Personagem>>
}
package com.lucas.filmesstarwars

import retrofit2.http.GET
import retrofit2.http.Path
import rx.Observable

/**
 * Created by lucas on 07/09/2017.
 */
interface StarWarsApiDef{
    @GET("films")
    fun listMovies() : Observable<FilmResult>

    @GET("people/{personId}")
    fun loadPerson(@Path("personId") personId : String) : Observable<Person>
}
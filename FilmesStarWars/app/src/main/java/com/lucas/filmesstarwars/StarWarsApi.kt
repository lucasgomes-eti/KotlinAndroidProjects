package com.lucas.filmesstarwars

import android.net.Uri
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.Observable

/**
 * Created by lucas on 07/09/2017.
 */
class StarWarsApi{
    val service : StarWarsApiDef

    init {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)

        val gson = GsonBuilder().setLenient().create()

        val retrofit = Retrofit.Builder()
                .baseUrl("http://swapi.co/api/")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient.build())
                .build()

        service = retrofit.create<StarWarsApiDef>(StarWarsApiDef::class.java)

    }

    fun loadMovies() : Observable<Movie>?{
        return service.listMovies()
                .flatMap { filmResults -> Observable.from(filmResults.results) }
                .map { film -> Movie(film.title, film.episodeId, ArrayList<Character>()) }
    }

    var peopleCache = mutableMapOf<String, Person>()

    fun loadMoviesFull() : Observable<Movie>?{
        return service.listMovies()
                .flatMap { filmResults -> Observable.from(filmResults.results) }
                .flatMap { film ->
                    Observable.zip(
                            Observable.just(Movie(film.title, film.episodeId, ArrayList<Character>())),
                            Observable.from(film.personsUrl)
                                    .flatMap { personUrl ->
                                        Observable.concat(
                                                getCache(personUrl),
                                                service.loadPerson(Uri.parse(personUrl).lastPathSegment).doOnNext { person -> peopleCache.put(personUrl, person) }
                                        ).first()
                                        }
                                    .map { person -> Character(person!!.name, person.gender) }
                                    .toList(),
                            {
                                movie, characters -> movie.characters.addAll(characters)
                                movie
                            }
                    )
                }
    }

    private fun getCache(personUrl : String) : Observable<Person?>?{
        return Observable.from(peopleCache.keys)
                .filter { key -> key == personUrl }
                .map { key -> peopleCache[key] }
    }
}
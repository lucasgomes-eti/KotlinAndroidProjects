package com.lucas.filmesstarwars

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListAdapter
import android.widget.ListView
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class FilmsListActivity : AppCompatActivity() {

    lateinit var listView : ListView
    lateinit var movieAdapter : ArrayAdapter<String>
    var movies = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        listView = ListView(this)
        this.setContentView(listView)
        movieAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, movies)
        listView.adapter = movieAdapter

        val api = StarWarsApi()
        api.loadMovies()
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe (
                        { (title, episodeId) ->
                            movies.add("$episodeId - $title")
                        },
                        { e ->
                            e?.printStackTrace()
                        },
                        {
                            movieAdapter?.notifyDataSetChanged()
                        }
                )

    }
}

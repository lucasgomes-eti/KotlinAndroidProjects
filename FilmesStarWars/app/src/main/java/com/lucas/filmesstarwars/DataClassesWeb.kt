package com.lucas.filmesstarwars

import com.google.gson.annotations.SerializedName

/**
 * Created by lucas on 07/09/2017.
 */
data class FilmResult(
        val results : List<Film>
)

data class Film(
        val title : String,
        @SerializedName("episode_id")
        val episodeId : Int,
        @SerializedName("characters")
        val personsUrl : List<String>
)

data class Person(
        val name : String,
        val gender : String
)
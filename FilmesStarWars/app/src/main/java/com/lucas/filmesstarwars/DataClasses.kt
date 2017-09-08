package com.lucas.filmesstarwars

/**
 * Created by lucas on 07/09/2017.
 */
data class Movie (
        val title : String,
        val episodeId : Int,
        val characters : MutableList<Character>
)

data class Character(
        val name : String,
        val gender : String
){
    override fun toString(): String {
        return "$name / $gender"
    }
}
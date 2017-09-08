package com.lucasgomes.gitrepositories

import com.google.gson.annotations.SerializedName

/**
 * Created by Lucas Gomes on 08/09/2017.
 */

data class Repository (
        @SerializedName("id")
        val id : Int,
        @SerializedName("name")
        val name : String,
        @SerializedName("description")
        val description : String,
        @SerializedName("language")
        val language : String
)
package com.lucasgomes.consultacep.models

import com.google.gson.annotations.SerializedName

/**
 * Created by Lucas Gomes on 11/09/2017.
 */
data class Endereco(
        @SerializedName("cep")
        val Cep : String,
        @SerializedName("logradouro")
        val Logradouro : String,
        @SerializedName("complemento")
        val Complemento : String,
        @SerializedName("bairro")
        val Bairro : String,
        @SerializedName("localidade")
        val Localidade : String,
        @SerializedName("uf")
        val Uf : String,
        @SerializedName("ibge")
        val Ibge : Int
)
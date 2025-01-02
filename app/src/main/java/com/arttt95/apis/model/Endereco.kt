package com.arttt95.apis.model

import com.google.gson.annotations.SerializedName

data class Endereco(
    val bairro: String,
    @SerializedName("cep")
    val ajustado: String,
    val complemento: String,
    val ddd: String,
    val estado: String,
    val gia: String,
    val ibge: String,
    val localidade: String,
    val logradouro: String,
    val regiao: String,
    val siafi: String,
    val uf: String,
    val unidade: String
)
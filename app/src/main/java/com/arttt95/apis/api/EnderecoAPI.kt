package com.arttt95.apis.api

import com.arttt95.apis.model.Endereco
import retrofit2.Response
import retrofit2.http.GET

interface EnderecoAPI {

    // GET, POST, PUT, PATCH, DELETE
    @GET("ws/13013051/json")
    suspend fun recuperarEndereco() : Response<Endereco>

}

// https://viacep.com.br/ws/13013051/json
package com.arttt95.apis.api

import com.arttt95.apis.model.Endereco
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface EnderecoAPI {

    // GET, POST, PUT, PATCH, DELETE
    @GET("ws/{cep}/{formato}")
    suspend fun recuperarEndereco(

        @Path("cep") cep: String,
        @Path("formato") formato: String

    ) : Response<Endereco>

}

// https://viacep.com.br/ws/13013051/json
package com.arttt95.apis.api

import com.arttt95.apis.model.Postagem
import retrofit2.Response
import retrofit2.http.GET

interface PostagemAPI {

    @GET("posts")
    suspend fun recuperarPostagens() : Response<List<Postagem> >

}
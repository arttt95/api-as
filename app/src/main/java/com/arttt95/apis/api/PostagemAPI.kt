package com.arttt95.apis.api

import com.arttt95.apis.model.Comentario
import com.arttt95.apis.model.Postagem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PostagemAPI {

    @GET("posts")
    suspend fun recuperarPostagens() : Response<List<Postagem> >

    @GET("posts/{id}")
    suspend fun recuperarPostagemUnica(

        @Path("id") id: Int

    ) : Response<Postagem>

    @GET("posts/{id}/comments")
    suspend fun recuperarComentariosParaPostagem(

        @Path("id") id: Int

    ) : Response<List<Comentario>>

}
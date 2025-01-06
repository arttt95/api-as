package com.arttt95.apis.api

import com.arttt95.apis.model.Comentario
import com.arttt95.apis.model.Postagem
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface PostagemAPI {

    @GET("posts")
    suspend fun recuperarPostagens() : Response<List<Postagem>>

    @GET("posts/{id}")
    suspend fun recuperarPostagemUnica(

        @Path("id") id: Int

    ) : Response<Postagem>

    @GET("posts/{id}/comments")
    suspend fun recuperarComentariosParaPostagem(

        @Path("id") id: Int

    ) : Response<List<Comentario>>

    @GET("comments") // comments?postId={param}&idComentario={param}
    suspend fun recuperarComentariosParaPostagemQuery(

        @Query("postId") postId: Int,
        @Query("idComentario") idComentario: String

    ) : Response<List<Comentario>>

    /*@GET("pesquisa/{marca}/{modelo}") // Path
    suspend fun pesquisaPath(

        @Path("marca") marca: String,
        @Path("modelo") modelo: String

    ) : Response<List<Comentario>>

    @GET("pesquisa") // Query
    // -> pesquisa?marca={param}&modelo={param}
    // -> pesquisa?marca=&modelo={param}
    suspend fun pesquisaQuery(

        @Query("marca") marca: String,
        @Query("modelo") modelo: String

    ) : Response<List<Comentario>>*/ // Exemplos de Path e Query

    @POST("posts")
    suspend fun salvarPostagem(
        @Body postagem: Postagem
    ) : Response<Postagem>

    @FormUrlEncoded
    @POST("posts")
    suspend fun salvarPostagemFormulario(
        @Field("userId") userId: Int,
        @Field("id") id: Int,
        @Field("title") title: String,
        @Field("body") body: String
    ) : Response<Postagem>

    @PUT("posts/{id}")
    suspend fun atualizarPostagemPut( // Atualização total
        @Path("id") id: Int,
        @Body postagem: Postagem
    ) : Response<Postagem>

    @PATCH("posts/{id}")
    suspend fun atualizarPostagemPatch( // Atualização parcial
        @Path("id") id: Int,
        @Body postagem: Postagem
    ) : Response<Postagem>

    @DELETE("posts/{id}")
    suspend fun removerPostagem( // Remover Postagem
        @Path("id") id : Int
    ) : Response<Unit>

}
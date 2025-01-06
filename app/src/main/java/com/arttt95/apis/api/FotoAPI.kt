package com.arttt95.apis.api

import com.arttt95.apis.model.Foto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface FotoAPI {

    @GET("photos/{id}")
    suspend fun recuperarFotoUnica(
        @Path("id") id: Int
    ) : Response<Foto>

}
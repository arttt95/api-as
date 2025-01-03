package com.arttt95.apis.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitHelper {

    companion object {
        val apiViaCep = Retrofit.Builder()
            .baseUrl("https://viacep.com.br/ws/")
            .addConverterFactory( GsonConverterFactory.create() ) // Json ou XML
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory( GsonConverterFactory.create() ) // Json ou XML
            .build()
    }

}

// https://viacep.com.br/ws/13013051/json
// https://jsonplaceholder.typicode.com/
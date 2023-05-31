package com.example.chatwiseuktask.api

import com.example.chatwiseuktask.model.Image
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("photos")
    suspend fun getImages() : Response<Image>

}
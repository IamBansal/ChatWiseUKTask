package com.example.chatwiseuktask.repository

import com.example.chatwiseuktask.api.RetrofitInstance


class ImageRepository {

    suspend fun getImages() = RetrofitInstance.api.getImages()

}
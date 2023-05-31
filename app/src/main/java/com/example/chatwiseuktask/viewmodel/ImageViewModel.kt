package com.example.chatwiseuktask.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatwiseuktask.model.Image
import com.example.chatwiseuktask.repository.ImageRepository
import com.example.myapplication.utils.Resource
import kotlinx.coroutines.launch

class ImageViewModel(private val repository: ImageRepository) : ViewModel(){

    val image: MutableLiveData<Resource<Image>> = MutableLiveData()

    fun getImages() = viewModelScope.launch {
        getSafeImages()
    }

    private suspend fun getSafeImages() {

        image.postValue(Resource.Loading())
        try {
            val response = repository.getImages()
            Log.d("response", response.toString())
            if (response.isSuccessful) {
                response.body()?.let {
                    image.postValue(Resource.Success(it))
                }
            } else {
                image.postValue(Resource.Error(response.message()))
            }
        } catch (t: Throwable) {
            image.postValue(Resource.Error(t.message.toString()))
        }

    }

}
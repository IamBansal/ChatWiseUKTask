package com.example.chatwiseuktask.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.chatwiseuktask.repository.ImageRepository

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(
    private var repository: ImageRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ImageViewModel(repository) as T
    }

}

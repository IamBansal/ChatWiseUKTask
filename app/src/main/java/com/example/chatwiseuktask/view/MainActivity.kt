package com.example.chatwiseuktask.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.chatwiseuktask.R
import com.example.chatwiseuktask.repository.ImageRepository
import com.example.chatwiseuktask.viewmodel.ImageViewModel
import com.example.chatwiseuktask.viewmodel.ViewModelFactory

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: ImageViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repository = ImageRepository()
        val factory = ViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[ImageViewModel::class.java]

    }
}
package com.example.chatwiseuktask.view.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chatwiseuktask.adapter.ImageAdapter
import com.example.chatwiseuktask.databinding.FragmentMainBinding
import com.example.chatwiseuktask.model.ImageItem
import com.example.chatwiseuktask.view.MainActivity
import com.example.chatwiseuktask.viewmodel.ImageViewModel
import com.example.myapplication.utils.Resource

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private lateinit var viewModel: ImageViewModel
    private lateinit var imageAdapter: ImageAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().navigateUp()
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(callback)

        viewModel = (activity as MainActivity).viewModel
        getImages()

    }

    private fun getImages() {

        binding.progressBar.visibility = View.VISIBLE
        viewModel.getImages()
        viewModel.image.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE

                    //Can adjust the response list size to be shown in recycler view.
                    setupRecyclerView(response.data!!.subList(0, 20))
                }
                is Resource.Error -> {
                    binding.progressBar.visibility = View.GONE
                    Log.d("Error", response.message.toString())
                }
                is Resource.Loading -> {
                    Log.d("Loading", "in loading state")
                }
            }
        }

    }

    private fun setupRecyclerView(imagesList: List<ImageItem>) {
        binding.rvImages.apply {
            imageAdapter = ImageAdapter()
            adapter = imageAdapter
            layoutManager = LinearLayoutManager(context)
            imageAdapter.differ.submitList(imagesList)
        }
    }
}
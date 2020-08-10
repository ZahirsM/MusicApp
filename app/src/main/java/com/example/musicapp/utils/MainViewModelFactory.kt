package com.example.musicapp.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.musicapp.data.repository.MusicRepo
import com.example.musicapp.ui.viewmodel.HomeViewModel

// creating function that whatever it takes turns it into null ViewModel
// and creates a modelClass type HomeViewModel(musicRepo)
@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(private val musicRepo: MusicRepo) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>) : T = HomeViewModel(
        musicRepo
    ) as T
}



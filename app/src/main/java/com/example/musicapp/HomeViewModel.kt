package com.example.musicapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicapp.model.MusicResponse
import kotlinx.coroutines.launch

class HomeViewModel(private val repo: MusicRepo) : ViewModel() {

    private val _artist = MutableLiveData<MusicResponse>()
    val artistObservable: LiveData<MusicResponse>
        get() = _artist


    fun fetchArtists(page: Int = 0, pageSize: Int, country: String) {
        viewModelScope.launch {
            repo.getArtists(page, pageSize, country).let {
                when(it.code() == 200) {
                    true -> it.body()?.let { musicResponse ->  _artist.postValue(musicResponse) }
                    else -> {}
                }
                return@let
            }
        }
    }
}
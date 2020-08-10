package com.example.musicapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicapp.data.repository.MusicRepo
import com.example.musicapp.data.model.MusicResponse
import com.example.musicapp.utils.Resource
import kotlinx.coroutines.launch

class HomeViewModel(private val repo: MusicRepo) : ViewModel() {

    //MUTABLE LIVE DATA IS LIFECYCLE AWARE
    //creating list of artist with Adding+Removing
    private val _artistResponse = MutableLiveData<Resource<MusicResponse>>()

    //creating artistObservable of the list of artist
    val artistObservable: LiveData<Resource<MusicResponse>>
        get() = _artistResponse

    // This will load first on instance creation
    init {
        // calling load function
        load()
    }

    // creating to fetch artists
    fun fetchArtists(page: Int, pageSize: Int, country: String) {
        setStateToLoading(_artistResponse)
        // launching coroutine to get the next page of artists when the code response code is 200
        viewModelScope.launch {
            val response = if (page != 0)
                repo.getArtists(page, pageSize, country)
            else
                repo.getArtists(0, 10, "mx")
            repo.getArtists(page, pageSize, country).let { response ->
                when (response.code() == 200) {
                    // if true, add the body of the response to the _artist list
                    true -> response.body()
                        ?.let { musicResponse -> _artistResponse.postValue(musicResponse) }
                    else -> {
                    }
                }
                // return the scope of let
                return@let
            }
        }
    }

    // creating function load
    fun load() {
        _artistResponse.value?.data?.message?.body?.data[index].artist.let { artistResponse ->
            val name = artistResponse?.artistName
            val rating = artistResponse?.artistRating
            val country = artistResponse?.artistCountry
            val id = artistResponse?.artistId
        }
        fetchArtists()
    }
}

private fun <T> setStateToLoading(mutableLiveData: MutableLiveData<Resource<T>>) {
    mutableLiveData.value = Resource.loading()
}
}
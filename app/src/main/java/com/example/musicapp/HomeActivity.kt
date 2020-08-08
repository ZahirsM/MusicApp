package com.example.musicapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels

class HomeActivity : AppCompatActivity() {

    private val factory by lazy { MainViewModelFactory(MusicRepo(MusicServiceImpl())) }
    private val vm by viewModels<HomeViewModel> { factory }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        vm.fetchArtists(pageSize = 10, country = "it")
    }
}
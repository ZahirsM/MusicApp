package com.example.musicapp.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import com.example.musicapp.utils.MainViewModelFactory
import com.example.musicapp.R
import com.example.musicapp.data.model.Artist
import com.example.musicapp.data.model.Message
import com.example.musicapp.data.repository.MusicRepo
import com.example.musicapp.databinding.ActivityMainBinding
import com.example.musicapp.ui.adapter.ArtistAdapter
import com.example.musicapp.ui.viewmodel.HomeViewModel
import com.example.musicapp.utils.RecyclerViewLoadMoreScroll
import com.example.musicapp.utils.Status
import com.example.musicapp.utils.TYPE

class HomeActivity : AppCompatActivity() {

    // creating private lazy factory immutable
    // access modifier var/val (factory : MainViewModelFactory) :
    private val factory by lazy {
        // using factory creathor method
        MainViewModelFactory(
            // from the musicXmatch repo
            MusicRepo()
        )
    }
    // creating viewmodel of type HomeViewModel
    // using the MainViewModelFactory as viewmodelprovider
    private val vm by viewModels<HomeViewModel> { factory }
    // creating list for Adding+Removing Artist objects
    private val artistList = mutableListOf<Artist>()
    // creating binding for activity_main.xml
    private lateinit var binding: ActivityMainBinding
    private val artistAdapter by lazy { ArtistAdapter() } // IMIGHT WANT TO PASS A LISTENER HERE
    private val gridLayoutManager by lazy {
        GridLayoutManager(this, 2).apply {
            spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return when (artistAdapter.getItemViewType(position)) {
                        TYPE.ITEM.ordinal -> 1
                        else -> 2
                    }
                }
            }
        }
    }

    private val scrollListener by lazy {
        RecyclerViewLoadMoreScroll(gridLayoutManager).apply {
            setOnLoadMoreListener(this@HomeActivity)
        }
    }

    // creating function onCreate
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // initializing binding by inflating with LayoutInflate with HomeActivity as context
        ActivityMainBinding.inflate(LayoutInflater.from(this)).also {
            // seting binding to it for more additions
            binding = it
            // setting the content view to the route of it
            setContentView(it.root)
        }
        // calling function setUpObservers()
        setUpObservers()

    }

    private fun initRV() {
        binding.rvArtist.apply {
            layoutManager = gridLayoutManager
            adapter = artistAdapter
            setHasFixedSize(true)
            addOnScrollListener(scrollListener)
        }
    }

    // creating observer with the artistObservable
    private fun setUpObservers() {
        vm.artistObservable.observe(this, Observer {
            when(it.){
                Status.LOADING -> artistAdapter.addLoader()
                Status.SUCCESS -> {
                    resource.message?
                }
            }
        })
    }

    //creating adding to Artist list function
    private fun updateMusicList(artist: List<Artist>, clear: Boolean = false) {
        if (clear) this.artistList.clear()
        this.artistList.addAll(artist)
    }

}
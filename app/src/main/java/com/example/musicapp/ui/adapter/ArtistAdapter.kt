package com.example.musicapp.ui.adapter

import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.musicapp.data.model.Artist
import com.example.musicapp.databinding.ItemArtistBinding
import com.example.musicapp.databinding.LoaderItemScrollBinding
import com.example.musicapp.utils.TYPE
import com.example.musicapp.utils.listen

// creating my artistAdapter taking a listener param and returning a Viewholder for my recycler view
class ArtistAdapter(private val listener: OnArtistSelectedListener? = null) :
    RecyclerView.Adapter<ArtistAdapter.ArtistViewHolder>() {
    // creating list im going to be looping from
    private val artistList = mutableListOf<Artist>()
    // creating the loader for the artirst view
    private val loader by lazy { Artist().apply { viewType = TYPE.LOADER } }

    // IMPLEMENTED METHODS -  creating view holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistViewHolder {
        // inflating the item
        val itemBinding = ItemArtistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        // inflating the loader
        val loaderBinding = LoaderItemScrollBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        // return whether the loader should show or the next item
        return when(viewType){
            TYPE.LOADER.ordinal -> ArtistViewHolder(loaderBinding)
            else -> ArtistViewHolder(itemBinding).listen{position, _ ->
                listener?.artistSelected(artistList[position])
            }
        }
    }
    // creating function to get the size of the artistList
    override fun getItemCount() = artistList.size
    // creating function to load the Artist object in the current position to the holder of the view
    override fun onBindViewHolder(holder: ArtistViewHolder, position: Int) {
        holder.load(artistList[position])
    }
    // creating the
    override fun getItemViewType(position: Int) = when (artistList[position].viewType) {
        TYPE.LOADER -> TYPE.LOADER.ordinal
        TYPE.ITEM -> TYPE.ITEM.ordinal
    }

    fun addLoader() {
        // if my mutableList of animeList has something in it
        if (artistList.isNotEmpty())
            Handler(Looper.getMainLooper()).post {
                artistList.add(loader)
                notifyItemInserted(artistList.size - 1)
            }
    }

    fun removeLoader() {
        if (artistList.contains(loader)) {
            artistList.remove(loader)
            notifyItemRemoved(artistList.size)
        }
    }

    fun updateArtistList(artistList: List<Artist>, clear: Boolean = false) {
        if (clear) this.artistList.clear()
        val positionStart = this.artistList.size
        this.artistList.addAll(artistList)
        notifyItemRangeInserted(positionStart, this.artistList.size)
    }

    class ArtistViewHolder(private val vBind: ViewBinding) : RecyclerView.ViewHolder(vBind.root) {
        fun load(artist: Artist) {
            when (vBind) {
                is ItemArtistBinding -> vBind.apply {
                    val attrs = artist.artist
                    val name =
                        attrs.artistName?.let { it ?: "Empty Artist" }
                            ?: attrs.artistCountry?.let {
                                it ?: "Empty Country"}

                    tvArtistName.text = name
                    tvArtistId.text = attrs.artistId.toString()
                }
                is LoaderItemScrollBinding -> {
                }
            }
        }
    }

    interface OnArtistSelectedListener {
        fun artistSelected(artist: Artist)
    }

}



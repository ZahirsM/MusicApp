package com.example.musicapp.utils

interface ViewType {
    fun getViewType(): TYPE
}

enum class TYPE {
    LOADER,
    ITEM
}
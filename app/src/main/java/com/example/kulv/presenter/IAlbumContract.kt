package com.example.kulv.presenter

import com.example.kulv.model.SongData

interface IAlbumContract {

    interface IPresenter {
        fun getAlbumList()
    }

    interface IView {
        fun updateAlbumListView(data: List<SongData>)
        fun showError()
    }
}
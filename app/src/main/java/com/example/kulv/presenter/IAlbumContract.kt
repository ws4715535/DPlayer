package com.example.kulv.presenter

import com.example.kulv.model.SongData

interface IAlbumContract {

    interface IPresenter {
        fun getAlbumList()
        fun getRecommendList()
        fun getSingerList()
        fun getMusicSource(mid: String)
    }

    interface IView {
        fun updateAlbumListView(data: List<SongData>)
        fun updateRecommendataionListView(data: List<SongData>)
        fun updateSingerListView(data: List<SongData>)
        fun updateMusicUrl(url: String)
        fun showError()
    }
}
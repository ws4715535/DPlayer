package com.example.kulv.presenter

import com.example.kulv.respository.AlbumRepository

 class AlbumPresenter(private val albumRepository: AlbumRepository): IAlbumContract.IPresenter {

    private var view: IAlbumContract.IView? = null

    fun attach(view: IAlbumContract.IView) {
        this.view = view
    }

    fun detach() {
        this.view = null
    }

    override fun getAlbumList() {
        albumRepository.getAlbumsData( { songdata ->
            view?.let {
                it.updateAlbumListView(songdata.take(10))
            }
        }, {
            view?.let {
                it.showError()
            }
        }
        )
    }

    override fun getRecommendList() {
        albumRepository.getAlbumsData( { songdata ->
            view?.let {
                it.updateRecommendataionListView(songdata.takeLast(10))
            }
        }, {
            view?.let {
                it.showError()
            }
        }
        )
    }

    override fun getSingerList() {
        albumRepository.getAlbumsData( { songdata ->
            view?.let {
                it.updateRecommendataionListView(songdata.take(30).takeLast(10))
            }
        }, {
            view?.let {
                it.showError()
            }
        }
        )

    }

     override fun getMusicSource(mid: String) {
         albumRepository.getSongUrl(mid, { url ->
             view?.let {
                it.updateMusicUrl(url)
             }
         }, {
             view?.let {
                 it.showError()
             }
         }
         )
     }
 }
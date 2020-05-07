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
}
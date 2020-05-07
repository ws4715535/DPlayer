package com.example.kulv.presenter

import com.example.kulv.respository.AlbumRepository
import com.example.kulv.ui.home.fragment.SingerFragment

class AlbumPresenter(private val albumRepository: AlbumRepository): IAlbumContract.IPresenter {

    private var view: IAlbumContract.IView? = null

    fun attach(view: SingerFragment) {
        this.view = view
    }

    fun detach() {
        this.view = null
    }

    override fun getAlbumList() {
        albumRepository.getAlbumsData( { songdata ->
            view?.let {
                it.updateAlbumListView(songdata)
            }
        }, {
            view?.let {
                it.showError()
            }
        }
        )
    }
}
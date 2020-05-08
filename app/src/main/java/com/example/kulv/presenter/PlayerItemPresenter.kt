package com.example.kulv.presenter

import android.view.View
import com.example.kulv.respository.AlbumRepository
import com.example.kulv.respository.PlayerItemRepository

class PlayerItemPresenter(private val playerItemRepository: PlayerItemRepository): IPlayerItemContract.IPresenter {

    private var view: IPlayerItemContract.IView? = null

    fun attach(view: IPlayerItemContract.IView) {
        this.view = view
    }

    fun detach() {
        this.view = null
    }

     override fun didSelectedItem(v: View, position: Int, mid:Int) {

     }
 }
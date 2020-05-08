package com.example.kulv.presenter

import android.view.View
import com.example.kulv.model.MusicSource

interface IPlayerItemContract {
    interface IPresenter {
        fun didSelectedItem(v:View, position: Int, mid: Int)
    }

    interface IView {
        fun readyLoadMusicSource(music: MusicSource)
    }
}
package com.example.kulv

import android.app.Application
import com.beardedhen.androidbootstrap.TypefaceProvider


class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        TypefaceProvider.registerDefaultIconSets()
    }
}
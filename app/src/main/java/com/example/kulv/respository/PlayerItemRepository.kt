package com.example.kulv.respository

import com.example.kulv.api.ApiClient
import com.example.kulv.model.Album
import com.example.kulv.model.AlbumData
import com.example.kulv.model.MusicVkey
import com.example.kulv.model.SongData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import java.lang.reflect.Type
import java.util.*

class PlayerItemRepository {

    fun getSongUrl(mid:String, success: (url: String) -> Unit, fail: () -> Unit) {
        val newCall = ApiClient.client.newCall(
            Request
                .Builder()
                .url(ApiClient.getSongUrl(String()))
                .get()
                .build()
        )
        newCall.enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                var str = response.body?.string()
                val data = Gson().fromJson(str, MusicVkey::class.java)
                val playList = data.response.playLists
                val playUrl = if (playList.isEmpty()) "" else playList[0]
                if (str != null) {
                    success(playUrl as String)
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                fail()
            }
        })
    }

}
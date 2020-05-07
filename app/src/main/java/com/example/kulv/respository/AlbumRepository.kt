package com.example.kulv.respository

import com.example.kulv.api.ApiClient
import com.example.kulv.model.Album
import com.example.kulv.model.AlbumData
import com.example.kulv.model.SongData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import java.lang.reflect.Type
import java.util.*

class AlbumRepository {

    fun getAlbumsData(success: (albums: List<SongData>) -> Unit, fail: () -> Unit) {
        val newCall = ApiClient.client.newCall(
            Request
                .Builder()
                .url(ApiClient.COMMENDATAION_URL)
                .get()
                .build()
        )
        newCall.enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                var str = response.body?.string()
                val data = Gson().fromJson(str, AlbumData::class.java)
                success(data.songlist)
            }

            override fun onFailure(call: Call, e: IOException) {
                fail()
            }
        })
    }

}
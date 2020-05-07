package com.example.kulv.api

import okhttp3.OkHttpClient

object ApiClient {

    const val COMMENDATAION_URL = "https://c.y.qq.com/v8/fcg-bin/fcg_v8_toplist_cp.fcg?g_tk=5381&uin=0&format=json&inCharset=utf-8&outCharset=utf-8¬ice=0&platform=h5&needNewCode=1&tpl=3&page=detail&type=top&topid=27&_=1519963122923"
    const val BANNER_URL = "https://c.y.qq.com/musichall/fcgi-bin/fcg_yqqhomepagerecommend.fcg?g_tk=701075963&uin=0&format=json&inCharset=utf-8&outCharset=utf-8&notice=0&platform=mobile&needNewCode=1&_=1512548815061"
    fun albumImage(id:Int) {"http://imgcache.qq.com/music/photo/album_300/17/300_albumpic_${id}_0.jpg"}
    val client by lazy(LazyThreadSafetyMode.NONE) { OkHttpClient.Builder().build()}
}
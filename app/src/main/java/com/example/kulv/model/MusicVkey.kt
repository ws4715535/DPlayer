package com.example.kulv.model


data class MusicVkey(
    val response: Response
)

data class Response(
    val code: Int,
    val playLists: List<Any>,
    val req: Req,
    val req_0: Req0,
    val start_ts: Long,
    val ts: Long
)

data class Req(
    val code: Int,
    val `data`: Data
)

data class Req0(
    val code: Int,
    val `data`: DataX
)

data class Data(
    val expiration: Int,
    val freeflowsip: List<String>,
    val keepalivefile: String,
    val msg: String,
    val retcode: Int,
    val servercheck: String,
    val sip: List<String>,
    val testfile2g: String,
    val testfilewifi: String,
    val uin: String,
    val userip: String,
    val vkey: String
)

data class DataX(
    val expiration: Int,
    val login_key: String,
    val midurlinfo: List<Any>,
    val msg: String,
    val retcode: Int,
    val servercheck: String,
    val sip: List<Any>,
    val testfile2g: String,
    val testfilewifi: String,
    val thirdip: List<String>,
    val uin: String,
    val verify_type: Int
)

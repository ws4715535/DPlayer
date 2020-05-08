package com.example.kulv.ui.home.adapters

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kulv.R
import com.example.kulv.api.ApiClient
import com.example.kulv.model.Album
import com.example.kulv.model.MusicSource
import com.example.kulv.model.SongData
import kotlinx.android.synthetic.main.item_horizontal_feed.view.icon
import kotlinx.android.synthetic.main.item_horizontal_feed.view.title
interface PlayerItemClickListener {
    fun onCick(mid: String, musicSource: MusicSource)
}

class RecommendListAdapter(private val list: MutableList<SongData>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var listener: PlayerItemClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val horizontalFeed = LayoutInflater.from(parent?.context).inflate(R.layout.item_song, parent, false)
        return RecommendHolder(horizontalFeed)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is RecommendHolder) {
            holder.configAlbum(list[position].data)
            holder.itemView.setOnClickListener { holder.onSelectedItem(position) }
        }
    }

    fun setClickItemListener(listener: PlayerItemClickListener) {
        this.listener = listener
    }

    inner class RecommendHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun configAlbum(album: Album) {
            itemView.title.text = album.songname
            Glide.with(itemView.context)
                .load(ApiClient.albumImage(album.albumid))
                .into(itemView.icon)

        }
        fun onSelectedItem(position: Int) {
            //     val image: String,
            //    val songname: String,
            //    val singername: String,
            //    val playUrl: String
            val album = list[position].data
            val musicSource = MusicSource(ApiClient.albumImage(album.albumid), album.songname, album.singer[0].name)
            listener?.onCick(album.songmid, musicSource)
        }
    }
}
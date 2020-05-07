package com.example.kulv.ui.home.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kulv.R
import com.example.kulv.model.Album
import com.example.kulv.model.SongData
import kotlinx.android.synthetic.main.item_horizontal_feed.view.*

class AlbumAdapter(private val list: MutableList<SongData>, private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val horizontalFeed = LayoutInflater.from(parent?.context).inflate(R.layout.item_album, parent, false)
        return AlbumHolder(horizontalFeed)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is AlbumHolder) {
            holder.configAlbum(list[position].data, context)
        }
    }

    inner class AlbumHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun configAlbum(album: Album, context: Context) {
            this.itemView.title.text = album.albumname
            Glide.with(context).load("http://imgcache.qq.com/music/photo/album_300/17/300_albumpic_8217_0.jpg").into(this.itemView.icon)
        }
    }
}
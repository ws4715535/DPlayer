package com.example.kulv.ui.home.adapters

import android.content.Context
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
import com.example.kulv.presenter.IPlayerItemContract
import kotlinx.android.synthetic.main.item_album.view.*
import kotlinx.android.synthetic.main.item_horizontal_feed.view.icon
import kotlinx.android.synthetic.main.item_horizontal_feed.view.title


class AlbumAdapter(private val list: MutableList<SongData>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val horizontalFeed = LayoutInflater.from(parent?.context).inflate(R.layout.item_album, parent, false)
        return AlbumHolder(horizontalFeed)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is AlbumHolder) {
            holder.configAlbum(list[position].data)
            holder.itemView.setOnClickListener { holder.onSelectedItem(position) }
        }
    }


    inner class AlbumHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun configAlbum(album: Album) {
            itemView.title.text = album.albumname
            if (album.albumdesc.isEmpty()) {
                itemView.subTitle.text = "<<${album.albumname}>>"
            } else {
                itemView.subTitle.text = album.albumdesc
            }
            Glide.with(itemView.context)
                .load(ApiClient.albumImage(album.albumid))
                .into(itemView.icon)

        }

        fun onSelectedItem(position: Int) {

        }
    }
}
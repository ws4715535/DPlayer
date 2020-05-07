package com.example.kulv.ui.home.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kulv.R
import com.example.kulv.model.Album
import com.example.kulv.model.Singer
import kotlinx.android.synthetic.main.item_horizontal_feed.view.*

class HorizontalListAdapter(list: MutableList<Any>, type: Int) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    private var mList = ArrayList<Any>()
    private var sourceType: Int = 0

    init {
        sourceType = type
        mList.addAll(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val horizontalFeed = LayoutInflater.from(parent?.context).inflate(R.layout.item_album, parent, false)
        return HorizontalHolder(horizontalFeed)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is HorizontalHolder) {
            if (sourceType == 1) {
                val singers = mList as ArrayList<Singer>
                holder.configSinger(singers[position])
            } else if (sourceType == 2) {
                val albums = mList as ArrayList<Album>
                holder.configAlbum(albums[position])
            }
        }
    }



    inner class HorizontalHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        fun configSinger(singer: Singer) {
            this.itemView.title.text = singer.username
        }

        fun configAlbum(album: Album) {
            this.itemView.title.text = album.albummName
        }
    }
}
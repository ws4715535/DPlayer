package com.example.kulv.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kulv.R
import com.example.kulv.model.Album
import com.example.kulv.model.Singer
import com.example.kulv.ui.home.adapters.HorizontalListAdapter
import kotlinx.android.synthetic.main.horizontal_feed_layout.view.*

class HomeFragment: Fragment() {

    lateinit var homeList: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        initializeRecyclerView()
    }

    private fun initializeRecyclerView() {
        val myAdapter = HomeAdapter(mutableListOf("1111", "2222", "2222"))
        homeList.layoutManager = LinearLayoutManager(activity)
        homeList.adapter = myAdapter
    }

    inner class HomeAdapter(var data: MutableList<Any>): RecyclerView.Adapter<MyViewHolder>() {

        private val TYPE_BANNER_FEED_LAYOUT = 1
        private val TYPE_MAIN_FEED_LAYOUT = 2

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            if (viewType == TYPE_BANNER_FEED_LAYOUT) {
                return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.banner_layout, parent, false))

            } else {
                return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.horizontal_feed_layout, parent, false))
            }
        }

        override fun getItemCount(): Int {
            return data.size
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            if (position == 0) {
                //TODO: banner
            } else if (position == 1 ) {
                holder.itemView.rv_horizontal?.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
                val singers = mutableListOf<Any>(Singer(username = "ws1", image = ""), Singer(username = "ws2", image = ""), Singer(username = "ws3", image = ""), Singer(username = "ws4", image = ""))
                holder.itemView.rv_horizontal?.adapter = HorizontalListAdapter(singers, 1)
            } else {
                holder.itemView.rv_horizontal?.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
                val albums = mutableListOf<Any>(Album(albummid = 1, albummName = "1", albummImage = ""), Album(albummid = 2, albummName = "2", albummImage = ""), Album(albummid = 2, albummName = "2", albummImage = ""), Album(albummid = 2, albummName = "2", albummImage = ""))
                holder.itemView.rv_horizontal?.adapter = HorizontalListAdapter(albums, 2)
            }
        }

        override fun getItemViewType(position: Int): Int {
            if (position == 0) {
                return TYPE_BANNER_FEED_LAYOUT
            }
            return  TYPE_MAIN_FEED_LAYOUT
        }

    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    }
}
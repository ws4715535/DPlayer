package com.example.kulv.ui.home.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kulv.R
import com.example.kulv.model.Singer
import com.example.kulv.model.SongData
import com.example.kulv.presenter.AlbumPresenter
import com.example.kulv.presenter.IAlbumContract
import com.example.kulv.respository.AlbumRepository
import com.example.kulv.ui.home.adapters.AlbumAdapter
import com.example.kulv.ui.player.PlayerFragment
import kotlinx.android.synthetic.main.fragment_singer.*

class SingerFragment: Fragment(), IAlbumContract.IView {

    private val albumPresenter = AlbumPresenter(AlbumRepository())
    private val sourceData: MutableList<SongData> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_singer, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        albumPresenter.attach(this)
        initializeRecyclerView()
        albumPresenter.getAlbumList()
    }

    private fun initializeRecyclerView() {
        singerList.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        singerList.adapter = context?.let { AlbumAdapter(sourceData, it) }
    }

    private fun refreshList() {
        if (sourceData.isEmpty()) return
        singerList.adapter?.notifyDataSetChanged()
    }

    override fun onDestroy() {
        super.onDestroy()
        albumPresenter.detach()
    }

    override fun updateAlbumListView(data: List<SongData>) {
        sourceData.addAll(data)
        activity?.runOnUiThread {
            refreshList()
        }
    }

    override fun showError() {

    }
}
package com.example.kulv.ui.home.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kulv.MainActivity
import com.example.kulv.R
import com.example.kulv.model.MusicSource
import com.example.kulv.model.SongData
import com.example.kulv.presenter.AlbumPresenter
import com.example.kulv.presenter.IAlbumContract
import com.example.kulv.presenter.IPlayerItemContract
import com.example.kulv.respository.AlbumRepository
import com.example.kulv.ui.home.adapters.PlayerItemClickListener
import com.example.kulv.ui.home.adapters.RecommendListAdapter
import kotlinx.android.synthetic.main.fragment_recommendation.*

class RecommendationFragment: Fragment(), IAlbumContract.IView, IPlayerItemContract.IView, PlayerItemClickListener  {

    private val albumPresenter = AlbumPresenter(AlbumRepository())
    private val sourceData: MutableList<SongData> = mutableListOf()
    private val adapter by lazy(LazyThreadSafetyMode.NONE) { RecommendListAdapter(sourceData) }
    var musicSource: MusicSource? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recommendation, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        albumPresenter.attach(this)
        initializeRecyclerView()
        albumPresenter.getRecommendList()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeRecyclerView()
    }

    private fun initializeRecyclerView() {
        songList.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        songList.adapter = adapter
        adapter.setClickItemListener(this)
    }

    private fun refreshList() {
        if (sourceData.isEmpty()) return
        songList.adapter?.notifyDataSetChanged()
    }

    override fun onDestroy() {
        super.onDestroy()
        albumPresenter.detach()
    }

    override fun updateAlbumListView(data: List<SongData>) {

    }

    override fun updateRecommendataionListView(data: List<SongData>) {
        sourceData.addAll(data)
        activity?.runOnUiThread {
            refreshList()
        }
    }

    override fun showError() {

    }

    override fun updateSingerListView(data: List<SongData>) {

    }

    override fun updateMusicUrl(url: String) {
        musicSource?.let { (activity as MainActivity).switchToPlayerItem(url, it) }
    }

    override fun readyLoadMusicSource(music: MusicSource) {

    }

    override fun onCick(mid: String, musicSource: MusicSource) {
        this.musicSource = musicSource
        albumPresenter.getMusicSource(mid)
    }
}
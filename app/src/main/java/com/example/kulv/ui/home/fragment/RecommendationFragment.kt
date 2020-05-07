package com.example.kulv.ui.home.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kulv.R
import com.example.kulv.model.Singer
import com.example.kulv.ui.home.adapters.RecommendListAdapter
import kotlinx.android.synthetic.main.fragment_recommendation.*

class RecommendationFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recommendation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        initializeRecyclerView()
    }

    private fun initializeRecyclerView() {
//        val singers = mutableListOf<Any>(Singer(name = "ws1"), Singer(username = "ws2", image = ""), Singer(username = "ws3", image = ""), Singer(username = "ws4", image = ""))
//        songList.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
//        songList.adapter = RecommendListAdapter(singers, 1)
    }
}
package com.example.kulv.ui.home.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kulv.R
import com.example.kulv.model.Singer
import com.example.kulv.ui.home.adapters.HorizontalListAdapter
import kotlinx.android.synthetic.main.fragment_singer.*

class SingerFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_singer, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeRecyclerView()
    }

    private fun initializeRecyclerView() {
        val singers = mutableListOf<Any>(Singer(username = "ws1", image = ""), Singer(username = "ws2", image = ""), Singer(username = "ws3", image = ""), Singer(username = "ws4", image = ""))
        singerList.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        singerList.adapter = HorizontalListAdapter(singers, 1)
    }
}
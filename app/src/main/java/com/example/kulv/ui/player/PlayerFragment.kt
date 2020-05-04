package com.example.kulv.ui.player

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.beardedhen.androidbootstrap.AwesomeTextView
import com.example.kulv.R
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.source.ConcatenatingMediaSource
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory
import java.util.*
import kotlin.collections.ArrayList


class PlayerFragment : Fragment() {

    private lateinit var playerViewModel: PlayerViewModel
    private var player: ExoPlayer? = null
    private lateinit var playerView: PlayerView
    private lateinit var name: TextView
    private val playWhenReady = true
    private val currentWindow = 0
    private val playbackPosition: Long = 0

    private val musicList: ArrayList<String> = ArrayList()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        playerViewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(requireActivity().application))
            .get(PlayerViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_player, container, false)
        name = root.findViewById(R.id.music_name)
        playerView = root.findViewById(R.id.player)
        initializePlayer()
        return root
    }

    private fun initializePlayer() {
        if (player == null) {
            player = ExoPlayerFactory.newSimpleInstance(
                context,
                DefaultTrackSelector()
            )
            playerView.player = player
            player?.playWhenReady = playWhenReady
            player?.seekTo(currentWindow, playbackPosition)
        }
        musicList.add("http://vjs.zencdn.net/v/oceans.mp4")
        musicList.add("https://media.w3.org/2010/05/sintel/trailer.mp4")
        musicList.add("http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4")
        musicList.add("http://music.163.com/song/media/outer/url?id=447925558.mp3")
//        val uri: Uri = Uri.parse("http://music.163.com/song/media/outer/url?id=447925558.mp3")

        val mediaSources = arrayOfNulls<MediaSource>(musicList.size)
        for (i in musicList.indices) {
            mediaSources[i] = buildMediaSource(Uri.parse(musicList[i]))
        }
        val mediaSource =
            if (mediaSources.size == 1) mediaSources[0] else ConcatenatingMediaSource(*mediaSources)
        player?.prepare(mediaSource, false, true)

    }

    private fun buildMediaSource(uri: Uri): MediaSource {
        return ExtractorMediaSource.Factory(
            DefaultHttpDataSourceFactory("exoplayer-codelab")
        ).createMediaSource(uri)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (player != null) {
            player?.stop()
            player?.release()
            player = null
        }
    }
}

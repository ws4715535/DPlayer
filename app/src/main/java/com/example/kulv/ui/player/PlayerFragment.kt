package com.example.kulv.ui.player

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.kulv.R
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory


class PlayerFragment : Fragment() {

    private lateinit var playerViewModel: PlayerViewModel
    private var player: ExoPlayer? = null
    private lateinit var playerView: PlayerView
    private val playWhenReady = true
    private val currentWindow = 0
    private val playbackPosition: Long = 0

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        playerViewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(requireActivity().application))
            .get(PlayerViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_player, container, false)
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
//        val uri: Uri = Uri.parse("http://music.163.com/song/media/outer/url?id=447925558.mp3")
        val uri: Uri = Uri.parse("http://vjs.zencdn.net/v/oceans.mp4")
        val mediaSource: MediaSource = buildMediaSource(uri)
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

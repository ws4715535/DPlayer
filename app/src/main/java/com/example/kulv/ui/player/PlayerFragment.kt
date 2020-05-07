package com.example.kulv.ui.player

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.beardedhen.androidbootstrap.AwesomeTextView
import com.bumptech.glide.Glide
import com.example.kulv.R
import com.example.kulv.ui.common.CircleImageView
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.source.ConcatenatingMediaSource
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer

class PlayerFragment : Fragment(), Player.EventListener {

    private var player: ExoPlayer? = null
    private lateinit var playerView: PlayerView
    private lateinit var circleImageView: CircleImageView
    private lateinit var pause: AwesomeTextView
    private lateinit var play: AwesomeTextView
    private lateinit var discAnimation: ObjectAnimator
    private lateinit var name: TextView
    private val playWhenReady = true
    private val currentWindow = 0
    private val playbackPosition: Long = 0

    //这个在跳转过来时应该用真实数据替换
    private val musicList: ArrayList<String> = ArrayList()
    private var musicName: String = "Now Playing"
    private var musicImage: String = "https://dss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1713396441,1487163637&fm=26&gp=0.jpg"
    private var music: String = "http://ws.stream.qqmusic.qq.com/C400004WcXN110uFwl.m4a?guid=4440410775&vkey=CD574E38661FA6DFF5A96291709A16109FF7EDF88611AC8EDDED1673DDC776A4409AAB89F53B7C4570DAA23FC180FCE3D9E8A00604C2F50A&uin=0&fromtag=66"


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_player, container, false)
        initVIew(root)
        setAnimation()
        initializePlayer()
        return root
    }

    private fun initVIew(root: View) {
        name = root.findViewById(R.id.music_name)
        circleImageView = root.findViewById(R.id.music_image)
        pause = root.findViewById(R.id.exo_pause)
        play = root.findViewById(R.id.exo_play)
        playerView = root.findViewById(R.id.player)
    }

    private fun initializePlayer() {
        configMusicName()
        configMusicImage()
        configMusicController()
        if (player == null) {
            configPlayer()
        }
        prepareData()
    }

    private fun configPlayer() {
        player = ExoPlayerFactory.newSimpleInstance(
            context,
            DefaultTrackSelector()
        )
        playerView.player = player
        player?.addListener(this)
        (player as SimpleExoPlayer).volume = 50f
        player?.playWhenReady = playWhenReady
        player?.seekTo(currentWindow, playbackPosition)
    }

    private fun configMusicController() {
//        play.setOnClickListener {
//            discAnimation.pause()
//        }
//
//        pause.setOnClickListener {
//            discAnimation.resume()
//        }
    }

    private fun configMusicImage() {
        Glide.with(requireContext())
            .load(musicImage)
            .apply(
                RequestOptions.bitmapTransform(CircleCrop())
                    .error(R.drawable.text)
                    .placeholder(R.drawable.text)
            )
            .into(circleImageView)
    }

    private fun configMusicName() {
        name.text = musicName
    }

    private fun prepareData() {
        musicList.add(music)

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

    private fun setAnimation(){
        discAnimation = ObjectAnimator.ofFloat(circleImageView, View.ROTATION, 0f, 360f)
        discAnimation.duration = 20000
        discAnimation.interpolator = LinearInterpolator()
        discAnimation.repeatCount = ValueAnimator.INFINITE
    }

    override fun onLoadingChanged(isLoading: Boolean) {
        super.onLoadingChanged(isLoading)
        if (isLoading) {
            discAnimation.start()
        }
    }

    override fun onPause() {
        super.onPause()
        discAnimation.pause()
    }

    override fun onResume() {
        super.onResume()
        discAnimation.resume()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (player != null) {
            player?.stop()
            player?.release()
            player = null
            discAnimation.cancel()
            circleImageView.clearAnimation()
        }
    }
}

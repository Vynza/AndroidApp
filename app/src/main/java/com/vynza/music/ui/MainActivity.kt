package com.vynza.music.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.vynza.music.R
import com.vynza.music.data.api.ApiClient
import com.vynza.music.data.model.Song
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private var exoPlayer: ExoPlayer? = null
    private var songs: List<Song> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initPlayer()
        fetchMusicCatalog()
    }

    private fun initPlayer() {
        exoPlayer = ExoPlayer.Builder(this).build()
    }

    private fun fetchMusicCatalog() {
        lifecycleScope.launch {
            try {
                val response = ApiClient.apiService.getSongs()
                if (response.isSuccessful && response.body() != null) {
                    songs = response.body()!!
                    Toast.makeText(
                        this@MainActivity,
                        "Loaded ${songs.size} tracks from VYNZA Backend",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun playSong(song: Song) {
        exoPlayer?.let { player ->
            val streamUrl = ApiClient.getStreamUrl(song.id)
            val mediaItem = MediaItem.fromUri(streamUrl)
            player.setMediaItem(mediaItem)
            player.prepare()
            player.play()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        exoPlayer?.release()
        exoPlayer = null
    }
}

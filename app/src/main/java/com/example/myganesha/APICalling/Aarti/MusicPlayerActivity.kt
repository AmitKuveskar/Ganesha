package com.example.myganesha.APICalling.Aarti

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.SeekBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageButton
import androidx.core.content.ContextCompat.startActivity

import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myganesha.R
import java.util.Locale
import java.util.logging.Handler

class MusicPlayerActivity : AppCompatActivity() {

    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var seekBar: SeekBar
    private lateinit var startTimeTextView: TextView
    private lateinit var endTimeTextView: TextView
    private lateinit var titleTextView: TextView
    private lateinit var playPauseButton: AppCompatImageButton
    private lateinit var nextButton: AppCompatImageButton
    private lateinit var prevButton: AppCompatImageButton

    private lateinit var songList: List<AartiPojoItem>
    private var currentSongIndex: Int = 0
    private var handler: android.os.Handler = android.os.Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music_player)

        // Initialize views
        seekBar = findViewById(R.id.sekbar)
        startTimeTextView = findViewById(R.id.startTimeTextView)
        endTimeTextView = findViewById(R.id.endTimeTextView)
        titleTextView = findViewById(R.id.titlee)
        playPauseButton = findViewById(R.id.playPauseButton)
        nextButton = findViewById(R.id.forwardButton)
        prevButton = findViewById(R.id.prevButton)

        // Retrieve song list from intent
        songList = intent.getParcelableArrayListExtra("songList")!!

        // Retrieve audio data from intent
        val id = intent.getStringExtra("id")

        // Find the index of the current song
        currentSongIndex = songList.indexOfFirst { it.id == id }

        // Set title and description if needed
        titleTextView.text = songList[currentSongIndex].title

        // Initialize MediaPlayer with the received audio URL
        mediaPlayer = MediaPlayer().apply {
            setDataSource(songList[currentSongIndex].audio)
            prepareAsync()
            setOnPreparedListener {
                // Update the end time TextView with the duration of the audio
                val durationInMillis = mediaPlayer.duration
                val durationInMinutes = durationInMillis / 1000 / 60
                val durationInSeconds = (durationInMillis / 1000) % 60
                endTimeTextView.text =
                    String.format(
                        Locale.getDefault(),
                        "%02d:%02d",
                        durationInMinutes,
                        durationInSeconds
                    )

                // Set the maximum value of the SeekBar to the duration of the audio
                seekBar.max = durationInMillis

                // Start playback
                start()
                playPauseButton.setImageResource(R.drawable.baseline_pause_24) // Change button icon to pause

                // Start updating SeekBar progress
                updateSeekBar()
            }
        }

        // SeekBar listener
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    // Seek to the selected position when the SeekBar is moved by the user
                    mediaPlayer.seekTo(progress)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // Pause the media player when the SeekBar is touched
                mediaPlayer.pause()
                handler.removeCallbacks(updateSeekBarTask)
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // Resume playback and start updating SeekBar progress again
                mediaPlayer.start()
                updateSeekBar()
            }
        })

        // Set OnClickListener to playPauseButton
        playPauseButton.setOnClickListener {
            if (mediaPlayer.isPlaying) {
                mediaPlayer.pause() // Pause the media player
                playPauseButton.setImageResource(R.drawable.playicon) // Change button icon to play
                handler.removeCallbacks(updateSeekBarTask) // Stop updating SeekBar progress
            } else {
                mediaPlayer.start() // Start the media player
                playPauseButton.setImageResource(R.drawable.baseline_pause_24) // Change button icon to pause
                updateSeekBar() // Start updating SeekBar progress
            }
        }

        // Set OnClickListener to nextButton
        nextButton.setOnClickListener {
            playNextSong()
        }

        // Set OnClickListener to prevButton
        prevButton.setOnClickListener {
            playPrevSong()
        }
    }

    private fun playNextSong() {
        // Increment current song index to play the next song
        currentSongIndex = (currentSongIndex + 1) % songList.size
        // Play the next song
        playSong(songList[currentSongIndex])
    }

    private fun playPrevSong() {
        // Decrement current song index to play the previous song
        currentSongIndex = (currentSongIndex - 1 + songList.size) % songList.size
        // Play the previous song
        playSong(songList[currentSongIndex])
    }

    private fun playSong(song: AartiPojoItem) {
        // Release current MediaPlayer resources
        mediaPlayer.release()

        // Initialize new MediaPlayer with the selected song's audio URL
        mediaPlayer = MediaPlayer().apply {
            setDataSource(song.audio)
            prepareAsync()
            setOnPreparedListener {
                // Update UI and start playback
                val durationInMillis = mediaPlayer.duration
                val durationInMinutes = durationInMillis / 1000 / 60
                val durationInSeconds = (durationInMillis / 1000) % 60
                endTimeTextView.text =
                    String.format(Locale.getDefault(), "%02d:%02d", durationInMinutes, durationInSeconds)
                seekBar.max = durationInMillis
                start()
                playPauseButton.setImageResource(R.drawable.baseline_pause_24)
                updateSeekBar() // Start updating SeekBar progress
            }
        }
        // Update title
        titleTextView.text = song.title
    }

    private fun updateSeekBar() {
        // Update SeekBar progress every second
        handler.postDelayed(updateSeekBarTask, 1000)
    }

    private val updateSeekBarTask = object : Runnable {
        override fun run() {
            // Update SeekBar progress
            seekBar.progress = mediaPlayer.currentPosition
            // Update start time TextView with current progress
            val minutes = mediaPlayer.currentPosition / 1000 / 60
            val seconds = (mediaPlayer.currentPosition / 1000) % 60
            startTimeTextView.text = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds)
            // Schedule the next update in 1 second
            handler.postDelayed(this, 1000)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // Release MediaPlayer resources and remove callbacks to prevent memory leaks
        mediaPlayer.release()
        handler.removeCallbacks(updateSeekBarTask)
    }
}

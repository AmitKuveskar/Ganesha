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

class MusicPlayerActivity : AppCompatActivity() {

    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var seekBar: SeekBar
    private lateinit var startTimeTextView: TextView
    private lateinit var endTimeTextView: TextView
    private lateinit var titleTextView: TextView
    private lateinit var playPauseButton: AppCompatImageButton
//    private lateinit var prevButton: Button
    private lateinit var forwardButton: AppCompatImageButton
    private lateinit var audioUrls: List<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music_player)

        // Initialize views
        seekBar = findViewById(R.id.sekbar)
        startTimeTextView = findViewById(R.id.startTimeTextView)
        endTimeTextView = findViewById(R.id.endTimeTextView)
        titleTextView = findViewById(R.id.titlee)
        playPauseButton = findViewById(R.id.playPauseButton)
        forwardButton = findViewById(R.id.forwardButton)

        // Retrieve audio data from intent
        val title = intent.getStringExtra("title")
        val description = intent.getStringExtra("description")
        val audioUrl = intent.getStringExtra("audio")

        // Set title and description if needed
        titleTextView.text = title
        // descriptionTextView.text = description

        // Initialize MediaPlayer with the received audio URL
        mediaPlayer = MediaPlayer().apply {
            setDataSource(audioUrl)
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

            }
        }

        // SeekBar listener
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(
                seekBar: SeekBar?,
                progress: Int,
                fromUser: Boolean
            ) {
                // Update the start time TextView with the current progress of the SeekBar
                val minutes = progress / 1000 / 60
                val seconds = (progress / 1000) % 60
                startTimeTextView.text =
                    String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // Pause the media player when the SeekBar is touched
                mediaPlayer.pause()
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // Seek to the selected position when the SeekBar is released
                mediaPlayer.seekTo(seekBar?.progress ?: 0)
                mediaPlayer.start()
            }
        })

        // Set OnClickListener to playPauseButton
        playPauseButton.setOnClickListener {
            if (mediaPlayer.isPlaying) {
                mediaPlayer.pause() // Pause the media player
                playPauseButton.setImageResource(R.drawable.playicon) // Change button icon to play
            } else {
                mediaPlayer.start() // Start the media player
                playPauseButton.setImageResource(R.drawable.baseline_pause_24) // Change button icon to pause
            }
        }


    }
}

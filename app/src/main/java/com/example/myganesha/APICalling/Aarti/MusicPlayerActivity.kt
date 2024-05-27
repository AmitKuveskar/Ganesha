package com.example.myganesha.APICalling.Aarti

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageButton
import androidx.core.content.ContextCompat.startActivity

import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myganesha.MainActivity
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
    private lateinit var shuffleButton: AppCompatImageButton
    private lateinit var repeatButton: AppCompatImageButton
    private lateinit var musicqueue: ImageView
    private lateinit var Homebtn: ImageView
    private lateinit var BackBtn: ImageView

    private lateinit var originalSongList: List<AartiPojoItem>
    private lateinit var shuffledSongList: MutableList<AartiPojoItem>
    private var currentSongIndex: Int = 0
    private var handler: android.os.Handler = android.os.Handler()

    private var isShuffleOn: Boolean = false
    private var isRepeatOn: Boolean = false

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
        shuffleButton = findViewById(R.id.shuffleButton)
        repeatButton = findViewById(R.id.repeatButton)
        musicqueue = findViewById(R.id.musicqueue)
        Homebtn = findViewById(R.id.homebtn)
        BackBtn = findViewById(R.id.backbtn)


        Homebtn.setOnClickListener {
            val intent = Intent(this@MusicPlayerActivity, MainActivity::class.java)
            startActivity(intent)
        }

        BackBtn.setOnClickListener {
            val intent = Intent(this@MusicPlayerActivity, APICallingActivity6::class.java)
            startActivity(intent)
            finish()
        }

        musicqueue.setOnClickListener{
            val intent = Intent(this@MusicPlayerActivity,APICallingActivity6::class.java)
            startActivity(intent)
        }

        // Retrieve song list from intent
        originalSongList = intent.getParcelableArrayListExtra("songList")!!

        // Create a copy of the original song list
        shuffledSongList = originalSongList.toMutableList()

        // Retrieve audio data from intent
        val id = intent.getStringExtra("id")

        // Find the index of the current song
        currentSongIndex = shuffledSongList.indexOfFirst { it.id == id }

        // Set title and description if needed
        titleTextView.text = shuffledSongList[currentSongIndex].title

        // Initialize MediaPlayer with the received audio URL
        mediaPlayer = MediaPlayer().apply {
            setDataSource(shuffledSongList[currentSongIndex].audio)
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
            setOnCompletionListener {
                if (isRepeatOn) {
                    mediaPlayer.seekTo(0)
                    mediaPlayer.start()
                } else {
                    playNextSong()
                }
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
                playPauseButton.setImageResource(R.drawable.baseline_play_arrow_24) // Change button icon to play
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

        // Set OnClickListener to shuffleButton
        shuffleButton.setOnClickListener {
            toggleShuffle()
        }

        // Set OnClickListener to repeatButton
        repeatButton.setOnClickListener {
            toggleRepeat()
        }
    }

    private fun playNextSong() {
        if (isShuffleOn) {
            currentSongIndex = (currentSongIndex + 1) % shuffledSongList.size
        } else {
            currentSongIndex = (currentSongIndex + 1) % originalSongList.size
        }
        playSong()
    }

    private fun playPrevSong() {
        if (isShuffleOn) {
            currentSongIndex = (currentSongIndex - 1 + shuffledSongList.size) % shuffledSongList.size
        } else {
            currentSongIndex = (currentSongIndex - 1 + originalSongList.size) % originalSongList.size
        }
        playSong()
    }

    private fun playSong() {
        mediaPlayer.reset()
        val song = if (isShuffleOn) shuffledSongList[currentSongIndex] else originalSongList[currentSongIndex]
        mediaPlayer.setDataSource(song.audio)
        mediaPlayer.prepareAsync()
        titleTextView.text = song.title
    }

    private fun updateSeekBar() {
        handler.postDelayed(updateSeekBarTask, 1000)
    }

    private val updateSeekBarTask = object : Runnable {
        override fun run() {
            seekBar.progress = mediaPlayer.currentPosition
            val minutes = mediaPlayer.currentPosition / 1000 / 60
            val seconds = (mediaPlayer.currentPosition / 1000) % 60
            startTimeTextView.text = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds)
            handler.postDelayed(this, 1000)
        }
    }

    private fun toggleShuffle() {
        isShuffleOn = !isShuffleOn
        if (isShuffleOn) {
            shuffleButton.setImageResource(R.drawable.baseline_shuffle_on_24)
            shuffledSongList.shuffle()
        } else {
            shuffleButton.setImageResource(R.drawable.baseline_shuffle_24)
            shuffledSongList.clear()
            shuffledSongList.addAll(originalSongList)
        }
    }

    private fun toggleRepeat() {
        isRepeatOn = !isRepeatOn
        if (isRepeatOn) {
            repeatButton.setImageResource(R.drawable.baseline_repeat_on_24)
        } else {
            repeatButton.setImageResource(R.drawable.baseline_repeat_24)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
        handler.removeCallbacks(updateSeekBarTask)
    }
}

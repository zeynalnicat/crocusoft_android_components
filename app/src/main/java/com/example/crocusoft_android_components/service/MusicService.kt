package com.example.crocusoft_android_components.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.pm.ServiceInfo
import android.media.MediaPlayer
import android.net.rtp.AudioStream
import android.os.Binder
import android.os.Build
import android.os.IBinder
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.example.crocusoft_android_components.MainActivity
import com.example.crocusoft_android_components.R
import com.example.crocusoft_android_components.SecondActivity


class MusicService : Service() {

    private lateinit var player: MediaPlayer

    inner class MusicBinder : Binder() {
        fun getService(): MusicService {
            return this@MusicService
        }
    }


    private val binder = MusicBinder()

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {



        val notification = NotificationCompat.Builder(this, "music")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Music Player")
            .setContentText("Music is playing")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            startForeground(1, notification, ServiceInfo.FOREGROUND_SERVICE_TYPE_MEDIA_PLAYBACK)
        } else {
            startForeground(1, notification)
        }
        return START_STICKY
    }

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
        player = MediaPlayer.create(this, R.raw.sample)

    }

    private fun createNotificationChannel() {

            val name = "Music Channel"
            val descriptionText = "Channel for Music Service"
            val importance = NotificationManager.IMPORTANCE_LOW
            val channel = NotificationChannel("music", name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager =
                getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)

    }

    override fun onBind(intent: Intent?): IBinder {
        return binder
    }

    fun startMusic() {
        player.setVolume(1f,1f)
        player.start()
    }

    fun stopMusic() {
        player.pause()
    }

    fun getCurrentPosition(): Int {
        return player.currentPosition
    }

    fun isPlaying(): Boolean{
        return player.isPlaying
    }

    fun getDuration(): Int {
        return player.duration
    }

    override fun onDestroy() {
        super.onDestroy()
        player.stop()
        player.release()
    }
}

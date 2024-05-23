package com.myo.swingingmt

import android.content.Context
import android.media.MediaPlayer

 object MediaPlayerHelper {
     private var mediaPlayer: MediaPlayer? = null

     fun init(id: Int) {
         release()
         mediaPlayer = MediaPlayer.create(ctx, id).apply {
             isLooping = true
             start()
         }
     }

     fun start() {
         mediaPlayer?.start()
     }

     fun pause() {
         mediaPlayer?.pause()
     }

     fun stop() {
         mediaPlayer?.stop()
     }

     fun release() {
         mediaPlayer?.release()
         mediaPlayer = null
     }
}
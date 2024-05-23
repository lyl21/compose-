package com.jrrzx.emergencyhelper.helper

import android.content.Context
import android.media.AudioManager
import android.media.Ringtone
import android.media.RingtoneManager
import android.os.Build
import android.os.Vibrator
import android.util.Log
import java.util.Locale


class VibratorUtil(context: Context) {
    protected var audioManager: AudioManager
    protected var vibrator: Vibrator
    private var ringtone: Ringtone? = null
    private var lastNotificationTime: Long = 0
    private var mContext = context

    init {
        audioManager =
            context.getSystemService(Context.AUDIO_SERVICE) as AudioManager //此方法是由Context调用的
        vibrator =
            context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator //同上
    }

    /**
     * 开启手机震动和播放系统提示铃声
     */
    fun vibrateAndPlayTone() {
        if (System.currentTimeMillis() - lastNotificationTime < MIN_TIME_OUT) {    //间隔少于4s
            return
        }
        try {
            lastNotificationTime = System.currentTimeMillis()
            //声音开启,振动开启：RINGER_MODE_NORMAL
            //声音关闭,振动关闭：RINGER_MODE_SILENT
            //声音关闭,振动开启：RINGER_MODE_VIBRATE
            if (audioManager.ringerMode == AudioManager.RINGER_MODE_SILENT) {
                return
            }
            val pattern = longArrayOf(0, 1000, 1000, 1000) // 停止0s 开启1s 停止1s 开启1s
            //第二个参数表示使用pattern第几个参数作为震动时间重复震动，如果是-1就震动一次   0一直震动
            vibrator.vibrate(pattern, -1)
            if (ringtone == null) {
                //TYPE_NOTIFICATION:通知的声音的类型。  TYPE_ALARM:闹钟的声音类型。
                val notificationUri =
                    RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
                ringtone = RingtoneManager.getRingtone(mContext, notificationUri)
                if (ringtone == null) {
                    return
                }
            }
            if (!ringtone!!.isPlaying) {
                ringtone!!.play()

                //判断手机品牌
                val vendor = Build.MANUFACTURER
                Log.d("vendor", "vibrateAndPlayTone: $vendor")
                if (vendor != null && vendor.lowercase(Locale.getDefault())
                        .contains("samsung")
                ) {  //三星的提示铃声关闭
                    val ctlThread: Thread = object : Thread() {
                        override fun run() {
                            try {
                                sleep(3000)
                                if (ringtone!!.isPlaying) {
                                    ringtone!!.stop()
                                }
                            } catch (e: Exception) {
                            }
                        }
                    }
                    ctlThread.run()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    companion object {
        private const val MIN_TIME_OUT = 4000 //时间间隔
    }
}



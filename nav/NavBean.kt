package com.jrrzx.emergencyhelper.nav

import android.os.Build
import android.os.Bundle
import androidx.navigation.NavType
import com.google.gson.Gson
import com.jrrzx.emergencyhelper.helper.MediaItemInfo


//class MediaItemInfoNavType : NavType<MediaItemInfo>(isNullableAllowed = false) {
//    override fun get(bundle: Bundle, key: String): MediaItemInfo? {
//        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
//            bundle.getParcelable(key,MediaItemInfo::class.java)
//        } else {
//            bundle.getParcelable(key)
//        }
//    }
//
//    override fun parseValue(value: String): MediaItemInfo {
//        return Gson().fromJson(value, MediaItemInfo::class.java)
//    }
//
//    override fun put(bundle: Bundle, key: String, value: MediaItemInfo) {
//        bundle.putParcelable(key, value)
//    }
//}

package com.sanzes.dwhj.utils


import android.content.Context
import android.util.Log
import com.tencent.mmkv.*
import kotlinx.serialization.*
import kotlinx.serialization.json.*

@Serializable
data class Person(val name: String="123", val ages: MutableList<AAA> = mutableListOf()) {
    @Serializable
    data class AAA(val name: String, val age: Int)
}

data object MMKVUtil {
    private lateinit var mmkv: MMKV
    private val json = Json { encodeDefaults = true } // 配置 JSON 序列化器

    fun initDefault(ctx: Context) {
        MMKV.initialize(ctx)
        mmkv = MMKV.defaultMMKV()
    }


    fun <T> set(key: String, value: T) {
        try {
            when (value) {
                is String -> mmkv.encode(key, value)
                is Int -> mmkv.encode(key, value)
                is Boolean -> mmkv.encode(key, value)
                is Float -> mmkv.encode(key, value)
                is Long -> mmkv.encode(key, value)
                is Double -> mmkv.encode(key, value)
                is ByteArray -> mmkv.encode(key, value)
                else -> throw IllegalArgumentException("Unsupported type")
            }
        } catch (e: Exception) {
            Log.e("xxx", e.message.toString())
        }
    }


    @Suppress("UNCHECKED_CAST")
    fun <T> get(key: String, default: T,clazz: Class<T>): T {
        try {
            return when (clazz) {
                String::class.java -> mmkv.decodeString(key, default as String) as T
                Integer::class.java -> mmkv.decodeInt(key, default as Int) as T
                Boolean::class.java -> mmkv.decodeBool(key, default as Boolean) as T
                Float::class.java -> mmkv.decodeFloat(key, default as Float) as T
                Long::class.java -> mmkv.decodeLong(key, default as Long) as T
                Double::class.java -> mmkv.decodeDouble(key, default as Double) as T
                ByteArray::class.java -> mmkv.decodeBytes(key, default as ByteArray) as T
                else -> throw IllegalArgumentException("Unsupported type")
            }
        } catch (e: Exception) {
            Log.e("xxx", e.message.toString())
            return default
        }
    }



    fun containsKey(key: String): Boolean {
        return mmkv.containsKey(key)
    }

    fun remove(key: String) {
        mmkv.remove(key)
    }

    fun clearAll() {
        mmkv.clearAll()
    }
}
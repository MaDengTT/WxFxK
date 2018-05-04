package com.xxm.mmd.wxfxk.utils

import android.content.Context
import android.content.SharedPreferences
import com.xxm.mmd.wxfxk.WxFxKApp
import java.io.*
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Created by MaDeng on 2018/4/19.
 */
class Preference<T>(val context: Context = WxFxKApp.instance,val name:String,val default:T): ReadWriteProperty<Any?, T> {
    val prefs:SharedPreferences by lazy { context.getSharedPreferences("WxFx",Context.MODE_PRIVATE) }

    override fun getValue(thisRef:Any?,property:KProperty<*>):T{
        return findPreference(name,default)
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        putPreference(name,value)
    }

    /**
     * 删除全部数据
     */
    fun clearPreference(){
        prefs.edit().clear().apply()
    }

    /**
     * 根据key删除存储数据
     */
    fun clearPreference(key : String){
        prefs.edit().remove(key).apply()
    }


    private fun <T >findPreference(name: String, default: T): T = with(prefs) {
        val res: Any = when (default) {
            is Long -> getLong(name,default)
            is String -> getString(name,default)
            is Int -> getInt(name,default)
            is Boolean -> getBoolean(name,default)
            is Float -> getFloat(name,default)
            else -> deSerialization(name)
        }
        res as T
    }

    private fun <T> putPreference(name :String,value: T) = with(prefs.edit()){
        when(value){
            is Long -> putLong(name,value)
            is String -> putString(name,value)
            is Int -> putInt(name,value)
            is Boolean -> putBoolean(name,value)
            is Float -> putFloat(name,value)
            else -> putString(name,serialize(value))
        }.apply()

    }
    /**
     * 序列化对象

     * @param person
     * *
     * @return
     * *
     * @throws IOException
     */
    @Throws(IOException::class)
    private fun<T> serialize(obj: T): String {
        val byteArrayOutputStream = ByteArrayOutputStream()
        val objectOutputStream = ObjectOutputStream(
                byteArrayOutputStream)
        objectOutputStream.writeObject(obj)
        var serStr = byteArrayOutputStream.toString("ISO-8859-1")
        serStr = java.net.URLEncoder.encode(serStr, "UTF-8")
        objectOutputStream.close()
        byteArrayOutputStream.close()
        return serStr
    }

    /**
     * 反序列化对象

     * @param str
     * *
     * @return
     * *
     * @throws IOException
     * *
     * @throws ClassNotFoundException
     */
    @Throws(IOException::class, ClassNotFoundException::class)
    private fun<T> deSerialization(str: String): T {
        val redStr = java.net.URLDecoder.decode(str, "UTF-8")
        val byteArrayInputStream = ByteArrayInputStream(
                redStr.toByteArray(charset("ISO-8859-1")))
        val objectInputStream = ObjectInputStream(
                byteArrayInputStream)
        val obj = objectInputStream.readObject() as T
        objectInputStream.close()
        byteArrayInputStream.close()
        return obj
    }
}
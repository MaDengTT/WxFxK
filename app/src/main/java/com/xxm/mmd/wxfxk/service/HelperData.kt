package com.xxm.mmd.wxfxk.service

import com.xxm.mmd.wxfxk.utils.Preference
import java.util.function.Predicate

/**
 * Created by MaDeng on 2018/4/19.
 */
object HelperData {
    fun get(name: String,boolean: Boolean):Boolean {
        val temp by Preference(name = name,default = boolean)
        return temp
    }
    fun get(name:String,string: String):String {
        val temp by Preference(name = name,default = string)
        return temp
    }

    fun get(name: String):String{
        val temp by Preference(name = name,default = "")
        return temp
    }
}
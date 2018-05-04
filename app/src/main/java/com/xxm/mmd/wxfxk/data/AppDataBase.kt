package com.xxm.mmd.wxfxk.data

import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

/**
 * Created by MaDeng on 2018/4/19.
 */
abstract class AppDataBase :RoomDatabase(){

    companion object {
        @Volatile private var INSTANCE: AppDataBase? = null
        fun getInstance(context: Context):AppDataBase =
                INSTANCE ?: synchronized(this){
                    INSTANCE?:buildDataBase(context).also{ INSTANCE = it}
                }
        private fun buildDataBase(context: Context) =
                Room.databaseBuilder(context.applicationContext,
                        AppDataBase::class.java,"wxfx.db")
                        .build()
    }

}
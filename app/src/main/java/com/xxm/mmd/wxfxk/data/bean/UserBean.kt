package com.xxm.mmd.wxfxk.data.bean

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by MaDeng on 2018/4/19.
 */
@Entity(tableName = "UserBean")
class UserBean {
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id:Int = 0
}
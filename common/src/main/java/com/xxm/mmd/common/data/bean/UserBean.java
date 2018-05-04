package com.xxm.mmd.common.data.bean;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by MaDeng on 2018/5/4.
 */
@Entity(tableName = "user_table")
public class UserBean {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public long id;

    @ColumnInfo(name = "userName")
    public String name;
}

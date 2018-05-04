package com.xxm.mmd.common.data;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.RoomDatabase;

import com.xxm.mmd.common.data.bean.UserBean;
import com.xxm.mmd.common.data.dao.UserDao;

/**
 * Created by MaDeng on 2018/5/4.
 */
@Database(entities = {UserBean.class},version = 1)
public abstract class AppDataBase extends RoomDatabase {
    public abstract UserDao getUserDao();
}

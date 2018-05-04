package com.xxm.mmd.wxfxk.data.datadao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.xxm.mmd.wxfxk.data.bean.UserBean
import io.reactivex.Single

/**
 * Created by MaDeng on 2018/4/19.
 */
@Dao
interface UserDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insetAll(userBeans:List<UserBean>)

    @Query("SELECT * FROM UserBean WHERE id= :id")
    fun getUserBeanById(id:Int):Single<UserBean>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserBean(userBean: UserBean)
}
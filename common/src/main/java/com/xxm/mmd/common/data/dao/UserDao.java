package com.xxm.mmd.common.data.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.xxm.mmd.common.data.bean.UserBean;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

/**
 * Created by MaDeng on 2018/5/4.
 */
@Dao
public interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insetAll(List<UserBean> userBeans);

    @Query("SELECT * FROM USER_TABLE WHERE id= :id")
    public Flowable<UserBean> getUserBeanById(long id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertUserBean(UserBean userBean);

    @Delete
    public void deleteUserBean(UserBean... userBeans);

    @Update
    public void updateUserBean(UserBean... userBeans);

    @Query("SELECT * FROM USER_TABLE")
    public Flowable<List<UserBean>> getAllUserBean();
}

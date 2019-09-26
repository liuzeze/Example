package com.future.electronicmusic.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.future.electronicmusic.db.bean.PhoneBean;

import java.util.List;


@Dao
public interface PhoneDao {

    /**
     * 查询所有
     *
     * @return
     */
    @Query("SELECT * FROM PHONE")
    List<PhoneBean> getPhoneAll();

    /**
     * 查询所有数量
     *
     * @return
     */
    @Query("SELECT COUNT(*) FROM PHONE")
    long getPhoneCount();

    /**
     * 根据指定字段查询
     *
     * @return
     */
    @Query("SELECT * FROM PHONE WHERE phone = :phone")
    List<PhoneBean> loadPhoneByIds(String phone);

    /**
     * 项数据库添加数据
     *
     * @param phone
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<PhoneBean> phone);

    /**
     * 修改数据
     *
     * @param phone
     */
    @Update()
    void update(PhoneBean phone);

    /**
     * 删除数据
     *
     * @param phoneBean
     */
    @Delete()
    void delete(PhoneBean phoneBean);
}

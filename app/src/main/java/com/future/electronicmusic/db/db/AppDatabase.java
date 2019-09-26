package com.future.electronicmusic.db.db;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.future.electronicmusic.db.bean.PhoneBean;
import com.future.electronicmusic.db.dao.PhoneDao;


@Database(entities = {PhoneBean.class}, version = 1, exportSchema = false)
@TypeConverters({ConversionFactory.class})
public abstract class AppDatabase extends RoomDatabase {

    public abstract PhoneDao getPhoneDao();
}

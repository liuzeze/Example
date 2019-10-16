package com.future.electronicmusic.db.db;



import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.future.electronicmusic.db.bean.PhoneBean;
import com.future.electronicmusic.db.dao.PhoneDao;


@Database(entities = {PhoneBean.class}, version = 1, exportSchema = false)
@TypeConverters({ConversionFactory.class})
public abstract class AppDatabase extends RoomDatabase {

    public abstract PhoneDao getPhoneDao();
}

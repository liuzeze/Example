package com.future.electronicmusic.db;


import androidx.room.Room;

import com.future.electronicmusic.db.db.AppDatabase;
import com.future.electronicmusic.db.db.VersionDataBase;
import com.future.utilslib.utils.LzInitUtil;


/**
 * @author : liuze
 * @e-mail : 835052259@qq.com
 * @date : 2019/9/20-10:47
 * @desc : 修改内容
 * @version: 1.0
 */
public class DBInstance {
    private static final String DB_NAME = "LZ.db";
    public static AppDatabase appDataBase;

    public static synchronized AppDatabase getInstance() {
        if (appDataBase == null) {
            synchronized (DBInstance.class) {
                if (appDataBase == null) {
                    return Room.databaseBuilder(LzInitUtil.getApp(), AppDatabase.class, DB_NAME)
                            .addMigrations(
                                    VersionDataBase.MIGRATION_1_2,
                                    VersionDataBase.MIGRATION_2_3,
                                    VersionDataBase.MIGRATION_3_4,
                                    VersionDataBase.MIGRATION_1_4)
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return appDataBase;
    }
}

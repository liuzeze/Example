package com.future.electronicmusic.db.db;


import androidx.annotation.NonNull;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

/**
 * @author : liuze
 * @e-mail : 835052259@qq.com
 * @date : 2019/9/20-11:21
 * @desc : 修改内容
 * @version: 1.0
 */
public class VersionDataBase {
    public static Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("alter table user add column sex text");
        }
    };
    public static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE student "
                    + " ADD COLUMN phone_num TEXT");
        }
    };
    public static final Migration MIGRATION_3_4 = new Migration(3, 4) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            //创建表
            database.execSQL(
                    "CREATE TABLE student_new (student_id TEXT, student_name TEXT, phone_num INTEGER, PRIMARY KEY(student_id))");
            //复制表
            database.execSQL(
                    "INSERT INTO student_new (student_id, student_name, phone_num) SELECT student_id, student_name, phone_num FROM student");
            //删除表
            database.execSQL("DROP TABLE student");
            //修改表名称
            database.execSQL("ALTER TABLE student_new RENAME TO students");
        }
    };

    public static final Migration MIGRATION_1_4 = new Migration(1, 4) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            //创建表
            database.execSQL(
                    "CREATE TABLE student_new (student_id TEXT, student_name TEXT, phone_num INTEGER, PRIMARY KEY(student_id))");
            //复制表
            database.execSQL(
                    "INSERT INTO student_new (student_id, student_name, phone_num) SELECT student_id, student_name, phone_num FROM student");
            //删除表
            database.execSQL("DROP TABLE student");
            //修改表名称
            database.execSQL("ALTER TABLE student_new RENAME TO students");
        }
    };
}

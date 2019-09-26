package com.future.electronicmusic.db.db;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;


public class ConversionFactory {

    @TypeConverter
    public static Long fromDateToLong(Date date) {
        return date == null ? null : date.getTime();
    }

    @TypeConverter
    public static Date fromLongToDate(Long value) {
        return value == null ? null : new Date(value);
    }
}

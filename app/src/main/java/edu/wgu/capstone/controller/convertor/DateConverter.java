package edu.wgu.capstone.controller.convertor;

import androidx.room.TypeConverter;

import java.util.Date;

/**
 * Converts a Date object to a long and vice versa.
 */
public class DateConverter {

    /**
     * Converts a long to a Date object.
     * @param value The long to convert.
     * @return The Date object.
     */
    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    /**
     * Converts a Date object to a long.
     * @param date The Date object to convert.
     * @return The long.
     */
    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }
}

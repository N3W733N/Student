package com.nilton.student.database.converter

import androidx.room.TypeConverter
import java.util.Calendar

class CalendarConverter {
    @TypeConverter
    fun toLong(value: Calendar?): Long? {
        return value?.timeInMillis
    }

    @TypeConverter
    fun toCalendar(value: Long?): Calendar? {
        val actualMoment = Calendar.getInstance()
        value?.let { timeInLong ->
            if (timeInLong.toInt() != 0) {
                actualMoment.timeInMillis = value
            }
        }
        return actualMoment
    }
}
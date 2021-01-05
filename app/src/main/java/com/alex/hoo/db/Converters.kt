package com.alex.hoo.db

import androidx.room.TypeConverter
import java.util.*

class Converters {

    @TypeConverter
    fun calendarToDatestamp(calendar: Calendar): Long = calendar.timeInMillis

    @TypeConverter
    fun detestampToCalendar(value: Long) : Calendar = Calendar.getInstance().apply {
        timeInMillis = value
    }

}
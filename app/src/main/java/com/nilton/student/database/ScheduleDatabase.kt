package com.nilton.student.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.nilton.student.database.ScheduleMigrations.ALL_MIGRATIONS
import com.nilton.student.database.converter.CalendarConverter
import com.nilton.student.database.dao.StudentDAO
import com.nilton.student.model.Student

@Database(entities = [Student::class], version = 4, exportSchema = false)
@TypeConverters(CalendarConverter::class)
abstract class ScheduleDatabase : RoomDatabase() {
    abstract val roomStudentDAO: StudentDAO?

    companion object {
        private const val DATABASE_NAME = "schedule.db"
        fun getInstance(context: Context): ScheduleDatabase {
            return Room
                .databaseBuilder(context, ScheduleDatabase::class.java, DATABASE_NAME)
                .allowMainThreadQueries()
                .addMigrations(*ALL_MIGRATIONS)
                .build()
        }
    }
}
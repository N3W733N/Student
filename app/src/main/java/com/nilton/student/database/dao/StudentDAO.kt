package com.nilton.student.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Delete
import androidx.room.Update
import com.nilton.student.model.Student

@Dao
interface StudentDAO {
    @Insert
    fun saveStudent(student: Student?)

    @Query("SELECT * FROM Student")
    fun getAll(): List<Student>

    @Delete
    fun removeStudent(student: Student?)

    @Update
    fun editStudent(student: Student?)
}
